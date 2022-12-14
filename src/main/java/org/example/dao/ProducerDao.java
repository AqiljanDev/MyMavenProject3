package org.example.dao;

import org.apache.log4j.Logger;
import org.example.entity.ActorEntity;
import org.example.entity.ProducerEntity;
import org.example.exeption.DaoExeption;
import org.example.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProducerDao implements Dao<Integer, ProducerEntity>{

    private static String SQL_SAVE = "INSERT INTO producer(fullname, birth) VALUES(?, ?)";
    private static String SQL_DELETE = "DELETE FROM producer WHERE id = ?";
    private static String SQL_UPDATE = "UPDATE producer SET fullname = ?, birth = ? WHERE id = ?";
    private static String SQL_FIND_BY_ID = "SELECT * FROM producer WHERE id = ?";
    private static String SQL_FIND_ALL = "SELECT * FROM producer";
    private static Logger logger = Logger.getLogger(ProducerDao.class.getName());

    @Override
    public ProducerEntity save(ProducerEntity producerEntity) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, producerEntity.getFullname());
            prepareStatement.setString(2, producerEntity.getBirth());
            prepareStatement.executeUpdate();

            try(var generate = prepareStatement.getGeneratedKeys();) {
                while (generate.next()) {
                    producerEntity.setId(generate.getInt("id"));
                }
            }

        } catch (SQLException e) {
            logger.error("Cannot create save on ProducerEntity", e);
            throw new RuntimeException("Cannot create save on ProducerEntity", e);
        }

        return producerEntity;
    }

    @Override
    public boolean delete(Integer id) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_DELETE)) {
            prepareStatement.setInt(1, id);

            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Cannot create delete on ActorEntity", e);
            throw new DaoExeption("Cannot create delete on ActorEntity", e);
        }

    }

    @Override
    public void update(ProducerEntity entity) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_UPDATE)) {
            prepareStatement.setString(1, entity.getFullname());
            prepareStatement.setString(2, entity.getBirth());
            prepareStatement.setInt(3, entity.getId());
            prepareStatement.executeUpdate();

        }catch (SQLException e) {
            logger.error("Cannot create update on ActorEntity", e);
            throw new DaoExeption("Cannot create update on ActorEntity", e);
        }

    }

    private ProducerEntity buildNewEntity(ResultSet resultSet) throws SQLException {
        return new ProducerEntity(
                resultSet.getInt("id"),
                resultSet.getString("fullname"),
                resultSet.getString("birth")
        );
    }

    @Override
    public Optional<ProducerEntity> findById(Integer id) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            ProducerEntity producer = null;

            try(var resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    producer = buildNewEntity(resultSet);
                }
            }
            return Optional.ofNullable(producer);
        }catch (SQLException e) {
            logger.error("Cannot create findById on ActorEntity", e);
            throw new DaoExeption("Cannot create findById on ActorEntity", e);
        }

    }

    @Override
    public List<ProducerEntity> findAll() {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_FIND_ALL);
            var resultSet = prepareStatement.executeQuery()) {

            List<ProducerEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildNewEntity(resultSet));
            }

            return list;
        }catch (SQLException e) {
            logger.error("Cannot create findAll on ActorEntity", e);
            throw new DaoExeption("Cannot create findAll on ActorEntity", e);
        }
    }

}
