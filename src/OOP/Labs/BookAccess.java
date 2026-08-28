package OOP.Labs;

import java.util.Objects;
import java.util.Scanner;

class Book implements Cloneable{
    private String bookTitle;
    private String bookAuthor;
    private float bookPrice;

    Book () {
        this.bookAuthor = this.bookTitle = null;
    }
    Book (String bookTitle, String bookAuthor, float bookPrice) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    @Override
    public String toString() {
        return """
                Book { Book Title =  '%s', Book Author =  '%s', Book Price =  '%.2f' }
               """.formatted(bookTitle, bookAuthor, bookPrice);
        // here we can see a %.2f (.2) with float, that is just to limit the decimal values after point to 2
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Float.compare(bookPrice, book.bookPrice) == 0 && Objects.equals(bookTitle, book.bookTitle)
                && Objects.equals(bookAuthor, book.bookAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, bookAuthor, bookPrice);
    }
}

public class BookAccess {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner input = new Scanner(System.in);
        Book[] Books = new Book[10];
        System.out.println("- MENU - \n(1) -> View all books\n(2) -> Add a book \n(3) -> To Compare Book Prices");
        System.out.println("(4) -> Compare Books\n(5) -> Clone a Book\n(Any key) -> To Exit");

        int bookIndex = 0;
        String bookTitle, bookAuthor;
        float bookPrice;
        String userChoice;
        int bookNumberOne, bookNumberTwo;
        label:
        while (true) {
            System.out.print("What operation you want to perform: ");
            userChoice = input.nextLine();
            switch (userChoice) {
                case "1":
                    printAllBooks(Books);
                    break;
                case "2":
                    if (bookIndex == 10) {
                        System.out.println("No more space to add BOOKS!!");
                    } else {
                        System.out.println("How do you want to add the books?");
                        System.out.println("All book attributes at once or attributes one by one");
                        System.out.print("To enter at once press 6 and to enter one by one enter 7: ");
                        userChoice = input.nextLine();
                        if (userChoice.equals("6")) {
                            System.out.print("Enter book title: ");
                            bookTitle = input.nextLine();
                            System.out.print("Enter book title: ");
                            bookAuthor = input.nextLine();
                            System.out.print("Enter book title: ");
                            bookPrice = Float.parseFloat(input.nextLine());
                            Books[bookIndex++] = new Book(bookTitle, bookAuthor, bookPrice);
                            System.out.println("Book added successfully");
                        } else if (userChoice.equals("7")) {
                            Book book = new Book();
                            System.out.print("Enter book title: ");
                            bookTitle = input.nextLine();
                            System.out.println("Book Title Added");
                            book.setBookTitle(bookTitle);
                            System.out.print("Enter book title: ");
                            bookAuthor = input.nextLine();
                            book.setBookAuthor(bookAuthor);
                            System.out.println("Book Author Added");
                            System.out.print("Enter book title: ");
                            bookPrice = Float.parseFloat(input.nextLine());
                            book.setBookPrice(bookPrice);
                            System.out.println("Book Price Added");
                            Books[bookIndex++] = book;
                        } else {
                            System.out.println("Invalid Input");
                        }
                    }
                    break;
                case "3":
                    printAllBooks(Books);
                    System.out.println("Which two books you want to compare?");
                    System.out.print("First Book Number: ");
                    bookNumberOne = Integer.parseInt(input.nextLine());
                    System.out.print("Second Book Number: ");
                    bookNumberTwo = Integer.parseInt(input.nextLine());
                    if (bookNumberOne == 0 || bookNumberOne > filledBucketSize(Books)
                            || bookNumberTwo == 0 || bookNumberTwo > filledBucketSize(Books)) {
                        System.out.println("Incorrect Book Numbers");
                    } else {
                        compareBookPrice(Books[bookNumberOne - 1], Books[bookNumberTwo - 1]);
                    }
                    break;
                case "4":
                    System.out.print("First Book Number: ");
                    bookNumberOne = Integer.parseInt(input.nextLine());
                    System.out.print("Second Book Number: ");
                    bookNumberTwo = Integer.parseInt(input.nextLine());
                    if (bookNumberOne == 0 || bookNumberOne > filledBucketSize(Books)
                            || bookNumberTwo == 0 || bookNumberTwo > filledBucketSize(Books)) {
                        System.out.println("Incorrect Book Numbers");
                    } else {
                        if (compareBooks(Books[bookNumberOne - 1] , Books[bookNumberTwo - 1])) {
                            System.out.println(Books[bookNumberOne - 1]);
                            System.out.println(Books[bookNumberTwo - 1]);
                            System.out.println("Books are same");
                        } else {
                            System.out.println("Books are not same");
                        }
                    }
                    break;
                case "5":
                    printAllBooks(Books);
                    System.out.print("Enter the number of book you want to clone: ");
                    bookNumberOne = Integer.parseInt(input.nextLine());
                    if (bookNumberOne == 0 || bookNumberOne > filledBucketSize(Books)) {
                        System.out.println("No Books To Clone");
                    } else {
                        Books[bookIndex++] = (Book) Books[bookNumberOne - 1].clone();
                        System.out.println("Book Clones Successfully");
                    }
                    break;
                default:
                    break label;
            }
        }
    }

    private static boolean compareBooks(Book book1, Book book2) {
        return book1.equals(book2);
    }

    private static void compareBookPrice(Book book1, Book book2) {
        float book1Price = book1.getBookPrice(), book2Price = book2.getBookPrice();
        if (book1Price < book2Price) {
            System.out.println('\'' + book1.getBookTitle() + '\'' + " costs less then " + '\'' +book2.getBookTitle() + '\'');
        } else if (book1Price > book2Price) {
            System.out.println('\'' + book1.getBookTitle() + '\'' + " costs more then " + '\'' + book2.getBookTitle() + '\'');
        } else {
            System.out.println('\'' + book1.getBookTitle() + '\'' + " costs as much as " + '\'' + book2.getBookTitle() + '\'');
        }
    }

    private static void printAllBooks(Book[] Books) {
        for (int i = 0; i < filledBucketSize(Books); i++) {
            System.out.println(i+1 + " : " + Books[i]);
        }
    }

    private static int filledBucketSize(Book[] Books) {
        int index= 0;
        while (Books[index] != null) {
            index++;
        }
        return index;
    }


}
