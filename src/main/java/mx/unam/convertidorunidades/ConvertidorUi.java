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
import javafx.scene.control.*;
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

        //etiquetas para unidades en split1
        Label labelMenuDimensiones = new Label("Dimensiones");
        Label labelUnidades = new Label("Unidades");
        //etiquetas para selector de unidades en split2


        Slider slider = new Slider(0, 100, 0);
        slider.setMajorTickUnit(10.0);
        slider.setBlockIncrement(1.0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        //ToggleButtons para las unidades
        ToggleButton toggleButtonLongitud = new ToggleButton("Longitud");
        ToggleButton toggleButtonMasa = new ToggleButton("Masa");
        ToggleButton toggleButtonVolumen = new ToggleButton("Volumen");

        ToggleGroup toggleGroupUnidades = new ToggleGroup();

        //radiobutton para seleccionar conversion
            //longitud
                RadioButton radioButtonCmIn = new RadioButton("cm - in");
                RadioButton radioButtonCmFt = new RadioButton("cm - ft");
                RadioButton radioButtonMMi = new RadioButton("m - millas");
                ToggleGroup radioGroupLongitud = new ToggleGroup();



        //calculos

        medicionSlider.bind(slider.valueProperty());
        cm.bind(medicionSlider);
        in.bind(medicionSlider.divide(2.54));

        //Link mediciones y contenedor
        textFieldUnidadUno.textProperty().bind(cm.asString());
        textFieldUnidadDos.textProperty().bind(in.asString());


        SplitPane splitPane = new SplitPane();

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

        //Vbox2 MenuUnidades
        VBox vBoxMenuUnidades = new VBox(labelMenuDimensiones);
        //ToggleGroup
        toggleButtonLongitud.setToggleGroup(toggleGroupUnidades);
        toggleButtonMasa.setToggleGroup(toggleGroupUnidades);
        toggleButtonVolumen.setToggleGroup(toggleGroupUnidades);
        //Agrupar los togglebuttons en vbox
        vBoxMenuUnidades.getChildren().addAll(toggleButtonLongitud, toggleButtonMasa,
                toggleButtonVolumen);

        //Vbox3 Unidades
        VBox vBoxUnidades = new VBox(labelUnidades);
            //toggle radiobutton
                radioButtonCmFt.setToggleGroup(radioGroupLongitud);
                radioButtonCmIn.setToggleGroup(radioGroupLongitud);
                radioButtonMMi.setToggleGroup(radioGroupLongitud);

        //vBoxUnidades.getChildren().addAll(radioButtonCmFt, radioButtonCmIn, radioButtonMMi);

        //La diivsionn de los tres contenedors
        splitPane.getItems().addAll(vBoxGrupo, vBoxMenuUnidades, vBoxUnidades);
        splitPane.setDividerPositions(0.5f, 0.8f, 0.4f);

        // Selleccion de tooglebutton

        toggleButtonLongitud.setOnAction(actionEvent -> {
            vBoxUnidades.getChildren().addAll(radioButtonCmFt, radioButtonCmIn, radioButtonMMi);
        });

        //Pane pane = new Pane(vBoxGrupo);

        Scene scene = new Scene(splitPane, 800, 180);
        stage.setTitle("Convertidor de unidades");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        launch();
    }
}