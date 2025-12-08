package my.important.job.dao;

import my.important.job.entity.Message;
import my.important.job.util.PoolConnectionUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements Dao<Message, Integer> {
    private static final String CREATE_TABLE_SQL = """
         CREATE TABLE IF NOT EXISTS message
         (
         id SERIAL PRIMARY KEY,
         text VARCHAR(255) NOT NULL,
         sender_id INTEGER NOT NULL,
         received_id INTEGER NOT NULL,
         create_time DATE NOT NULL,
         FOREIGN KEY (sender_id) REFERENCES users (id),
         FOREIGN KEY (received_id) REFERENCES users (id)
         )
         """;

    private static final String DROP_TABLE = """
            DROP TABLE message if not exists message
            """;

    private static final String FIND_All_MESSAGE = """
            SELECT * FROM message
            """;

    private static final String FIND_BY_ID_MESSAGE = """
            SELECT * FROM message
            WHERE id = ?;
            """;

    private static final String DELETE_BY_ID_MESSAGE = """
            DELETE FROM message
            WHERE id = ?;
            """;

    private static final String INSERT_MESSAGE = """
            INSERT INTO message (id, text, sender_id, received_id, create_time)
            VALUES (?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_MESSAGE = """
            UPDATE message
            SET
              text = ?,
              sender_id = ?,
              received_id = ?,
              create_time = ?
            WHERE id = ?;
            """;

    @Override
    public void createTable() throws InterruptedException, SQLException {
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

    @Override
    public void save(Message obj) throws InterruptedException, SQLException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(INSERT_MESSAGE)) {
            statement.execute();
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }

    @Override
    public Message findById(Integer index) throws InterruptedException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(FIND_BY_ID_MESSAGE)) {
            statement.setInt(1, index);
            var execute = statement.executeQuery();

            if (execute.next()) {
                var message = new Message();
                var id = execute.getInt("id");
                var text = execute.getString("text");
                var senderId = execute.getInt("senderId");
                var receivedId = execute.getInt("receivedId");
                var createTime = execute.getDate("createTime");

                message.setMessageId(id);
                message.setText(text);
                message.setSenderId(senderId);
                message.setReceivedId(receivedId);
                message.setCreateTime(createTime.toLocalDate());

                return message;
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
    public List<Message> findByAll() throws InterruptedException, SQLException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(FIND_All_MESSAGE)) {
            var execute = statement.executeQuery();
            var list = new ArrayList<Message>();

            while (execute.next()) {
                var id = execute.getInt("id");
                var text = execute.getString("text");
                var senderId = execute.getInt("senderId");
                var receivedId = execute.getInt("receivedId");
                var createTime = execute.getDate("createTime");

                var message = new Message();
                message.setMessageId(id);
                message.setText(text);
                message.setSenderId(senderId);
                message.setReceivedId(receivedId);
                message.setCreateTime(createTime.toLocalDate());

                list.add(message);
            }

            return list;
        } finally {
            PoolConnectionUtil.returnConnection(connection);
        }
    }

    @Override
    public void update(Message obj) throws InterruptedException, SQLException {
        var connection = PoolConnectionUtil.receiveConnection();
        try (var statement = connection.prepareStatement(UPDATE_MESSAGE)) {
            statement.setString(2, obj.getText());
            statement.setInt(3, obj.getSenderId());
            statement.setInt(4, obj.getReceivedId());
            statement.setDate(5, Date.valueOf(obj.getCreateTime()));
            statement.setInt(6, obj.getMessageId());

            var execute = statement.executeQuery();
            var message = new Message();

            if (execute.next()) {
                var text = execute.getString("text");
                var senderId = execute.getInt("senderId");
                var receivedId = execute.getInt("receivedId");
                var createTime = execute.getDate("createTime");

                message.setText(text);
                message.setSenderId(senderId);
                message.setReceivedId(receivedId);
                message.setCreateTime(createTime.toLocalDate());
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
}
