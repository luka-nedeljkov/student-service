module me.studentservice {
	requires javafx.controls;
	requires javafx.fxml;
    requires java.sql;
	requires java.desktop;
	requires javafx.swing;


	opens me.studentservice to javafx.fxml;
	exports me.studentservice;
    exports me.studentservice.ui;
    opens me.studentservice.ui to javafx.fxml;
	exports me.studentservice.ui.controller;
	opens me.studentservice.ui.controller to javafx.fxml;
	opens me.studentservice.model to javafx.base;
	exports me.studentservice.model;
}