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



public class VolumeController {

    private Controller controller;


    @FXML
    private TextArea outputTextArea;


    public Slider getVolume() {
        return volume;
    }

    public void setVolume(double value) {
        this.volume.setValue(value);
    }

    @FXML
    private Slider volume;


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

}

public void changeVolume(){
    System.out.println("Volume Changed" + " to " + volume.getValue());


}

public void increaseVolume(){

}



}
