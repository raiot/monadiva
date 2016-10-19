package shared;

import griffon.core.artifact.GriffonService;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService;


@ArtifactProviderFor(GriffonService.class)
public class TranslationService extends AbstractGriffonService {

    public String getTranslation(String name) {
        return name;
    }
}
