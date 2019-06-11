package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Window;
import javafx.scene.control.*;
import com.leapmotion.leap.*;



public class VolumeController {
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


        // Listen for Slider value changes
        volume.valueProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            setVolume(newValue.doubleValue());
            outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");

        }));

    }


public void changeVolume(){
    System.out.println("Volume Changed" + " to " + volume.getValue());


}

public void increaseVolume(){

}



}
