package application;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.leapmotion.leap.*;


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
        System.out.println(controller.frame().fingers().get(1).tipPosition().getX());
        setVolume();


    }

    // Set volume via gesture in y-direction
    public void setVolume() {

        if (controller.frame().fingers().get(1).isExtended() == true) {
            volumeSlider.setValue(controller.frame().fingers().get(1).tipPosition().getX());
        }
    }

    //





}

