/**
 Main class to demonstrate the star topology network.
 Creates a network, adds nodes, and simulates messages.
 */

public class Main {
    public static void main(String[] args) {
        // Create the star network
        StarTopology network = new StarTopology();

        // Add client nodes
        network.insertNode("Client1");
        network.insertNode("Client2");
        network.insertNode("Client3");

        // Get references to clients
        ClientNode client1 = network.getClient("Client1");
        ClientNode client2 = network.getClient("Client2");
        ClientNode client3 = network.getClient("Client3");

        // Send messages between clients
        if (client1 != null && client2 != null) {
            client1.send("Client2", "Hello from Client1!");
            client2.send("Client1", "Hi there, Client1!");
        }

        // Demonstrate removing a node
        network.deleteNode("Client3");

        // Try sending to removed client
        if (client1 != null) {
            client1.send("Client3", "This should fail");
        }
    }
}