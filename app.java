import java.awt.Desktop;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class app {
    public static void main(String[] args) {
        String[] paths = {
            "C:/Program Files/NVIDIA Corporation/NVIDIA GeForce Experience/NVIDIA GeForce Experience.exe",
            "C:/Java/AutoDesktop/Auto/new_window.html",
            "https://www.epicgames.com/"
        };

        int displayIndex = 0; // Index of the display to open the window on

        try {
            GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            if (displayIndex >= 0 && displayIndex < devices.length) {

                Desktop desktop = Desktop.getDesktop();

                for (String path : paths) {
                    if (path.equals("C:/Java/AutoDesktop/Auto/new_window.html")) {
                        // Skip opening the new_window.html file
                        continue;
                    }

                    if (path.startsWith("http")) {
                        desktop.browse(new URI(path));
                    } else {
                        File file = new File(path);
                        if (file.exists()) {
                            desktop.open(file);
                            Thread.sleep(1000); // Wait for the application/window to open
                        } else {
                            System.err.println("File does not exist: " + file.getAbsolutePath());
                        }
                    }
                }
            } else {
                System.err.println("Invalid display index: " + displayIndex);
            }
        } catch (IOException | InterruptedException | java.net.URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
