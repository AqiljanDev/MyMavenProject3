package org.example.dao;

import com.sun.jdi.PrimitiveValue;
import org.apache.log4j.Logger;
import org.example.entity.ActorEntity;
import org.example.entity.MovieEntity;
import org.example.exeption.DaoExeption;
import org.example.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDao implements Dao<Integer, MovieEntity>{

    private static String SQL_SAVE = "INSERT INTO movie(name, id_actor, id_producer, relise_date, country, genre) VALUES (?, ?, ?, ?, ?, ?)";
    private static String SQL_DELETE = "DELETE FROM movie WHERE id = ?";
    private static String SQL_UPDATE = "UPDATE movie SET " +
            "name = ?, id_actor = ?, id_producer = ?, relise_date = ?, country = ?, genre = ? WHERE id = ?";
    private static String SQL_FIND_BY_ID = "SELECT * FROM movie WHERE id = ?";
    private static String SQL_FIND_ALL = "SELECT * FROM movie";
    private static Logger logger = Logger.getLogger(MovieDao.class.getName());

    @Override
    public MovieEntity save(MovieEntity movieEntity) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, movieEntity.getName());
            prepareStatement.setInt(2, movieEntity.getId_actor());
            prepareStatement.setInt(3, movieEntity.getId_producer());
            prepareStatement.setString(4, movieEntity.getRelise_date());
            prepareStatement.setString(5, movieEntity.getCountry());
            prepareStatement.setString(6, movieEntity.getGenre());
            prepareStatement.executeUpdate();

            try(var generate = prepareStatement.getGeneratedKeys()) {
                while (generate.next()) {
                    movieEntity.setId(generate.getInt("id"));
                }
            }
        }catch (SQLException e) {
            logger.error("Cannot create save on MovieEntity", e);
            throw new DaoExeption("Cannot create save on MovieEntity", e);
        }

        return movieEntity;
    }

    @Override
    public boolean delete(Integer id) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_DELETE)) {
            prepareStatement.setInt(1, id);

            return prepareStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            logger.error("Cannot create delete on MovieEntity", e);
            throw new DaoExeption("Cannot create delete on MovieEntity", e);
        }

    }

    @Override
    public void update(MovieEntity entity) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_UPDATE)) {
            prepareStatement.setString(1, entity.getName());
            prepareStatement.setInt(2, entity.getId_actor());
            prepareStatement.setInt(3, entity.getId_producer());
            prepareStatement.setString(4, entity.getRelise_date());
            prepareStatement.setString(5, entity.getCountry());
            prepareStatement.setString(6, entity.getGenre());
            prepareStatement.setInt(7, entity.getId());
            prepareStatement.executeUpdate();

        }catch (SQLException e) {
            logger.error("Cannot create update on MovieEntity", e);
            throw new DaoExeption("Cannot create update on MovieEntity", e);
        }

    }

    private MovieEntity buildNewEntity(ResultSet resultSet) throws SQLException {
        return new MovieEntity(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("id_actor"),
                resultSet.getInt("id_producer"),
                resultSet.getString("relise_date"),
                resultSet.getString("country"),
                resultSet.getString("genre")
        );
    }


    @Override
    public Optional<MovieEntity> findById(Integer id) {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            MovieEntity movieEntity = null;

            try(var resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    movieEntity = buildNewEntity(resultSet);
                }
            }

            return Optional.ofNullable(movieEntity);
        }catch (SQLException e) {
            logger.error("Cannot create findById on MovieEntity", e);
            throw new DaoExeption("Cannot create findById on MovieEntity", e);
        }

    }

    @Override
    public List<MovieEntity> findAll() {

        try(var conn = ConnectionManager.open();
            var prepareStatement = conn.prepareStatement(SQL_FIND_ALL);
            var resultSet = prepareStatement.executeQuery()) {

            List<MovieEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildNewEntity(resultSet));
            }

            return list;
        }catch (SQLException e) {
            logger.error("Cannot create findAll on MovieEntity", e);
            throw new DaoExeption("Cannot create findAll on MovieEntity", e);
        }

    }
}
