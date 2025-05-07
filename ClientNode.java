/**
 * Represents a client node in the star topology network.
 * Each client connects only to the central server node.
 */

public class ClientNode {
    private String clientId;  // Unique identifier for the client
    private ServerNode server;  // Reference to the central server
    // Data storage (at least 2 values as per requirements)
    private String data1;
    private String data2;


    //Constructor for ClientNode

    public ClientNode(String clientId, ServerNode server) {
        this.clientId = clientId;
        this.server = server;
        this.data1 = "";
        this.data2 = "";
        // Register with server upon creation
        this.server.registerClient(clientId, this);
    }


    //Send a message to another client through the server

    public void send(String receiverId, String message) {
        System.out.println("Client " + clientId + " sending message to " + receiverId);
        server.brokerMessage(clientId, receiverId, message);
    }


    // Receive a message from another client

    public void receive(String senderId, String message) {
        System.out.println("Client " + clientId + " received message from " + senderId + ": " + message);
    }

    // Getters and setters for data values

    public String getData1() { return data1; }
    public void setData1(String data1) { this.data1 = data1; }
    public String getData2() { return data2; }
    public void setData2(String data2) { this.data2 = data2; }

    public String getClientId() { return clientId; }
}
