module org.example.javabeans {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens org.example.javabeans to javafx.fxml;
    exports org.example.javabeans;
}