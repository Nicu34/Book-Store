package ro.ubb.c04remoting.server.repository.mysql;

import ro.ubb.c04remoting.common.domain.Client;
import ro.ubb.c04remoting.common.domain.validator.ClientValidator;
import ro.ubb.c04remoting.common.domain.validator.Validator;
import ro.ubb.c04remoting.server.repository.RepositoryException;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nicu on 3/29/2017.
 */
public class ClientInDBRepository extends InDBRepository<Long, Client> {

    private static final String TABLE_NAME = "client";
    private static final String COLUMNS_FORMAT = "(id, name, moneyAmount)";
    private static final String COLUMNS_VALUE_FORMAT = "(?, ?, ?)";
    private static final Validator<Client> CLIENT_VALIDATOR = new ClientValidator();

    public ClientInDBRepository(Connection connection) {
        super(connection, TABLE_NAME, COLUMNS_FORMAT, COLUMNS_VALUE_FORMAT, CLIENT_VALIDATOR);
    }

    public ClientInDBRepository() throws RepositoryException {
        super(TABLE_NAME, COLUMNS_FORMAT, COLUMNS_VALUE_FORMAT, CLIENT_VALIDATOR);
    }

    @Override
    protected Client createEntity(Object[] objectsArray) {
        return new Client(((BigInteger) objectsArray[0]).longValue(), ((String) objectsArray[1]), ((Long) objectsArray[2]));
    }

    @Override
    protected PreparedStatement createInsertPreparedStatement(Client entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setLong(3, entity.getMoneyAmount());

        return preparedStatement;
    }
}
