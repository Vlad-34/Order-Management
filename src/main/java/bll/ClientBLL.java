package bll;

import bll.validators.ClientValidator;
import dao.ClientDAO;
import model.Client;

import javax.swing.table.TableModel;

public class ClientBLL {
    private ClientDAO clientDAO;

    public ClientBLL(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    /**
     * It builds the client table
     * @return The table model needed to build the JTable.
     */
    public TableModel buildClientTable() {
        return this.clientDAO.extractFromDatabase(this.clientDAO.getAll());
    }

    /**
     * It adds a client to the client table.
     * @param client The client to be added to the table.
     */
    public void addClient(Client client) {
        ClientValidator.validate(client);
        clientDAO.add(client);
    }

    /**
     * It edits a client in the client table
     * @param client The client to be edited.
     */
    public void editClient(Client client) {
        ClientValidator.validate(client);
        clientDAO.edit(client);
    }

    /**
     * It removes a client from the client table.
     * @param client The client to be removed.
     */
    public void removeClient(Client client) {
        clientDAO.remove(client);
    }
}
