package me.studentservice.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Window;
import me.studentservice.model.SchoolClass;
import me.studentservice.model.Student;
import me.studentservice.utils.SQLUtils;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StudentUpdateDialog extends Dialog<Student> implements Initializable {

	@FXML
	private TextField address;
	@FXML
	private DatePicker birthDate;
	@FXML
	private TextField father;
	@FXML
	private ChoiceBox<String> gender;
	@FXML
	private TextField gpa;
	@FXML
	private TextField mother;
	@FXML
	private TextField name;
	@FXML
	private TextField previousGpa;
	@FXML
	private ChoiceBox<SchoolClass> schoolClass;
	@FXML
	private TextField surname;
	@FXML
	private ButtonType submitButtonType;

	SQLUtils sqlUtils;
	Student student;

	public StudentUpdateDialog(Window window, Student student) {
		try {
			this.student = student;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/studentservice/ui/fxml/Insert.fxml"));
			loader.setController(this);
			DialogPane root = loader.load();

			initOwner(window);
			initModality(Modality.APPLICATION_MODAL);
			setResizable(false);
			setTitle("Update Student");
			setDialogPane(root);
			setResizable(false);
			setResultConverter(buttonType -> {
				if(!ButtonBar.ButtonData.OK_DONE.equals(buttonType.getButtonData())) {
					return null;
				}
				student.setClassId(schoolClass.getSelectionModel().getSelectedItem().getId());
				student.setName(name.getText());
				student.setSurname(surname.getText());
				student.setGender(gender.getValue());
				student.setBirthDate(birthDate.getValue().toString());
				student.setAddress(address.getText());
				student.setFather(father.getText());
				student.setMother(mother.getText());
				student.setGpa(gpa.getText());
				student.setPreviousGpa(previousGpa.getText());
				return student;
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		gender.getItems().add("M");
		gender.getItems().add("F");
		gender.getItems().add("NB");
		gender.getItems().add("/");
		gender.getSelectionModel().select(0);
		sqlUtils = SQLUtils.getInstance();
		initClass();
		initFields();
	}

	private void initFields() {
		name.setText(student.getName());
		surname.setText(student.getSurname());
		gender.setValue(student.getGender());
		birthDate.setValue(LocalDate.parse(student.getBirthDate()));
		address.setText(student.getAddress());
		father.setText(student.getFather());
		mother.setText(student.getMother());
		gpa.setText(student.getGpa());
		previousGpa.setText(student.getPreviousGpa());
		for(int i = 0; i < schoolClass.getItems().size(); i++) {
			if(schoolClass.getItems().get(i).getId() == student.getClassId()) {
				schoolClass.getSelectionModel().select(i);
			}
		}
	}

	private void initClass() {
		try {
			sqlUtils.connect();
			ResultSet rs = sqlUtils.exequteSelectQuery("select * from school_class");
			ObservableList<SchoolClass> list = FXCollections.observableArrayList();
			while(rs.next()) {
				SchoolClass temp = new SchoolClass(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3)
				);
				list.add(temp);
			}
			schoolClass.setItems(list);
			schoolClass.getSelectionModel().select(0);
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
