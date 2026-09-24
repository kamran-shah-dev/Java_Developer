package CollectionsFramework.Projects.LibraryManagementSystemUsingMaps;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryManagementSystem {
    static class Book {
        private String title;
        private String author;
        private String genre;
        private int publicationYear;

        public Book() {
            title = null;
            author = null;
            genre = null;
            publicationYear = 0;
        }

        public Book(String title) {
            this.title = title;
        }

        public Book(String title, String author, String genre,  int publicationYear) {
            this.title = title;
            this.publicationYear = publicationYear;
            this.genre = genre;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public int getPublicationYear() {
            return publicationYear;
        }

        public void setPublicationYear(int publicationYear) {
            this.publicationYear = publicationYear;
        }

        public String getBookDetails() {
            return """
                    Book {
                        title = %s
                        author = %s
                        genre = %s
                        publicationYear =  %d
                    }""".formatted(title, author, genre, publicationYear);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<String, Book> library = new HashMap<>(20, 0.76f);

        // Initial values for a better simulation
        {
            library.put("978-0747532699", new Book("Harry Potter", "J.K. Rowling", "Fantasy", 1997));
            library.put("978-0061120084", new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 1960));
            library.put("978-0451524935", new Book("1984", "George Orwell", "Dystopian", 1949));
            library.put("978-0743273565", new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925));
            library.put("978-0345391803", new Book("The Hitchhiker's Guide", "Douglas Adams", "Sci-Fi", 1979));
            library.put("978-0547928227", new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937));
            library.put("978-0241950432", new Book("Animal Farm", "George Orwell", "Satire", 1945));
            library.put("978-1524763138", new Book("A Gentleman in Moscow", "Amor Towles", "Historical Fiction", 2016));
        }

        String menu = """
                Choose an option from menu (1-7)
                1. Add a book
                2. View all books
                3. Search for a book
                4. Remove a book
                5. View sorted books
                6. Exit
                """;

        boolean isExit = true;
        while (isExit)
        {
            System.out.println(menu);
            System.out.print("Your Option: ");
            int option = Integer.parseInt(input.nextLine());
            switch (option) {
                case 1:
                    String isbnNumber = takeTextInput(input, "ISBN Number");
                    String title = takeTextInput(input, "title");
                    String author = takeTextInput(input, "author");
                    String genre = takeTextInput(input, "genre");
                    System.out.println("Enter Publication year");
                    int publicationYear = Integer.parseInt(input.nextLine());
                    while (!isValidYear(publicationYear)) {
                        System.out.println("Invalid "+ publicationYear + " !! Try Again..");
                        System.out.println("Enter Book " + publicationYear + " again : ");
                        publicationYear = Integer.parseInt(input.nextLine());
                    }
                    library.put(isbnNumber, new Book(title, author, genre, publicationYear));
                    break;
                case 2:
                   viewAllBooks(library);
                    break;
                case 3:
                    System.out.println("Do you want to search ISBN Number, Title, Author?");
                    System.out.println("1. Search By ISBN Number\n2. Search By Book Title\n3. Search By Book Author");
                    System.out.print("Your Choice: ");
                    option = Integer.parseInt(input.nextLine());
                    if (option == 1) {
                        System.out.println(searchByISBNNumber(input, library));
                    } else if (option == 2) {
                        System.out.println(searchByBookTitle(input, library));
                    } else {
                        System.out.println(searchByBookAuthor(input, library));
                    }
                    break;
                case 4:
                    removeBook(input, library);
                    break;
                case 5:
                    viewSortedBooks(library);
                    break;
                case 6:
                    isExit = false;
                    break;

                default:
                    System.out.println("Invalid input!! Try Again...");
            }

        }

    }


    // Validate input functions
    public static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    public static boolean isValidYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year >= 100 && year <= currentYear;
    }

    // Function to take a verified user input
    public static String takeTextInput(Scanner sc, String inputVariable) {
        System.out.println("Enter Book " + inputVariable + " : ");
        String inputText = sc.nextLine();
        while (!isValidText(inputText)) {
            System.out.println("Invalid "+ inputVariable + " !! Try Again..");
            System.out.println("Enter Book " + inputVariable + " again : ");
            inputText = sc.nextLine();
        }
        return inputText;
    }

    // Search A Book Functions
    private static String searchByBookTitle(Scanner input, HashMap<String, Book> library) {
        if (library.isEmpty()) {
            return "No Books In Library";
        }
        String foundBook = null;
        String bookTitle = takeTextInput(input, "BookTitle");
        // Set<Map.Entry<String, Book>> bookSet = library.entrySet();
        for (Map.Entry<String, Book> book : library.entrySet()) {
            if (book.getValue().getTitle().equalsIgnoreCase(bookTitle)) {
                foundBook = book.getValue().getBookDetails();
                break;
            }
        }
        return foundBook != null? foundBook : "Book Does Not Exist";
    }

    private static String searchByBookAuthor(Scanner input, HashMap<String, Book> library) {
        if (library.isEmpty()) {
            return "No Books In Library";
        }
        String foundBook = null;
        String bookAuthor = takeTextInput(input, "Book Author");
        // Set<Map.Entry<String, Book>> bookSet = library.entrySet();
        for (Map.Entry<String, Book> book : library.entrySet()) {
            if (book.getValue().getAuthor().equalsIgnoreCase(bookAuthor)) {
                foundBook = book.getValue().getBookDetails();
                break;
            }
        }
        return foundBook != null? foundBook : "Book Does Not Exist";
    }

    private static String searchByISBNNumber(Scanner input, HashMap<String, Book> library) {
        if (library.isEmpty()) {
            return "No Books In Library";
        }
        String isbnNumber = takeTextInput(input, "ISBN Number");

        return library.containsKey(isbnNumber) ?
                library.get(isbnNumber).getBookDetails() : "Book Not Found";
    }

    // View all books Function
    private static void viewAllBooks(HashMap<String, Book> library) {
        if (library.isEmpty()) {
            System.out.println("No Books Present In Library...");
            return;
        }
        library.forEach((key, book) -> {
            System.out.println("ISBN Number: " + key);
            System.out.println(book.getBookDetails());
        });
    }

    // View Sorted Books Function
    private static void viewSortedBooks(HashMap<String, Book> library) {
        if (library.isEmpty()) {
            System.out.println("No Books Present In Library...");
            return;
        }
        Map<String, Book> sortedMap = library.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Book::getPublicationYear)
                        .thenComparing(Book::getTitle).thenComparing(Book::getAuthor)
                        .thenComparing(Book::getGenre)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        sortedMap.forEach((key, book) -> {
            System.out.println("ISBN Number: " + key);
            System.out.println(book.getBookDetails());
        });

    }

    // Remove a book function
    private static void removeBook(Scanner input, HashMap<String, Book> library) {
        if (library.isEmpty()) {
            System.out.println("Library is empty");
            return;
        }

        String isbnNumber = takeTextInput(input, "ISBN Number");
        Book book = library.remove(isbnNumber);
        if(book == null) {
            System.out.println("Book Not Found");
        } else {
            System.out.println(book.getBookDetails());
            System.out.println("Above book removed successfully!!");
        }
    }

}
