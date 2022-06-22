package model;

public class Order {
    private int id;
    private int clientId;
    private int productId;
    private double price;
    private int quantity;

    public Order() {

    }

    public Order(int id, int clientId, int productId, double price, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
