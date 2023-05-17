package me.studentservice.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import me.studentservice.model.SchoolClass;
import me.studentservice.model.Student;
import me.studentservice.model.Subject;
import me.studentservice.model.TableStudentData;
import me.studentservice.utils.SQLUtils;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	@FXML
	private TableColumn<TableStudentData, String> address;
	@FXML
	private TableColumn<TableStudentData, String> birthDate;
	@FXML
	private TableColumn<TableStudentData, String> father;
	@FXML
	private TableColumn<TableStudentData, String> gender;
	@FXML
	private TableColumn<TableStudentData, String> homeroom;
	@FXML
	private TableColumn<TableStudentData, String> schoolClass;
	@FXML
	private TableColumn<TableStudentData, String> gpa;
	@FXML
	private TableColumn<TableStudentData, String> mother;
	@FXML
	private TableColumn<TableStudentData, String> name;
	@FXML
	private TableColumn<TableStudentData, String> previousGpa;
	@FXML
	private TableColumn<TableStudentData, String> surname;
	@FXML
	private TableView<TableStudentData> table;

	@FXML
	private TableColumn<Subject, String> subjectName;
	@FXML
	private TableColumn<Subject, String> subjectClass;
	@FXML
	private TableView<Subject> subjectTable;

	@FXML
	private ChoiceBox<SchoolClass> classPicker;
	@FXML
	private ChoiceBox<String> genderPicker;

	@FXML
	private ImageView logo;

	SQLUtils sqlUtils;

	@FXML
	private void generateCertificate() {
		if(table.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Nema učenika");
			alert.setContentText("Molimo vas izaberite učenika");
			alert.show();
			return;
		}
		new CertificateController(new Stage(), table.getSelectionModel().getSelectedItem());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		sqlUtils = new SQLUtils();

		logo.setImage(new Image(getClass().getResourceAsStream("/me/studentservice/assets/logo.png")));

		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		address.setCellFactory(TextFieldTableCell.forTableColumn());

		birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		birthDate.setCellFactory(TextFieldTableCell.forTableColumn());

		father.setCellValueFactory(new PropertyValueFactory<>("father"));
		father.setCellFactory(TextFieldTableCell.forTableColumn());

		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		gender.setCellFactory(TextFieldTableCell.forTableColumn());

		gpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
		gpa.setCellFactory(TextFieldTableCell.forTableColumn());

		mother.setCellValueFactory(new PropertyValueFactory<>("mother"));
		mother.setCellFactory(TextFieldTableCell.forTableColumn());

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());

		previousGpa.setCellValueFactory(new PropertyValueFactory<>("previousGpa"));
		previousGpa.setCellFactory(TextFieldTableCell.forTableColumn());

		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		surname.setCellFactory(TextFieldTableCell.forTableColumn());

		homeroom.setCellValueFactory(new PropertyValueFactory<>("homeroom"));

		schoolClass.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));

		subjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
		subjectClass.setCellValueFactory(new PropertyValueFactory<>("className"));

		genderPicker.getItems().add("M");
		genderPicker.getItems().add("F");
		genderPicker.getItems().add("NB");
		genderPicker.getItems().add("/");
		genderPicker.getItems().add("");
		genderPicker.getSelectionModel().select(4);
		updateTables();
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
			list.add(null);
			sqlUtils.disconnect();
			classPicker.setItems(list);
			classPicker.getSelectionModel().select(2);
		} catch(SQLException se) {
			se.printStackTrace();
		}

		classPicker.getSelectionModel().selectedItemProperty().addListener(observable -> updateTables());
		genderPicker.getSelectionModel().selectedItemProperty().addListener(observable -> updateTables());
	}

	@FXML
	private void refresh(KeyEvent event) {
		if(event.getCode() == KeyCode.F5) {
			updateTables();
		}
	}

	private void updateTables() {
		try {
			sqlUtils.connect();
			StringBuilder sql = new StringBuilder("select STUDENT_ID, student.CLASS_ID, STUDENT_NAME, STUDENT_SURNAME, STUDENT_GENDER, STUDENT_BIRTH_DATE, STUDENT_ADDRESS, STUDENT_FATHER, STUDENT_MOTHER, STUDENT_GPA, STUDENT_PREVIOUS_GPA, TEACHER_NAME, CLASS_NAME\n" +
					"from student inner join school_class on student.CLASS_ID = school_class.CLASS_ID\n" +
					"inner join homeroom_teacher on school_class.TEACHER_ID = homeroom_teacher.TEACHER_ID");
			if(classPicker.getSelectionModel().getSelectedItem() != null || !genderPicker.getSelectionModel().getSelectedItem().equals("")) {
				sql.append(" where ");
				if(classPicker.getSelectionModel().getSelectedItem() != null) {
					sql.append("school_class.class_id = ").append(classPicker.getSelectionModel().getSelectedItem().getId());
				}
				if(classPicker.getSelectionModel().getSelectedItem() != null && !genderPicker.getSelectionModel().getSelectedItem().equals("")) {
					sql.append(" and ");
				}
				if(!genderPicker.getSelectionModel().getSelectedItem().equals("")) {
					sql.append("student.student_gender = '").append(genderPicker.getSelectionModel().getSelectedItem()).append("'");
				}
			}
			sql.append(";");
			System.out.println(sql);
			ResultSet rs = sqlUtils.exequteSelectQuery(sql.toString());
			ObservableList<TableStudentData> list = FXCollections.observableArrayList();
			while(rs.next()) {
				list.add(new TableStudentData(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13)
				));
			}
			sqlUtils.disconnect();
			table.setItems(list);
		} catch(SQLException se) {
			se.printStackTrace();
		}
		try {
			sqlUtils.connect();
			ResultSet rs = sqlUtils.exequteSelectQuery("select subject.SUBJECT_ID, school_class.CLASS_ID, SUBJECT_NAME, CLASS_NAME from subject\n" +
					"inner join subject2class on subject.SUBJECT_ID = subject2class.SUBJECT_ID\n" +
					"inner join school_class on subject2class.CLASS_ID = school_class.CLASS_ID" +
					(classPicker.getSelectionModel().getSelectedItem() == null ?
							";" : (" where school_class.class_id = " + classPicker.getSelectionModel().getSelectedItem().getId() + ";")));
			ObservableList<Subject> list = FXCollections.observableArrayList();
			while(rs.next()) {
				list.add(new Subject(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getString(4)
				));
			}
			sqlUtils.disconnect();
			subjectTable.setItems(list);
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}

	@FXML
	private void delete() {
		TableStudentData temp = table.getSelectionModel().getSelectedItem();
		try {
			sqlUtils.executeQuery("delete from student where student_id = " + temp.getId() + ";");
		} catch(NullPointerException ignored) {

		}
		updateTables();
	}

	@FXML
	private void insert() {
		StudentInputDialog dialog = new StudentInputDialog(table.getScene().getWindow());
		Optional<Student> optionalStudent = dialog.showAndWait();
		if(optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			sqlUtils.executeQuery("INSERT INTO student_service.student VALUES " +
					"(" + student.getId() + ", " + student.getClassId() + ", '" + student.getName() +
					"', '" + student.getSurname() + "', '" + student.getGender() + "', '" + student.getBirthDate() +
					"', '" + student.getAddress() + "', '" + student.getFather() + "', '" + student.getMother() +
					"', '" + student.getGpa() + "', '" + student.getPreviousGpa() + "');");
			updateTables();
		}
	}

	@FXML
	private void update() {
		if(table.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Nema učenika");
			alert.setContentText("Molimo vas izaberite učenika");
			alert.show();
			return;
		}
		Student student = null;
		try {
			sqlUtils.connect();
			ResultSet rs = sqlUtils.exequteSelectQuery("select * from student where student_id = " + table.getSelectionModel().getSelectedItem().getId() + ";");
			rs.next();
			student = new Student(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11)
			);
		} catch(SQLException se) {
			se.printStackTrace();
		}
		StudentUpdateDialog dialog = new StudentUpdateDialog(table.getScene().getWindow(), student);
		Optional<Student> optionalStudent = dialog.showAndWait();
		if(optionalStudent.isPresent()) {
			Student temp = optionalStudent.get();
			sqlUtils.executeQuery("update student set class_id = " + temp.getClassId() + "\n" +
					", student_name = '" + temp.getName() + "'\n" +
					", student_surname = '" + temp.getSurname() + "'\n" +
					", STUDENT_GENDER = '" + temp.getGender() + "'\n" +
					", STUDENT_BIRTH_DATE = '" + temp.getBirthDate() + "'\n" +
					", STUDENT_ADDRESS = '" + temp.getAddress() + "'\n" +
					", STUDENT_FATHER = '" + temp.getFather() + "'\n" +
					", STUDENT_MOTHER = '" + temp.getMother() + "'\n" +
					", STUDENT_GPA = '" + temp.getGpa() + "'\n" +
					", STUDENT_PREVIOUS_GPA = '" + temp.getPreviousGpa() + "'\n" +
					"where student_id = " + temp.getId() + ";");
			updateTables();
		}
	}

}