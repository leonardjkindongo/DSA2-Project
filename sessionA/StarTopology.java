import java.util.ArrayList;
import java.util.List;

/**
 * Creates the star topology network.
 * Managing the server node and provides methods to add/remove client nodes.
 */
public class StarTopology {
    private ServerNode server;
    private List<ClientNode> clients;


    // Constructor for Star network

    public StarTopology() {
        this.server = new ServerNode();
        this.clients = new ArrayList<>();
    }

     // Insert a new client node into the network

    public void insertNode(String clientId) {
        ClientNode newClient = new ClientNode(clientId, server);
        clients.add(newClient);
        System.out.println("Added client node: " + clientId);
    }

     //Delete a client node from the network

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

     // Get a client node by ID

    public ClientNode getClient(String clientId) {
        for (ClientNode client : clients) {
            if (client.getClientId().equals(clientId)) {
                return client;
            }
        }
        return null;
    }
}