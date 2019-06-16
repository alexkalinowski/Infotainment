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
    private ToggleButton playBtn;
    @FXML
    private Button prevSongBtn;
    @FXML
    private Button nextSongBtn;
    @FXML
    private Label onOffLabel;

    private boolean infotainmentStatus;


    public boolean isInfotainmentStatus() {
        return this.infotainmentStatus;
    }

    public void setOn() {
        this.infotainmentStatus = true;
        volumeSlider.setValue(0);
        volumeSlider.setDisable(false);
        playBtn.setDisable(false);
        prevSongBtn.setDisable(false);
        nextSongBtn.setDisable(false);
        onOffLabel.setText("ON");
    }


    public void setOff() {
        this.infotainmentStatus = false;
        volumeSlider.setValue(0);
        volumeSlider.setDisable(true);
        playBtn.setDisable(true);
        nextSongBtn.setDisable(true);
        prevSongBtn.setDisable(true);
        onOffLabel.setText("OFF");
    }


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
        System.out.println(controller.frame().hands().leftmost().palmNormal().pitch());
        setVolume();

            powerOn();


            powerOff();



    }

    // Set volume via gesture in y-direction with index finger
    public void setVolume() {

        if (controller.frame().fingers().get(1).isExtended() == true) {
            volumeSlider.setValue(controller.frame().fingers().get(1).tipPosition().getX());
        }

    }


    // Turn on infotainment system via hand turn
    public void powerOn() {
        if(!isInfotainmentStatus()){


            if (controller.frame().hands().leftmost().palmNormal().pitch() >= -0.05) {
                setOn();
            }
        }

    }

    // Turn off infotainment system via hand turn in the opposite direction
    public void powerOff() {
        if (isInfotainmentStatus()) {

            if (controller.frame().hands().leftmost().palmNormal().pitch() < -2.0) {
                setOff();
            }
        }

    }


}

