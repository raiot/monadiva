package user;

import control.ControlView;
import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.swing.artifact.AbstractSwingGriffonView;
import shared.TranslationService;
import shared.windows.WindowProperties;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.ResourceBundle;

@ArtifactProviderFor(GriffonView.class)
public class UserView extends AbstractSwingGriffonView {
    @MVCMember
    @Nonnull
    private ControlView parentView;

    @Inject
    TranslationService translationService;

    public void initUI() {
        JPanel userControlTab = new JPanel();
        JLabel userNameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel addressLabel = new JLabel();
        JLabel phoneLabel = new JLabel();

        userControlTab.setName("userControlTab");
        userControlTab.setMinimumSize(new Dimension(500, 500));
        userNameLabel.setText(translationService.getTranslation("USER_NAME_LABEL"));
        passwordLabel.setText(translationService.getTranslation("PASSWORD_LABEL"));
        addressLabel.setText(translationService.getTranslation("ADDRESS_LABEL"));
        phoneLabel.setText(translationService.getTranslation("PHONE_LABEL"));
        userControlTab.add(userNameLabel);
        userControlTab.add(passwordLabel);
        userControlTab.add(addressLabel);
        userControlTab.add(phoneLabel);
        parentView.getTabbedPane().addTab(translationService.getTranslation("USER_CONTROL_TAB"), userControlTab);
    }
}
