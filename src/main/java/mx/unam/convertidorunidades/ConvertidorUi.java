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
    DoubleProperty cm = new SimpleDoubleProperty(0.0);
    DoubleProperty in = new SimpleDoubleProperty(0.0);
    DoubleProperty m = new SimpleDoubleProperty(0.0);
    DoubleProperty mi = new SimpleDoubleProperty(0.0);
    DoubleProperty ft = new SimpleDoubleProperty(0.0);
    DoubleProperty kg = new SimpleDoubleProperty(0.0);
    DoubleProperty lb = new SimpleDoubleProperty(0.0);
    DoubleProperty medicionSlider = new SimpleDoubleProperty(0.0);

    @Override
    public void start(Stage stage) throws IOException {

//        //cambio manual de las unidades
//        etiqueta1.set("centimetros(cm)");
//        etiqueta2.set("pulgadas(in)");

        SplitPane splitPane = new SplitPane();

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
                RadioButton radioButtonmFt = new RadioButton("m - ft");
                RadioButton radioButtonMMi = new RadioButton("m - millas");
                ToggleGroup radioGroupLongitud = new ToggleGroup();
             //volumen
                RadioButton radioButtoncm3L = new RadioButton("lt - cm3");
                RadioButton radioButtonLGa = new RadioButton("lt- Galones");
                ToggleGroup radioGroupVolumen = new ToggleGroup();
             //masa
                RadioButton radioButtonLbKg = new RadioButton("Kg - Lb");
                ToggleGroup radioGroupMasa = new ToggleGroup();



        //calculos

        medicionSlider.bind(slider.valueProperty());
//        cm.bind(medicionSlider);
//        in.bind(medicionSlider.divide(2.54));
//
//        //Link mediciones y contenedor
//        textFieldUnidadUno.textProperty().bind(cm.asString());
//        textFieldUnidadDos.textProperty().bind(in.asString());




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
                radioButtonmFt.setToggleGroup(radioGroupLongitud);
                radioButtonCmIn.setToggleGroup(radioGroupLongitud);
                radioButtonMMi.setToggleGroup(radioGroupLongitud);


        //La diivsionn de los tres contenedors
        splitPane.getItems().addAll(vBoxGrupo, vBoxMenuUnidades, vBoxUnidades);
        splitPane.setDividerPositions(0.5f, 0.8f, 0.4f);

        // Selleccion de tooglebutton

        toggleButtonLongitud.setOnAction(actionEvent -> {
            vBoxUnidades.getChildren().clear();
            vBoxUnidades.getChildren().addAll(radioButtonmFt, radioButtonCmIn, radioButtonMMi);
        });
        toggleButtonMasa.setOnAction(actionEvent -> {
            vBoxUnidades.getChildren().clear();
            vBoxUnidades.getChildren().addAll(radioButtonLbKg);
        });
        toggleButtonVolumen.setOnAction(actionEvent -> {
            vBoxUnidades.getChildren().clear();
            vBoxUnidades.getChildren().addAll(radioButtoncm3L, radioButtonLGa);
        });

        //CALCULOS DE  SELECCION DE CONVERSION

        radioButtonCmIn.setOnAction(actionEvent -> {

            //cambio manual de las unidades
            etiqueta1.set("centimetros(cm)");
            etiqueta2.set("pulgadas(in)");
            labelUnidad1.textProperty().bind(etiqueta1);
            labelUnidad2.textProperty().bind(etiqueta2);

            cm.bind(medicionSlider);
            in.bind(medicionSlider.divide(2.54));

            //Link mediciones y contenedor
            textFieldUnidadUno.textProperty().bind(cm.asString());
            textFieldUnidadDos.textProperty().bind(in.asString());

        });

        radioButtonMMi.setOnAction(actionEvent -> {
            etiqueta1.set("metros (m)");
            etiqueta2.set("millas (mi)");
            labelUnidad1.textProperty().bind(etiqueta1);
            labelUnidad2.textProperty().bind(etiqueta2);

            m.bind(medicionSlider);
            mi.bind(medicionSlider.multiply(0.00062137));

            textFieldUnidadUno.textProperty().bind(m.asString());
            textFieldUnidadDos.textProperty().bind(mi.asString());
        });

        radioButtonmFt.setOnAction(actionEvent -> {
            etiqueta1.set("metros (m)");
            etiqueta2.set("pies (ft)");
            labelUnidad1.textProperty().bind(etiqueta1);
            labelUnidad2.textProperty().bind(etiqueta2);

            m.bind(medicionSlider);
            ft.bind(medicionSlider.multiply(3.28));

            textFieldUnidadUno.textProperty().bind(m.asString());
            textFieldUnidadDos.textProperty().bind(ft.asString());

        });

        radioButtonLbKg.setOnAction(actionEvent -> {
           etiqueta1.set("Kilogramos (Kg)");
           etiqueta2.set("Libras (Lb)");
           labelUnidad1.textProperty().bind(etiqueta1);
           labelUnidad2.textProperty().bind(etiqueta2);

           kg.bind(medicionSlider);
           lb.bind(medicionSlider.multiply(2.2046));

           textFieldUnidadUno.textProperty().bind(kg.asString());
           textFieldUnidadDos.textProperty().bind(lb.asString());

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