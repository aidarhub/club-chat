package my.important.job.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<C, F, I> {
    void save(C obj) throws SQLException, InterruptedException;
    F findById(I index) throws SQLException, InterruptedException;
    List<F> findByAll() throws SQLException, InterruptedException;
    void update(F obj) throws SQLException, InterruptedException;
    void deleteById(I index) throws SQLException, InterruptedException;
    void createTable() throws SQLException, InterruptedException;
    void dropTable() throws SQLException, InterruptedException;
}
