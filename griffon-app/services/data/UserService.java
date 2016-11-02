package data;

import griffon.core.artifact.GriffonService;
import griffon.metadata.ArtifactProviderFor;
import org.apache.log4j.Logger;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService;
import shared.ExceptionFactory;
import shared.ExceptionType;
import shared.users.User;
import shared.users.UserPrivilege;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ArtifactProviderFor(GriffonService.class)
class UserService extends AbstractGriffonService {

    private final static Logger logger = Logger.getLogger(UserService.class);
    private Connection connection;

    public UserService() {
        connection = DatabaseConnectorManager.getDatabaseConnection();
    }

    public User getUser(String name, String password) throws Exception {
        User user = new User();
        try {
            //language=PostgresSQL
            final String sqlStatement = "SELECT u.name AS name, u.hashedPassword AS hashedPassword, u.lastName AS lastName, u.salt AS salt, up.name AS privilege" +
                    "u.address AS address, u.phone AS phone" +
                    " FROM users AS u JOIN user_privileges AS up ON u.privilege_id = up.id" +
                    " WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (PasswordUtils.isExpectedPassword(password.toCharArray(), resultSet.getBytes("salt"), resultSet.getBytes("hashedPassword"))) {
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAddress(resultSet.getString("address"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setPrivilege(UserPrivilege.valueOf(resultSet.getString("privilege")));
                }
            }
        } catch (SQLException e) {
            logger.error("A SQL error occurred: " + e.getMessage());
            throw ExceptionFactory.create(ExceptionType.USER_NOT_FOUND);
        }
        return user;
    }

    public void addUser(User user) throws Exception {
        try {
            //language=PostgresSQL
            final String sqlStatement = "INSERT INTO public.users (name, lastName, privilege_id, address, phone hashedPassword, salt) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getPrivilege() == UserPrivilege.ADMIN ? 1 : 2);
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getPhone());
            final byte [] salt = PasswordUtils.getNextSalt();
            final byte [] hashedPassword = PasswordUtils.hash(user.getPassword(), salt);
            statement.setBytes(6, hashedPassword);
            statement.setBytes(7, salt);
        } catch (SQLException e) {
            logger.error("A SQL error occurred: " + e.getMessage());
            throw ExceptionFactory.create(ExceptionType.USER_NOT_SAVED);
        }
    }
}
