package assignment_5.datamodel;

/**
 * This class included the attributes for the customer.
 * @author Jan
 */
public class Customer {
    // define all the information related to customer
    private String customerName;
    private Address address;
    private int numOrders;
    private double orderValue;

    // constructor for the customer
    public Customer(String customerName, Address address, int numOrders, double orderValue) {
        this.customerName = customerName;
        this.address = address;
        this.numOrders = numOrders;
        this.orderValue = orderValue;
    }

    // Getter for the customerName
    public String getCustomerName() {
        return customerName;
    }

    // Getter for the address
    public Address getAddress() {
        return address;
    }

    // Getter for the numOrders (number of orders)
    public int getNumOrders() {
        return numOrders;
    }

    // Getter for the orderValue
    public double getOrderValue() {
        return orderValue;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerName=" + customerName + ", address=" + address + ", numOrders=" + numOrders + ", orderValue=" + orderValue + '}' + "\n";
    }
}