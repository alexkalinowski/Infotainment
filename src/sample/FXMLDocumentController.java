package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Window;
import javafx.scene.control.*;
import com.leapmotion.leap.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;



public class FXMLDocumentController {

    private Controller controller;


    @FXML
    private TextArea outputTextArea;


    @FXML
    private Slider volumeSlider;


    @FXML
    private void initialize() {
        controller = new Controller();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                refresh();
            }
        };
        timer.start();

    }


    public void refresh() {
        System.out.println(controller.frame().hands().leftmost().palmPosition().getX());
        if (controller.frame().hands().leftmost().palmVelocity().getX() >= 10.0) {
            volumeSlider.setValue(30.0);
        }


    }
}
