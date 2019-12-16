package assignment_5.datamodel;

import java.time.*;
import java.util.*;

/**
 * This class stores 3 information in year end summary which included customers, products and employees.
 * @author Jan
 */
public class YearEndSummary extends Summary{
    // information for year end summary
    private Set<Customer> customers;
    private Map<String, Set<Product>> products;
    private Set<Employee> employees;
    
    // constructor to initiate all the information
    public YearEndSummary(LocalDate startDate, LocalDate endDate, Set<Customer> customers, Map<String, Set<Product>> products, Set<Employee> employees) {
        super(startDate, endDate);
        this.customers = customers;
        this.products = products;
        this.employees = employees;
    }

    // Getter for customers
    public Set<Customer> getCustomers() {
        Set<Customer> customersCopy = customers;
        return customersCopy;
    }

    // Getter for products
    public Map<String, Set<Product>> getProducts() {
        Map<String, Set<Product>> productsCopy = products;
        return productsCopy;
    }

    // Getter for employees
    public Set<Employee> getEmployees() {
        Set<Employee> employeesCopy = employees;
        return employeesCopy;
    }
}