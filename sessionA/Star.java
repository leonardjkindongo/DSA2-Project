import java.util.ArrayList; // Importing ArrayList for managing client nodes
import java.util.List; // Importing List for managing client nodes


 //Creates the star topology network.
 //Managing the server node and provides methods to add/remove client nodes.

public class Star {
    private ServerNode server;
    private List<ClientNode> clients;


    // Constructor initializes the star topology with a server node and an empty list of clients
    public Star() {
        this.server = new ServerNode();
        this.clients = new ArrayList<>();
    }

     // This method creates a new client node and adds it to the list of clients.
    public void insertNode(String clientId) {
        ClientNode newClient = new ClientNode(clientId, server);
        clients.add(newClient);
        System.out.println("Added client node: " + clientId);
    }

     // This method removes the client node from the list of clients and unregisters it from the server.
    public void deleteNode(String clientId) {
        for (ClientNode client : clients) {
            if (client.getClientId().equals(clientId)) {
                server.unregisterClient(clientId);
                clients.remove(client);
                System.out.println("Removed client node: " + clientId);
                return;
            }
        }
        System.out.println("Client " + clientId + " not found in network.");
    }

     // This method retrieves a client node from the list of clients based on its ID.
    public ClientNode getClient(String clientId) {
        for (ClientNode client : clients) {
            if (client.getClientId().equals(clientId)) {
                return client;
            }
        }
        return null;
    }
}
