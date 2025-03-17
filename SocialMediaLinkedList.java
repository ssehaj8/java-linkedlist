import java.io.*;
import java.util.*;

// Java program to implement a Singly Linked List for Social Media Friend Connections
public class SocialMediaLinkedList {
    // Head of list
    UserNode head;

    // Linked list Node representing a user
    static class UserNode {
        int userId;
        String name;
        int age;
        List<Integer> friendIds;
        UserNode next;

        // Constructor
        UserNode(int userId, String name, int age) {
            this.userId = userId;
            this.name = name;
            this.age = age;
            this.friendIds = new ArrayList<>();
            this.next = null;
        }
    }

    // Method to add a user to the list
    public static SocialMediaLinkedList addUser(SocialMediaLinkedList list, int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        if (list.head == null) {
            list.head = newUser;
        }
        else {
            UserNode temp = list.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
        return list;
    }

    // Method to search for a user by ID
    public static UserNode searchUserById(SocialMediaLinkedList list, int userId) {
        UserNode temp = list.head;
        while (temp != null) {
            if (temp.userId == userId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // Method to add a friend connection between two users
    public static void addFriendConnection(SocialMediaLinkedList list, int userId1, int userId2) {
        UserNode user1 = searchUserById(list, userId1);
        UserNode user2 = searchUserById(list, userId2);

        if (user1 != null && user2 != null) {
            user1.friendIds.add(userId2);
            user2.friendIds.add(userId1);
            System.out.println("Friend connection added between " + userId1 + " and " + userId2);
        }
        else {
            System.out.println("One or both users not found.");
        }
    }

    // Method to remove a friend connection
    public static void removeFriendConnection(SocialMediaLinkedList list, int userId1, int userId2) {
        UserNode user1 = searchUserById(list, userId1);
        UserNode user2 = searchUserById(list, userId2);

        if (user1 != null && user2 != null) {
            user1.friendIds.remove(Integer.valueOf(userId2));
            user2.friendIds.remove(Integer.valueOf(userId1));
            System.out.println("Friend connection removed between " + userId1 + " and " + userId2);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Method to find mutual friends between two users
    public static void findMutualFriends(SocialMediaLinkedList list, int userId1, int userId2) {
        UserNode user1 = searchUserById(list, userId1);
        UserNode user2 = searchUserById(list, userId2);

        if (user1 != null && user2 != null) {
            Set<Integer> mutualFriends = new HashSet<>(user1.friendIds);
            mutualFriends.retainAll(user2.friendIds);
            System.out.println("Mutual friends between " + userId1 + " and " + userId2 + ": " + mutualFriends);
        }
        else {
            System.out.println("One or both users not found.");
        }
    }

    // Method to display all friends of a specific user
    public static void displayFriends(SocialMediaLinkedList list, int userId) {
        UserNode user = searchUserById(list, userId);
        if (user != null) {
            System.out.println("Friends of " + userId + ": " + user.friendIds);
        }
        else {
            System.out.println("User not found.");
        }
    }

    // Method to count the number of friends for each user
    public static void countFriends(SocialMediaLinkedList list) {
        UserNode temp = list.head;
        while (temp != null) {
            System.out.println("User " + temp.userId + " has " + temp.friendIds.size() + " friends.");
            temp = temp.next;
        }
    }

    // Method to print all users
    public static void printUsers(SocialMediaLinkedList list) {
        UserNode temp = list.head;
        System.out.println("\nUser Records:");
        while (temp != null) {
            System.out.println("ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
            temp = temp.next;
        }
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialMediaLinkedList list = new SocialMediaLinkedList();
        int choice;

        do {
            System.out.println("\nSocial Media Friend Management System");
            System.out.println("1 -> Add User");
            System.out.println("2 -> Add Friend Connection");
            System.out.println("3 -> Remove Friend Connection");
            System.out.println("4 -> Find Mutual Friends");
            System.out.println("5 -> Display Friends");
            System.out.println("6 -> Count Friends");
            System.out.println("7 -> Display Users");
            System.out.println("8 -> Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    list = addUser(list, userId, name, age);
                    break;
                case 2:
                    System.out.print("Enter first User ID: ");
                    int userId1 = sc.nextInt();
                    System.out.print("Enter second User ID: ");
                    int userId2 = sc.nextInt();
                    addFriendConnection(list, userId1, userId2);
                    break;
                case 3:
                    System.out.print("Enter first User ID: ");
                    userId1 = sc.nextInt();
                    System.out.print("Enter second User ID: ");
                    userId2 = sc.nextInt();
                    removeFriendConnection(list, userId1, userId2);
                    break;
                case 4:
                    System.out.print("Enter first User ID: ");
                    userId1 = sc.nextInt();
                    System.out.print("Enter second User ID: ");
                    userId2 = sc.nextInt();
                    findMutualFriends(list, userId1, userId2);
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    userId = sc.nextInt();
                    displayFriends(list, userId);
                    break;
                case 6:
                    countFriends(list);
                    break;
                case 7:
                    printUsers(list);
                    break;
                case 8:
                    System.out.println("Exiting Social Media Friend Management System.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        while (choice != 8);
    }
}