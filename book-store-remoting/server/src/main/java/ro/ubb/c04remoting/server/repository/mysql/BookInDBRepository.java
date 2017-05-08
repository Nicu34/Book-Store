package ro.ubb.c04remoting.server.repository.mysql;

import ro.ubb.c04remoting.common.domain.Book;
import ro.ubb.c04remoting.common.domain.validator.BookValidator;
import ro.ubb.c04remoting.common.domain.validator.Validator;
import ro.ubb.c04remoting.server.repository.RepositoryException;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nicu on 3/29/2017.
 */
public class BookInDBRepository extends InDBRepository<Long, Book> {

    private static final String TABLE_NAME = "book";
    private static final String COLUMNS_FORMAT = "(id, name, writer, price, quantity)";
    private static final String COLUMNS_VALUE_FORMAT = "(?, ?, ?, ?, ?)";
    private static final Validator<Book> CLIENT_VALIDATOR = new BookValidator();

    public BookInDBRepository(Connection connection) {
        super(connection, TABLE_NAME, COLUMNS_FORMAT, COLUMNS_VALUE_FORMAT, CLIENT_VALIDATOR);
    }

    public BookInDBRepository() throws RepositoryException {
        super(TABLE_NAME, COLUMNS_FORMAT, COLUMNS_VALUE_FORMAT, CLIENT_VALIDATOR);
    }

    @Override
    protected Book createEntity(Object[] objectsArray) {
        return new Book(((BigInteger) objectsArray[0]).longValue(), ((String) objectsArray[1]), ((String) objectsArray[2]), ((Long) objectsArray[3]), ((Integer) objectsArray[4]));
    }

    @Override
    protected PreparedStatement createInsertPreparedStatement(Book entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getWriter());
        preparedStatement.setLong(4, entity.getPrice());
        preparedStatement.setInt(5, entity.getQuantity());

        return preparedStatement;
    }
}
