import java.awt.Desktop;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

public class app {
    public static void main(String[] args) {
        String htmlFilePath = "C:/Java/AutoDesktop/Auto/new_window.html";
        int displayIndex = 0; // Index of the display to open the window on

        try {
            File file = new File(htmlFilePath);
            Desktop desktop = Desktop.getDesktop();

            // Get the GraphicsDevice for the specified display index
            GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            if (displayIndex >= 0 && displayIndex < devices.length) {
                GraphicsDevice targetDevice = devices[displayIndex];

                // Get the bounds of the target display
                Rectangle displayBounds = targetDevice.getDefaultConfiguration().getBounds();

                // Set the position and size of the window based on the target display bounds
                String options = String.format("width=%d,height=%d,x=%d,y=%d",
                        500, 500, displayBounds.x, displayBounds.y);

                // Open the HTML file on the target display
                desktop.open(file);
                Thread.sleep(1000); // Wait for the window to open
                desktop.browse(file.toURI().resolve(options));
            } else {
                System.err.println("Invalid display index: " + displayIndex);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
