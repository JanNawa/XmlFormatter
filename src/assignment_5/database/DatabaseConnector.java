package assignment_5.database;

/**
 * interface for database connector to interact with database provider
 * @author Jan
 */
public interface DatabaseConnector {
    // extract data from properties file
    public void extractProperties(String filename, PropertiesType type);
}