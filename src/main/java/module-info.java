module wargames {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.logging;

    opens no.stonedstonar.wargames.ui.controllers to javafx.graphics, javafx.fxml;
    opens no.stonedstonar.wargames.ui.windows to javafx.fxml, javafx.graphics;
    opens no.stonedstonar.wargames.ui to javafx.graphics, javafx.fxml;

    exports no.stonedstonar.wargames.ui.controllers;
    exports no.stonedstonar.wargames.ui.windows;
    exports no.stonedstonar.wargames.ui;

}