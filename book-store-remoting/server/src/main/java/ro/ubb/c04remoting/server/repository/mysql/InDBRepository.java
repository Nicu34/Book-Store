package ro.ubb.c04remoting.server.repository.mysql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import ro.ubb.c04remoting.common.domain.BaseEntity;
import ro.ubb.c04remoting.common.domain.validator.Validator;
import ro.ubb.c04remoting.common.domain.validator.ValidatorException;
import ro.ubb.c04remoting.server.repository.Repository;
import ro.ubb.c04remoting.server.repository.RepositoryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by nicu on 3/29/2017.
 */
public abstract class InDBRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    protected Connection connection;
    protected String tableName;
    protected String columnsFormat;
    protected String columnsValuesFormat;
    protected Validator<T> validator;

    protected abstract T createEntity(Object[] objectsArray);

    protected abstract PreparedStatement createInsertPreparedStatement(T entity, PreparedStatement preparedStatement) throws SQLException;

    public InDBRepository(Connection connection, String tableName, String columnsFormat, String columnsValuesFormat, Validator<T> validator) {
        this.connection = connection;
        this.tableName = tableName;
        this.columnsFormat = columnsFormat;
        this.columnsValuesFormat = columnsValuesFormat;
        this.validator = validator;
    }

    public InDBRepository(String tableName, String columnsFormat, String columnsValuesFormat, Validator<T> validator) throws RepositoryException {
        this.tableName = tableName;
        this.columnsFormat = columnsFormat;
        this.columnsValuesFormat = columnsValuesFormat;
        this.validator = validator;

        // create our mysql database connection
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/book_store";
        try {
            Class.forName(myDriver);
            connection = DriverManager.getConnection(myUrl, "root", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Optional<T> findOne(ID id) throws RepositoryException {
        String query = " select * from " + tableName + " where id = " + id;

        try {
            return new QueryRunner()
                    .query(connection, query, new ArrayListHandler())
                    .stream()
                    .findFirst()
                    .map(this::createEntity);

        }
        catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Iterable<T> findAll() throws RepositoryException {
        String query = " select * from " + tableName + ";";
        Iterable<T> entities;

        try {
            entities = new QueryRunner()
                    .query(connection, query, new ArrayListHandler())
                    .stream()
                    .map(this::createEntity)
                    .collect(Collectors.toSet());

        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        return entities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException, RepositoryException {
        String query = " insert into " + tableName + " " + columnsFormat + " values " + columnsValuesFormat + ";";
        validator.validate(entity);

        try {
            if (findOne(entity.getId()).equals(Optional.empty())) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement = createInsertPreparedStatement(entity, preparedStatement);
                preparedStatement.execute();

            }
            else {
                update(entity);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }

        return Optional.of(entity);
    }

    @Override
    public Optional<T> delete(ID id) throws RepositoryException {
        String query = " delete from " + tableName + " where id = " + id + ";";
        Optional<T> entityOptional = findOne(id);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }

        return entityOptional;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, RepositoryException {
        ID id = entity.getId();

        delete(id);
        save(entity);

        return Optional.of(entity);
    }
}
