package OOP.Labs;

import java.util.Scanner;

class Book {
    private String bookTitle;
    private String bookAuthor;
    private float bookPrice;

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
}
public class BookAccess {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Book[] Books = new Book[10];
        System.out.println("- MENU - \n(1) -> View all books\n(2) -> Add a book \n(3) -> To Compare Book Prices\n(Any key) -> To Exit");

        int bookIndex = 0;
        String bookTitle, bookAuthor;
        float bookPrice;
        String userChoice;
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
                        System.out.print("Enter book title: ");
                        bookTitle = input.nextLine();
                        System.out.print("Enter book title: ");
                        bookAuthor = input.nextLine();
                        System.out.print("Enter book title: ");
                        bookPrice = Float.parseFloat(input.nextLine());
                        Book book = new Book();
                        book.setBookTitle(bookTitle);
                        book.setBookAuthor(bookAuthor);
                        book.setBookPrice(bookPrice);
                        Books[bookIndex++] = book;
                        System.out.println("Book added successfully");
                    }
                    break;
                case "3":
                    printAllBooks(Books);
                    System.out.println("Which two books you want to compare?");
                    System.out.print("First Book Number: ");
                    int bookNumberOne = Integer.parseInt(input.next());
                    System.out.print("Second Book Number: ");
                    int bookNumberTwo = Integer.parseInt(input.next());
                    compareBookPrice(Books[bookNumberOne - 1], Books[bookNumberTwo - 1]);
                    break;
                default:
                    break label;
            }
        }
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
