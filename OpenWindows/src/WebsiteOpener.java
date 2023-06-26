import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class WebsiteOpener {
    public static void openWebPage(String url) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue == Worker.State.SUCCEEDED) {
                    stage.show();
                }
            });
            webEngine.load(url);

            Scene scene = new Scene(webView);
            stage.setScene(scene);
        });
    }
}
