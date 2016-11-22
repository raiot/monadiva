package control;

import griffon.core.artifact.GriffonView;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.swing.artifact.AbstractSwingGriffonView;
import shared.TranslationService;
import shared.windows.WindowProperties;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.swing.*;
import java.util.Collections;
import java.util.Map;

@ArtifactProviderFor(GriffonView.class)
public class ControlView extends AbstractSwingGriffonView {

    private JTabbedPane tabbedPane;

    @Inject
    private TranslationService translationService;



    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
        createMVCGroup("user");
        //createMVCGroup("moneyControl");
    }

    @Override
    public void initUI() {
        JFrame window = (JFrame) getApplication().createApplicationContainer(Collections.<String, Object>emptyMap());
        tabbedPane = new JTabbedPane();

        JPanel moneyControlTab = new JPanel();

        final String windowName = "controlWindow";
        window.setName(windowName);
        window.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        window.setMinimumSize(WindowProperties.getStandarWindowDimension());
        tabbedPane.setMinimumSize(WindowProperties.getStandarWindowDimension());

        moneyControlTab.setName("moneyControlTab");

        tabbedPane.setName("controlTab");
        tabbedPane.addTab(translationService.getTranslation("MONEY_CONTROL_TAB"), moneyControlTab);

        window.add(tabbedPane);
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getApplication().getWindowManager().attach(windowName, window);

    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
