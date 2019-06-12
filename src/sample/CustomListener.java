package sample;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller;

public class CustomListener extends Listener {



    public CustomListener(FXMLDocumentController FXMLDocumentController){
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


