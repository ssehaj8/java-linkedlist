import java.util.Scanner;

class Student {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next;

    public Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    private Student head;

    public void addStudentAtBeginning(int rollNumber, String name, int age, String grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addStudentAtEnd(int rollNumber, String name, int age, String grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    public void addStudentAtPosition(int rollNumber, String name, int age, String grade, int position) {
        if (position == 1) {
            addStudentAtBeginning(rollNumber, name, age, grade);
            return;
        }
        Student newStudent = new Student(rollNumber, name, age, grade);
        Student temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Invalid position");
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    public void deleteStudent(int rollNumber) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != rollNumber) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Student not found");
            return;
        }
        temp.next = temp.next.next;
    }

    public void searchStudent(int rollNumber) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                System.out.println("Student Found: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found");
    }

    public void updateStudentGrade(int rollNumber, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                temp.grade = newGrade;
                System.out.println("Grade updated successfully");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found");
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("No student records found");
            return;
        }
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentLinkedList studentList = new StudentLinkedList();

        while (true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Position");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Update Student Grade");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    if (choice == 1) studentList.addStudentAtBeginning(rollNumber, name, age, grade);
                    else if (choice == 2) studentList.addStudentAtEnd(rollNumber, name, age, grade);
                    else {
                        System.out.print("Enter Position: ");
                        int position = scanner.nextInt();
                        studentList.addStudentAtPosition(rollNumber, name, age, grade, position);
                    }
                    break;
                case 4:
                    System.out.print("Enter Roll Number to delete: ");
                    int delRoll = scanner.nextInt();
                    studentList.deleteStudent(delRoll);
                    break;
                case 5:
                    System.out.print("Enter Roll Number to search: ");
                    int searchRoll = scanner.nextInt();
                    studentList.searchStudent(searchRoll);
                    break;
                case 6:
                    System.out.print("Enter Roll Number to update grade: ");
                    int updateRoll = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Grade: ");
                    String newGrade = scanner.nextLine();
                    studentList.updateStudentGrade(updateRoll, newGrade);
                    break;
                case 7:
                    studentList.displayStudents();
                    break;
                case 8:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
