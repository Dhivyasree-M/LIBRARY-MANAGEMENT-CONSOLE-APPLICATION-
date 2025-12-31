
// File: LibraryMain.java
import java.util.*;

// Abstract base class
abstract class Book {
    private String title;
    private String author;
    private double price;
    private String isbn;

    public Book(String title, String author, double price, String isbn) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getIsbn() { return isbn; }

    public void setPrice(double price) { this.price = price; }

    public abstract void displayInfo();
}

// EBook subclass
class EBook extends Book {
    private double fileSizeMB;

    public EBook(String title, String author, double price, String isbn, double fileSizeMB) {
        super(title, author, price, isbn);
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + getTitle() + ", Author: " + getAuthor() + 
                           ", Price: ₹" + getPrice() + ", ISBN: " + getIsbn());
        System.out.println("Type: EBook, File Size: " + fileSizeMB + " MB");
    }
}

// PrintedBook subclass
class PrintedBook extends Book {
    private int pages;

    public PrintedBook(String title, String author, double price, String isbn, int pages) {
        super(title, author, price, isbn);
        this.pages = pages;
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + getTitle() + ", Author: " + getAuthor() + 
                           ", Price: ₹" + getPrice() + ", ISBN: " + getIsbn());
        System.out.println("Type: Printed Book, Pages: " + pages);
    }
}

// Library class
class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("✅ Book added successfully!");
    }

    public void removeBook(String isbn) {
        books.removeIf(b -> b.getIsbn().equalsIgnoreCase(isbn));
        System.out.println("🗑 Book removed (if existed).");
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("📭 No books in library.");
            return;
        }
        for (Book b : books) {
            b.displayInfo();
            System.out.println("-----------------");
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                b.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("❌ No book found with that title.");
    }

    public void searchByAuthor(String author) {
        boolean found = false;
        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                b.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("❌ No book found by that author.");
    }
}

// Main application
public class LibraryMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n📚 Library Menu:");
            System.out.println("1. Add EBook");
            System.out.println("2. Add Printed Book");
            System.out.println("3. Remove Book");
            System.out.println("4. List All Books");
            System.out.println("5. Search by Title");
            System.out.println("6. Search by Author");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String etitle = sc.nextLine();
                    System.out.print("Author: ");
                    String eauthor = sc.nextLine();
                    System.out.print("Price: ");
                    double eprice = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("ISBN: ");
                    String eisbn = sc.nextLine();
                    System.out.print("File Size (MB): ");
                    double size = sc.nextDouble();
                    library.addBook(new EBook(etitle, eauthor, eprice, eisbn, size));
                    break;

                case 2:
                    System.out.print("Title: ");
                    String ptitle = sc.nextLine();
                    System.out.print("Author: ");
                    String pauthor = sc.nextLine();
                    System.out.print("Price: ");
                    double pprice = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("ISBN: ");
                    String pisbn = sc.nextLine();
                    System.out.print("Pages: ");
                    int pages = sc.nextInt();
                    library.addBook(new PrintedBook(ptitle, pauthor, pprice, pisbn, pages));
                    break;

                case 3:
                    System.out.print("Enter ISBN to remove: ");
                    String risbn = sc.nextLine();
                    library.removeBook(risbn);
                    break;

                case 4:
                    library.listBooks();
                    break;

                case 5:
                    System.out.print("Enter title to search: ");
                    String stitle = sc.nextLine();
                    library.searchByTitle(stitle);
                    break;

                case 6:
                    System.out.print("Enter author to search: ");
                    String sauthor = sc.nextLine();
                    library.searchByAuthor(sauthor);
                    break;

                case 7:
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("⚠ Invalid choice.");
            }
        }
    }
}
