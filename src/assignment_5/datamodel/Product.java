package assignment_5.datamodel;

/**
 * This class included the attributes for the product.
 * @author Jan
 */
public class Product {
    // define all the information related to product
    private String productLine;
    private String productName;
    private String productVendor;
    private int unitsSold;
    private double totalSales;

    // constructor for the product
    public Product(String productLine, String productName, String productVendor, int unitsSold, double totalSales) {
        this.productLine = productLine;
        this.productName = productName;
        this.productVendor = productVendor;
        this.unitsSold = unitsSold;
        this.totalSales = totalSales;
    }

    // Getter for productLine
    public String getProductLine() {
        return productLine;
    }
    
    // Getter for productName
    public String getProductName() {
        return productName;
    }

    // Getter for productVendor
    public String getProductVendor() {
        return productVendor;
    }

    // Getter for unitsSold
    public int getUnitsSold() {
        return unitsSold;
    }

    // Getter for totalSales
    public double getTotalSales() {
        return totalSales;
    }

    @Override
    public String toString() {
        return "Product{" + "productLine=" + productLine + ", productName=" + productName + ", productVendor=" + productVendor + ", unitsSold=" + unitsSold + ", totalSales=" + totalSales + '}' + "\n";
    }
}