package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import presentation.views.ClientOperationsView;
import presentation.views.MainFrame;
import presentation.views.ProductOperationsView;
import presentation.views.ProductOrdersView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class Controller {
    private MainFrame mainFrame;
    private ClientOperationsView clientOperationsView;
    private ProductOperationsView productOperationsView;
    private ProductOrdersView productOrdersView;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;

    public Controller(MainFrame mainFrame, ClientOperationsView clientOperationsView, ProductOperationsView productOperationsView, ProductOrdersView productOrdersView, ClientBLL clientBLL, ProductBLL productBLL, OrderBLL orderBLL) {
        this.mainFrame = mainFrame;
        this.clientOperationsView = clientOperationsView;
        this.productOperationsView = productOperationsView;
        this.productOrdersView = productOrdersView;

        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.orderBLL = orderBLL;

        this.mainFrame.setVisible(true);

        this.mainFrame.addClientViewListener (new AddClientViewListener());
        this.mainFrame.addProductViewListener (new AddProductViewListener());
        this.mainFrame.addOrderViewListener (new AddOrderViewListener());

        this.clientOperationsView.addAddClientListener(new AddClientListener());
        this.clientOperationsView.addEditClientListener(new EditClientListener());
        this.clientOperationsView.addRemoveClientListener(new RemoveClientListener());

        this.productOperationsView.addAddProductListener(new AddProductListener());
        this.productOperationsView.addEditProductListener(new EditProductListener());
        this.productOperationsView.addRemoveProductListener(new RemoveProductListener());

        this.productOrdersView.addPlaceOrderListener(new PlaceOrderListener());
    }

    /**
     * It adds an ActionListener to the MainFrame to access the ClientOperationView
     */
    public class AddClientViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientOperationsView.setVisible(true);
            clientOperationsView.setTable(clientBLL.buildClientTable());
        }
    }

    /**
     * It adds an ActionListener to the MainFrame to access the ProductOperationsView
     */
    public class AddProductViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productOperationsView.setVisible(true);
            productOperationsView.setTable(productBLL.buildProductTable());
        }
    }

    /**
     * It adds an ActionListener to the MainFrame to access the ProductOrdersView
     */
    public class AddOrderViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productOrdersView.setVisible(true);
            productOrdersView.setTable(orderBLL.buildOrderTable());
        }
    }

    /**
     * It adds an ActionListener to the ClientOperationsView to add a client
     */
    public class AddClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientBLL.addClient(clientOperationsView.getUserInput());
            clientOperationsView.setTable(clientBLL.buildClientTable());
        }
    }

    /**
     * It adds an ActionListener to the ClientOperationsView to edit a client
     */
    public class EditClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientBLL.editClient(clientOperationsView.getUserInput());
            clientOperationsView.setTable(clientBLL.buildClientTable());
        }
    }

    /**
     * It adds an ActionListener to the ClientOperationsView to remove a client
     */
    public class RemoveClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientBLL.removeClient(clientOperationsView.getUserInput());
            clientOperationsView.setTable(clientBLL.buildClientTable());
        }
    }

    /**
     * It adds an ActionListener to the ProductOperationsView to add a product
     */
    public class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productBLL.addProduct(productOperationsView.getUserInput());
            productOperationsView.setTable(productBLL.buildProductTable());
        }
    }

    /**
     * It adds an ActionListener to the ProductOperationsView to edit a product
     */
    public class EditProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productBLL.editProduct(productOperationsView.getUserInput());
            productOperationsView.setTable(productBLL.buildProductTable());
        }
    }

    /**
     * It adds an ActionListener to the ProductOperationsView to remove a product
     */
    public class RemoveProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productBLL.removeProduct(productOperationsView.getUserInput());
            productOperationsView.setTable(productBLL.buildProductTable());
        }
    }

    /**
     * It adds an ActionListener to the ProductOrdersView to place an order.
     */
    public class PlaceOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            orderBLL.placeOrder(productOrdersView.getUserInput());
            productOrdersView.setTable(orderBLL.buildOrderTable());
        }
    }
}
