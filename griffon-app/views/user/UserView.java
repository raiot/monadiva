package user;

import griffon.core.artifact.GriffonView;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.swing.artifact.AbstractSwingGriffonView;

import javax.swing.*;
import java.util.Collections;

@ArtifactProviderFor(GriffonView.class)
public class UserView extends AbstractSwingGriffonView {

    public void initUI() {
        JFrame window = (JFrame) getApplication().createApplicationContainer(Collections.<String, Object>emptyMap());
    }
}
