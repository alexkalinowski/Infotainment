package application;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.leapmotion.leap.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class FXMLDocumentController {

    private Controller controller;
    public Music music = new Music();


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
    @FXML
    private Rectangle inactive;


    private boolean infotainmentStatus = true;

    private boolean isSwipingRight = false;

    private boolean isSwipingLeft = false;




    public int coverIterator = 0;

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
        this.inactive.setVisible(false);

    }


    public void setOff() {
        this.volumeSlider.setValue(0);
        this.volumeSlider.setDisable(true);
        this.playBtn.setDisable(true);
        this.nextSongBtn.setDisable(true);
        this.prevSongBtn.setDisable(true);
        this.onOffLabel.setText("OFF");
        this.infotainmentStatus = false;
        this.inactive.setVisible(true);
    }


    public void loadNextSong() {
        if (coverIterator == 14) {
            coverIterator = 0;
        } else {
            coverIterator++;
        }
        //System.out.println("next Song");
        coverArt.setImage(music.cover.get(coverIterator));

    }

    public void loadPrevSong() {
        if (coverIterator == 14 || coverIterator == 0) {
            coverIterator = 0;
        } else {
            coverIterator--;
        }
        //System.out.println("previous Song");
        coverArt.setImage(music.cover.get(coverIterator));
    }

    public void setPlay() {
        this.playBtn.setSelected(true);


    }

    public void setPause() {
        this.playBtn.setSelected(false);
    }


    @FXML
    private void initialize() {
        controller = new Controller();
        music.loadImages();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                refresh();
            }
        };
        timer.start();
    }

    //Method to observe gestures
    public void refresh() {
        if (controller.frame().hands().get(0).finger(1).isExtended()) {
            System.out.println("volumefingers detected");
        }
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

        //TODO die hier noch mit System.out.print testen
        //TODO Mapping anpassen und relative Lautstärkeregelung
        //TODO zum Mapping -> analog zu swipeLeft/Right implementieren mit 3 States
        FingerList allFingers = controller.frame().fingers();
        Finger thumb = allFingers.get(0);
        Finger indexFinger = allFingers.get(1);
        Finger middleFinger = allFingers.get(2);
        Finger ringFinger = allFingers.get(3);
        Finger pinky = allFingers.get(4);


        if (controller.frame().fingers().get(1).isExtended() && controller.frame().fingers().get(2).isExtended()){
            System.out.println("success");
        }



        int extendedFingers = 0;
        for (Finger finger : controller.frame().hands().leftmost().fingers()) {
            if (finger.isExtended()) extendedFingers++;
        }
        if (infotainmentStatus) {
            if (extendedFingers == 2) {
                double currentVolume = volumeSlider.getValue();

                if (controller.frame().fingers().get(1).direction().getY() > 0) {
                    volumeSlider.setValue(currentVolume + controller.frame().fingers().get(1).stabilizedTipPosition().getY() * 0.01);

                } else if (controller.frame().fingers().get(1).direction().getY() < 0) {
                    volumeSlider.setValue(currentVolume - controller.frame().fingers().get(1).stabilizedTipPosition().getY() * 0.01);
                }
            }
        }
    }


    //Turn on infotainment system 2 hand gesture up or down with fingers extended
    public void powerOn() {
        int extendedFingers = 0;
        for (Finger finger : controller.frame().fingers()) {
            if (finger.isExtended()) extendedFingers++;
        }
        if (controller.frame().hands().count() == 2 && extendedFingers == 10) {
            if (controller.frame().hands().rightmost().palmVelocity().getY() > 100) {
                setOn();
                System.out.println("system on");
            }
        }
    }

    //Turn off infotainment system 2 hand gesture up or down with fists
    public void powerOff() {
        int extendedFingers = 0;
        for (Finger finger : controller.frame().fingers()) {
            if (finger.isExtended()) extendedFingers++;
        }

        if (extendedFingers == 0 && controller.frame().hands().count() == 2) {
            if (controller.frame().hands().rightmost().palmVelocity().getY() > 100) {
                setOff();
                System.out.println("system off");
            }
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

    //Punch the screen to pause
    public void pause() {
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

    public void prevSong() {
        if (controller.frame().hands().leftmost().palmVelocity().getX() > 80 && controller.frame().hands().leftmost().palmPosition().getX() < -150) {
            isSwipingLeft = true;
        }

        if (controller.frame().hands().leftmost().palmVelocity().getX() < 40 && isSwipingLeft) {
            isSwipingLeft = false;
            loadPrevSong();
        }
    }

    public void nextSong() {
        if (controller.frame().hands().leftmost().palmVelocity().getX() > 80 && controller.frame().hands().leftmost().palmPosition().getX() > 150) {
            isSwipingRight = true;
        }

        if (controller.frame().hands().leftmost().palmVelocity().getX() < 40 && isSwipingRight) {
            isSwipingRight = false;
            loadNextSong();
        }
    }


}

