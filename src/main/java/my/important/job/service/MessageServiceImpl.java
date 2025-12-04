package my.important.job.service;

import my.important.job.dao.Dao;
import my.important.job.dto.MessageCreateDto;
import my.important.job.dto.MessageFindDto;
import my.important.job.entity.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements Service<MessageCreateDto, MessageFindDto, Integer> {
    private final Dao<Message, Integer> dao;

    public MessageServiceImpl(Dao<Message, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void save(MessageCreateDto obj) throws SQLException, InterruptedException {
        dao.save(obj.toEntity());
    }

    @Override
    public MessageFindDto findById(Integer index) throws SQLException, InterruptedException {
        return dao.findById(index).toDto();
    }

    @Override
    public List<MessageFindDto> findByAll() throws SQLException, InterruptedException {
        return dao.findByAll().stream().map(Message::toDto).toList();
    }

    @Override
    public void update(MessageFindDto obj) throws SQLException, InterruptedException {
        dao.update(obj.toEntity());
    }

    @Override
    public void deleteById(Integer index) throws SQLException, InterruptedException {
        dao.deleteById(index);
    }

    @Override
    public void createTable() throws SQLException, InterruptedException {
        dao.createTable();
    }

    @Override
    public void dropTable() throws SQLException, InterruptedException {
        dao.dropTable();
    }
}
