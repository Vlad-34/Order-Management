package bll;

import bll.validators.ProductValidator;
import dao.ProductDAO;
import model.Product;

import javax.swing.table.TableModel;

public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * It builds the product table
     * @return The table model needed to build the JTable.
     */
    public TableModel buildProductTable() {
        return this.productDAO.extractFromDatabase(this.productDAO.getAll());
    }

    /**
     * It adds a product to the product table.
     * @param product The product to be added to the table.
     */
    public void addProduct(Product product) {
        ProductValidator.validate(product);
        productDAO.add(product);
    }

    /**
     * It edits a product in the product table.
     * @param product The product to be edited.
     */
    public void editProduct(Product product) {
        ProductValidator.validate(product);
        productDAO.edit(product);
    }

    /**
     * It removes a product from the product table.
     * @param product The product to be removed.
     */
    public void removeProduct(Product product) {
        productDAO.remove(product);
    }
}
