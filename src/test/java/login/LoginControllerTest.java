package login;

import griffon.core.artifact.ArtifactManager;
import griffon.core.test.GriffonUnitRule;
import griffon.core.test.TestFor;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@TestFor(LoginController.class)
public class LoginControllerTest {
    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
    }

    @Inject
    private ArtifactManager artifactManager;

    private LoginController controller;

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule();

    @Test
    public void executeLoginAction() {
        LoginModel model = artifactManager.newInstance(LoginModel.class);
        controller.setModel(model);

        controller.invokeAction("login");
        await().atMost(2, TimeUnit.SECONDS);
    }
}
