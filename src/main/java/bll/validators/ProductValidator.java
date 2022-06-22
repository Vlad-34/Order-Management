package bll.validators;

import model.Product;

public abstract class ProductValidator {

    public static void validate(Product product) {
        if(product.getPrice() < 0)
            try {
                throw new RuntimeException("Invalid Price.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        if(product.getStock() < 0)
            try {
                throw new RuntimeException("Invalid Stock.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
    }
}
