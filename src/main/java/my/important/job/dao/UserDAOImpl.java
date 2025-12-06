package my.important.job.dao;

import my.important.job.entity.User;
import my.important.job.util.PoolConnectionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements Dao<User, Integer> {
    private static final String CREATE_TABLE_SQL = """
         create table if not exists users
         (
         id SERIAL PRIMARY KEY,
         login VARCHAR(255) NOT NULL,
         email VARCHAR(255) NOT NULL,
         password VARCHAR(255) NOT NULL
         )
         """;

    private static final String DROP_TABLE = """
            DROP TABLE users if not exists users
            """;

    private static final String FIND_All_MESSAGE = """
            SELECT * FROM users
            """;

    private static final String FIND_BY_ID_MESSAGE = """
            SELECT * FROM users
            WHERE id = ?;
            """;

    private static final String DELETE_BY_ID_MESSAGE = """
            DELETE FROM users
            WHERE id = ?;
            """;

    private static final String INSERT_MESSAGE = """
            INSERT INTO users (id, login, email, password)
            VALUES (?, ?, ?, ?);
            """;

    private static final String UPDATE_MESSAGE = """
            UPDATE users
            SET login = ?, email = ?
            WHERE id = ?;
            """;

    @Override
    public void save(User obj) throws InterruptedException, SQLException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(INSERT_MESSAGE)) {
            statement.execute();
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }

    @Override
    public User findById(Integer index) throws InterruptedException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(FIND_BY_ID_MESSAGE)) {
            statement.setInt(1, index);
            var execute = statement.executeQuery();

            if (execute.next()) {
                var user = new User();
                var id = execute.getInt("id");
                var login = execute.getString("login");
                var email = execute.getString("email");

                user.setId(id);
                user.setLogin(login);
                user.setEmail(email);

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("SQLException in findById: " + e.getMessage());
            throw new RuntimeException("Error retrieving message by ID " + index, e);
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }

    @Override
    public List<User> findByAll() throws InterruptedException, SQLException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(FIND_All_MESSAGE)) {
            var execute = statement.executeQuery();
            var list = new ArrayList<User>();

            while (execute.next()) {
                var id = execute.getInt("id");
                var login = execute.getString("login");
                var email = execute.getString("email");

                var user = new User();
                user.setId(id);
                user.setLogin(login);
                user.setEmail(email);

                list.add(user);
            }

            return list;
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }

    @Override
    public void update(User obj) throws InterruptedException, SQLException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(UPDATE_MESSAGE)) {
            statement.setString(2, obj.getLogin());
            statement.setString(3, obj.getEmail());

            var execute = statement.executeQuery();
            var user = new User();

            if (execute.next()) {
                var login = execute.getString("login");
                var email = execute.getString("email");

                user.setLogin(login);
                user.setEmail(email);
            }
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer index) throws InterruptedException, SQLException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(DELETE_BY_ID_MESSAGE)) {
            statement.setInt(1, index);
            statement.execute();
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }

    @Override
    public void createTable() throws SQLException, InterruptedException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(CREATE_TABLE_SQL)) {
            statement.execute();
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }

    @Override
    public void dropTable() throws InterruptedException, SQLException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(DROP_TABLE)) {
            statement.execute();
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }
}
