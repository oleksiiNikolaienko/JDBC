package JDBC;

import java.sql.*;
import java.util.Scanner;

public class JDBC {

    private static final String DB_USERNAME ="postgres";
    private static final String DB_PASSWORD ="postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_db";

    public static void main(String[] args) throws Exception{
        //создали объект, который считывает информацию с консоли
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);


        while (true) {
            System.out.println("1. Показать список всех задач");
            System.out.println("2. Выполнить задачу");
            System.out.println("3. Создать задачу");
            System.out.println("4. Выход");

            int command = scanner.nextInt();

            if (command == 1) {
                //объект, который умеет отправлять запросы в бд
                Statement statement = connection.createStatement();
                String SQL_SELECT_TASKS = "select * from task";
                // объект, который хранит результат выполнения запроса
                ResultSet result = statement.executeQuery(SQL_SELECT_TASKS);

                while (result.next()) {
                    System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getString("state"));
                }
            } else if (command == 4) {
                System.exit(0);
            } else {
                System.err.println("Команда не распознана");
            }

        }


    }
}
