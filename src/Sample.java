import com.mritunjd.order_management.util.DBConnection;

import java.io.Closeable;
import java.sql.*;

public class Sample {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        Connection connection = new DBConnection().getConnection();

        statement = connection.createStatement();
        // resultSet gets the result of the SQL query
        resultSet = statement.executeQuery("select * from customers");
        System.out.println(resultSet.getFetchSize());

        resultSet = statement.executeQuery("select * from orders");
        System.out.println(resultSet.getFetchSize());

        resultSet = statement.executeQuery("select * from products");
        System.out.println(resultSet.getFetchSize());

        resultSet = statement.executeQuery("select * from order_product");
        System.out.println(resultSet.getFetchSize());


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