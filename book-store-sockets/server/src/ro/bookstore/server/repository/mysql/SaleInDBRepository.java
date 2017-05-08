package ro.bookstore.server.repository.mysql;

import ro.bookstore.common.domain.Sale;
import ro.bookstore.common.domain.validator.SaleValidator;
import ro.bookstore.common.domain.validator.Validator;
import ro.bookstore.server.repository.RepositoryException;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nicu on 3/29/2017.
 */
public class SaleInDBRepository extends InDBRepository<Long, Sale> {

    private static final String TABLE_NAME = "sales";
    private static final String COLUMNS_FORMAT = "(id, clientId, bookId)";
    private static final String COLUMNS_VALUE_FORMAT = "(?, ?, ?)";
    private static final Validator<Sale> CLIENT_VALIDATOR = new SaleValidator();

    public SaleInDBRepository(Connection connection) {
        super(connection, TABLE_NAME, COLUMNS_FORMAT, COLUMNS_VALUE_FORMAT, CLIENT_VALIDATOR);
    }

    public SaleInDBRepository() throws RepositoryException {
        super(TABLE_NAME, COLUMNS_FORMAT, COLUMNS_VALUE_FORMAT, CLIENT_VALIDATOR);
    }

    @Override
    protected Sale createEntity(Object[] objectsArray) {
        return new Sale(((BigInteger) objectsArray[0]).longValue(), ((BigInteger) objectsArray[1]).longValue(), ((BigInteger) objectsArray[2]).longValue());
    }

    @Override
    protected PreparedStatement createInsertPreparedStatement(Sale entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setLong(2, entity.getClientId());
        preparedStatement.setLong(3, entity.getBookId());

        return preparedStatement;
    }
}
