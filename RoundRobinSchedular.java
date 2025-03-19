import java.util.*;

// Java program to implement Round Robin Scheduling using Circular Linked List
public class RoundRobinSchedular {
    ProcessNode head = null;
    int timeQuantum;

    // Circular linked list Node representing a process
    static class ProcessNode {
        int processId;
        int burstTime;
        int priority;
        ProcessNode next;

        // Constructor
        ProcessNode(int processId, int burstTime, int priority) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.priority = priority;
            this.next = null;
        }
    }

    // Constructor to initialize time quantum
    public RoundRobinSchedular(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    // Method to add a process at the end
    public void addProcess(int processId, int burstTime, int priority) {
        ProcessNode newProcess = new ProcessNode(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            head.next = head; // Circular link
        } else {
            ProcessNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newProcess;
            newProcess.next = head;
        }
    }

    // Method to remove a process by Process ID
    public void removeProcess(int processId) {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }
        ProcessNode temp = head, prev = null;
        while (temp.processId != processId) {
            if (temp.next == head) {
                System.out.println("Process not found.");
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        if (temp == head) {
            ProcessNode last = head;
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

    // Simulate round-robin scheduling
    public void executeProcesses() {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }
        ProcessNode current = head;
        int time = 0;
        List<Integer> waitingTimes = new ArrayList<>();
        List<Integer> turnaroundTimes = new ArrayList<>();

        while (head != null) {
            System.out.println("Executing Process: " + current.processId + " for time quantum " + timeQuantum);
            if (current.burstTime > timeQuantum) {
                time += timeQuantum;
                current.burstTime -= timeQuantum;
                current = current.next;
            }
            else {
                time += current.burstTime;
                turnaroundTimes.add(time);
                removeProcess(current.processId);
                if (head == null) break;
                current = head;
            }
        }

        System.out.println("All processes executed.");
    }

    // Display all processes in the circular queue
    public void viewProcesses() {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }
        ProcessNode temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        }
        while (temp != head);
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter time quantum: ");
        int timeQuantum = sc.nextInt();
        RoundRobinSchedular scheduler = new RoundRobinSchedular(timeQuantum);
        int choice;

        do {
            System.out.println("\nRound Robin Scheduler");
            System.out.println("1 -> Add Process");
            System.out.println("2 -> Execute Processes");
            System.out.println("3 -> View Processes");
            System.out.println("4 -> Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID: ");
                    int processId = sc.nextInt();
                    System.out.print("Enter Burst Time: ");
                    int burstTime = sc.nextInt();
                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();
                    scheduler.addProcess(processId, burstTime, priority);
                    break;
                case 2:
                    scheduler.executeProcesses();
                    break;
                case 3:
                    scheduler.viewProcesses();
                    break;
                case 4:
                    System.out.println("Exiting Round Robin Scheduler.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        while (choice != 4);
    }
}