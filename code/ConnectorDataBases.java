import java.sql.*;
import java.util.Properties;

public class ConnectorDataBases {

    private Statement statement;
    public ConnectorDataBases(String port, String nameDB, String user, String password) throws ClassNotFoundException, SQLException {
        System.out.println("=================================");
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:" + port +"/" + nameDB;
        Properties autorization = new Properties();
        autorization.put("user", user);

        autorization.put("password", password);
        Connection connection = DriverManager.getConnection(url, autorization);
        System.out.println("Подключение к базе данных Railway-station произошло успешно!");
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("=================================");
    }

    public Statement getStatement() {
        return statement;
    }
}
