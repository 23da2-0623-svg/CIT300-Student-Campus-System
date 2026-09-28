import java.util.Stack;

class Action {
    private String actionType; // e.g., "ADD", "DELETE", "UPDATE"
    private Student student;

    public Action(String actionType, Student student) {
        this.actionType = actionType;
        this.student = student;
    }

    public String getActionType() { return actionType; }
    public Student getStudent() { return student; }

    @Override
    public String toString() {
        return "Action: " + actionType + " | Student ID: " + student.getStudentId() + " (" + student.getName() + ")";
    }
}

public class ActionStack {
    private Stack<Action> actionStack;

    public ActionStack() {
        this.actionStack = new Stack<>();
    }

    // Push action to history stack
    public void pushAction(String actionType, Student student) {
        if (student != null) {
            actionStack.push(new Action(actionType, student));
            System.out.println("Action recorded in Stack: " + actionType);
        }
    }

    // Pop/Undo last action
    public Action popAction() {
        if (actionStack.isEmpty()) {
            System.out.println("No actions to undo.");
            return null;
        }
        return actionStack.pop();
    }

    // View action history
    public void displayHistory() {
        if (actionStack.isEmpty()) {
            System.out.println("Action history is empty.");
            return;
        }
        System.out.println("\n--- Recent Action History (Stack - LIFO) ---");
        for (int i = actionStack.size() - 1; i >= 0; i--) {
            System.out.println(actionStack.get(i));
        }
    }

    public boolean isEmpty() {
        return actionStack.isEmpty();
    }
}