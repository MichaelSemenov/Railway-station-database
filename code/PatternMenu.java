import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class PatternMenu {
    private static String schemaName = "Railway-station";
    static String menuShowInformation(){
        return "Основные действия программы: \n" +
                "[1] Работа с данными таблицами. \n" +
                "[2] Отображение результатов лабораторной №4 и №5 работы. \n" +
                "[3] Завершение программы! \n" +
                "Ваш вариант действия: ";
    }
    static String showTableInformation(){
        return "Выберите с какой таблицей вы хотите работать: \n" +
                "[1] -> CITY \n" +
               "[2] -> CONDUCTOR \n" +
               "[3] -> PASSENGER \n" +
               "[4] -> PASSENGER&TRAIN \n" +
               "[5] -> RAILWAY_CARRIAGE \n" +
               "[6] -> STATION \n" +
               "[7] -> TRAIN \n" +
               "[8] -> TRIP \n" +
               "[9] Вернуться назад \n" +
               "Ваш выбор: ";
    }

    static String showTableInfo(String tableName){
        return "Действие с таблицей [" + tableName + "]\n"+
                "[1] -> Просмотр данных таблицы.\n" +
                "[2] -> Удаление данных таблицы.\n" +
                "[3] -> Редактирование данных.\n" +
                "[4] -> Добавление данных в таблицу.\n" +
                "[5] Вернуться назад \n" +
                "Ваш выбор: ";
    }
    static String returnNameTable(int choice){
        return switch (choice){
            case 1: yield "CITY";
            case 2: yield "CONDUCTOR";
            case 3: yield "PASSENGER";
            case 4: yield "PASSENGER&TRAIN";
            case 5: yield "RAILWAY_CARRIAGE";
            case 6: yield "STATION";
            case 7: yield "TRAIN";
            case 8: yield "TRIP";
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        };
    }

    static void showTableData(Statement statement, String tableName) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + schemaName + "\"." + "\"" +tableName + "\";");
        while(resultSet.next()){
            showLineInformation(resultSet, tableName);
        }

    }
    static void showLineInformation(ResultSet rs, String tableName) throws SQLException {
        if(Objects.equals(tableName, "CITY")){
            String CITY_NAME = rs.getString("CITY_NAME");
            String REGION = rs.getString("REGION");
            String COUNTRY = rs.getString("COUNTRY");
            System.out.printf("%-15s %20s %20s%n", CITY_NAME, REGION, COUNTRY);
        }
        if(tableName.equals("CONDUCTOR")){
            int PERSONAL_NUMBER = rs.getInt("PERSONAL_NUMBER");
            String SURNAME = rs.getString("SURNAME");
            String NAME = rs.getString("NAME");
            String MIDDLE_NAME = rs.getString("MIDDLE_NAME");
            System.out.printf("%-15d %20s %20s %20s%n", PERSONAL_NUMBER, SURNAME, NAME, MIDDLE_NAME);
        }
        if(tableName.equals("PASSENGER")){
            String NUMBER_PASSPORT = rs.getString("NUMBER_PASSPOER");
            String SURNAME = rs.getString("SURNAME");
            String NAME = rs.getString("NAME");
            String MIDDLE_NAME = rs.getString("MIDDLE_NAME");
            int PERSONAL_NUMBER_FK = rs.getInt("PERSONAL_NUMBER_FK");
            System.out.printf("%-15s %15s %15s %15s %15d%n", NUMBER_PASSPORT, SURNAME, NAME,  MIDDLE_NAME, PERSONAL_NUMBER_FK);
        }
        if(tableName.equals("PASSENGER&TRAIN")){
            String NUMBER_PASSPORT_FK_PK = rs.getString("NUMBER_PASSPORT_FK_PK");
            String TRAIN_ID_PK_FK = rs.getString("TRAIN_ID_PK_FK");
            System.out.printf("%-10s %15s%n", NUMBER_PASSPORT_FK_PK, TRAIN_ID_PK_FK);
        }
        if(tableName.equals("RAILWAY_CARRIAGE")){
            int RAILWAY_CARRIAGE_ID = rs.getInt("RAILWAY_CARRIAGE_ID");
            int PLACE = rs.getInt("PLACE");
            int NUMBER_CARRIAGE = rs.getInt("NUMBER_CARRIAGE");
            String TYPE_CARRIAGE = rs.getString("TYPE_CARRIAGE");
            int TRAIN_ID_FK = rs.getInt("TRAIN_ID_FK");
            System.out.printf("%-10d %10d %15d %10s %10d%n", RAILWAY_CARRIAGE_ID, PLACE, NUMBER_CARRIAGE, TYPE_CARRIAGE, TRAIN_ID_FK);
        }
        if(tableName.equals("STATION")){
            int STATION_ID = rs.getInt("STATION_ID");
            String STATION_NAME = rs.getString("STATION_NAME");
            int COUNT_PATH = rs.getInt("COUNT_PATH");
            float SQUARE = rs.getFloat("SQUARE");
            String CITY_NAME_FK = rs.getString("CITY_NAME_FK");
            System.out.printf("%-10d %25s %10d %15f %15s%n", STATION_ID, STATION_NAME, COUNT_PATH, SQUARE, CITY_NAME_FK);
        }
        if(tableName.equals("TRAIN")){
            int TRAIN_ID = rs.getInt("TRAIN_ID");
            String STATION_DEPARTURE = rs.getString("STATION_DEPARTURE");
            String STATION_ARRIVAL = rs.getString("STATION_ARRIVAL");
            String FUEL_TYPE = rs.getString("FUEL_TYPE");
            int  STATION_ID_FK = rs.getInt("STATION_ID_FK");
            System.out.printf("%-10d %20s %20s %15s %10d%n", TRAIN_ID, STATION_DEPARTURE, STATION_ARRIVAL, FUEL_TYPE, STATION_ID_FK);
        }
        if(tableName.equals("TRIP")){
            int TRAIN_ID_PK_FK = rs.getInt("TRAIN_ID_PK_FK");
            int RAILWAY_CARRIAGE_ID_PK_FK = rs.getInt("RAILWAY_CARRIAGE_ID_PK_FK");
            int PERSONAL_NUMBER_PK_FK = rs.getInt("PERSONAL_NUMBER_PK_FK");
            String DATA = rs.getDate("DATA").toString();
            String TIME = rs.getTime("TIME").toString();
            System.out.printf("%-5d %10d %10d %15s %15s%n", TRAIN_ID_PK_FK, RAILWAY_CARRIAGE_ID_PK_FK, PERSONAL_NUMBER_PK_FK, DATA, TIME);
        }
    }
    static String showLaboratoryInformation(){
        return "[Запросы лабораторной работы №4] \n" +
                "[1] -> Вывести все табличные данные с использованием оператора SELECT для таблицы <CITY>\n" +
                "[2] -> Вывести данные столбцов <STATION_DEPARTURE> и <STATION_ARRIVAL> для таблицы <TRAIN>\n"+
                "[3] -> Вывести из таблицы <RAILWAY_CARRIAGE> вагоны, в которых количество мест для сидений превышает 30\n" +
                "[4] -> Вывести имена проводников из таблицы <CONDUCTOR> без повторений\n" +
                "[5] -> Вывести первые 10 значений таблицы <PASSENGER&TRAIN>\n" +
                "[6] -> Вывести таблицу <STATION> с информацией, сколько площади приходится на один пути (примечание: рассчитывается как общая площадь поделить на кол-во путей)\n" +
                "[7] -> Реализовать вывод таблицы <PASSENGER> с объединением данных фамилии, имени и отчества в одну колонку\n" +
                "[8] -> Выполнить отсортированный вывод таблицы <TRIP> по времени\n" +
                "[9] -> Вывести информацию о поездах изз таблицы <TRAIN>, у которых тип топлива <Газ> или <Электричество>\n" +
                "[10] -> Вывести вагоны, количество мест которых от 30 до 50 в таблицы <RAILWAY_CARRIAGE>\n" +
                "[11] -> Вывести информацию о городах, которые входят в Могилевскую и Брестскую область исходя данных таблицы <CITY>\n" +
                "[12] -> Соединить связанные данные таблиц <CITY> и <STATION>\n" +
                "[13] -> Вывести всех пассажиров из таблицы <PASSENGER>, фамилия которых начинается на букву <С>\n" +
                "[14] -> Вывести личные номера проводников, которые задействованы на поездку в месяце феврале и между 8:00:00 и 19:00:00 дня таблицы <TRIP>\n" +
                "[15] -> Выполнить вывод вагон с количеством мест более 40 в порядке убывания таблицы <RAILWAY_CARRIAGE>\n" +
               "[Запросы лабораторной работы %5]\n" +
                "[16] -> Вывести средние значения количества путей и площади, соответствующих колонок <COUNT_PATH> и <SQUARE>, в таблице <STATION>\n" +
                "[17] -> Вывести количество вагонов, у которых количество мест больше, чем 30 в таблицы <RAILWAY_CARRIAGE>\n" +
                "[18] -> Вывести максимальное количество путей и максимальную площадь станции, согласно данным таблицы <STATION>\n" +
                "[19] -> Выполнить минимальный персональный номер, минимальное количество символов в имени и фамилии из всей таблицы, выданный проводнику, согласно таблице <CONDUCTOR>\n" +
                "[20] -> Найти суммарное количество путей и суммарно занимаемую площадь на станциях, согласно таблице <STATION>\n" +
                "[21] -> Выполнить группировку данных таблице <CITY> по областям и вывести количество городов в каждой области\n" +
                "[22] -> Вывести информацию количестве пассажиров, к которым относятся к проводниками в то время как у проводника должно быть больше двух пассажиров. Согласно таблице <PASSENGER>\n" +
                "[23] -> Вывести информацию о проводниках, у которых персональный номер больше среднего значения по всей таблице <CONDUCTOR>\n" +
                "[24] -> Найти медианные значения площади, количества путей согласно таблице <STATION>\n" +
                "[25] -> Вывести всю информацию о поездках после 2023 года марта, согласно данным таблицы <TRIP>\n" +
                "[26] -> Вывести ФИО пассажиров, которые принадлежат проводникам с личным номером от 500000 до 800000, и есть ли фамилия <Лебедев> в исходной таблице <PASSENGER>\n" +
                "[27] -> Вывести информацию о поездках, которые не осуществляются между датами от <2023-03-05> до <2023-09-12>, согласно таблице <TRIP>\n" +
                "[28] -> Вывести данные о вагонах купе, где количество мест больше 20 и информацию о типах <Плацкарт>, количество мест, где больше 20\n" +
                "[29] -> Вывести информацию об поездках, которые прошли в дни кроме месяца март\n" +
                "[30] -> Вывести поезда, в которых количество мест больше 20, но без типа вагона <Плацкарт>\n" +
                "[31] Вернуться назад.\n" +
               "Ваш вариант действия: ";
    }


    //Для строго вывода лабораторной работы №5 №6
    public static void labWorkShowRequest(int choice, ResultSet resultSet) throws SQLException {
        switch (choice) {
            case 2: {
                while (resultSet.next()) {
                    String STATION_DEPARTURE = resultSet.getString("STATION_DEPARTURE");
                    String STATION_ARRIVAL = resultSet.getString("STATION_ARRIVAL");
                    System.out.printf("%-20s %20s%n", STATION_ARRIVAL, STATION_DEPARTURE);
                }
            }
            break;

            case 3: {
                while (resultSet.next()) {
                    int RAILWAY_CARRIAGE_ID = resultSet.getInt("RAILWAY_CARRIAGE_ID");
                    int PLACE = resultSet.getInt("PLACE");
                    int NUMBER_CARRIAGE = resultSet.getInt("NUMBER_CARRIAGE");
                    String TYPE_CARRIAGE = resultSet.getString("TYPE_CARRIAGE");
                    int TRAIN_ID_FK = resultSet.getInt("TRAIN_ID_FK");
                    System.out.printf("%-10d %10d %15d %10s %10d%n", RAILWAY_CARRIAGE_ID, PLACE, NUMBER_CARRIAGE, TYPE_CARRIAGE, TRAIN_ID_FK);
                }
            }
            break;
            case 4: {
                while (resultSet.next()) {
                    String NAME = resultSet.getString("NAME");
                    System.out.println(NAME);
                }
            }
            break;
            case 5: {
                while (resultSet.next()) {
                    String NUMBER_PASSPORT_FK_PK = resultSet.getString("fast");
                    String TRAIN_ID_PK_FK = resultSet.getString("TRAIN_ID_PK_FK");
                    System.out.printf("%-10s %15s%n", NUMBER_PASSPORT_FK_PK, TRAIN_ID_PK_FK);
                }
            }
            break;
            case 6: {
                while (resultSet.next()) {
                    int STATION_ID = resultSet.getInt("STATION_ID");
                    String STATION_NAME = resultSet.getString("STATION_NAME");
                    int COUNT_PATH = resultSet.getInt("COUNT_PATH");
                    float SQUARE = resultSet.getFloat("SQUARE");
                    String CITY_NAME_FK = resultSet.getString("CITY_NAME_FK");
                    int PATH_SQUARE = resultSet.getInt("PATH_SQUARE");
                    System.out.printf("%-10d %25s %10d %15f %15s %15d%n", STATION_ID, STATION_NAME, COUNT_PATH, SQUARE, CITY_NAME_FK, PATH_SQUARE);
                }
            }
            break;
            case 7: {
                while (resultSet.next()) {
                    String NUMBER_PASSPOER = resultSet.getString("NUMBER_PASSPOER");
                    int PERSONAL_NUMBER_FK = resultSet.getInt("PERSONAL_NUMBER_FK");
                    String FIO = resultSet.getString("FIO");
                    System.out.printf("%-15s %20d %40s%n", NUMBER_PASSPOER, PERSONAL_NUMBER_FK, FIO);
                }
            }
            break;
            case 8: {
                while (resultSet.next()) {
                    int TRAIN_ID_PK_FK = resultSet.getInt("TRAIN_ID_PK_FK");
                    int RAILWAY_CARRIAGE_ID_PK_FK = resultSet.getInt("RAILWAY_CARRIAGE_ID_PK_FK");
                    int PERSONAL_NUMBER_PK_FK = resultSet.getInt("PERSONAL_NUMBER_PK_FK");
                    String DATA = resultSet.getDate("DATA").toString();
                    String TIME = resultSet.getTime("TIME").toString();
                    System.out.printf("%-5d %10d %10d %15s %15s%n", TRAIN_ID_PK_FK, RAILWAY_CARRIAGE_ID_PK_FK, PERSONAL_NUMBER_PK_FK, DATA, TIME);
                }
            }
            break;
            case 9: {
                while (resultSet.next()) {
                    int TRAIN_ID = resultSet.getInt("TRAIN_ID");
                    String STATION_DEPARTURE = resultSet.getString("STATION_DEPARTURE");
                    String STATION_ARRIVAL = resultSet.getString("STATION_ARRIVAL");
                    String FUEL_TYPE = resultSet.getString("FUEL_TYPE");
                    int STATION_ID_FK = resultSet.getInt("STATION_ID_FK");
                    System.out.printf("%-10d %20s %20s %15s %10d%n", TRAIN_ID, STATION_DEPARTURE, STATION_ARRIVAL, FUEL_TYPE, STATION_ID_FK);
                }
            }
            break;
            case 11: {
                while (resultSet.next()) {
                    String CITY_NAME = resultSet.getString("CITY_NAME");
                    String REGION = resultSet.getString("REGION");
                    String COUNTRY = resultSet.getString("COUNTRY");
                    System.out.printf("%-15s %20s %20s%n", CITY_NAME, REGION, COUNTRY);
                }
            }
            break;
            case 12: {
                while (resultSet.next()) {
                    int STATION_ID = resultSet.getInt("STATION_ID");
                    String STATION_NAME = resultSet.getString("STATION_NAME");
                    int COUNT_PATH = resultSet.getInt("COUNT_PATH");
                    float SQUARE = resultSet.getFloat("SQUARE");
                    String CITY_NAME_FK = resultSet.getString("CITY_NAME_FK");
                    System.out.printf("%-10d %25s %10d %15f %15s%n", STATION_ID, STATION_NAME, COUNT_PATH, SQUARE, CITY_NAME_FK);
                }
            }break;
            case 13:{
                while (resultSet.next()){
                    String NUMBER_PASSPORT = resultSet.getString("NUMBER_PASSPOER");
                    String SURNAME = resultSet.getString("SURNAME");
                    String NAME = resultSet.getString("NAME");
                    String MIDDLE_NAME = resultSet.getString("MIDDLE_NAME");
                    int PERSONAL_NUMBER_FK = resultSet.getInt("PERSONAL_NUMBER_FK");
                    System.out.printf("%-15s %15s %15s %15s %15d%n", NUMBER_PASSPORT, SURNAME, NAME,  MIDDLE_NAME, PERSONAL_NUMBER_FK);
                }
            }break;
            case 14:{
                while (resultSet.next()){
                    int PERSONAL_NUMBER_PK_FK = resultSet.getInt("PERSONAL_NUMBER_PK_FK");
                    System.out.println(PERSONAL_NUMBER_PK_FK);
                }
            }
            case 16:{
                while (resultSet.next()){
                    int count_path_average = resultSet.getInt("count_path_average");
                    int square_average = resultSet.getInt("square_average");
                    System.out.printf("%-15d %15d%n", count_path_average, square_average);
                }
            }break;
            case 17:{
                resultSet.next();
                System.out.println(resultSet.getInt("count"));
            }break;
            case 18: {
                resultSet.next();
                System.out.println(resultSet.getInt("MAX_COUNT_PATH") + "         " + resultSet.getInt("MAX_SQUARE"));
            }break;
            case 19: {
                resultSet.next();
                System.out.println(resultSet.getInt("MIN_PERSONAL_NUMBER") + "      " + resultSet.getInt("MIN_LENGTH_NAME") + "        " + resultSet.getInt("MIN_LENGTH_SURNAME"));
            }break;
            case 20:{
                resultSet.next();
                System.out.println(resultSet.getInt("SUM_SQUARE") + "        " + resultSet.getInt("SUM_COUNT_PATH"));
            }break;
            case 21:{
                while(resultSet.next()){
                    String REGION = resultSet.getString("REGION");
                    int count = resultSet.getInt("count");
                    System.out.printf("%-30s %15d%n", REGION, count);
                }
            }break;
            case 22:{
                while (resultSet.next()){
                    int PERSONAL_NUMBER_FK = resultSet.getInt("PERSONAL_NUMBER_FK");
                    int count = resultSet.getInt("count");
                    System.out.printf("%-15d %15d%n", PERSONAL_NUMBER_FK, count);
                }
            }break;
            case 23:{
                while (resultSet.next()){
                    int PERSONAL_NUMBER = resultSet.getInt("PERSONAL_NUMBER");
                    String SURNAME = resultSet.getString("SURNAME");
                    String NAME = resultSet.getString("NAME");
                    String MIDDLE_NAME = resultSet.getString("MIDDLE_NAME");
                    System.out.printf("%-15d %20s %20s %20s%n", PERSONAL_NUMBER, SURNAME, NAME, MIDDLE_NAME);
                }
            }break;
            case 24:{
                while (resultSet.next()){
                    int MEDIAN_PATH = resultSet.getInt("MEDIAN_PATH");
                    float MEDIAN_SQUARE = resultSet.getFloat("MEDIAN_SQUARE");
                    System.out.println(MEDIAN_SQUARE + "             " + MEDIAN_PATH);
                }
            }break;
            case 25:{

            }break;
        }


    }
    public static void labWorkRequest(int choice, Statement statement) throws SQLException {
        ResultSet result = null;
        if(choice == 1){
            showTableData(statement, "CITY");
        }
        else if(choice == 2){
            result = statement.executeQuery("SELECT \"STATION_DEPARTURE\", \"STATION_ARRIVAL\" FROM \"" + schemaName +"\".\"TRAIN\"");
        }
        else if(choice == 3){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"RAILWAY_CARRIAGE\" WHERE \"PLACE\">30;");
        }
        else if(choice == 4){
            result = statement.executeQuery("SELECT DISTINCT \"NAME\" FROM \"" + schemaName + "\".\"CONDUCTOR\";");
        }
        else if(choice == 5){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"PASSENGER&TRAIN\" OPTION(FAST) LIMIT 10");
        }
        else if(choice == 6){
            result = statement.executeQuery("SELECT *, \"SQUARE\"/\"COUNT_PATH\" AS \"PATH_SQUARE\" FROM \"" + schemaName + "\".\"STATION\";");
        }
        else if(choice == 7){
            result = statement.executeQuery("SELECT \"NUMBER_PASSPOER\", \"PERSONAL_NUMBER_FK\", CONCAT(\"SURNAME\", \'  \', \"NAME\", \' \', \"MIDDLE_NAME\") AS \"FIO\" FROM \"" + schemaName + "\".\"PASSENGER\";");
        }
        else if(choice == 8){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"TRIP\" ORDER BY \"TIME\";");
        }
        else if(choice == 9){
            result = statement.executeQuery("SELECT * FROM \""+schemaName + "\".\"TRAIN\" WHERE (\"FUEL_TYPE\" = \'Газ\' OR \"FUEL_TYPE\" = \'Электричество\');");
        }
        else if(choice == 10){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"RAILWAY_CARRIAGE\" WHERE \"PLACE\" BETWEEN 30 AND 50;");
            choice = 3;
        }
        else if(choice == 11){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"CITY\" WHERE \"REGION\" IN (\'Могилевская область\', \'Брестская область\');");
        }
        else if(choice == 12){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"STATION\" INNER JOIN \"" + schemaName + "\".\"CITY\" ON \"STATION\".\"CITY_NAME_FK\" = \"CITY\".\"CITY_NAME\"; ");
        }
        else if(choice == 13){
            result = statement.executeQuery("SELECT * FROM \""+ schemaName + "\".\"PASSENGER\" WHERE \"SURNAME\" LIKE \'С%\';");
        }
        else if(choice == 14){
            result = statement.executeQuery("SELECT \"PERSONAL_NUMBER_PK_FK\" FROM \"" + schemaName + "\".\"TRIP\" WHERE EXTRACT(MONTH FROM \"DATA\") = 2 AND \"TIME\" BETWEEN \'8:00:00\' AND \'19:00:00\';");
        }
        else if(choice == 15){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"RAILWAY_CARRIAGE\" WHERE \"PLACE\" > 40 ORDER BY \"PLACE\" DESC;");
            choice = 3;
        }
        else if(choice == 16){
            result = statement.executeQuery("SELECT AVG(\"COUNT_PATH\") AS \"count_path_average\", AVG(\"SQUARE\") AS \"square_average\" FROM \"" + schemaName + "\".\"STATION\";");
        }
        else if(choice == 17){
            result = statement.executeQuery("SELECT COUNT(*) FROM \"" + schemaName + "\".\"RAILWAY_CARRIAGE\" WHERE \"PLACE\" > 30;");
        }
        else if(choice == 18){
            result = statement.executeQuery("SELECT MAX(\"COUNT_PATH\") AS \"MAX_COUNT_PATH\", MAX(\"SQUARE\") AS \"MAX_SQUARE\" FROM \"" + schemaName + "\".\"STATION\";");
        }
        else if(choice == 19){
            result = statement.executeQuery("SELECT MIN(\"PERSONAL_NUMBER\") AS \"MIN_PERSONAL_NUMBER\", MIN(LENGTH(\"NAME\")) AS \"MIN_LENGTH_NAME\", MIN(LENGTH(\"SURNAME\")) AS \"MIN_LENGTH_SURNAME\" FROM \"" + schemaName + "\".\"CONDUCTOR\";");
        }
        else if(choice == 20){
            result = statement.executeQuery("SELECT SUM(\"SQUARE\") AS \"SUM_SQUARE\", SUM(\"COUNT_PATH\") AS \"SUM_COUNT_PATH\" FROM \"" + schemaName + "\".\"STATION\";" );
        }
        else if(choice == 21){
            result = statement.executeQuery("SELECT \"REGION\", COUNT(DISTINCT \"CITY_NAME\") FROM \""+ schemaName + "\".\"CITY\" GROUP BY \"REGION\";");
        }
        else if(choice == 22){
            result = statement.executeQuery("SELECT \"PERSONAL_NUMBER_FK\", COUNT(*) FROM \"" + schemaName + "\".\"PASSENGER\" GROUP BY \"PERSONAL_NUMBER_FK\" HAVING COUNT(*) > 2;");
        }
        else if(choice == 23){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"CONDUCTOR\" WHERE \"PERSONAL_NUMBER\" > (SELECT AVG(\"PERSONAL_NUMBER\") FROM \"" + schemaName + "\".\"CONDUCTOR\");");
        }
        else if(choice == 24){
            result = statement.executeQuery("SELECT percentile_cont(0.5) WITHIN GROUP (ORDER BY \"COUNT_PATH\") AS \"MEDIAN_PATH\", percentile_cont(0.5) WITHIN GROUP (ORDER BY \"SQUARE\") AS \"MEDIAN_SQUARE\" FROM \""+ schemaName + "\".\"STATION\";");
        }
        else if(choice == 25){
            result = statement.executeQuery("SELECT * FROM \""+ schemaName + "\".\"TRIP\" WHERE \"DATA\" = ANY (SELECT \"DATA\" FROM \"" +schemaName + "\".\"TRIP\" WHERE \"DATA\" > \'2023-04-04\');");
            choice = 8;
        }
        else if(choice == 26){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"PASSENGER\" WHERE \"PERSONAL_NUMBER_FK\" BETWEEN 500000 AND 800000 AND EXISTS(SELECT * FROM \"" + schemaName + "\".\"PASSENGER\" WHERE \"SURNAME\" LIKE \'Лебедев\');");
            choice = 13;
        }
        else if(choice == 27){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"TRIP\" WHERE \"DATA\" <> ALL (SELECT \"DATA\" FROM \"" +schemaName + "\".\"TRIP\" WHERE \"DATA\" BETWEEN \'2023-03-05\' AND \'2023-09-12\');");
            choice = 8;
        }
        else if(choice == 28){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"RAILWAY_CARRIAGE\" WHERE \"TYPE_CARRIAGE\" = \'Плацкарт\' AND\"PLACE\" > 25 UNION SELECT * FROM \""+ schemaName + "\".\"RAILWAY_CARRIAGE\" WHERE \"TYPE_CARRIAGE\" LIKE \'Купе\' AND \"PLACE\" > 20");
            choice = 3;
        }
        else if(choice == 29){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"TRIP\" INTERSECT SELECT * FROM \"" + schemaName + "\".\"TRIP\" WHERE \"DATA\" NOT BETWEEN \'2023-03-01\' AND \'2023-04-01\';");
            choice = 8;
        }
        else if(choice == 30){
            result = statement.executeQuery("SELECT * FROM \"" + schemaName + "\".\"RAILWAY_CARRIAGE\" WHERE \"PLACE\" > 20 EXCEPT SELECT * FROM \"" + schemaName + "\".\"RAILWAY_CARRIAGE\" WHERE \"TYPE_CARRIAGE\" LIKE \'Плацкарт\';");
            choice = 3;
        }
        labWorkShowRequest(choice, result);
    }

    public static void deleteRowTable(Statement statement, String tableName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        if(Objects.equals(tableName, "CITY")){
            System.out.print("Введите название города для удаления: ");
            String cityName = scanner.nextLine();
            statement.executeUpdate("DELETE FROM \"" + schemaName + "\".\"" + tableName + "\" WHERE \"CITY_NAME\" = \'" + cityName + "\';");
        }
        else if(Objects.equals(tableName, "CONDUCTOR")){
            System.out.print("Введите персональный номер проводника: ");
            int personalNumber = scanner.nextInt();
            statement.executeUpdate("DELETE FROM \"" + schemaName + "\".\"" + tableName + "\" WHERE \"PERSONAL_NUMBER\" = " + personalNumber + ";");
        }
        else if(Objects.equals(tableName, "PASSENGER")){
            System.out.print("Введите номер паспорта: ");
            String numberPassport = scanner.nextLine();
            statement.executeUpdate("DELETE FROM \"" + schemaName + "\".\"" + tableName + "\" WHERE \"NUMBER_PASSPOER\" = \'" + numberPassport + "\';");
        }
        else if(Objects.equals(tableName, "PASSENGER&TRAIN")){
            System.out.print("Введите номер паспорта: ");
            String numberPassport = scanner.nextLine();
            statement.executeUpdate("DELETE FROM \"" + schemaName + "\".\"" + tableName + "\" WHERE \"NUMBER_PASSPORT_FK_PK\" = \'" + numberPassport + "\';");
        }
        else if(Objects.equals(tableName, "RAILWAY_CARRIAGE")){
            System.out.print("Введите номер вагона: ");
            int numberRailwayCarriage = scanner.nextInt();
            statement.executeUpdate("DELETE FROM \"" + schemaName + "\".\"" + tableName + "\" WHERE \"RAILWAY_CARRIAGE_ID\" = " + numberRailwayCarriage + ";");
        }
        else if(Objects.equals(tableName, "STATION")){
            System.out.print("Введите номер станции: ");
            int numberStation = scanner.nextInt();
            statement.executeUpdate("DELETE FROM \"" + schemaName + "\".\"" + tableName +"\" WHERE \"STATION_ID\" = " + numberStation + ";");
        }
        else if(Objects.equals(tableName, "TRAIN")){
            System.out.print("Введите номер поезда: ");
            int numberTrain = scanner.nextInt();
            statement.executeUpdate("DELETE FROM \"" + schemaName + "\".\"" + tableName + "\" WHERE \"TRAIN_ID\" = " + numberTrain + ";");
        }
        else if(Objects.equals(tableName, "TRIP")){
            System.out.print("Введите номера поезда: ");
            int numberTrain = scanner.nextInt();
            statement.executeUpdate("DELETE FROM \"" + schemaName + "\".\"" + tableName + "\" WHERE \"TRAIN_ID\" = " + numberTrain + ";");
        }
    }

    public static void changeRowsInformation(Statement statement, String tableName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        if(Objects.equals(tableName, "CITY")){
            System.out.print("Введите название города: ");
            String cityName = scanner.nextLine();
            System.out.print("Введите новый регион: ");
            String REGION = scanner.nextLine();
            System.out.print("Введите новую страну: ");
            String COUNTRY = scanner.nextLine();
            statement.executeUpdate("UPDATE \"" + schemaName + "\".\"" + tableName + "\" SET \"REGION\" = \'" + REGION + "\', \"COUNTRY\" = \'" + COUNTRY + "\' WHERE \"" + "CITY_NAME" + "\" = \'" + cityName + "\';");
        }
        else if(Objects.equals(tableName, "CONDUCTOR")){
            System.out.print("Введите персональный номер проводника: ");
            int personalNumber = scanner.nextInt();
            System.out.print("Введите имя:");
            String NAME = scanner.nextLine();
            System.out.print("Введите фамилию: ");
            String SURNAME = scanner.nextLine();
            System.out.print("Введите отчество: ");
            String MIDDLE_NAME = scanner.nextLine();
            statement.executeUpdate("UPDATE \"" + schemaName + "\".\"" + tableName + "\" SET \"NAME\" = \'" + NAME + "\', SET \"SURNAME\" = \'" + SURNAME + "\' SET \"MIDDLE_NAME\" = \'" + MIDDLE_NAME + "\' WHERE \"" + "PESONAL_NUMBER" + "\" = " + personalNumber + ";");
        }
        else if(Objects.equals(tableName, "PASSENGER")){
            System.out.print("Введите номер паспорта: ");
            String numberPassport = scanner.nextLine();
            System.out.print("Введите имя:");
            String NAME = scanner.nextLine();
            System.out.print("Введите фамилию: ");
            String SURNAME = scanner.nextLine();
            System.out.print("Введите отчество: ");
            String MIDDLE_NAME = scanner.nextLine();
            System.out.print("Введите персональный номер проводника: ");
            int PERSONAL_NUMBER_FK = scanner.nextInt();
            statement.executeUpdate("UPDATE \"" +schemaName + "\".\"" + tableName + "\" SET \"SURNAME\" = \'" + SURNAME + "\', SET \"NAME\" = \'" + NAME + "\', SET \"MIDDLE_NAME\" = \'" + MIDDLE_NAME + "\', SET \"PERSONAL_NUMBER_FK\" = " + PERSONAL_NUMBER_FK + " WHERE \"NUMBER_PASSPOER\" = \'" + numberPassport + "\';");

        }
        else if(Objects.equals(tableName, "PASSENGER&TRAIN")){
            System.out.print("Введите номер паспорта: ");
            String numberPassport = scanner.nextLine();
            System.out.print("Введите номер вагона: ");
            int trainId = scanner.nextInt();
            statement.executeUpdate("UPDATE \"" + schemaName + "\".\"PASSENGER&TRAIN\" SET \"TRAIN_ID_PK_FK\" = " + trainId +" WHERE \"NUMBER_PASSPORT_FK_PK\" = \'" + numberPassport + "\';" );
        }
        else if(Objects.equals(tableName, "RAILWAY_CARRIAGE")){
            System.out.print("Введите номер вагона: ");
            int numberRailwayCarriage = scanner.nextInt();
            System.out.print("Введите количество мест");
            int PLACE = scanner.nextInt();
            System.out.print("Введите номер вагона: ");
            int number_carriage = scanner.nextInt();
            System.out.print("Введите тип вагона: ");
            String typeCarriage = scanner.nextLine();
            System.out.print("Введите номер поезда к которому он относится: ");
            int numberTrain = scanner.nextInt();
            statement.executeUpdate("UPDATE \"" + schemaName + "\".\"" + tableName + "\" SET \"PLACE\" = " + PLACE + " ,SET \"NUMBER_CARRIAGE\" = " + number_carriage + " ,SET \"TYPE_CARRIAGE\" = \'" + typeCarriage + "\' ,SET \"TRAIN_ID_FK\" = " + numberTrain + " WHERE \"RAILWAY_CARRIAGE_ID\" = " + numberRailwayCarriage +";");
        }
        else if(Objects.equals(tableName, "STATION")){
            System.out.print("Введите номер станции: ");
            int numberStation = scanner.nextInt();
            System.out.print("Введите название станции: ");
            String stationName = scanner.nextLine();
            System.out.print("Введите количество путей: ");
            int countPath = scanner.nextInt();
            System.out.print("Введите площадь станции: ");
            float square = scanner.nextFloat();
            System.out.print("Введите город: ");
            String cityName = scanner.nextLine();
            statement.executeUpdate("UPDATE \"" + schemaName + "\".\"" + tableName + "\" SET \"STATION_NAME\" = \'" + stationName + "\', SET \"COUNT_PATH\" = " + countPath + ", SET \"SQUARE\" = " + square +" ,SET \"CITY_NAME_FK\" = \'" + cityName + "\' WHERE \"STATION_ID\" = " + numberStation + ";");
        }
        else if(Objects.equals(tableName, "TRAIN")){
            System.out.print("Введите номер поезда: ");
            int numberTrain = scanner.nextInt();
            System.out.print("Введите станцию отправления: ");
            String stationDeparture = scanner.nextLine();
            System.out.println("Введите станцию прибытия: ");
            String stationArrival = scanner.nextLine();
            System.out.print("Введите тип топлива: ");
            String fuelType = scanner.nextLine();
            System.out.println("Введите станцию: ");
            int stationId = scanner.nextInt();
            statement.executeUpdate("UPDATE \"" + schemaName + "\".\""+ tableName + "\" SET \"STATION_DEPARTURE\" = \'" + stationDeparture + "\', SET \"STATION_ARRIVAL\" = \'" + stationArrival + "\', SET \"FUEL_TYPE\" = \'" + fuelType + "\', SET \"STATION_ID_FK\" = " + stationId + " WHERE \"TRAIN_ID\" = " + numberTrain + ";");
        }
        else if(Objects.equals(tableName, "TRIP")){
            System.out.print("Введите номера поезда: ");
            int numberTrain = scanner.nextInt();
            System.out.print("Введите дату поездки");
            String data = scanner.nextLine();
            System.out.print("Введите время поездки:");
            String time = scanner.nextLine();
            statement.executeUpdate("UPDATE \"" + schemaName + "\".\"" + tableName + "\" SET \"DATA\" = \'" + data + "\', SET \"TIME\" = \'" + time + "\' WHERE \"TRAIN_ID_PK_FK\" = " +numberTrain +";");
        }
    }
    public static void addRowsInTable(Statement statement, String tableName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        if(Objects.equals(tableName, "CITY")){
            System.out.print("Введите название нового города: ");
            String cityName = scanner.nextLine();
            System.out.print("Введите новый регион: ");
            String REGION = scanner.nextLine();
            System.out.print("Введите новую страну: ");
            String COUNTRY = scanner.nextLine();
            statement.executeUpdate("INSERT INTO \"" + schemaName + "\".\"" + tableName + "\" (\"CITY_NAME\", \"REGION\", \"COUNTRY\") VALUES (\'" + cityName + "\', \'" + REGION + "\', \'" + COUNTRY + "\');");
        }
        else if(Objects.equals(tableName, "CONDUCTOR")){
            System.out.print("Введите персональный номер проводника: ");
            int personalNumber = scanner.nextInt();
            System.out.print("Введите имя:");
            String NAME = scanner.nextLine();
            System.out.print("Введите фамилию: ");
            String SURNAME = scanner.nextLine();
            System.out.print("Введите отчество: ");
            String MIDDLE_NAME = scanner.nextLine();
            statement.executeUpdate("INSERT INTO \"" + schemaName + "\".\"" + tableName + "\" (\"PERSONAL_NUMBER\", \"SURNAME\", \"NAME\", \"MIDDLE_NAME\") VALUES (" + personalNumber + ", \'" + SURNAME + "\', \'" + NAME + "\', \'" + MIDDLE_NAME + "\');");
        }
        else if(Objects.equals(tableName, "PASSENGER")){
            System.out.print("Введите номер паспорта: ");
            String numberPassport = scanner.nextLine();
            System.out.print("Введите имя:");
            String NAME = scanner.nextLine();
            System.out.print("Введите фамилию: ");
            String SURNAME = scanner.nextLine();
            System.out.print("Введите отчество: ");
            String MIDDLE_NAME = scanner.nextLine();
            System.out.print("Введите персональный номер проводника: ");
            int PERSONAL_NUMBER_FK = scanner.nextInt();
            statement.executeUpdate("INSERT INTO \"" + schemaName + "\".\"" + tableName + "\" (\"NUMBER_PASSPOER\", \"SURNAME\", \"NAME\", \"MIDDLE_NAME\", \"PERSONAL_NUMBER_FK\") VALUES (\'" + numberPassport + "\', \'" + SURNAME + "\', \'" + NAME + "\', \'" + MIDDLE_NAME + "\', " + PERSONAL_NUMBER_FK + ");");

        }
        else if(Objects.equals(tableName, "PASSENGER&TRAIN")){
            System.out.print("Введите номер паспорта: ");
            String numberPassport = scanner.nextLine();
            System.out.print("Введите номер вагона: ");
            int trainId = scanner.nextInt();
            statement.executeUpdate("INSERT INTO \"" + schemaName + "\".\"" + tableName + "\" (\"NUMBER_PASSPORT_FK_PK\", \"TRAIN_ID_PK_FK\") VALUES (\'" + numberPassport + "\', " + trainId + ");");
        }
        else if(Objects.equals(tableName, "RAILWAY_CARRIAGE")){
            System.out.print("Введите номер вагона: ");
            int numberRailwayCarriage = scanner.nextInt();
            System.out.print("Введите количество мест");
            int PLACE = scanner.nextInt();
            System.out.print("Введите номер вагона: ");
            int number_carriage = scanner.nextInt();
            System.out.print("Введите тип вагона: ");
            String typeCarriage = scanner.nextLine();
            System.out.print("Введите номер поезда к которому он относится: ");
            int numberTrain = scanner.nextInt();
            statement.executeUpdate("INSERT INTO \"" + schemaName + "\".\"" + tableName + "\" (\"RAILWAY_CARRIAGE_ID\", \"PLACE\", \"NUMBER_CARRIAGE\", \"TYPE_CARRIAGE\", \"TRAIN_ID_FK\") VALUES (" + numberRailwayCarriage + ", " + PLACE + ", " + number_carriage + ", \'" + typeCarriage + "\', " + numberTrain + ");");
        }
        else if(Objects.equals(tableName, "STATION")){
            System.out.print("Введите номер станции: ");
            int numberStation = scanner.nextInt();
            System.out.print("Введите название станции: ");
            String stationName = scanner.nextLine();
            System.out.print("Введите количество путей: ");
            int countPath = scanner.nextInt();
            System.out.print("Введите площадь станции: ");
            float square = scanner.nextFloat();
            System.out.print("Введите город: ");
            String cityName = scanner.nextLine();
            statement.executeUpdate("INSERT INTO \"" + schemaName + "\".\"" + tableName + "\" (\"STATION_ID\", \"COUNT_PATH\", \"SQUARE\", \"STATION_NAME\", \"CITY_NAME_FK\") VALUES (" + numberStation + ", " + countPath + ", " + square + ", \'" + stationName + "\', \'" + cityName + "\');");
        }
        else if(Objects.equals(tableName, "TRAIN")){
            System.out.print("Введите номер поезда: ");
            int numberTrain = scanner.nextInt();
            System.out.print("Введите станцию отправления: ");
            String stationDeparture = scanner.nextLine();
            System.out.println("Введите станцию прибытия: ");
            String stationArrival = scanner.nextLine();
            System.out.print("Введите тип топлива: ");
            String fuelType = scanner.nextLine();
            System.out.println("Введите станцию: ");
            int stationId = scanner.nextInt();
            statement.executeUpdate("INSERT INTO \"" + schemaName + "\".\"" + tableName + "\" (\"TRAIN_ID\", \"STATION_DEPARTURE\", \"STATION_ARRIVAL\", \"FUEL_TYPE\", \"STATION_ID_FK\") VALUES (" + numberTrain + ", \'" + stationDeparture + "\', \'" + stationArrival + "\', \'" + fuelType + "\', "+ stationId + ");");
        }
        else if(Objects.equals(tableName, "TRIP")){
            System.out.print("Введите номера поезда: ");
            int numberTrain = scanner.nextInt();
            System.out.print("Введите дату поездки");
            String data = scanner.nextLine();
            System.out.print("Введите время поездки:");
            String time = scanner.nextLine();
            System.out.print("Введите ID поезда: ");
            int trainID = scanner.nextInt();
            System.out.print("Введите ID вагона: ");
            int railwayID = scanner.nextInt();
            System.out.print("Введите персональный номер: ");
            int personalnumber = scanner.nextInt();
            statement.executeUpdate("INSERT INTO \"" + schemaName + "\".\"" + tableName + "\" (\"TRAIN_ID_PK_FK\", \"RAILWAY_CARRIAGE_ID_PK_FK\", \"PERSONAL_NUMBER_PK_FK\", \"DATA\", \"TIME\") VALUES (" +trainID+", " + railwayID + ", " + personalnumber+", \'"+ data +"\', \'" +time +  "\');");
        }
    }
}
