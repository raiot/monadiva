import griffon.util.AbstractMapResourceBundle;
import shared.TranslationService;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;

import static java.util.Arrays.asList;
import static griffon.util.CollectionUtils.map;

public class Config extends AbstractMapResourceBundle {

    @Override
    protected void initialize(@Nonnull Map<String, Object> entries) {
        map(entries)
            .e("application", map()
                .e("title", "MonaDiva App")
                .e("startupGroups", Collections.singletonList("login"))
                .e("autoShutdown", true)
            )
            .e("mvcGroups", map()
                .e("login", map()
                    .e("model", "login.LoginModel")
                    .e("view", "login.LoginView")
                    .e("controller", "login.LoginController")
                ).e("control", map()
                    .e("model", "control.ControlModel")
                    .e("view", "control.ControlView")
                    .e("controller", "control.ControlController")
                ).e("user", map()
                    .e("model", "user.UserModel")
                    .e("view", "user.UserView")
                    .e("controller", "user.UserController"))
            );
    }
}