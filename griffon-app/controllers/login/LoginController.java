package login;

import griffon.core.artifact.GriffonController;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import griffon.transform.Threading;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import javax.annotation.Nonnull;

@ArtifactProviderFor(GriffonController.class)
public class LoginController extends AbstractGriffonController {
    public LoginModel model;

    @MVCMember
    public void setModel(@Nonnull LoginModel model) {
        this.model = model;
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void login() {
        //log the user through LoginService
    }
}
