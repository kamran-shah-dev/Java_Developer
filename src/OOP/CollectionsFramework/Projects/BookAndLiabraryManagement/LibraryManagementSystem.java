package CollectionsFramework.Projects.BookAndLiabraryManagement;

import com.sun.jdi.InvalidLineNumberException;

import java.util.*;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Variable that holds the menu
        String menu = """
                 1. Add a book
                 2. View all books
                 3. Search for a book by title
                 4. Check out a book
                 5. Return a book
                 6. Sort books (by title, author, or publication year)
                 7. View available books only
                 8. Exit
                """;

        // Scanner object to use it for getting user inputs
        Scanner input = new Scanner(System.in);
        List<Book> library = new ArrayList<>();

        boolean isExit = false;
        String bookTitle;
        Book book;
        while (!isExit) {
            System.out.println("Menu Items\n" + menu + "\nWhat Operation You Want To Perform: ");
            int option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 1:
                    addBook(input, library);
                    break;
                case 2:
                    viewAllBooks(library);
                    break;
                case 3:
                    System.out.print("Enter Book Title: ");
                    bookTitle = input.nextLine();
                    while (isValidText(bookTitle)) {
                        System.out.println("Not a valid title. Title cannot be empty or null!");
                        System.out.print("Enter Book Title Again: ");
                        bookTitle = input.nextLine();
                    }

                    searchBookByTitle(bookTitle, library);
                    break;
                case 4:
                    System.out.print("Enter Book Title: ");
                    bookTitle = input.nextLine();
                    while (isValidText(bookTitle)) {
                        System.out.println("Not a valid title. Title cannot be empty or null!");
                        System.out.print("Enter Book Title Again: ");
                        bookTitle = input.nextLine();
                    }

                    book = findBookByTitle(bookTitle, library);
                    if (book != null) {
                        System.out.println("Checking Out: " + book);
                        book.checkOut();
                    } else {
                        System.out.println("Book Not Found!!");
                    }
                    break;
                case 5:
                    System.out.print("Enter Book Title: ");
                    bookTitle = input.nextLine();
                    while (isValidText(bookTitle)) {
                        System.out.println("Not a valid title. Title cannot be empty or null!");
                        System.out.print("Enter Book Title Again: ");
                        bookTitle = input.nextLine();
                    }

                    book = findBookByTitle(bookTitle, library);
                    if (book != null) {
                        System.out.println("Returning Book: " + book);
                        book.returnBook();
                    } else {
                        System.out.println("Book Not Found!!");
                    }
                    break;
                case 6:
                    System.out.println("1: Sort by oldest");
                    System.out.println("2: Sort by latest");
                    System.out.print("Enter sorting order: ");
                    int choice = Integer.parseInt(input.nextLine());
                    if(choice == 1) {
                        ascendingOrderSorting(library);
                    } else if (choice == 2) {
                        descendingOrderSorting(library);
                    } else {
                        descendingOrderSorting(library);
                    }
                    break;
                case 7:
                    viewAvailableBooks(library);
                    break;
                case 8:
                    isExit = true;
                    break;
                default:
            }
        }
    }

    private static Book findBookByTitle(String bookTitle, List<Book> library) {
        Book book = null;
        if (library.isEmpty()) {
            System.out.println("Library is Empty!!");
            return book;
        }
        for (Book b : library) {
            if (b.getBookTitle().equals(bookTitle)) {
                book = b;
            }
        }
        return book;
    }

    private static void descendingOrderSorting(List<Book> library) {
        if (library.isEmpty()) {
            System.out.println("Library is Empty!!");
        } else {
            Comparator<Book> comparator = Comparator.comparing(Book::getPublicationYear)
                    .thenComparing(Book::getBookTitle)
                    .thenComparing(Book::getBookAuthor).reversed();
            library.sort(comparator);
            System.out.println("Books are sorted by oldest..");
        }
    }

    private static void ascendingOrderSorting(List<Book> library) {

        if (library.isEmpty()) {
            System.out.println("Library is Empty!!");
        } else {
            Comparator<Book> comparator = Comparator.comparing(Book::getPublicationYear)
                    .thenComparing(Book::getBookTitle)
                    .thenComparing(Book::getBookAuthor);
            library.sort(comparator);
            System.out.println("Books are sorted by latest..");
        }
    }

    private static void searchBookByTitle(String bookTitle, List<Book> library) {
        if (library.isEmpty()) {
            System.out.println("Library is Empty!!");
            return;
        }
        for (Book b : library) {
            if (b.getBookTitle().equals(bookTitle)) {
                System.out.println("Book Found: " + b);
                return;
            }
        }
    }

    private static void viewAllBooks(List<Book> library) {
        if (library.isEmpty()) {
            System.out.println("Library is Empty!!");
        } else {
            library.forEach(System.out::println);
        }
    }

    private static void viewAvailableBooks(List<Book> library) {
        if (library.isEmpty()) {
            System.out.println("Library is Empty!!");
        } else {
            for (Book b : library) {
                if (b.isAvailable()) {
                    System.out.println(b);
                }
            }
        }
    }

    private static void addBook(Scanner input, List<Book> library) {
        System.out.print("Enter Book Title: ");
        String bookTitle = input.nextLine();
        while (isValidText(bookTitle)) {
            System.out.println("Not a valid title. Title cannot be empty or null!");
            System.out.print("Enter Book Title Again: ");
            bookTitle = input.nextLine();
        }
        System.out.print("Enter Book Author: ");
        String bookAuthor = input.nextLine();
        while (isValidText(bookAuthor)) {
            System.out.println("Not a valid author name. Author Name cannot be empty or null!");
            System.out.print("Enter Book Title Again: ");
            bookAuthor = input.nextLine();
        }
        System.out.print("Enter Book Publication Year: ");

        int publicationYear;
        while (true) {
            try {
                publicationYear = Integer.parseInt(input.nextLine());
                if (validYear(publicationYear)) {
                    break;
                } else
                    System.out.print("Enter a a valid year between (1000 - Current Year): ");
            } catch (NumberFormatException e) {
                System.out.println("Not a valid year...");
                System.out.print("Not a valid year try again: ");
            }
        }
        library.add(new Book(bookTitle, bookAuthor, publicationYear));
    }

    private static boolean validYear(int publicationYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return publicationYear >= 1000 && publicationYear <= currentYear;
    }

    private static boolean isValidText(String text) {
        if (text == null) {
            return true;
        } else return text.isBlank();
    }



}
