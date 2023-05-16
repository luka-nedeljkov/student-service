module me.studentservice {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens me.studentservice to javafx.fxml;
    exports me.studentservice;
}