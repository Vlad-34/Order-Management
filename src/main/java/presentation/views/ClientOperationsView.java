package presentation.views;

import model.Client;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientOperationsView extends JFrame {

    private JTextField clientIdTextField;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JTable table;
    private JButton addClientButton;
    private JButton editClientButton;
    private JButton removeClientButton;

    public ClientOperationsView() {
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("CLIENT OPERATIONS");
        titleLabel.setBounds(216, 10, 193, 22);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        this.getContentPane().add(titleLabel);

        JLabel clientIdLabel = new JLabel("ID:");
        clientIdLabel.setBounds(70, 107, 78, 17);
        clientIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(clientIdLabel);

        clientIdTextField = new JTextField();
        clientIdTextField.setBounds(158, 108, 96, 19);
        clientIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        clientIdTextField.setColumns(10);
        this.getContentPane().add(clientIdTextField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(70, 134, 78, 17);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(158, 135, 96, 19);
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameTextField.setColumns(10);
        this.getContentPane().add(nameTextField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(70, 161, 78, 17);
        addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(addressLabel);

        addressTextField = new JTextField();
        addressTextField.setBounds(158, 162, 96, 19);
        addressTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addressTextField.setColumns(10);
        this.getContentPane().add(addressTextField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(70, 188, 78, 17);
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(158, 189, 96, 19);
        emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailTextField.setColumns(10);
        this.getContentPane().add(emailTextField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(70, 215, 78, 17);
        phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(phoneLabel);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(158, 216, 96, 19);
        phoneTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        phoneTextField.setColumns(10);
        this.getContentPane().add(phoneTextField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(320, 42, 296, 391);
        this.getContentPane().add(scrollPane);

        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table);

        addClientButton = new JButton("Add Client");
        addClientButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addClientButton.setBounds(70, 271, 184, 21);
        this.getContentPane().add(addClientButton);

        editClientButton = new JButton("Edit Client");
        editClientButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        editClientButton.setBounds(70, 302, 184, 21);
        this.getContentPane().add(editClientButton);

        removeClientButton = new JButton("Remove Client");
        removeClientButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        removeClientButton.setBounds(70, 333, 184, 21);
        this.getContentPane().add(removeClientButton);
    }

    public int getClientIdTextField() {
        return Integer.parseInt(clientIdTextField.getText());
    }

    public String getNameTextField() {
        return nameTextField.getText();
    }

    public String getAddressTextField() {
        return addressTextField.getText();
    }

    public String getEmailTextField() {
        return emailTextField.getText();
    }

    public String getPhoneTextField() {
        return phoneTextField.getText();
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(TableModel tableModel) {
        this.table.setModel(tableModel);
    }

    public void addAddClientListener(ActionListener action) {
        addClientButton.addActionListener(action);
    }

    public void addEditClientListener(ActionListener action) {
        editClientButton.addActionListener(action);
    }

    public void addRemoveClientListener(ActionListener action) {
        removeClientButton.addActionListener(action);
    }

    public Client getUserInput() {
        int id = this.getClientIdTextField();
        String name = this.getNameTextField();
        String address = this.getAddressTextField();
        String email = this.getEmailTextField();
        String phone = this.getPhoneTextField();
        return new Client(id, name, address, email, phone);
    }
}
