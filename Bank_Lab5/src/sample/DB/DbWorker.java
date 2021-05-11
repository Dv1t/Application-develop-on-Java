package sample.DB;


import sample.Account;
import sample.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbWorker {
    private static DbWorker instance;
    private DbConnection dbConnection;
    private Connection connection;

    private DbWorker(){
        dbConnection = new DbConnection();
        connection = dbConnection.getConnection();
    }

    public static DbWorker getInstance(){
        if (instance == null){
            instance = new DbWorker();
        }
        return instance;
    }

    public void insertUser(User user) throws SQLException {
        String sqlQuery = "INSERT INTO users(id, surname, first_name, second_name,accont) VALUES ";

        sqlQuery += "(" + user.getId() + ",'" + user.getSurname() + "','" + user.getName() +
                "','" + user.accounts.toString()+"'),";

        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + ";";
        System.out.println(sqlQuery);

        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);

        statement.close();
    }

    public void updateUser(User user) throws SQLException {
        String sqlQuery = "UPDATE users SET " +
                "surname = '" + user.getSurname() + "'," +
                "first_name = '" + user.getName() + "'," +
                "account = '" + user.accounts.toString()
                + "' WHERE id = " + user.getId();

        System.out.println(sqlQuery);

        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);

        statement.close();
    }

    public List<User> loadUsers() throws SQLException, IOException, ClassNotFoundException {
        List<User>users = new ArrayList<User>();
        String sql = "SELECT * FROM users";

        System.out.println(sql);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next())
        {
            String firstName = rs.getString("first_name");
            String surname = rs.getString("surname");
            List<Account> accountList =(List<Account>) new ObjectInputStream(new ByteArrayInputStream(
                    rs.getString("account").getBytes(StandardCharsets.UTF_8))).readObject();
            User user = new User(surname, firstName);
            for (Account account:accountList) {
                user.addAccount(account);
            }
            users.add(user);
        }

        sql = "SELECT * FROM accounts";
        System.out.println(sql);
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        statement.close();

        return users;
    }

    public void deleteUser(User user) throws SQLException{
        String sqlQuery = "DELETE FROM users WHERE id = " + user.getId() + ";";

        System.out.println(sqlQuery);

        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);

        statement.close();
    }
}
