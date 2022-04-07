package mx.unam.convertidorunidades;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ConvertidorUi extends Application {

    StringProperty etiqueta1 = new SimpleStringProperty("Unidad1");
    StringProperty etiqueta2 = new SimpleStringProperty("Unidad2");

    @Override
    public void start(Stage stage) throws IOException {

        Label labelUnidad1 = new Label(etiqueta1.getValue());
        Label labelUnidad2 = new Label(etiqueta2.getValue());

        TextField textFieldUnidadUno = new TextField();
        TextField textFieldUnidadDos = new TextField();

        Slider slider = new Slider(0, 100, 0);
        slider.setMajorTickUnit(10.0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        HBox hBoxBloque1 = new HBox(2, textFieldUnidadUno, labelUnidad1);
        HBox hBoxBloque2 = new HBox(2, textFieldUnidadDos, labelUnidad2);
        HBox.setMargin(textFieldUnidadUno, new Insets(30, 0, 20, 10));
        HBox.setMargin(labelUnidad1, new Insets(30, 0, 20, 10));
        HBox.setMargin(textFieldUnidadDos, new Insets(30, 0, 10, 10));
        HBox.setMargin(labelUnidad2, new Insets(30, 0, 10, 10));

        VBox vBoxGrupo = new VBox(hBoxBloque1, slider, hBoxBloque2);
        VBox.setMargin(slider, new Insets(0, 0, 0, 10));

        Pane pane = new Pane(vBoxGrupo);

        Scene scene = new Scene(pane, 400, 180);
        stage.setTitle("Convertidor de unidades");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}