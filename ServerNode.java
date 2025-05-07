import java.util.HashMap;

/**
 * Represents the central server node in the star topology network.
 * This node handles messages between client nodes and connections to all clients.
 */

public class ServerNode {

    // Store client nodes with their IDs as keys
    private HashMap<String, ClientNode> clients;

    // Data storage (at least 2 values as per requirements)
    private String data1;
    private String data2;

    // Constructor for ServerNode

    public ServerNode() {
        this.clients = new HashMap<>();
        this.data1 = "";
        this.data2 = "";
    }

    // Broker a message from one client to another

    public void brokerMessage(String senderId, String receiverId, String message) {
        if (clients.containsKey(receiverId)) {
            ClientNode receiver = clients.get(receiverId);
            receiver.receive(senderId, message);
        } else {
            System.out.println("Error: Receiver " + receiverId + " not found.");
        }
    }

    // Register a new client with the server

    public void registerClient(String clientId, ClientNode client) {
        clients.put(clientId, client);
        System.out.println("Client " + clientId + " registered with server.");
    }

    // Unregister a client from the server

    public void unregisterClient(String clientId) {
        if (clients.containsKey(clientId)) {
            clients.remove(clientId);
            System.out.println("Client " + clientId + " unregistered from server.");
        } else {
            System.out.println("Error: Client " + clientId + " not found.");
        }
    }

    // Getters and setters for data values

    public String getData1() { return data1; }
    public void setData1(String data1) { this.data1 = data1; }
    public String getData2() { return data2; }
    public void setData2(String data2) { this.data2 = data2; }
}
