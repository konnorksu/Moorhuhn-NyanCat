module com.example.nyanmoorhuhn01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.nyanmoorhuhn01 to javafx.fxml;
    exports com.example.nyanmoorhuhn01;
}