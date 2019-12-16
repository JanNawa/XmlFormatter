package assignment_5.database;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import assignment_5.datamodel.*;
import java.time.*;

/**
 * This class connect to MySql database and extract information to data model
 *
 * @author Jan
 */
public class MySqlConnector implements DatabaseConnector {

    // create properties instance
    private Properties dbProperties = new Properties();

    // store the info from the properties file for configuration
    private String driver;
    private String url;
    private String username;
    private String password;

    // store the info from properties file for year end summary
    private String chooseDatabase;
    private String customerQuery;
    private String productQuery;
    private String employeeQuery;

    // extract data from database and generate year end summary
    public YearEndSummary generateYearEndSummary(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException {
        String startDateStr = startDate.toString();
        String endDateStr = endDate.toString();
        
        YearEndSummaryProcessor processor = new YearEndSummaryProcessor();
        
        // connect to database
        try (Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();) {
            if (driver != null) {
                Class.forName(driver);
            }
            // select database csci3901
            statement.execute(chooseDatabase);

            // prepare sql statement
            PreparedStatement prepareStatement = connection.prepareStatement(customerQuery);
            prepareStatement.setString(1, startDateStr);
            prepareStatement.setString(2, endDateStr);
            prepareStatement.setString(3, startDateStr);
            prepareStatement.setString(4, endDateStr);

            // extract customer information
            ResultSet resultSet = prepareStatement.executeQuery();
            processor.extractData(resultSet, InformationType.CUSTOMER);
            System.out.println("customers: " + processor.getCustomers());

            // prepare sql statement
            prepareStatement = connection.prepareStatement(productQuery);
            prepareStatement.setString(1, startDateStr);
            prepareStatement.setString(2, endDateStr);

            // extract product information
            resultSet = prepareStatement.executeQuery();
            processor.extractData(resultSet, InformationType.PRODUCT);
            System.out.println("products: " + processor.getProducts());

            // prepare sql statement
            prepareStatement = connection.prepareStatement(employeeQuery);
            prepareStatement.setString(1, startDateStr);
            prepareStatement.setString(2, endDateStr);
            prepareStatement.setString(3, startDateStr);
            prepareStatement.setString(4, endDateStr);

            // extract employee information
            resultSet = prepareStatement.executeQuery();
            processor.extractData(resultSet, InformationType.EMPLOYEE);
            System.out.println("employees: " + processor.getEmployees());

        } catch (SQLException ex) {
            System.out.println("Error on SQL");
            Logger.getLogger(MySqlConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        // create year end summary from extract information
        YearEndSummary yearEndSummary = new YearEndSummary(startDate, endDate, processor.getCustomers(), processor.getProducts(), processor.getEmployees());
        return yearEndSummary;
    }

    // extract data from properties file
    @Override
    public void extractProperties(String filename, PropertiesType type) {
        String location = "";
        switch (type) {
            case CONFIGURATION:
                location += "external/configuration/";
                break;
            case MYSQL_YEAR_END_SUMMARY:
                location += "external/mySql/";
                break;
        }

        // load properties file to extract data from it
        try (InputStream inputFile = new FileInputStream(location + filename)) {
            dbProperties.clear();
            dbProperties.load(inputFile);
        } catch (IOException ex) {
            System.out.println("Error with IO");
            Logger.getLogger(MySqlConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        assignVariables(type);
    }

    private void assignVariables(PropertiesType type) {
        switch (type) {
            case CONFIGURATION:
                // assign data for configuration
                driver = dbProperties.getProperty("jdbc.driver");
                url = dbProperties.getProperty("jdbc.url");
                username = dbProperties.getProperty("jdbc.username");
                password = dbProperties.getProperty("jdbc.password");
                break;
            case MYSQL_YEAR_END_SUMMARY:
                // assign sql to variables
                chooseDatabase = dbProperties.getProperty("choose.db");
                customerQuery = dbProperties.getProperty("query.customer");
                productQuery = dbProperties.getProperty("query.product");
                employeeQuery = dbProperties.getProperty("query.employee");
                break;
        }
    }
}
