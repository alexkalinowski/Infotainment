package application;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Music {
    List<Image> cover = new ArrayList<>();

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

}