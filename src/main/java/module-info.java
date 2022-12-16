module presentacio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens presentacio to javafx.fxml;
    exports presentacio;
}
