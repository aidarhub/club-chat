package my.important.job;

import my.important.job.dao.MessageDAOImpl;
import my.important.job.dao.UserDAOImpl;

import java.sql.SQLException;

public class Main {

    private Main() { }
    public static void main(String[] args) throws SQLException, InterruptedException {
        MessageDAOImpl dao = new MessageDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.createTable();
        dao.createTable();

    }
}