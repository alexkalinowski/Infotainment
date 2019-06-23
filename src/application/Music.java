package application;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Music {
    LinkedList<Image> cover = new LinkedList<>();
    ListIterator<Image> listIterator = cover.listIterator();
    boolean forward = true;


    public void loadImages() {
        cover.add(new Image(getClass().getResource("images/Cover_1.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_2.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_3.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_4.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_5.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_6.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_7.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_8.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_9.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_10.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_11.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_12.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_13.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_14.png").toExternalForm()));
        cover.add(new Image(getClass().getResource("images/Cover_15.png").toExternalForm()));
    }

    public void playNextSong() {
        if (!forward) {
            if (listIterator.hasNext()) {
                listIterator.next();
                forward = true;
            } else {
                forward = false;
            }
        }
    }

    public void playPrevSong() {
        if (forward) {
            if (listIterator.hasPrevious()) {
                listIterator.previous();
                forward = false;
            } else {
                forward = true;
            }
        }
    }




}
