package login;

import griffon.core.artifact.GriffonController;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import griffon.transform.Threading;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;
import shared.TranslationService;

import javax.annotation.Nonnull;
import javax.inject.Inject;

@ArtifactProviderFor(GriffonController.class)
public class LoginController extends AbstractGriffonController {
    private LoginModel model;

    @Inject
    private TranslationService translationService;

    @MVCMember
    void setModel(@Nonnull LoginModel model) {
        this.model = model;
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void login() {
        model.getModel().setIsValidUser(true);
        //model.getModel().setError(translationService.getTranslation("ERROR_LOGIN_INCORRECT_USER_PASSWORD"));
    }
}
