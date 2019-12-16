package assignment_5.datamodel;

/**
 * This class included attributes for the employee.
 * @author Jan
 */
public class Employee {
    // define all the information related to employee
    private String firstName;
    private String lastName;
    private String officeCity;
    private int activeCustomers;
    private double totalSales;
    
    // constructor for the employee
    public Employee(String firstName, String lastName, String officeCity, int activeCustomers, double totalSales) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeCity = officeCity;
        this.activeCustomers = activeCustomers;
        this.totalSales = totalSales;
    }

    // Getter for the firstName
    public String getFirstName() {
        return firstName;
    }

    // Getter for the lastName
    public String getLastName() {
        return lastName;
    }

    // Getter for the officeCity
    public String getOfficeCity() {
        return officeCity;
    }

    // Getter for the activeCustomers
    public int getActiveCustomers() {
        return activeCustomers;
    }

    // Getter for the totalSales
    public double getTotalSales() {
        return totalSales;
    }

    @Override
    public String toString() {
        return "Employee{" + "firstName=" + firstName + ", lastName=" + lastName + ", officeCity=" + officeCity + ", activeCustomers=" + activeCustomers + ", totalSales=" + totalSales + '}' + "\n";
    }
}