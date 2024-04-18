package com.myproyectodam.demo;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import java.io.IOException;
import javafx.animation.ParallelTransition;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;

public class SecondaryController implements Initializable {

    private int currentPaneIndex = 0;
    private final double transitionDistance = 829; // Distancia de transición en píxeles

    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane3;
    @FXML
    private AnchorPane pane4;
    @FXML
    private AnchorPane pane5;

    @FXML
    private ImageView next;

    @FXML
    private ImageView back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Ocultar todos los paneles excepto el primero
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
    }

    @FXML
    private void next(MouseEvent event) {
        if (currentPaneIndex < 4) {
            transitionAnimation(currentPaneIndex, ++currentPaneIndex);
            applyReflectionEffect(next);
            mouseEnterZoom(next); // Aplicar efecto de zoom al botón next
            removeReflectionEffect(back); // Quitar efecto de reflejo de back
            mouseExitReset(back); // Quitar zoom del botón back si estaba activo
        }
    }

    @FXML
    private void back(MouseEvent event) {
        if (currentPaneIndex > 0) {
            transitionAnimation(currentPaneIndex, --currentPaneIndex);
            applyReflectionEffect(back);
            mouseEnterZoom(back); // Aplicar efecto de zoom al botón back
            removeReflectionEffect(next); // Quitar efecto de reflejo de next
            mouseExitReset(next); // Quitar zoom del botón next si estaba activo
        }
    }

    private void transitionAnimation(int fromIndex, int toIndex) {
        AnchorPane fromPane = switch (fromIndex) {
            case 0 ->
                pane1;
            case 1 ->
                pane2;
            case 2 ->
                pane3;
            case 3 ->
                pane4;
            case 4 ->
                pane5;
            default ->
                throw new IllegalArgumentException("Invalid fromIndex: " + fromIndex);
        };

        AnchorPane toPane = switch (toIndex) {
            case 0 ->
                pane1;
            case 1 ->
                pane2;
            case 2 ->
                pane3;
            case 3 ->
                pane4;
            case 4 ->
                pane5;
            default ->
                throw new IllegalArgumentException("Invalid toIndex: " + toIndex);
        };

        double distance = toIndex > fromIndex ? transitionDistance : -transitionDistance;

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), fromPane);
        fadeOut.setToValue(0);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), toPane);
        fadeIn.setToValue(1);

        TranslateTransition moveOut = new TranslateTransition(Duration.seconds(0.5), fromPane);
        moveOut.setToX(distance);

        TranslateTransition moveIn = new TranslateTransition(Duration.seconds(0.5), toPane);
        moveIn.setToX(0);

        ParallelTransition parallelTransition = new ParallelTransition(fadeOut, fadeIn, moveOut, moveIn);
        parallelTransition.play();

        parallelTransition.setOnFinished(e -> {
            fromPane.setVisible(false);
            toPane.setVisible(true);
        });
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("Primary");
    }

    @FXML
    private void mouseEnterZoom(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), imageView);
        scaleTransition.setToX(1.2); // Escalar a 120% en X
        scaleTransition.setToY(1.2); // Escalar a 120% en Y
        scaleTransition.play();
    }

    @FXML
    private void mouseExitReset(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), imageView);
        scaleTransition.setToX(1.0); // Volver a 100% en X
        scaleTransition.setToY(1.0); // Volver a 100% en Y
        scaleTransition.play();
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
        dropShadow.setRadius(10); // Reducir el radio para que la sombra sea más pequeña
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.5)); // Color oscuro con opacidad reducida

        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setWidth(15); // Ajustar la anchura del desenfoque
        boxBlur.setHeight(15); // Ajustar la altura del desenfoque
        boxBlur.setIterations(3); // Ajustar la cantidad de iteraciones para el desenfoque

        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);
        blend.setTopInput(dropShadow);
        blend.setBottomInput(boxBlur);

        imageView.setEffect(blend);
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
