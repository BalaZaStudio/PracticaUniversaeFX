module com.myproyectodam.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media; 
 

    opens com.myproyectodam.demo to javafx.fxml;
    exports com.myproyectodam.demo;
    requires com.google.gson;
}

