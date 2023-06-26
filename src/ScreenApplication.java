import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.util.List;

public class ScreenApplication {
    private final Screen screen;
    private final String message;
    private final int deviceNumber;

    public ScreenApplication(Screen screen, int deviceNumber) {
        this.screen = screen;
        this.deviceNumber = deviceNumber;
        this.message = "Hello from Display Device #" + deviceNumber;
    }

    public void show() {
        Stage stage = createStage();
        VBox root = createRoot();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Stage createStage() {
        Rectangle2D bounds = screen.getBounds();
        Stage stage = new Stage();
        stage.setTitle("Hello World!");
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        return stage;
    }

    private VBox createRoot() {
        Button btn = new Button();
        btn.setText("Click Me");

        Text displayText = new Text();
        displayText.setText("Display Device #" + deviceNumber);
        displayText.setFont(Font.font("Arial", 16));
        displayText.setFill(Color.WHITE);

        Text helloText = new Text();
        helloText.setText("Hello");
        helloText.setFont(Font.font("Arial", 24));
        helloText.setFill(Color.WHITE);

        Text resolutionText = new Text();
        resolutionText.setText("Resolution: " + screenBoundsToString(screen.getBounds()));
        resolutionText.setFont(Font.font("Arial", 14));
        resolutionText.setFill(Color.WHITE);

        Text emptyLine = new Text();

        Text text = new Text();
        text.setText(message + "\n\n" + "Resolution: " + screenBoundsToString(screen.getBounds())
                + "\n" + "Width: " + screenBoundsToString(screen.getBounds().getWidth())
                + "\n" + "Height: " + screenBoundsToString(screen.getBounds().getHeight()));
        text.setWrappingWidth(300);
        text.setTextAlignment(TextAlignment.CENTER); // Center align the hidden message
        text.setVisible(false);
        text.setFont(Font.font("Arial", 18));
        text.setFill(Color.WHITE);

        btn.setOnAction(event -> {
            if (text.isVisible()) {
                text.setVisible(false);
                btn.setText("Click Me");
            } else {
                text.setVisible(true);
                btn.setText("Hide");
            }
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, null)));
        root.getChildren().addAll(displayText, helloText, emptyLine, btn, text);
        return root;
    }

    private String screenBoundsToString(Rectangle2D bounds) {
        return (int) bounds.getWidth() + "x" + (int) bounds.getHeight();
    }

    private String screenBoundsToString(double value) {
        return String.format("%.0f", value);
    }
}
