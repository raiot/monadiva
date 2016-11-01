package login;

import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

@ArtifactProviderFor(GriffonModel.class)
public class LoginModel extends AbstractGriffonModel {

    private Login login = new Login();

    public LoginModel() {
    }

    public Login getModel() {
        return login;
    }
}
