package shared.windows;

import java.awt.*;

public class WindowProperties {

    private static final Dimension dimension = new Dimension(800, 300);

    private WindowProperties() {

    }

    public static Dimension getStandarWindowDimension() {
        return dimension;
    }
}
