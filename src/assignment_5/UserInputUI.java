package assignment_5;

import assignment_5.database.*;
import static assignment_5.database.PropertiesType.*;
import assignment_5.xml.*;
import assignment_5.datamodel.*;
import java.time.*;
import java.util.*;

/**
 * This class interact with the user for input and automatically generate the
 * year end summary in xml file.
 *
 * @author Jan
 */
public class UserInputUI {

    // Define symbols to use in the program as a way of knowing what we're seeing in the text.
    public static final String DATE_FORMAT = "([0-9]{4})-([0-9]{2})-([0-9]{2})";

    // define variables to manage user input
    private static String startDate;
    private static String endDate;
    private static String filename;
    private static boolean isDateValid = false;

    // define variables for creating instance
    private static LocalDate localStartDate = null;
    private static LocalDate localEndDate = null;

    public static void main(String[] args) throws ClassNotFoundException {
        // Ensure the user provided some input.
        // Using a scanner, the system will keep asking for input until it gets something.
        try (Scanner userInput = new Scanner(System.in)) {
            // Get the starting date from the user
            System.out.println("Enter the starting date in YYYY-MM-DD: ");
            localStartDate = validateDate(userInput, startDate);

            // Get the ending date from the user
            System.out.println("Enter the ending date in YYYY-MM-DD: ");
            localEndDate = validateDate(userInput, endDate);

            // Get the filename from the user
            System.out.println("Enter the filename: ");
            filename = userInput.next();
        }

        // create database connector
        DatabaseConnector databaseConnector = new MySqlConnector();
        // extract data from properties file
        databaseConnector.extractProperties("mySqlDatabase.properties", CONFIGURATION);
        databaseConnector.extractProperties("mySql_yearEndSummary.properties", MYSQL_YEAR_END_SUMMARY);
        YearEndSummary yearEndSummary = ((MySqlConnector) databaseConnector).generateYearEndSummary(localStartDate, localEndDate);
        // write the data into xml format
        XmlTransformer xml = new XmlTransformer();
        xml.writeToXml("output/" + filename, yearEndSummary);

    }

    // validate the date from user input
    private static LocalDate validateDate(Scanner userInput, String date) {
        // define the default value for the variable
        isDateValid = false;
        LocalDate localDate = null;
        // loop as long as user input is invalid date (including format)
        do {
            date = userInput.next();
            try {
                localDate = LocalDate.parse(date);
                isDateValid = true;
            } catch (DateTimeException e) {
                continue;
            }
        } while (!isDateFormat(date) || !isDateValid);
        return localDate;
    }

    // check if the date is format as required
    private static boolean isDateFormat(String date) {
        return date.matches(DATE_FORMAT);
    }
}
