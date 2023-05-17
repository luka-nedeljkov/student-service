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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentInputDialog extends Dialog<Student> implements Initializable {

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

	public StudentInputDialog(Window window) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/studentservice/ui/fxml/Insert.fxml"));
			loader.setController(this);
			DialogPane root = loader.load();

			initOwner(window);
			initModality(Modality.APPLICATION_MODAL);
			setResizable(false);
			setTitle("Add Student");
			setDialogPane(root);
			setResizable(false);
			setResultConverter(buttonType -> {
				if(!ButtonBar.ButtonData.OK_DONE.equals(buttonType.getButtonData())) {
					return null;
				}
				return new Student(
						getMaxId(),
						schoolClass.getSelectionModel().getSelectedItem().getId(),
						name.getText(),
						surname.getText(),
						gender.getValue(),
						birthDate.getValue().toString(),
						address.getText(),
						father.getText(),
						mother.getText(),
						gpa.getText(),
						previousGpa.getText()
				);
			});
		} catch(NullPointerException | IOException npe) {
			npe.printStackTrace();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		gender.getItems().add("M");
		gender.getItems().add("F");
		gender.getItems().add("NB");
		gender.getItems().add("/");
		gender.getSelectionModel().select(0);
		sqlUtils = new SQLUtils();
		initClass();
	}

	private int getMaxId() {
		try {
			sqlUtils.connect();
			ResultSet rs = sqlUtils.exequteSelectQuery("select max(STUDENT_ID) from student");
			rs.next();
			int id = rs.getInt(1) + 1;
			System.out.println(id);
			sqlUtils.disconnect();
			return id;
		} catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}
	}

	private void initClass() {
		try {
			sqlUtils.connect();
			ResultSet rs = sqlUtils.exequteSelectQuery("select * from school_class");
			ObservableList<SchoolClass> list = FXCollections.observableArrayList();
			while(rs.next()) {
				list.add(new SchoolClass(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3)
				));
			}
			schoolClass.setItems(list);
			schoolClass.getSelectionModel().select(0);
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
