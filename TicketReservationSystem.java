import java.util.*;

// Java program to implement Online Ticket Reservation System using Circular Linked List
public class TicketReservationSystem {
    TicketNode head = null;

    // Circular linked list Node representing a ticket
    static class TicketNode {
        int ticketId;
        String customerName;
        String movieName;
        int seatNumber;
        String bookingTime;
        TicketNode next;

        // Constructor
        TicketNode(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
            this.next = null;
        }
    }

    // Method to add a new ticket reservation at the end
    public void addTicket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        TicketNode newTicket = new TicketNode(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            head.next = head; // Circular link
        }
        else {
            TicketNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    // Method to remove a ticket by Ticket ID
    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }
        TicketNode temp = head, prev = null;
        while (temp.ticketId != ticketId) {
            if (temp.next == head) {
                System.out.println("Ticket not found.");
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        if (temp == head) {
            TicketNode last = head;
            while (last.next != head) {
                last = last.next;
            }
            if (head == head.next) {
                head = null;
            }
            else {
                head = head.next;
                last.next = head;
            }
        }
        else {
            prev.next = temp.next;
        }
    }

    // Display all tickets in the circular list
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }
        TicketNode temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Booking Time: " + temp.bookingTime);
            temp = temp.next;
        }
        while (temp != head);
    }

    // Search for a ticket by Customer Name or Movie Name
    public void searchTicket(String key) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }
        TicketNode temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(key) || temp.movieName.equalsIgnoreCase(key)) {
                System.out.println("Ticket Found - Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Booking Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        }
        while (temp != head);
        if (!found) System.out.println("No matching ticket found.");
    }

    // Count total number of booked tickets
    public int countTickets() {
        if (head == null) return 0;
        int count = 0;
        TicketNode temp = head;
        do {
            count++;
            temp = temp.next;
        }
        while (temp != head);
        return count;
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();
        int choice;

        do {
            System.out.println("\nOnline Ticket Reservation System");
            System.out.println("1 -> Add Ticket");
            System.out.println("2 -> Remove Ticket");
            System.out.println("3 -> Display Tickets");
            System.out.println("4 -> Search Ticket");
            System.out.println("5 -> Count Tickets");
            System.out.println("6 -> Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int ticketId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = sc.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movieName = sc.nextLine();
                    System.out.print("Enter Seat Number: ");
                    int seatNumber = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String bookingTime = sc.nextLine();
                    system.addTicket(ticketId, customerName, movieName, seatNumber, bookingTime);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to remove: ");
                    int removeId = sc.nextInt();
                    system.removeTicket(removeId);
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String searchKey = sc.nextLine();
                    system.searchTicket(searchKey);
                    break;
                case 5:
                    System.out.println("Total Tickets Booked: " + system.countTickets());
                    break;
                case 6:
                    System.out.println("Exiting Ticket Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        while (choice != 6);
    }
}