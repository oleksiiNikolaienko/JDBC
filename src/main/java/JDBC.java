import java.sql.*;
import java.util.Scanner;

public class JDBC {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_db";

    public static void main(String[] args) throws Exception{
        //создали объект, который считывает информацию с консоли
        Scanner scanner = new Scanner(System.in);
        // создали подключение
        Connection connection = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);

        // бесконечный вывод меню
        while (true) {
            System.out.println("1. Показать список всех задач");
            System.out.println("2. Выполнить задачу");
            System.out.println("3. Создать задачу");
            System.out.println("4. Выход");
            // считываем команды пользователя
            int command = scanner.nextInt();

            if (command == 1) {
                //объект, который умеет отправлять запросы в бд
                Statement statement = connection.createStatement();
                String SQL_SELECT_TASKS = "select * from task order by id";
                // объект, который хранит результат выполнения запроса
                ResultSet result = statement.executeQuery(SQL_SELECT_TASKS);
                // просматриваем все данные, которые вернулись из БД ы выводим их на экран
                while (result.next()) {
                    System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getString("state"));
                }
            } else if (command == 2) {
                // описываем запрос, не зная, какие параметры там будут
                String sql = "update task set state = 'DONE' where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                System.out.println("Введите идентификатор задачи:");
                int taskId = scanner.nextInt();
                // кладем параметр, который мы считали с консоли в строку запроса
                preparedStatement.setInt(1, taskId);
                preparedStatement.execute();
            } else if (command == 3) {
                String sql = "insert into task (name, state) values (?,'IN_PROCESS');";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                System.out.println("Введите название задачи:");
                scanner.nextLine();
                String taskName = scanner.nextLine();
                preparedStatement.setString(1, taskName);
                preparedStatement.execute();
            } else if (command == 4) {
                System.exit(0);
            } else {
                System.err.println("Команда не распознана");
            }

        }


    }
}
