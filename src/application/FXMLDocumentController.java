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

    public void loadNextSong() {
        System.out.println("next Song");
    }

    public void loadPrevSong() {
        System.out.println("previous Song");
    }

    public void setPlay() {
        this.playBtn.setSelected(true);
        this.playBtn.setText("Pause");
    }

    public void setPause() {
        this.playBtn.setSelected(false);
        this.playBtn.setText("Play");
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

    //Method to observe frames
    public void refresh() {
        pause();
        setVolume();
        powerOn();
        powerOff();
        play();
        pause();
        nextSong();
        prevSong();
    }

    //Set volume via gesture in y-direction with index finger
    public void setVolume() {
        int extendedFingers = 0;
        for (Finger finger : controller.frame().hands().leftmost().fingers()) {
            if (finger.isExtended()) extendedFingers++;
        }

        if (extendedFingers==2){

                volumeSlider.setValue(controller.frame().fingers().get(1).tipPosition().getY());

        }
    }


    //Turn on infotainment pitch up
    public void powerOn() {

        if (controller.frame().hands().leftmost().palmNormal().pitch() >= -0.06 && controller.frame().hands().leftmost().palmNormal().pitch() <= -0.04) {
            setOn();
        }
    }

    //Turn off infotainment system via pitch down
    public void powerOff() {
        if (controller.frame().hands().leftmost().palmNormal().pitch() < -2.55) {
            setOff();
        }
    }


    //Play/Pause by tip
    public void play() {
        if (controller.frame().hands().leftmost().pointables().frontmost().tipPosition().getZ() <= -150) {
            if (controller.frame().hands().leftmost().palmVelocity().getZ() < -50) {
                setPlay();
            }
        }
    }

    public void pause() {
        int extendedFingers = 0;
        for (Finger finger : controller.frame().hands().leftmost().fingers()) {
            if (finger.isExtended()) extendedFingers++;
        }

        if (extendedFingers==0 && controller.frame().hands().leftmost().palmPosition().getZ() <= -150){
            if (controller.frame().hands().leftmost().palmVelocity().getZ()< -50) {
                setPause();
            }
        }
    }


    public void prevSong() {
        if (controller.frame().hands().leftmost().palmVelocity().getX() > 80){
            if (controller.frame().hands().leftmost().palmPosition().getX() < -160.0){
                loadPrevSong();
            }
        }
    }

    public void nextSong() {
        if (controller.frame().hands().leftmost().palmVelocity().getX() > 80){
            if (controller.frame().hands().leftmost().palmPosition().getX() > 160.0){
                loadNextSong();
            }
        }
    }



}

