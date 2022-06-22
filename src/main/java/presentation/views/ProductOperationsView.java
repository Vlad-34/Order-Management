package presentation.views;

import model.Product;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.table.TableModel;

public class ProductOperationsView extends JFrame {

    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField descriptionTextField;
    private JTextField stockTextField;
    private JTable table;
    private JButton addProductButton;
    private JButton editProductButton;
    private JButton removeProductButton;

    public ProductOperationsView() {
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("PRODUCT OPERATIONS");
        titleLabel.setBounds(210, 10, 206, 22);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        this.getContentPane().add(titleLabel);

        JLabel clientIdLabel = new JLabel("ID:");
        clientIdLabel.setBounds(70, 107, 78, 17);
        clientIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(clientIdLabel);

        idTextField = new JTextField();
        idTextField.setBounds(158, 108, 96, 19);
        idTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        idTextField.setColumns(10);
        this.getContentPane().add(idTextField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(70, 134, 78, 17);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(158, 135, 96, 19);
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameTextField.setColumns(10);
        this.getContentPane().add(nameTextField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(70, 161, 78, 17);
        priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(priceLabel);

        priceTextField = new JTextField();
        priceTextField.setBounds(158, 162, 96, 19);
        priceTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        priceTextField.setColumns(10);
        this.getContentPane().add(priceTextField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(70, 188, 78, 17);
        descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(descriptionLabel);

        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(158, 189, 96, 19);
        descriptionTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        descriptionTextField.setColumns(10);
        this.getContentPane().add(descriptionTextField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(70, 215, 78, 17);
        stockLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(stockLabel);

        stockTextField = new JTextField();
        stockTextField.setBounds(158, 216, 96, 19);
        stockTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        stockTextField.setColumns(10);
        this.getContentPane().add(stockTextField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(320, 42, 296, 391);
        this.getContentPane().add(scrollPane);

        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table);

        addProductButton = new JButton("Add Product");
        addProductButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addProductButton.setBounds(70, 271, 184, 21);
        this.getContentPane().add(addProductButton);

        editProductButton = new JButton("Edit Product");
        editProductButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        editProductButton.setBounds(70, 302, 184, 21);
        this.getContentPane().add(editProductButton);

        removeProductButton = new JButton("Remove Product");
        removeProductButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        removeProductButton.setBounds(70, 333, 184, 21);
        this.getContentPane().add(removeProductButton);
    }

    public int getIdTextField() {
        return Integer.parseInt(idTextField.getText());
    }

    public String getNameTextField() {
        return nameTextField.getText();
    }

    public double getPriceTextField() {
        return Double.parseDouble(priceTextField.getText());
    }

    public String getDescriptionTextField() {
        return descriptionTextField.getText();
    }

    public int getStockTextField() {
        return Integer.parseInt(stockTextField.getText());
    }

    public void setTable(TableModel tableModel) { // to be determined
        this.table.setModel(tableModel);
    }

    public void addAddProductListener(ActionListener action) {
        addProductButton.addActionListener(action);
    }

    public void addEditProductListener(ActionListener action) {
        editProductButton.addActionListener(action);
    }

    public void addRemoveProductListener(ActionListener action) {
        removeProductButton.addActionListener(action);
    }

    public Product getUserInput() {
        int id = 0, stock;
        double price;
        String name, description;

        try {
            id = this.getIdTextField();
            price = this.getPriceTextField();
            stock = this.getStockTextField();

        } catch (NumberFormatException exception){
            price = 0;
            stock = 0;
        }
        description = this.getDescriptionTextField();
        name = this.getNameTextField();

        return new Product(id, name, price, description, stock);
    }
}
