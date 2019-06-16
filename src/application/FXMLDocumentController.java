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
        this.volumeSlider.setValue(0);
        this.volumeSlider.setDisable(false);
        this.playBtn.setDisable(false);
        this.prevSongBtn.setDisable(false);
        this.nextSongBtn.setDisable(false);
        this.onOffLabel.setText("ON");
        this.infotainmentStatus = true;

    }


    public void setOff() {
        this.volumeSlider.setValue(0);
        this.volumeSlider.setDisable(true);
        this.playBtn.setDisable(true);
        this.nextSongBtn.setDisable(true);
        this.prevSongBtn.setDisable(true);
        this.onOffLabel.setText("OFF");
        this.infotainmentStatus = false;
    }


    @FXML
    private void initialize() {
        controller = new Controller();
        setOn();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                refresh();
            }
        };
        timer.start();

    }

    //Method to observe frames
    public void refresh() {
        System.out.println(controller.frame().hands().leftmost().palmNormal().pitch());
        System.out.println(isInfotainmentStatus());
        setVolume();
        powerOn();
        powerOff();
}

    //Set volume via gesture in y-direction with index finger
    public void setVolume() {
        if (controller.frame().fingers().get(1).isExtended() == true) {
            volumeSlider.setValue(controller.frame().fingers().get(1).tipPosition().getX());
        }

    }


    //Turn on infotainment pitch up
    public void powerOn() {
            if (controller.frame().hands().leftmost().palmNormal().pitch() >= -0.05) {
                setOn();
            }

    }

    //Turn off infotainment system via pitch down
    public void powerOff() {
        if (isInfotainmentStatus()) {
            if (controller.frame().hands().leftmost().palmNormal().pitch() < -2.0) {
                setOff();
            }
        }
    }

    //Play/Pause by tip
    public void playPause() {
        if (!playBtn.isDisabled()) {
            if (controller.frame().hands().leftmost().palmVelocity().getZ() > 20) {
                if (controller.frame().hands().leftmost().finger(1).isExtended()) {
                    playBtn.setDisable(true);
                }
            }
        }
    }


}

