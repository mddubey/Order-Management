import java.io.Closeable;
import java.sql.*;

public class Sample{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            // this will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // setup the connection with the DB.
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/order_management?"
                            + "user=jdbc&password=password");

            // statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // resultSet gets the result of the SQL query
            resultSet = statement.executeQuery("select * from customers");
            System.out.println(resultSet.getFetchSize());

            resultSet = statement.executeQuery("select * from orders");
            System.out.println(resultSet.getFetchSize());

            resultSet = statement.executeQuery("select * from products");
            System.out.println(resultSet.getFetchSize());

            resultSet = statement.executeQuery("select * from order_product");
            System.out.println(resultSet.getFetchSize());

        } catch (Exception e) {
            throw e;
        } finally {
            //close();
        }

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // resultSet is initialised before the first data set
        while (resultSet.next()) {
            // it is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g., resultSet.getSTring(2);
            int user = resultSet.getInt("cust_id");
            String website = resultSet.getString("cust_name");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
        }
    }

    private void close(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            // don't throw now as it might leave following closables in undefined state
        }
    }

    public static void main(String[] args) throws Exception {
        new Sample().readDataBase();
    }
}