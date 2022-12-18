module M03UF6pracMD {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.base;
    requires java.sql;
    

    opens model to javafx.base;
    
    opens presentacio to javafx.fxml;
    exports presentacio;
}
