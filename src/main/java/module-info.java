module hu.unideb.inf.abdullah.chatapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.unideb.inf.abdullah.chatapplication to javafx.fxml;
    exports hu.unideb.inf.abdullah.chatapplication;
}