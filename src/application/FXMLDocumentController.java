package application;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.leapmotion.leap.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class FXMLDocumentController {

    private Controller controller;

    List<Image> art = new ArrayList<>();

    public void loadImages() {
        for (int i=0; i < 3; i++) {
            art.add(new Image(getClass().getResource( "images/picture_1.jpg").toExternalForm()));
            art.add(new Image(getClass().getResource( "images/picture_2.jpg").toExternalForm()));
        }
    }



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
    @FXML
    private ImageView coverArt;

    private boolean infotainmentStatus = true;

    private Frame frame5;

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
        coverArt.setImage(art.listIterator().next());

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
        loadImages();
        coverArt.setImage(art.get(1));


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
        System.out.println(controller.frame().fingers().get(1).tipPosition().getY());
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
        if (infotainmentStatus) {
            if (extendedFingers == 2) {
                float fingerPos = 0;
                fingerPos = controller.frame().fingers().get(1).tipPosition().getY();
                volumeSlider.setValue(fingerPos);
            }
            if (extendedFingers == 2) {
                volumeSlider.setValue(controller.frame().fingers().get(1).tipPosition().getY());
            }
        }
    }


        //Turn on infotainment pitch up
        public void powerOn () {
            if (controller.frame().hands().leftmost().palmNormal().pitch() >= -0.06 && controller.frame().hands().leftmost().palmNormal().pitch() <= -0.04) {
                setOn();
            }
        }

        //Turn off infotainment system via pitch down
        public void powerOff () {
            if (controller.frame().hands().leftmost().palmNormal().pitch() < -3.55) {
                setOff();
            }
        }


        //Play/Pause by tip
        public void play () {
            if (controller.frame().hands().leftmost().pointables().frontmost().tipPosition().getZ() <= -150) {
                if (controller.frame().hands().leftmost().palmVelocity().getZ() < -50) {
                    setPlay();
                }
            }
        }

        public void pause () {
            int extendedFingers = 0;
            for (Finger finger : controller.frame().hands().leftmost().fingers()) {
                if (finger.isExtended()) extendedFingers++;
            }

            if (extendedFingers == 0 && controller.frame().hands().leftmost().palmPosition().getZ() <= -150) {
                if (controller.frame().hands().leftmost().palmVelocity().getZ() < -50) {
                    setPause();
                }
            }
        }


        public void prevSong () {
            if (controller.frame().hands().leftmost().palmVelocity().getX() > 50) {
                if (controller.frame().hands().leftmost().palmPosition().getX() < -160.0) {
                    loadPrevSong();

                }
            }
        }

        public void nextSong () {

            if (controller.frame().hands().leftmost().palmVelocity().getX() > 50) {
                if (controller.frame().hands().leftmost().palmPosition().getX() < 160.0) {
                    loadNextSong();
                }
            }
        }


    }

