import javafx.stage.Screen;

import java.util.List;

public class ScreenApplicationManager {
    public void showScreenApplications() {
        List<Screen> screens = ScreenUtil.getScreens();

        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            ScreenApplication screenApp = new ScreenApplication(screen, i + 1);
            screenApp.show();
        }
    }
}
