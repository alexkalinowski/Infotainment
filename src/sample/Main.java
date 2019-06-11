package sample;

import com.leapmotion.leap.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    CustomListener listener;
    com.leapmotion.leap.Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception{
        listener = new CustomListener(new VolumeController());
        controller.addListener(listener);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Infotainment");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();






        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void stop() {
        controller.removeListener(listener);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
