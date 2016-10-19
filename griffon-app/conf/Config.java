import griffon.util.AbstractMapResourceBundle;

import javax.annotation.Nonnull;
import java.util.Map;

import static java.util.Arrays.asList;
import static griffon.util.CollectionUtils.map;

public class Config extends AbstractMapResourceBundle {
    @Override
    protected void initialize(@Nonnull Map<String, Object> entries) {
        map(entries)
            .e("application", map()
                .e("title", "login")
                .e("startupGroups", asList("login"))
                .e("autoShutdown", true)
            )
            .e("mvcGroups", map()
                .e("login", map()
                    .e("model", "login.LoginModel")
                    .e("view", "login.LoginView")
                    .e("controller", "login.LoginController")
                )
            );
    }
}