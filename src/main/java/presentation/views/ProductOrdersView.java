package presentation.views;

import model.Order;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.table.TableModel;

public class ProductOrdersView extends JFrame {

    private JTextField idTextField;
    private JTextField clientIdTextField;
    private JTextField productIdTextField;
    private JTextField quantityTextField;
    private JLabel priceValueLabel;
    private JTable table;
    private JButton placeOrderButton;

    public ProductOrdersView() {
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("ORDERS");
        titleLabel.setBounds(216, 10, 193, 22);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        this.getContentPane().add(titleLabel);

        JLabel orderIdLabel = new JLabel("Order ID:");
        orderIdLabel.setBounds(70, 118, 78, 17);
        orderIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(orderIdLabel);

        idTextField = new JTextField();
        idTextField.setBounds(158, 119, 96, 19);
        idTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        idTextField.setColumns(10);
        this.getContentPane().add(idTextField);

        JLabel clientIdLabel = new JLabel("Client ID:");
        clientIdLabel.setBounds(70, 145, 78, 17);
        clientIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(clientIdLabel);

        clientIdTextField = new JTextField();
        clientIdTextField.setBounds(158, 146, 96, 19);
        clientIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        clientIdTextField.setColumns(10);
        this.getContentPane().add(clientIdTextField);

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setBounds(70, 172, 78, 17);
        productIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(productIdLabel);

        productIdTextField = new JTextField();
        productIdTextField.setBounds(158, 173, 96, 19);
        productIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        productIdTextField.setColumns(10);
        this.getContentPane().add(productIdTextField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(70, 199, 78, 17);
        quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(quantityLabel);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(158, 200, 96, 19);
        quantityTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        quantityTextField.setColumns(10);
        this.getContentPane().add(quantityTextField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(320, 42, 296, 391);
        this.getContentPane().add(scrollPane);

        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        placeOrderButton.setBounds(70, 292, 184, 21);
        getContentPane().add(placeOrderButton);
    }

    public int getIdTextField() {
        return Integer.parseInt(idTextField.getText());
    }

    public int getClientIdTextField() {
        return Integer.parseInt(clientIdTextField.getText());
    }

    public int getProductIdTextField() {
        return Integer.parseInt(productIdTextField.getText());
    }

    public int getQuantityTextField() {
        return Integer.parseInt(quantityTextField.getText());
    }

    public void setTable(TableModel tableModel) {
        this.table.setModel(tableModel);
    }

    public void setPriceValueLabel(double price) {
        this.priceValueLabel.setText(String.valueOf(price));
    }

    public void addPlaceOrderListener(ActionListener action) {
        placeOrderButton.addActionListener(action);
    }

    public Order getUserInput() {
        int id = this.getIdTextField();
        int clientId = this.getClientIdTextField();
        int productId = this.getProductIdTextField();
        double price = 0;
        int quantity = this.getQuantityTextField();
        return new Order(id, clientId, productId, price, quantity);
    }
}
