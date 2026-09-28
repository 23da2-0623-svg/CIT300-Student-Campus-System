import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instantiating all members' data structures
        StudentLinkedList linkedList = new StudentLinkedList(); // Member 1
        ActionStack actionStack = new ActionStack();           // Member 2
        ServiceQueue serviceQueue = new ServiceQueue();         // Member 2
        StudentBST bst = new StudentBST();                     // Member 3
        StudentHashTable hashTable = new StudentHashTable();   // Member 3
        CampusGraph campusGraph = new CampusGraph();           // Member 4

        // Setup default campus map locations and paths
        campusGraph.addPath("Main Gate", "Library");
        campusGraph.addPath("Library", "IT Faculty");
        campusGraph.addPath("IT Faculty", "Student Center");
        campusGraph.addPath("Main Gate", "Auditorium");
        campusGraph.addPath("Auditorium", "Sports Complex");

        while (true) {
            System.out.println("\n=======================================================");
            System.out.println("  CAMPUS STUDENT & ROUTE MANAGEMENT SYSTEM (CIT300) ");
            System.out.println("=======================================================");
            System.out.println("--- [ Member 1: Linked List Operations ] ---");
            System.out.println(" 1. Add Student Record");
            System.out.println(" 2. Display All Student Records");
            System.out.println(" 3. Update Student Record");
            System.out.println(" 4. Delete Student Record");

            System.out.println("\n--- [ Member 2: Action Stack & Service Queue ] ---");
            System.out.println(" 5. Undo Last Action (Stack)");
            System.out.println(" 6. View Action History (Stack)");
            System.out.println(" 7. Submit Service Request (Queue)");
            System.out.println(" 8. Process Next Service Request (Queue)");
            System.out.println(" 9. Display Service Queue");

            System.out.println("\n--- [ Member 3: BST & Hash Table ] ---");
            System.out.println("10. Display Students Sorted by ID (BST)");
            System.out.println("11. Fast Search Student by ID (Hash Table)");

            System.out.println("\n--- [ Member 4: Campus Graph Traversals ] ---");
            System.out.println("12. Add Campus Route / Path (Graph)");
            System.out.println("13. Display Campus Network (Graph)");
            System.out.println("14. Breadth-First Traversal (BFS)");
            System.out.println("15. Depth-First Traversal (DFS)");

            System.out.println("\n16. Exit Application");
            System.out.println("=======================================================");
            System.out.print("Select an option (1-16): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Programme: ");
                    String prog = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();

                    Student student = new Student(id, name, prog, marks);
                    linkedList.addStudent(student);
                    bst.insert(student);
                    hashTable.put(id, student);
                    actionStack.pushAction("ADD", student);
                    break;

                case 2:
                    linkedList.displayAll();
                    break;

                case 3:
                    System.out.print("Enter Student ID to Update: ");
                    String uId = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String uName = scanner.nextLine();
                    System.out.print("Enter New Programme: ");
                    String uProg = scanner.nextLine();
                    System.out.print("Enter New Marks: ");
                    double uMarks = scanner.nextDouble();

                    if (linkedList.updateStudent(uId, uName, uProg, uMarks)) {
                        Student updatedStudent = new Student(uId, uName, uProg, uMarks);
                        bst.insert(updatedStudent);
                        hashTable.put(uId, updatedStudent);
                        actionStack.pushAction("UPDATE", updatedStudent);
                        System.out.println("Student record updated successfully!");
                    } else {
                        System.out.println("Student ID not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    String dId = scanner.nextLine();
                    Student deleted = linkedList.deleteStudent(dId);
                    if (deleted != null) {
                        actionStack.pushAction("DELETE", deleted);
                        System.out.println("Deleted Student: " + deleted);
                    } else {
                        System.out.println("Student ID not found.");
                    }
                    break;

                case 5:
                    Action undone = actionStack.popAction();
                    if (undone != null) {
                        System.out.println("Undone Action: " + undone);
                    }
                    break;

                case 6:
                    actionStack.displayHistory();
                    break;

                case 7:
                    System.out.print("Enter Request ID: ");
                    String reqId = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    String sId = scanner.nextLine();
                    System.out.print("Enter Request Details: ");
                    String details = scanner.nextLine();
                    serviceQueue.enqueueRequest(reqId, sId, details);
                    break;

                case 8:
                    serviceQueue.dequeueRequest();
                    break;

                case 9:
                    serviceQueue.displayQueue();
                    break;

                case 10:
                    bst.displayInOrder();
                    break;

                case 11:
                    System.out.print("Enter Student ID to Search: ");
                    String searchId = scanner.nextLine();
                    Student found = hashTable.get(searchId);
                    if (found != null) {
                        System.out.println("Found (Hash Table): " + found);
                    } else {
                        System.out.println("Student not found in Hash Table.");
                    }
                    break;

                case 12:
                    System.out.print("Enter Source Location: ");
                    String src = scanner.nextLine();
                    System.out.print("Enter Destination Location: ");
                    String dest = scanner.nextLine();
                    campusGraph.addPath(src, dest);
                    System.out.println("Path added successfully!");
                    break;

                case 13:
                    campusGraph.displayNetwork();
                    break;

                case 14:
                    System.out.print("Enter Start Location for BFS: ");
                    String bfsStart = scanner.nextLine();
                    campusGraph.bfsTraversal(bfsStart);
                    break;

                case 15:
                    System.out.print("Enter Start Location for DFS: ");
                    String dfsStart = scanner.nextLine();
                    campusGraph.dfsTraversal(dfsStart);
                    break;

                case 16:
                    System.out.println("Exiting System. Good luck with assignment submission!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select between 1 and 16.");
            }
        }
    }
}