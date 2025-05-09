// Represents a client in our star-shaped network
public class ClientNode {

    // client unique name
    private String clientId;
    private ServerNode server;
    // Each client can store two pieces of information (its ID and a server reference)
    private String data1;
    private String data2;

    // Constructor to create a new client
    // - Takes a client ID and a server reference
    public ClientNode(String clientId, ServerNode server) {
        this.clientId = clientId;
        this.server = server;
        this.data1 = "";
        this.data2 = "";
        // Automatically signs in to the server when created
        this.server.registerClient(clientId, this);
    }

    // How this client sends messages to others:
    // - Shows who sent it
    public void send(String receiverId, String message) {
        System.out.println("Client " + clientId + " sending message to " + receiverId);
        // Actually sends through the server
        server.brokerMessage(clientId, receiverId, message);
    }

    // How this client receives messages:
    // - Shows who sent it and the message
    public void receive(String senderId, String message) {
        System.out.println("Client " + clientId + " received message from " + senderId + ": " + message);
    }

    // Getters and setters for the data fields
    // - These are used to access and modify the data stored in the client
    public String getData1() { return data1; }
    public void setData1(String data1) { this.data1 = data1; }
    public String getData2() { return data2; }
    public void setData2(String data2) { this.data2 = data2; }

    // Getter for client ID
    // - This is used to identify the client in the network
    public String getClientId() { return clientId; }
}