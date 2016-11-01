package login;

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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collections;
import java.util.Map;

import static java.awt.GridBagConstraints.*;

@ArtifactProviderFor(GriffonView.class)
class LoginView extends AbstractSwingGriffonView {

    private LoginModel model;
    private LoginController controller;
    private JFrame window;

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

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
        createMVCGroup("control");
    }

    public void initUI() {
        window = (JFrame) getApplication().createApplicationContainer(Collections.<String, Object>emptyMap());
        final String windowName = "loginWindow";
        window.setName(windowName);
        window.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.getContentPane().setLayout(new GridBagLayout());
        window.setMinimumSize(WindowProperties.getStandarWindowDimension());

        final JPanel errorPanel = new JPanel();
        final JPanel userNamePanel = new JPanel();
        final JPanel passwordPanel = new JPanel();
        final JPanel loginButtonPanel = new JPanel();
        final JPanel headerPanel = new JPanel();
        final ImageIcon image = new ImageIcon(((new ImageIcon(getImage("/monadiva-logo.png")).getImage()).getScaledInstance(350, 205, Image.SCALE_SMOOTH)));
        final JLabel imageLabel = new JLabel("", image, JLabel.CENTER);
        final JLabel errorDisplayLabel = new JLabel();
        final JLabel userNameLabel = new JLabel(translationService.getTranslation("LOGIN_USERNAME_LABEL"));
        final JLabel passwordLabel = new JLabel(translationService.getTranslation("LOGIN_PASSWORD_LABEL"));
        final JTextField userNameTextField = new JTextField(15);
        final JPasswordField passwordField = new JPasswordField(15);
        final GridBagConstraints headerConstraints = new GridBagConstraints();
        final GridBagConstraints userNameConstraints = new GridBagConstraints();
        final GridBagConstraints passwordConstraints = new GridBagConstraints();
        final GridBagConstraints loginButtonConstraints = new GridBagConstraints();
        final GridBagConstraints errorConstraints = new GridBagConstraints();
        final Action action = toolkitActionFor(controller, "login");
        final JButton loginButton = new JButton(action);

        errorPanel.setName("errorPanel");
        userNamePanel.setName("userNamePanel");
        passwordPanel.setName("passwordPanel");
        loginButtonPanel.setName("loginButtonPanel");


        errorPanel.setMinimumSize(new Dimension(500, 30));
        userNamePanel.setMinimumSize(new Dimension(500, 30));
        passwordPanel.setMinimumSize(new Dimension(500, 30));
        loginButtonPanel.setMinimumSize(new Dimension(500, 30));

        headerConstraints.anchor = FIRST_LINE_START;
        headerConstraints.gridy = 0;
        errorConstraints.anchor = FIRST_LINE_START;
        errorConstraints.gridy = 1;
        userNameConstraints.anchor = FIRST_LINE_START;
        userNameConstraints.gridy = 2;
        passwordConstraints.anchor = LINE_START;
        passwordConstraints.gridy = 3;
        loginButtonConstraints.anchor = PAGE_END;
        loginButtonConstraints.gridy = 4;

        headerPanel.setName("headerPanel");
        headerPanel.add(imageLabel, BorderLayout.CENTER);
        headerPanel.setMaximumSize(new Dimension(600, 300));
        headerPanel.setMaximumSize(new Dimension(500, 250));

        errorDisplayLabel.setForeground(Color.RED);

        userNameLabel.setName("userNameLabel");
        userNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        userNameLabel.setSize(100, 5);

        userNameTextField.setName("userNameTextField");
        userNameTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        userNameTextField.setSize(100, 5);

        passwordLabel.setName("passwordLabel");
        passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setSize(100, 5);

        passwordField.setName("passwordField");
        passwordField.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordField.setSize(100, 5);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        loginButton.setName("loginButton");
        loginButton.setText(translationService.getTranslation("LOGIN_BUTTON"));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.setVerticalAlignment(SwingConstants.CENTER);

        errorPanel.add(errorDisplayLabel);
        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameTextField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        loginButtonPanel.add(loginButton);

        bindLoginAction(errorDisplayLabel);

        window.getContentPane().add(headerPanel, headerConstraints);
        window.getContentPane().add(errorPanel, errorConstraints);
        window.getContentPane().add(userNamePanel, userNameConstraints);
        window.getContentPane().add(passwordPanel, passwordConstraints);
        window.getContentPane().add(loginButtonPanel, loginButtonConstraints);
        getApplication().getWindowManager().attach(windowName, window);
        window.pack();
    }

    private Image getImage(String path) {
        return Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource(path));
    }

    private void bindLoginAction(JLabel errorDisplayLabel) {
        model.getModel().addPropertyChangeListener("isValidUser", evt -> {
            if ((boolean) evt.getNewValue()) {
                getApplication().getWindowManager().show("controlWindow");
                window.dispose();
            } else {
                errorDisplayLabel.setText(model.getModel().getError());
            }
        });
    }
}
