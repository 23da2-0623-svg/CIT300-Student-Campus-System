import java.util.LinkedList;
import java.util.Queue;

class ServiceRequest {
    private String requestId;
    private String studentId;
    private String requestDetails;

    public ServiceRequest(String requestId, String studentId, String requestDetails) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.requestDetails = requestDetails;
    }

    public String getRequestId() { return requestId; }
    public String getStudentId() { return studentId; }
    public String getRequestDetails() { return requestDetails; }

    @Override
    public String toString() {
        return "Request ID: " + requestId + " | Student ID: " + studentId + " | Details: " + requestDetails;
    }
}

public class ServiceQueue {
    private Queue<ServiceRequest> queue;

    public ServiceQueue() {
        this.queue = new LinkedList<>();
    }

    // Enqueue a new service request
    public void enqueueRequest(String requestId, String studentId, String details) {
        ServiceRequest req = new ServiceRequest(requestId, studentId, details);
        queue.add(req);
        System.out.println("Service request added to Queue successfully.");
    }

    // Dequeue (process) the next service request
    public ServiceRequest dequeueRequest() {
        if (queue.isEmpty()) {
            System.out.println("No pending service requests in queue.");
            return null;
        }
        ServiceRequest processed = queue.poll();
        System.out.println("Processing Request ID: " + processed.getRequestId() + " for Student: " + processed.getStudentId());
        return processed;
    }

    // Display all queued requests
    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Service Queue is currently empty.");
            return;
        }
        System.out.println("\n--- Pending Service Requests (Queue - FIFO) ---");
        for (ServiceRequest req : queue) {
            System.out.println(req);
        }
    }
}