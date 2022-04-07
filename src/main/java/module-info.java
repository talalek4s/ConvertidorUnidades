module mx.unam.convertidorunidades {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.unam.convertidorunidades to javafx.fxml;
    exports mx.unam.convertidorunidades;
}