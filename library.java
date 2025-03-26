import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    String bookId, title, author, genre, availability;

    public Book(String bookId, String title, String author, String genre, String availability) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public void updateDetails(String key, String newValue) {
        switch (key.toLowerCase()) {
            case "title":
                this.title = newValue;
                break;
            case "author":
                this.author = newValue;
                break;
            case "genre":
                this.genre = newValue;
                break;
            case "availability":
                this.availability = newValue;
                break;
            default:
                System.out.println("Invalid field.");
        }
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + availability;
    }
}

class LibrarySystem {
    private List<Book> books = new ArrayList<>();

    public void addBook(String bookId, String title, String author, String genre, String availability) {
        books.add(new Book(bookId, title, author, genre, availability));
        System.out.println("Book added successfully.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBook(String searchKey) {
        for (Book book : books) {
            if (book.bookId.equals(searchKey) || book.title.equalsIgnoreCase(searchKey)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void updateBook(String bookId, String key, String newValue) {
        for (Book book : books) {
            if (book.bookId.equals(bookId)) {
                book.updateDetails(key, newValue);
                System.out.println("Book details updated successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void deleteBook(String bookId) {
        books.removeIf(book -> book.bookId.equals(bookId));
        System.out.println("Book deleted successfully.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem library = new LibrarySystem();
        
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Availability (Available/Not Available): ");
                    String availability = scanner.nextLine();
                    library.addBook(bookId, title, author, genre, availability);
                    break;
                case "2":
                    library.viewBooks();
                    break;
                case "3":
                    System.out.print("Enter Book ID or Title to search: ");
                    String searchKey = scanner.nextLine();
                    library.searchBook(searchKey);
                    break;
                case "4":
                    System.out.print("Enter Book ID to update: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter field to update (Title/Author/Genre/Availability): ");
                    String key = scanner.nextLine();
                    System.out.print("Enter new value: ");
                    String newValue = scanner.nextLine();
                    library.updateBook(bookId, key, newValue);
                    break;
                case "5":
                    System.out.print("Enter Book ID to delete: ");
                    bookId = scanner.nextLine();
                    library.deleteBook(bookId);
                    break;
                case "6":
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
