import java.io.*;
import java.util.*;

// Java program to implement a Task Scheduler using a Circular Linked List
public class TaskScheduler {
    TaskNode head = null;

    // Circular linked list Node representing a task
    static class TaskNode {
        int taskId;
        String taskName;
        int priority;
        String dueDate;
        TaskNode next;

        // Constructor
        TaskNode(int taskId, String taskName, int priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
            this.next = null;
        }
    }

    // Method to add a task at the end
    public void addTask(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newTask = new TaskNode(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head; // Circular link
        }
        else {
            TaskNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }

    // Method to remove a task by Task ID
    public void removeTask(int taskId) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        TaskNode temp = head, prev = null;
        while (temp.taskId != taskId) {
            if (temp.next == head) {
                System.out.println("Task not found.");
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        if (temp == head) {
            TaskNode last = head;
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

    // View the current task and move to the next task
    public void viewTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        TaskNode temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    // Search for a task by priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        TaskNode temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Due Date: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();
        int choice;

        do {
            System.out.println("\nTask Scheduler");
            System.out.println("1 -> Add Task");
            System.out.println("2 -> Remove Task");
            System.out.println("3 -> View Tasks");
            System.out.println("4 -> Search Task by Priority");
            System.out.println("5 -> Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int taskId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String taskName = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    String dueDate = sc.nextLine();
                    scheduler.addTask(taskId, taskName, priority, dueDate);
                    break;
                case 2:
                    System.out.print("Enter Task ID to remove: ");
                    int removeId = sc.nextInt();
                    scheduler.removeTask(removeId);
                    break;
                case 3:
                    scheduler.viewTasks();
                    break;
                case 4:
                    System.out.print("Enter Priority to search: ");
                    int searchPriority = sc.nextInt();
                    scheduler.searchByPriority(searchPriority);
                    break;
                case 5:
                    System.out.println("Exiting Task Scheduler.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        while (choice != 5);
    }
}