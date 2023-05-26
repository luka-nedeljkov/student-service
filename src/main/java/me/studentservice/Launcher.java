package me.studentservice;

import me.studentservice.ui.MainApplication;
import me.studentservice.utils.DatabaseInitializer;

public class Launcher {

    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        MainApplication.main(args);
    }

}
