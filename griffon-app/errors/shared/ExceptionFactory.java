package shared;

import griffon.core.artifact.GriffonArtifact;
import griffon.metadata.ArtifactProviderFor;

import javax.inject.Inject;

@ArtifactProviderFor(GriffonArtifact.class)
public class ExceptionFactory {

    private ExceptionFactory(){}

    @Inject
    TranslationService translationService;

    public static Exception create(ExceptionType type) {
        ExceptionFactory factory = new ExceptionFactory();
        switch (type) {
            case USER_NOT_FOUND: return new UserNotFoundException();
            case USER_NOT_SAVED: return new UserNotSavedException();
            default: return new Exception("An unexpected error occurred");
        }
    }
}
