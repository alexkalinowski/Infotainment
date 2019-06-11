package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Window;
import javafx.scene.control.*;
import com.leapmotion.leap.*;



public class Controller {
    @FXML
    private TextArea outputTextArea;

    public Slider getVolume() {
        return volume;
    }

    @FXML
    private Slider volume;


    @FXML
    private void initialize() {


        // Listen for Slider value changes
        volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {

                outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");
            }
        });

    }


public void changeVolume(){
    System.out.println("Volume Changed" + " to " + volume.getValue());


}

public void increaseVolume(){

}



}
