package shared;

import data.DatabaseConnectorManager;
import griffon.core.artifact.GriffonService;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@ArtifactProviderFor(GriffonService.class)
public class TranslationService extends AbstractGriffonService {

    private Connection connection;

    public TranslationService() {
        connection = DatabaseConnectorManager.getDatabaseConnection();
    }

    public String getTranslation(String name) {
        final String langId = AppProperties.getProperty("language", "1");
        final int langid = Integer.parseInt(langId);
        return getTranslation(name, langid);
    }

    public String getTranslation(String name, int langId) {
        String translation = "";
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT value FROM translations WHERE name = ? AND langid = ?");
            statement.setString(1, name);
            statement.setInt(2, langId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                translation = resultSet.getString("value");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            translation = name;
        }
        return translation;
    }
}
