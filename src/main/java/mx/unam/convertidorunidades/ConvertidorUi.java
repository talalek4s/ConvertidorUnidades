package mx.unam.convertidorunidades;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ConvertidorUi extends Application {

    StringProperty etiqueta1 = new SimpleStringProperty("Unidad1");
    StringProperty etiqueta2 = new SimpleStringProperty("Unidad2");
    DoubleProperty cm = new SimpleDoubleProperty(null, "cm", 0.0);
    DoubleProperty in = new SimpleDoubleProperty(0.0);
    DoubleProperty medicionSlider = new SimpleDoubleProperty(0.0);

    @Override
    public void start(Stage stage) throws IOException {

        //cambio manual de las unidades
        etiqueta1.set("centimetros(cm)");
        etiqueta2.set("pulgadas(in)");

        //etiquetas para unidades
        Label labelUnidad1 = new Label(etiqueta1.getValue());
        Label labelUnidad2 = new Label(etiqueta2.getValue());

        TextField textFieldUnidadUno = new TextField();
        TextField textFieldUnidadDos = new TextField();

        //etiquetas para unidades
        Label labelMenuDimensiones = new Label("Dimensiones");
        Label labelUnidades = new Label("Unidades");

        Slider slider = new Slider(0, 100, 0);
        slider.setMajorTickUnit(10.0);
        slider.setBlockIncrement(1.0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        //calculos

        medicionSlider.bind(slider.valueProperty());
        cm.bind(medicionSlider);
        in.bind(medicionSlider.divide(2.54));

        //Link mediciones y contenedor
        textFieldUnidadUno.textProperty().bind(cm.asString());
        textFieldUnidadDos.textProperty().bind(in.asString());

        //control textFields
       /* textFieldUnidadUno.setOnMouseClicked(mouseEvent -> {

        });*/


        //Hbox 1
        HBox hBoxBloque1 = new HBox(2, textFieldUnidadUno, labelUnidad1);
        HBox hBoxBloque2 = new HBox(2, textFieldUnidadDos, labelUnidad2);
        HBox.setMargin(textFieldUnidadUno, new Insets(30, 0, 20, 10));
        HBox.setMargin(labelUnidad1, new Insets(30, 0, 20, 10));
        HBox.setMargin(textFieldUnidadDos, new Insets(20, 0, 10, 10));
        HBox.setMargin(labelUnidad2, new Insets(20, 0, 10, 10));

        //Vbox 1
        VBox vBoxGrupo = new VBox(hBoxBloque1, slider, hBoxBloque2);
        VBox.setMargin(slider, new Insets(0, 10, 0, 10));

        //Hbox2
        // aqui van las etiquetas de unidades

        Pane pane = new Pane(vBoxGrupo);

        Scene scene = new Scene(pane, 600, 180);
        stage.setTitle("Convertidor de unidades");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        launch();
    }
}