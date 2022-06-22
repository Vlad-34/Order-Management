package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton clientViewButton;
    private JButton productViewButton;
    private JButton orderViewButton;

    public MainFrame() {
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("ORDER MANAGEMENT SYSTEM");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        titleLabel.setBounds(159, 10, 307, 29);
        this.getContentPane().add(titleLabel);

        clientViewButton = new JButton("Client View");
        clientViewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        clientViewButton.setBounds(159, 89, 307, 29);
        this.getContentPane().add(clientViewButton);

        productViewButton = new JButton("Product View");
        productViewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        productViewButton.setBounds(159, 207, 307, 29);
        this.getContentPane().add(productViewButton);

        orderViewButton = new JButton("Order View");
        orderViewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        orderViewButton.setBounds(159, 325, 307, 29);
        this.getContentPane().add(orderViewButton);
    }

    public void addClientViewListener(ActionListener action) {
        clientViewButton.addActionListener(action);
    }

    public void addProductViewListener(ActionListener action) {
        productViewButton.addActionListener(action);
    }

    public void addOrderViewListener(ActionListener action) {
        orderViewButton.addActionListener(action);
    }

}
