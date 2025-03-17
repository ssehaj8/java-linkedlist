import java.io.*;
import java.util.Scanner;

// Java program to implement a Singly Linked List for Inventory Management
public class InventoryLinkedList {
    // Head of list
    Node head;

    // Linked list Node
    static class Node {
        String itemName;
        int itemId;
        int quantity;
        double price;
        Node next;

        // Constructor
        Node(String itemName, int itemId, int quantity, double price) {
            this.itemName = itemName;
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
            this.next = null;
        }
    }

    // Method to insert a new item at the end
    public static InventoryLinkedList insert(InventoryLinkedList list, String itemName, int itemId, int quantity, double price) {
        Node new_node = new Node(itemName, itemId, quantity, price);

        if (list.head == null) {
            list.head = new_node;
        }
        else {
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        return list;
    }

    // Method to delete an item by Item ID
    public static InventoryLinkedList deleteByItemId(InventoryLinkedList list, int itemId) {
        Node temp = list.head, prev = null;

        if (temp != null && temp.itemId == itemId) {
            list.head = temp.next;
            System.out.println("Item with ID " + itemId + " deleted.");
            return list;
        }

        while (temp != null && temp.itemId != itemId) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Item with ID " + itemId + " not found.");
            return list;
        }

        prev.next = temp.next;
        System.out.println("Item with ID " + itemId + " deleted.");
        return list;
    }

    // Method to search for an item by Item ID
    public static void searchByItemId(InventoryLinkedList list, int itemId) {
        Node currNode = list.head;

        while (currNode != null) {
            if (currNode.itemId == itemId) {
                System.out.println("Item Found -> ID: " + currNode.itemId + ", Name: " + currNode.itemName + ", Quantity: " + currNode.quantity + ", Price: " + currNode.price);
                return;
            }
            currNode = currNode.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    // Method to update an item's quantity by Item ID
    public static void updateQuantity(InventoryLinkedList list, int itemId, int newQuantity) {
        Node currNode = list.head;

        while (currNode != null) {
            if (currNode.itemId == itemId) {
                currNode.quantity = newQuantity;
                System.out.println("Quantity updated for Item ID " + itemId);
                return;
            }
            currNode = currNode.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    // Method to calculate total inventory value
    public static void calculateTotalValue(InventoryLinkedList list) {
        Node currNode = list.head;
        double totalValue = 0;

        while (currNode != null) {
            totalValue += currNode.quantity * currNode.price;
            currNode = currNode.next;
        }

        System.out.println("Total Inventory Value: $" + totalValue);
    }

    // Method to print the inventory records
    public static void printList(InventoryLinkedList list) {
        Node curr = list.head;

        System.out.println("\nInventory Records:");

        while (curr != null) {
            System.out.println("ID: " + curr.itemId + ", Name: " + curr.itemName + ", Quantity: " + curr.quantity + ", Price: " + curr.price);
            curr = curr.next;
        }
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryLinkedList list = new InventoryLinkedList();
        int choice;

        do {
            System.out.println("\nInventory Management System");
            System.out.println("1 -> Add Item");
            System.out.println("2 -> Delete Item by ID");
            System.out.println("3 -> Search Item by ID");
            System.out.println("4 -> Update Item Quantity");
            System.out.println("5 -> Display Inventory");
            System.out.println("6 -> Calculate Total Value");
            System.out.println("7 -> Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Item Name: ");
                    sc.nextLine();
                    String itemName = sc.nextLine();
                    System.out.print("Enter Item ID: ");
                    int itemId = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    list = insert(list, itemName, itemId, quantity, price);
                    break;

                case 2:
                    System.out.print("Enter Item ID to Delete: ");
                    int deleteId = sc.nextInt();
                    list = deleteByItemId(list, deleteId);
                    break;

                case 3:
                    System.out.print("Enter Item ID to Search: ");
                    int searchId = sc.nextInt();
                    searchByItemId(list, searchId);
                    break;

                case 4:
                    System.out.print("Enter Item ID to Update Quantity: ");
                    int updateId = sc.nextInt();
                    System.out.print("Enter New Quantity: ");
                    int newQuantity = sc.nextInt();
                    updateQuantity(list, updateId, newQuantity);
                    break;

                case 5:
                    printList(list);
                    break;

                case 6:
                    calculateTotalValue(list);
                    break;

                case 7:
                    System.out.println("Exiting Inventory Management System.");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        while (choice != 7);
    }
}