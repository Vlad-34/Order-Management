package bll;

import bll.validators.OrderValidator;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;
import presentation.views.ProductOrdersView;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.IOException;

public class OrderBLL {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private ClientDAO clientDAO;

    public OrderBLL(OrderDAO orderDAO, ProductDAO productDAO, ClientDAO clientDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
    }

    /**
     * It builds the order table
     * @return The table model needed to build the JTable.
     */
    public TableModel buildOrderTable() {
        return this.orderDAO.extractFromDatabase(this.orderDAO.getAll());
    }

    /**
     * It places an order: it updates the order table, the price label and generates the receipt.
     * @param order The received order.
     */
    public void placeOrder(Order order) {
        OrderValidator.validate(order);
        int productId = order.getProductId();
        Product product = productDAO.getById(productId);
        double price = product.getPrice();

        order.setPrice(price * order.getQuantity());
        int newStock = product.getStock() - order.getQuantity();
        if(newStock >= 0) {
            product.setStock(product.getStock() - order.getQuantity());
            productDAO.edit(product);
            orderDAO.add(order);
            giveReceipt(order);
        }
        else
            JOptionPane.showMessageDialog(null, "Insufficient stock.");
    }

    /**
     * It writes the receipt details to a text file.
     * @param order The received order.
     */
    public void giveReceipt(Order order) {
        try {
            FileWriter fileWriter = new FileWriter("Receipt.txt", false);
            int clientId = order.getClientId();
            int productId = order.getProductId();
            Client client = clientDAO.getById(clientId);
            Product product = productDAO.getById(productId);
            fileWriter.write("Order ID: " + order.getId() + "\n\n" +
                    "Name: " + client.getName() + "\n" +
                    "Address: " + client.getAddress() + "\n" +
                    "Email: " + client.getEmail() + "\n" +
                    "Phone : " + client.getPhone() + "\n\n" +
                    "Product: " + product.getName() + "\n" +
                    "Quantity: " + order.getQuantity() + "\n" +
                    "Price: " + order.getPrice() + "\n");
            fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
