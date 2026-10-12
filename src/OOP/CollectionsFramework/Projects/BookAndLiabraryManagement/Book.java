package CollectionsFramework.Projects.BookAndLiabraryManagement;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private String bookTitle, bookAuthor;
    private int publicationYear;
    private boolean isAvailable = true;

    public Book() {
        bookAuthor = bookTitle = null;
        publicationYear = 0;
    }

    public Book(String bookTitle) {
        this.bookTitle = bookTitle;
        bookAuthor = null;
        publicationYear = 0;
    }

    public Book(String bookTitle, String bookAuthor, int publicationYear) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publicationYear = publicationYear;
    }





    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // If book is not available no one can checkOut the book
    public boolean checkOut() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    // If book is not taken it cannot be returned. A checkedOut book can be returned
    public boolean returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookTitle, book.bookTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, bookAuthor, publicationYear, isAvailable);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publicationYear=" + publicationYear +
                ", isAvailable=" + isAvailable +
                '}';
    }
    
    @Override
    public int compareTo(Book other) {
        if (other.getBookTitle().equals(this.getBookTitle())) {
            return other.getBookAuthor().compareTo(this.getBookAuthor());
        }
        return other.getBookTitle().compareTo(this.getBookTitle());
    }
}
