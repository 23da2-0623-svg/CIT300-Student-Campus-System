class BSTNode {
    Student student;
    BSTNode left, right;

    public BSTNode(Student student) {
        this.student = student;
        this.left = this.right = null;
    }
}

public class StudentBST {
    private BSTNode root;

    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private BSTNode insertRec(BSTNode root, Student student) {
        if (root == null) {
            root = new BSTNode(student);
            return root;
        }
        if (student.getStudentId().compareToIgnoreCase(root.student.getStudentId()) < 0) {
            root.left = insertRec(root.left, student);
        } else if (student.getStudentId().compareToIgnoreCase(root.student.getStudentId()) > 0) {
            root.right = insertRec(root.right, student);
        }
        return root;
    }

    // Display In-Order (Sorted by ID)
    public void displayInOrder() {
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        System.out.println("\n--- Students Sorted by ID (BST In-Order) ---");
        inOrderRec(root);
    }

    private void inOrderRec(BSTNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.student);
            inOrderRec(root.right);
        }
    }
}