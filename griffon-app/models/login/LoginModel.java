package login;

import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

@ArtifactProviderFor(GriffonModel.class)
public class LoginModel extends AbstractGriffonModel {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        firePropertyChange("userName", userName, this.userName = userName);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        firePropertyChange("password", password, this.password = password);
    }
}
