package my.important.job.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, I> {
    void save(T obj) throws InterruptedException, SQLException;
    T findById(I index) throws InterruptedException;
    List<T> findByAll() throws InterruptedException, SQLException;
    void update(T obj) throws InterruptedException, SQLException;
    void deleteById(I index) throws InterruptedException, SQLException;
    void createTable() throws SQLException, InterruptedException;
    void dropTable() throws InterruptedException, SQLException;
}
