module cn.edu.sspu.miracle.movingball {
    requires javafx.controls;
    requires javafx.fxml;


    opens cn.edu.sspu.miracle.movingball to javafx.fxml;
    exports cn.edu.sspu.miracle.movingball;
}