package application;

import javafx.scene.image.Image;

import java.util.LinkedList;


public class Music {
    LinkedList<Image> cover = new LinkedList<>();


    public void loadImages() {
        for (int i = 1; i <= 15; i++) {
            cover.add(new Image(getClass().getResource("images/Cover_" + i + ".png").toExternalForm()));
        }
    }
}