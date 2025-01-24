import java.util.*;
import java.io.*;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    String id, title, author;
    boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Title: %s | Author: %s | Issued: %s", id, title, author, isIssued ? "Yes" : "No");
    }
}

public class libmanagement {
    private static final String FILE_NAME = "library.txt";
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        loadBooks();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBook(sc);
                case 2 -> viewBooks();
                case 3 -> issueBook(sc);
                case 4 -> returnBook(sc);
                case 5 -> searchBook(sc);
                case 6 -> {
                    saveBooks();
                    System.out.println("Exiting system...");
                     return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Book Author: ");
        String author = sc.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("\nAvailable Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void issueBook(Scanner sc) {
        System.out.print("Enter Book ID to issue: ");
        String id = sc.nextLine();
        for (Book book : books) {
            if (book.id.equals(id)) {
                if (!book.isIssued) {
                    book.isIssued = true;
                    System.out.println("Book issued successfully!");
                    return;
                } else {
                    System.out.println("Book is already issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found!");
    }

    private static void returnBook(Scanner sc) {
        System.out.print("Enter Book ID to return: ");
        String id = sc.nextLine();
        for (Book book : books) {
            if (book.id.equals(id)) {
                if (book.isIssued) {
                    book.isIssued = false;
                    System.out.println("Book returned successfully!");
                    return;
                } else {
                    System.out.println("Book was not issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found!");
    }

    private static void searchBook(Scanner sc) {
        System.out.print("Enter Book Title to search: ");
        String title = sc.nextLine();
        for (Book book : books) {
            if (book.title.toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("No books found with that title.");
    }

    private static void loadBooks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            books = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    private static void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }
}
