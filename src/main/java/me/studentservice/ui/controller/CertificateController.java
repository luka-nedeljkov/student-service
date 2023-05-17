package me.studentservice.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javafx.embed.swing.SwingFXUtils;
import me.studentservice.model.TableStudentData;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class CertificateController {

	@FXML
	private TextField birthDate;
	@FXML
	private TextField city;
	@FXML
	private TextField course;
	@FXML
	private TextField date;
	@FXML
	private TextField educationalProfile;
	@FXML
	private TextField name;
	@FXML
	private TextField parent;
	@FXML
	private TextField refNumber;
	@FXML
	private TextField school;
	@FXML
	private TextField schoolClass;
	@FXML
	private TextField yearP1;
	@FXML
	private TextField yearP2;
	@FXML
	private Button button;

	TableStudentData selectedStudent;
	Random random;

	public CertificateController(Stage stage, TableStudentData tableStudentData) {
		try {
			selectedStudent = tableStudentData;
			random = new Random();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/studentservice/ui/fxml/Certificate.fxml"));
			loader.setController(this);
			stage.setScene(new Scene(loader.load()));
			stage.setResizable(false);
			stage.setTitle("Potvrda");
			stage.show();
			init();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void init() {
		LocalDate curDate = LocalDate.now();

		refNumber.setText("POT-" + ((Math.abs(random.nextInt()) % 100) + 1) + "/23");
		date.setText(curDate.toString());
		name.setText(selectedStudent.getName() + " " + selectedStudent.getSurname());

		if(!selectedStudent.getFather().equals("/")) {
			parent.setText(selectedStudent.getFather());
		} else if(!selectedStudent.getMother().equals("/")) {
			parent.setText(selectedStudent.getMother());
		} else {
			parent.setText("/");
		}

		birthDate.setText(selectedStudent.getBirthDate());
		school.setText("Tehnička škola \"9. maj\"");
		city.setText("Bačkoj Palanci");
		course.setText("/");

		switch(selectedStudent.getSchoolClass().charAt(selectedStudent.getSchoolClass().length() - 1)) {
			case '1' -> educationalProfile.setText("elektrotehničar informacionih tehnologija");
			case '2' -> educationalProfile.setText("administrator računarskih mreža");
			case '3' -> educationalProfile.setText("tehničar multimedije");
			case '4' -> educationalProfile.setText("tehničar za kompjutersko upravljanje (CNC) mašina");
			case '5' -> educationalProfile.setText("mehaničar motornih vozila");
			case '6' -> educationalProfile.setText("bravar - zavarivač");
		}

		if(curDate.getMonth().getValue() < 9) {
			yearP1.setText(Integer.toString(curDate.getYear() - 1));
			yearP2.setText(Integer.toString(curDate.getYear()));
		} else {
			yearP1.setText(Integer.toString(curDate.getYear()));
			yearP2.setText(Integer.toString(curDate.getYear() + 1));
		}

		if(selectedStudent.getSchoolClass().startsWith("IV")) {
			schoolClass.setText("četvrti");
		} else if(selectedStudent.getSchoolClass().startsWith("III")) {
			schoolClass.setText("treći");
		} else if(selectedStudent.getSchoolClass().startsWith("II")) {
			schoolClass.setText("drugi");
		} else if(selectedStudent.getSchoolClass().startsWith("I")) {
			schoolClass.setText("prvi");
		}
	}

	@FXML
	private void saveAsImage() {
		button.setVisible(false);
		WritableImage writableImage = name.getScene().snapshot(null);
		File file = new File("Potvrda.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		button.setVisible(true);
	}

}
