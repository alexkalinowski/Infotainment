package sample;

import java.io.IOException;
import java.lang.Math;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture.State;
import com.sun.javafx.geom.Point2D;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx. beans.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class CustomListener extends Listener {

    private final ObjectProperty<Point2D> point = new SimpleObjectProperty<>();
    public ObservableValue<Point2D> pointProperty() {return point;}
    public VolumeController volumeController;

    public CustomListener(VolumeController volumeController){
        this.volumeController = volumeController;
    }

    public void onInit(com.leapmotion.leap.Controller controller) {
        System.out.println("Initialized");
    }


    public void onConnect(com.leapmotion.leap.Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }



    public void onDisconnect(com.leapmotion.leap.Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }


    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information

    }
}


