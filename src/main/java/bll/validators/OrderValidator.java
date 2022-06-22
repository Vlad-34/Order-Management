package bll.validators;

import model.Order;

public abstract class OrderValidator {

    public static void validate(Order order) {
        if(order.getQuantity() < 0)
            try {
                throw new RuntimeException("Invalid Quantity");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
    }
}
