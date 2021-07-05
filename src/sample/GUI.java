package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GUI {

    public static Clip mainSound = playSound(new File("src\\sample\\pictures\\Farmville.wav"));

    public static void addBtnGUI(Button button){
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Clip clip = playSound(new File("src\\sample\\pictures\\mouseMove.wav"));
                        clip.start();
                        button.setEffect(new DropShadow());
                    }
                });

        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        button.setEffect(null);
                    }
                });

        button.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Clip clip = playSound(new File("src\\sample\\pictures\\click.wav"));
                        clip.start();
                    }
                });
    }

    public static void addLabelGUI(ImageView imageView){
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Clip clip = playSound(new File("src\\sample\\pictures\\mouseMove.wav"));
                        clip.start();
                        imageView.setEffect(new DropShadow());
                    }
                });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        imageView.setEffect(null);
                    }
                });

        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Clip clip = playSound(new File("src\\sample\\pictures\\click.wav"));
                        clip.start();
                    }
                });
    }

    public static Clip playSound(File file){
        Clip clip = null;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            if (file.getPath().equals("src\\sample\\pictures\\Farmville.wav"))
                clip.loop(1000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }

    public static void createAlert(Alert.AlertType type, String title, String text){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
