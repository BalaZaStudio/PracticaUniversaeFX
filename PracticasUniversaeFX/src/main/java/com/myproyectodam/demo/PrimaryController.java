package com.myproyectodam.demo;

import java.io.IOException;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class PrimaryController {

    private final String soundEffectPath = "file:///C:/Users/zabal/Documents/GitHub/PracticaUniversaeFX/PracticasUniversaeFX/src/main/resources/Sonidos/click.mp3";

    @FXML
    private void switchToSecondary() throws IOException {
        playSoundEffect();
        App.setRoot("Secondary");
    }

    @FXML
    private void switchToTertiary() throws IOException {
        playSoundEffect();
        App.setRoot("Tertiary");
    }

    @FXML
    private void switchToQuaternary() throws IOException {
        playSoundEffect();
        App.setRoot("Quaternary");
    }

    @FXML
    private void switchToQuinary() throws IOException {
        playSoundEffect();
        App.setRoot("Quinary");
    }

    @FXML
    private void switchToSenary() throws IOException {
        playSoundEffect();
        App.setRoot("Senary");
    }

    @FXML
    private void switchToSeptenary() throws IOException {
        playSoundEffect();
        App.setRoot("Septenary");
    }

    private void playSoundEffect() {
        Media sound = new Media(soundEffectPath);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    @FXML
    private void mouseEnterZoom(MouseEvent event) {
        ImageView icon = (ImageView) event.getSource();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), icon);
        scaleTransition.setToX(1.2); // Escalar a 120% en X
        scaleTransition.setToY(1.2); // Escalar a 120% en Y
        scaleTransition.play();

        // Agregar efecto de reflejo
        applyReflectionEffect(icon);
        scaleTransition.setToX(1.0); // Restablecer a 100% en X
        scaleTransition.setToY(1.0); // Restablecer a 100% en Y
        scaleTransition.play();

    }

    @FXML
    private void mouseExitReset(MouseEvent event) {
        ImageView icon = (ImageView) event.getSource();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), icon);
        scaleTransition.setToX(1.0); // Volver a 100% en X
        scaleTransition.setToY(1.0); // Volver a 100% en Y
        scaleTransition.play();

        // Eliminar efecto de reflejo
        removeReflectionEffect(icon);
    }

    @FXML
    private void applyReflectionEffect(ImageView imageView) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(60);
        dropShadow.setColor(Color.WHITE); // Color del reflejo
        imageView.setEffect(dropShadow);
    }

    @FXML
    private void removeReflectionEffect(ImageView imageView) {
        imageView.setEffect(null);
    }

    @FXML
    private void logo(MouseEvent event) throws IOException {
        App.setRoot("Primary");
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}
