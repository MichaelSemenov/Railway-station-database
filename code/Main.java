import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Лабораторная работа №6: Railway-station-databases");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите порт для подключения: ");
        String port = scanner.nextLine();
        System.out.print("Введите название базы данных: ");
        String nameDataBase = scanner.nextLine();
        System.out.print("Введите пользователя для входа: ");
        String userName = scanner.nextLine();
        System.out.print("Введите пароль для подключения: ");
        String password = scanner.nextLine();
        ConnectorDataBases connectorDataBases = new ConnectorDataBases(port, nameDataBase, userName, password);
        UIUXInterface uiuxInterface = new UIUXInterface(connectorDataBases.getStatement());
        System.out.println("Программа успешно завершена!");
    }
}