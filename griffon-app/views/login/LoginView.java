package login;

import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.swing.artifact.AbstractSwingGriffonView;
import shared.TranslationService;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.Collections;

import static java.awt.GridBagConstraints.*;

@ArtifactProviderFor(GriffonView.class)
public class LoginView extends AbstractSwingGriffonView {

    private LoginModel model;
    private LoginController controller;

    private final String LOGIN_WINDOW_NAME = "loginWindow";

    @MVCMember
    public void setModel(@Nonnull LoginModel model) {
        this.model = model;
    }

    @MVCMember
    public void setController(@Nonnull LoginController controller) {
        this.controller = controller;
    }

    @Inject
    private TranslationService translationService;

    public void initUI() {
        JFrame window = (JFrame) getApplication().createApplicationContainer(Collections.<String, Object>emptyMap());
        window.setName(LOGIN_WINDOW_NAME);
        window.setTitle(getApplication().getConfiguration().getAsString("login.title"));
        window.setMinimumSize(new Dimension(800, 300));
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getApplication().getWindowManager().attach(LOGIN_WINDOW_NAME, window);
        JPanel userNamePanel = new JPanel();
        userNamePanel.setName("userNamePanel");
        JPanel passwordPanel = new JPanel();
        passwordPanel.setName("passwordPanel");
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setName("loginButtonPanel");
        window.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints headerConstraints = new GridBagConstraints();
        GridBagConstraints userNameConstraints = new GridBagConstraints();
        GridBagConstraints passwordConstraints = new GridBagConstraints();
        GridBagConstraints loginButtonConstraints = new GridBagConstraints();
        headerConstraints.anchor = FIRST_LINE_START;
        headerConstraints.gridy = 0;
        userNameConstraints.anchor = FIRST_LINE_START;
        userNameConstraints.gridy = 1;
        passwordConstraints.anchor = LINE_START;
        passwordConstraints.gridy = 2;
        loginButtonConstraints.anchor = PAGE_END;
        loginButtonConstraints.gridy = 3;

        final JPanel headerPanel = new JPanel();
        ImageIcon image = new ImageIcon(((new ImageIcon(getImage("/monadiva-logo.png")).getImage()).getScaledInstance(350, 205, Image.SCALE_SMOOTH)));
        JLabel imageLabel = new JLabel("", image, JLabel.CENTER);
        headerPanel.setName("headerPanel");
        headerPanel.add(imageLabel, BorderLayout.CENTER);
        headerPanel.setMaximumSize(new Dimension(100, 50));


        final JLabel userNameLabel = new JLabel(translationService.getTranslation("LOGIN_USERNAME_LABEL"));
        userNameLabel.setName("userNameLabel");
        userNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        userNameLabel.setSize(100, 5);

        final JTextField userNameTextField = new JTextField(15);
        userNameTextField.setName("userNameTextField");
        userNameTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        userNameTextField.setSize(100, 5);

        final JLabel passwordLabel = new JLabel(translationService.getTranslation("LOGIN_PASSWORD_LABEL"));
        passwordLabel.setName("passwordLabel");
        passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setSize(100, 5);

        final JPasswordField passwordField = new JPasswordField(15);
        passwordField.setName("passwordField");
        passwordField.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordField.setSize(100, 5);

        Action action = toolkitActionFor(controller, "login");

        JButton loginButton = new JButton(action);
        loginButton.setName("loginButton");
        loginButton.setText(translationService.getTranslation("LOGIN_BUTTON"));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.setVerticalAlignment(SwingConstants.CENTER);

        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameTextField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        loginButtonPanel.add(loginButton);
        window.getContentPane().add(headerPanel, headerConstraints);
        window.getContentPane().add(userNamePanel, userNameConstraints);
        window.getContentPane().add(passwordPanel, passwordConstraints);
        window.getContentPane().add(loginButtonPanel, loginButtonConstraints);
        window.pack();
    }

    private Image getImage(String path) {
        return Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource(path));
    }
}
