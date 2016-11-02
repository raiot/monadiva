package user;

import griffon.core.artifact.GriffonView;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.swing.artifact.AbstractSwingGriffonView;
import shared.TranslationService;

import javax.inject.Inject;
import javax.swing.*;
import java.util.Collections;

@ArtifactProviderFor(GriffonView.class)
public class UserView extends AbstractSwingGriffonView {

    @Inject
    TranslationService translationService;

    public void initUI() {
        JFrame window = (JFrame) getApplication().createApplicationContainer(Collections.<String, Object>emptyMap());
        JLabel userNameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel addressLabel = new JLabel();
        JLabel phoneLabel = new JLabel();

        userNameLabel.setText(translationService.getTranslation("USER_NAME_LABEL"));
        passwordLabel.setText(translationService.getTranslation("PASSWORD_LABEL"));
        addressLabel.setText(translationService.getTranslation("ADDRESS_LABEL"));
        phoneLabel.setText(translationService.getTranslation("PHONE_LABEL"));
        final String windowName = "userWindow";
        window.setName(windowName);
        window.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
}
