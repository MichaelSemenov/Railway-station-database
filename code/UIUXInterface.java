import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UIUXInterface {
    private Scanner scanner;
    private Statement statement;
    public UIUXInterface(Statement statement) throws SQLException {
        scanner = new Scanner(System.in);
        this.statement = statement;
        showMenu();
    }
    void showMenu() throws SQLException {
        boolean flag = true;
        int choice;
        do{
            System.out.print(PatternMenu.menuShowInformation());
            choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    showChoiceTableMenu();
                }break;
                case 2:{
                    showInfoLabWorks();
                }break;
                case 3: {
                    flag = false;
                }break;
                default:{
                    System.out.println("Выбрано неверное действие, попробуйте попытку снова");
                }
            }

        }while(flag);
    }

    void showChoiceTableMenu() throws SQLException {
        while(true){
            System.out.print(PatternMenu.showTableInformation());
            int choice = scanner.nextInt();
            if(choice != 9) showTableMenu(choice, PatternMenu.returnNameTable(choice));
            else break;
        }

    }

    void showInfoLabWorks() throws SQLException {
        int choice;
        boolean flag = true;
        do{
            System.out.print(PatternMenu.showLaboratoryInformation());
            choice = scanner.nextInt();
            switch (choice){
                case 31: flag = false; break;
                default:
                    if(choice >=1 && choice <= 30) PatternMenu.labWorkRequest(choice, statement);
                    else System.out.println("Вы указали неверное действие, попробуйте снова!");
            }
        }while(flag);
    }
    void showTableMenu(int choice, String tableName) throws SQLException {
        boolean flag = true;
        do{
            System.out.print(PatternMenu.showTableInfo(tableName));
            choice = scanner.nextInt();
            switch(choice){
                case 1:{
                    PatternMenu.showTableData(statement,tableName);
                }break;
                case 2:{
                    PatternMenu.deleteRowTable(statement, tableName);
                    System.out.println("Данные таблицы успешно удалены!");
                }break;
                case 3:{
                    PatternMenu.changeRowsInformation(statement, tableName);
                    System.out.println("Данные успешно изменены!");
                }break;
                case 4:{
                    PatternMenu.addRowsInTable(statement, tableName);
                    System.out.println("Данные в таблицу успешно добавлены!");
                }break;
                case 5:{
                    flag = false;
                }break;
                default:
                    System.out.println("Неправильный выбор!");
            }
        }while (flag);
    }
}
