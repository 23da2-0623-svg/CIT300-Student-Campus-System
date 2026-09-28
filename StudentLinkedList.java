class Node {
    Student student;
    Node next;

    public Node(Student student) {
        this.student = student;
        this.next = null;
    }
}

public class StudentLinkedList {
    private Node head;

    public void addStudent(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                if (temp.student.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                    System.out.println("Error: Student ID already exists!");
                    return;
                }
                temp = temp.next;
            }
            if (temp.student.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                System.out.println("Error: Student ID already exists!");
                return;
            }
            temp.next = newNode;
        }
        System.out.println("Student record added successfully!");
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n--- All Student Records ---");
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.student);
            temp = temp.next;
        }
    }

    public boolean updateStudent(String id, String newName, String newProgramme, double newMarks) {
        Node temp = head;
        while (temp != null) {
            if (temp.student.getStudentId().equalsIgnoreCase(id)) {
                temp.student.setName(newName);
                temp.student.setProgramme(newProgramme);
                temp.student.setMarks(newMarks);
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public Student deleteStudent(String id) {
        if (head == null) return null;

        if (head.student.getStudentId().equalsIgnoreCase(id)) {
            Student deleted = head.student;
            head = head.next;
            return deleted;
        }

        Node temp = head;
        while (temp.next != null) {
            if (temp.next.student.getStudentId().equalsIgnoreCase(id)) {
                Student deleted = temp.next.student;
                temp.next = temp.next.next;
                return deleted;
            }
            temp = temp.next;
        }
        return null;
    }
}