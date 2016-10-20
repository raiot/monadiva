package monadiva;

import griffon.core.test.GriffonFestRule;
import org.fest.swing.fixture.FrameFixture;
import org.junit.Rule;
import org.junit.Test;

public class LoginIntegrationTest {
    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
        System.setProperty("griffon.swing.edt.violations.check", "true");
        System.setProperty("griffon.swing.edt.hang.monitor", "true");
    }

    @Rule
    public final GriffonFestRule fest = new GriffonFestRule();

    private FrameFixture window;

    @Test
    public void loginButton() {
        //set
        window.textBox("userNameTextField").setText("adm");
        window.textBox("passwordField").setText("123");

        // given:
        window.textBox("userNameTextField").requireText("adm");
        window.textBox("passwordField").requireText("123");
    }
}
