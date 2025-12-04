package my.important.job.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class PoolConnectionUtil {

    private static final ArrayDeque<Connection> CONNECTION_POOL = new ArrayDeque<>();

    static {
        var user = PropertiesUtil.get(PropertiesUtil.DB_USERNAME).orElseThrow();
        var password = PropertiesUtil.get(PropertiesUtil.DB_PASSWORD).orElseThrow();
        var url = PropertiesUtil.get(PropertiesUtil.DB_URL).orElseThrow();
        var poolSize = PropertiesUtil.get(PropertiesUtil.POOL_SIZE).orElseThrow();

        for (int i = 0; i < Integer.parseInt(poolSize); i++) {
            try {
                Connection connection = DriverManager.getConnection(PropertiesUtil.get(PropertiesUtil.DB_URL).orElseThrow(),
                        PropertiesUtil.get(PropertiesUtil.DB_USERNAME).orElseThrow(),"password");
                CONNECTION_POOL.add(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection receiveConnection() throws InterruptedException {
        while (true) {
            if (!CONNECTION_POOL.isEmpty()) {
                break;
            } else {
                Thread.sleep(5000);
            }
        }

        return CONNECTION_POOL.pollFirst();
    }

    public static void returnConnection(Connection connection) {
        CONNECTION_POOL.addLast(connection);
    }
}
