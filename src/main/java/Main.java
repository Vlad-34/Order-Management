import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import presentation.Controller;
import presentation.views.ClientOperationsView;
import presentation.views.MainFrame;
import presentation.views.ProductOperationsView;
import presentation.views.ProductOrdersView;

public class Main {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();

        ClientBLL clientBLL = new ClientBLL(clientDAO);
        ProductBLL productBLL = new ProductBLL(productDAO);
        OrderBLL orderBLL = new OrderBLL(orderDAO, productDAO, clientDAO);

        MainFrame mainFrame = new MainFrame();
        ClientOperationsView clientOperationsView = new ClientOperationsView();
        ProductOperationsView productOperationsView = new ProductOperationsView();
        ProductOrdersView productOrdersView = new ProductOrdersView();

        Controller controller = new Controller(mainFrame, clientOperationsView, productOperationsView, productOrdersView, clientBLL, productBLL, orderBLL);
    }
}
