package assignment_5.database;

import java.sql.*;

/**
 * interface for extract data from the result set
 * @author Jan
 */
public interface MySqlProcessor {
    // extract data from result set
    public void extractData(ResultSet resultSet, InformationType information) throws SQLException;
}
