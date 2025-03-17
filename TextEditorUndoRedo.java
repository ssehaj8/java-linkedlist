import java.io.*;
import java.util.*;

// Java program to implement Undo/Redo functionality using a Doubly Linked List
public class TextEditorUndoRedo {
    // Head and Tail of the list
    TextStateNode head, tail, current;
    int maxSize = 10; // Limit history to last 10 states
    int size = 0;

    // Doubly linked list Node representing a text state
    static class TextStateNode {
        String text;
        TextStateNode next, prev;

        // Constructor
        TextStateNode(String text) {
            this.text = text;
            this.next = null;
            this.prev = null;
        }
    }

    // Method to add a new state
    public void addState(String text) {
        TextStateNode newState = new TextStateNode(text);

        // Remove all redo states after the current state
        if (current != null && current.next != null) {
            current.next = null;
            tail = current;
        }

        if (head == null) {
            head = tail = current = newState;
            size = 1;
        }
        else {
            newState.prev = current;
            current.next = newState;
            current = newState;
            tail = newState;

            // Maintain max history size
            if (size == maxSize) {
                head = head.next;
                head.prev = null;
            }
            else {
                size++;
            }
        }
    }

    // Undo operation
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
        else {
            System.out.println("No more undo possible");
        }
    }

    // Redo operation
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        }
        else {
            System.out.println("No more redo steps possible.");
        }
    }

    // Display current text state
    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.text);
        }
        else {
            System.out.println("No text available.");
        }
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditorUndoRedo editor = new TextEditorUndoRedo();
        int choice;

        do {
            System.out.println("\nText Editor - Undo/Redo");
            System.out.println("1 -> Add Text State");
            System.out.println("2 -> Undo");
            System.out.println("3 -> Redo");
            System.out.println("4 -> Display Current Text");
            System.out.println("5 -> Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new text state: ");
                    String text = sc.nextLine();
                    editor.addState(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayCurrentState();
                    break;
                case 5:
                    System.out.println("Exiting Text Editor.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        while (choice != 5);
    }
}