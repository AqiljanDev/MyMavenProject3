package org.example.dao;

import org.apache.log4j.Logger;
import org.example.entity.ActorEntity;
import org.example.exeption.DaoExeption;
import org.example.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorDao implements Dao<Integer, ActorEntity> {


    private static String SQL_SAVE = "INSERT INTO actor(fullname, birth) VALUES(? ?)";
    private static String SQL_DELETE = "DELETE FROM actor WHERE id = ?";
    private static String SQL_UPDATE = "UPDATE actor SET fullname = ?, birth = ? WHERE id = ?";
    private static String SQL_FIND_BY_ID = "SELECT * FROM actor WHERE id = ?";
    private static String SQL_FIND_ALL = "SELECT * FROM actor";
    private static Logger logger = Logger.getLogger(ActorDao.class.getName());

    @Override
    public ActorEntity save(ActorEntity actorEntity) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, actorEntity.getFullname());
            prepareStatement.setString(2, actorEntity.getBirth());
            prepareStatement.executeUpdate();

            try(var generate = prepareStatement.getGeneratedKeys();) {
                while (generate.next()) {
                    actorEntity.setId(generate.getInt("id"));
                }
            }

        } catch (SQLException e) {
            logger.error("Cannot create save on ActorEntity", e);
            throw new DaoExeption("Cannot create save on ActorEntity", e);
        }

        return actorEntity;
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
    public void update(ActorEntity entity) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_UPDATE)) {
            prepareStatement.setString(1, entity.getFullname());
            prepareStatement.setString(2, entity.getBirth());
            prepareStatement.setInt(3, entity.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot create update on ActorEntity", e);
            throw new DaoExeption("Cannot create update on ActorEntity", e);
        }

    }

    @Override
    public Optional<ActorEntity> findById(Integer id) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            ActorEntity actor = null;
            try(var resultSet = prepareStatement.executeQuery();) {
                if (resultSet.next()) {
                    buildNewEntity(resultSet);
                }
            }

            return Optional.ofNullable(actor);
        } catch (SQLException e) {
            logger.error("Cannot create findById on ActorEntity", e);
            throw new DaoExeption("Cannot create findById on ActorEntity", e);
        }

    }

    private ActorEntity buildNewEntity(ResultSet resultSet) throws SQLException {
            return new ActorEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("fullname"),
                    resultSet.getString("birth")
            );
    }

    @Override
    public List<ActorEntity> findAll() {

        try (var conn = ConnectionManager.open();
             var prepareStatement = conn.prepareStatement(SQL_FIND_ALL);
             var resultSet = prepareStatement.executeQuery()) {

            List<ActorEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildNewEntity(resultSet));
            }

            return list;

        } catch (SQLException e) {
            logger.error("Cannot create findAll on ActorEntity", e);
            throw new DaoExeption("Cannot create findAll on ActorEntity", e);
        }


    }
}
