import java.io.*;
import java.util.*;

// Java program to implement a Doubly Linked List for Library Management System
public class LibraryManagementSystem {
    // Head and Tail of the list
    BookNode head, tail;

    // Doubly linked list Node representing a book
    static class BookNode {
        String title;
        String author;
        String genre;
        int bookID;
        boolean isAvailable;
        BookNode next, prev;

        // Constructor
        BookNode(String title, String author, String genre, int bookID, boolean isAvailable) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.bookID = bookID;
            this.isAvailable = isAvailable;
            this.next = null;
            this.prev = null;
        }
    }

    // Method to add a book at the end
    public void addBook(String title, String author, String genre, int bookID, boolean isAvailable) {
        BookNode newBook = new BookNode(title, author, genre, bookID, isAvailable);
        if (head == null) {
            head = tail = newBook;
        }
        else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    // Method to remove a book by Book ID
    public void removeBook(int bookID) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.bookID == bookID) {
                if (temp.prev != null) {
                    temp.prev.next = temp.next;
                }
                else {
                    head = temp.next;
                }
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                else {
                    tail = temp.prev;
                }
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Method to search for a book by Title
    public void searchByTitle(String title) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                displayBook(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Method to search for a book by Author
    public void searchByAuthor(String author) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                displayBook(temp);
            }
            temp = temp.next;
        }
    }

    // Method to update a book’s availability status
    public void updateAvailability(int bookID, boolean isAvailable) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.bookID == bookID) {
                temp.isAvailable = isAvailable;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Method to display all books in forward order
    public void displayBooksForward() {
        BookNode temp = head;
        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }

    // Method to display all books in reverse order
    public void displayBooksReverse() {
        BookNode temp = tail;
        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }

    // Method to count total books
    public int countBooks() {
        int count = 0;
        BookNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Helper method to display book details
    private void displayBook(BookNode book) {
        System.out.println("Title: " + book.title + ", Author: " + book.author + ", Genre: " + book.genre + ", Book ID: " + book.bookID + ", Available: " + book.isAvailable);
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryManagementSystem library = new LibraryManagementSystem();
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1 -> Add Book");
            System.out.println("2 -> Remove Book");
            System.out.println("3 -> Search by Title");
            System.out.println("4 -> Search by Author");
            System.out.println("5 -> Update Book Availability");
            System.out.println("6 -> Display Books Forward");
            System.out.println("7 -> Display Books Reverse");
            System.out.println("8 -> Count Total Books");
            System.out.println("9 -> Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Enter Book ID: ");
                    int bookID = sc.nextInt();
                    System.out.print("Is Available (true/false): ");
                    boolean isAvailable = sc.nextBoolean();
                    library.addBook(title, author, genre, bookID, isAvailable);
                    break;
                case 2:
                    System.out.print("Enter Book ID to Remove: ");
                    bookID = sc.nextInt();
                    library.removeBook(bookID);
                    break;
                case 3:
                    System.out.print("Enter Book Title: ");
                    title = sc.nextLine();
                    library.searchByTitle(title);
                    break;
                case 4:
                    System.out.print("Enter Author Name: ");
                    author = sc.nextLine();
                    library.searchByAuthor(author);
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    bookID = sc.nextInt();
                    System.out.print("Enter New Availability (true/false): ");
                    isAvailable = sc.nextBoolean();
                    library.updateAvailability(bookID, isAvailable);
                    break;
                case 6:
                    library.displayBooksForward();
                    break;
                case 7:
                    library.displayBooksReverse();
                    break;
                case 8:
                    System.out.println("Total Books: " + library.countBooks());
                    break;
                case 9:
                    System.out.println("Exiting Library Management System.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        while (choice != 9);
    }
}