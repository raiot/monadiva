package data;

import griffon.core.artifact.GriffonService;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService;
import users.User;
import users.UserPrivilege;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

@ArtifactProviderFor(GriffonService.class)
public class UserService extends AbstractGriffonService {

    final static Logger logger = Logger.getLogger(UserService.class);
    Connection connection;

    public UserService() {
        connection = DatabaseConnectorManager.getDatabaseConnection();
    }

    public User getUser(String name, String password) {
        User user = new User();
        try {
            String sqlStatement = "SELECT u.name, u.hashedPassword, u.lastName, up.name, u.salt AS 'privilege'" +
                    " FROM users u JOIN user_privileges ON u.privilege_id = up.id" +
                    " WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (PasswordUtils.isExpectedPassword(password.toCharArray(), resultSet.getBytes("salt"), resultSet.getBytes("hashedPassword"))) {
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setPrivilege(UserPrivilege.valueOf(resultSet.getString("privilege")));
                }
            }
        } catch (SQLException e) {
            logger.error("A SQL error ocurred: " + e.getMessage());
        }
        return user;
    }
}
