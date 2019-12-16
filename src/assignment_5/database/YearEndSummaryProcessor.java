package assignment_5.database;

import assignment_5.datamodel.*;
import java.sql.*;
import java.util.*;

/**
 * This class extract data from the result set, specifically for year end summary
 * @author Jan
 */
public class YearEndSummaryProcessor implements MySqlProcessor {

    // store the columnName from the result set
    private List<String> columnNames = new ArrayList<>();

    // store data extract from database
    private Set<Customer> customers = new HashSet<>();
    private Map<String, Set<Product>> products = new HashMap<>();
    private Set<Employee> employees = new HashSet<>();

    // extract data from resultSet
    @Override
    public void extractData(ResultSet resultSet, InformationType info) throws SQLException {
        // get metadata of the result set
        ResultSetMetaData metaData = resultSet.getMetaData();
        // extract number of column
        int columnCount = metaData.getColumnCount();

        // clear the existing column name, if have one
        columnNames.clear();
        // add the column name in meta data to data structure
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnLabel(i));
        }

        // assign the data in database to data structure (choosing by type of info)
        switch (info) {
            case CUSTOMER:
                assignCustomers(resultSet, columnCount);
                break;
            case PRODUCT:
                assignProducts(resultSet, columnCount);
                break;
            case EMPLOYEE:
                assignEmployees(resultSet, columnCount);
                break;
        }
    }

    // assign the data to customers' data structure
    private void assignCustomers(ResultSet resultSet, int columnCount) throws SQLException {
        // assign all the data from the resultSet to data structure
        while (resultSet.next()) {
            // store the data from result set
            List<String> array = assignValues(resultSet, columnCount);
            // define the variables for readability
            String customerName = array.get(0);
            String streetAddress = array.get(1);
            String city = array.get(2);
            String postalCode = array.get(3);
            String country = array.get(4);
            int numOrders = Integer.parseInt(array.get(5));
            double orderValues = Double.parseDouble(array.get(6));

            // create instances and add to data structure
            Address address = new Address(streetAddress, city, postalCode, country);
            Customer customer = new Customer(customerName, address, numOrders, orderValues);
            customers.add(customer);
        }
    }

    // assign the data to products' data structure
    private void assignProducts(ResultSet resultSet, int columnCount) throws SQLException {
        // assign all the data from the resultSet to data structure
        while (resultSet.next()) {
            // store the data from result set
            List<String> array = assignValues(resultSet, columnCount);
            // define the variables for readability
            String productLineName = array.get(0);
            String productName = array.get(1);
            String productVendor = array.get(2);
            int unitsSold = Integer.parseInt(array.get(3));
            double totalSales = Double.parseDouble(array.get(4));

            // create instance of product
            Product product = new Product(productLineName, productName, productVendor, unitsSold, totalSales);
            // if key is not exist, add key to data structure
            if (!products.containsKey(productLineName)) {
                products.put(productLineName, new HashSet<>());
            }
            // add product to productLine
            products.get(productLineName).add(product);
        }
    }

    // assign the data to employees' data structure
    private void assignEmployees(ResultSet resultSet, int columnCount) throws SQLException {
        // assign all the data from the resultSet to data structure
        while (resultSet.next()) {
            // store the data from result set
            List<String> array = assignValues(resultSet, columnCount);
            // define the variables for readability
            String firstName = array.get(0);
            String lastName = array.get(1);
            String officeCity = array.get(2);
            int activeCustomers = Integer.parseInt(array.get(3));
            double totalSales = Double.parseDouble(array.get(4));

            // create instances and add to data structure
            Employee employee = new Employee(firstName, lastName, officeCity, activeCustomers, totalSales);
            employees.add(employee);
        }
    }

    // store the data from result set
    private List<String> assignValues(ResultSet resultSet, int columnCount) throws SQLException {
        List<String> array = new ArrayList<>();
        // loop through all the column in the result set
        for (int i = 0; i < columnCount; i++) {
            array.add(resultSet.getString(columnNames.get(i)));
        }
        return array;
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
