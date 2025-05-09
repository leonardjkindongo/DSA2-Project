public class Main {
    public static void main(String[] args) {
        // Create a new star network
        Star network = new Star();

        // Add three client computers to the network
        network.insertNode("Client1");  // Add first computer named "Client1"
        network.insertNode("Client2");  // Add second computer named "Client2"
        network.insertNode("Client3");  // Add third computer named "Client3"

        // Get handles to each client so we can send messages later
        ClientNode client1 = network.getClient("Client1");  // Get reference to Client1
        ClientNode client2 = network.getClient("Client2");  // Get reference to Client2
        ClientNode client3 = network.getClient("Client3");  // Get reference to Client3

        // Send messages between clients
        if (client1 != null && client2 != null) {

            // Client1 sends "Hello" to Client2
            client1.send("Client2", "Hello from Client1!");
            
            // Client2 replies to Client1
            client2.send("Client1", "Hi there, Client1!");
        }

        // Remove Client3 from the network
        network.deleteNode("Client3");

        // Try sending a message to the removed client (should fail)
        if (client1 != null) {

            // This message won't be delivered because Client3 is gone
            client1.send("Client3", "This should fail");
        }
    }
}