package me.studentservice.utils;

import java.io.File;

public class DatabaseInitializer {

	private static final String sqlBlock = """
			drop table if exists homeroom_teacher;
			drop table if exists school_class;
			drop table if exists student;
			drop table if exists subject;
			drop table if exists subject2class;
			   
			    create table homeroom_teacher (
			    teacher_id integer primary key autoincrement,
			    teacher_name text
			);
			   
			create table school_class (
			    class_id integer primary key autoincrement,
			    teacher_id integer,
			    class_name text,
			    foreign key (teacher_id)
			        references homeroom_teacher (teacher_id)
			);
			   
			create table student (
			    student_id integer primary key autoincrement,
			    class_id integer,
			    student_name text,
			    student_surname text,
			    student_gender text,
			    student_birth_date text,
			    student_address text,
			    student_father text,
			    student_mother text,
			    student_gpa text,
			    student_previous_gpa text,
			    foreign key (class_id)
			        references school_class (class_id)
			);
			   
			create table subject (
			    subject_id integer primary key autoincrement,
			    subject_name text
			);
			   
			create table subject2class (
			    subject_id integer,
			    class_id integer,
			    primary key (subject_id, class_id),
			    foreign key (subject_id)
			        references subject (subject_id),
			    foreign key (class_id)
			        references school_class (class_id)
			);
			   
			INSERT INTO homeroom_teacher (teacher_name) VALUES ('Svetlana Erceg');
			INSERT INTO homeroom_teacher (teacher_name) VALUES ('Maja Kozomora');
			   
			INSERT INTO school_class (teacher_id, class_name) VALUES (1, 'IV-1');
			INSERT INTO school_class (teacher_id, class_name) VALUES (2, 'IV-2');
			   
			INSERT INTO student VALUES (1, 1, 'Boris', 'Avgustinov', 'M', '2005-02-02', 'Kralja Petra I 1', 'Marko', 'Jovana', '4.6', '4.45');
			INSERT INTO student VALUES (2, 1, 'Milan', 'Agbaba', 'M', '2005-06-10', 'Vatroslava Jagica 3a', 'Nikola', 'Marija', '5.0', '5.0');
			INSERT INTO student VALUES (3, 1, 'Luka', 'Nedeljkov', 'M', '2004-02-15', 'Konstantina Lekica 3', 'Zeljko', 'Ana', '5.0', '5.0');
			INSERT INTO student VALUES (4, 1, 'Stevan', 'Boskov', 'M', '2004-06-15', ' Visnjicka 25', 'Filip', 'Ema', '4.8', '4.00');
			INSERT INTO student VALUES (5, 1, 'Andrija', 'Kalic', 'M', '2004-09-23', 'Prominska 85', 'Borislav', 'Vesna', '3.0', '3.50');
			INSERT INTO student VALUES (6, 1, 'Aleksandar', 'Petrovic', 'M', '2004-08-03', 'Kralja Petra I 1', 'Petar', 'Sofija', '5.0', '4.50');
			INSERT INTO student VALUES (7, 1, 'Stefan', 'Basic', 'M', '2005-03-17', 'Kralja Petra I 1', 'Goran', 'Lara', '5.0', '4.45');
			INSERT INTO student VALUES (8, 1, 'Aleksandar', 'Pavkovic', 'M', '2004-08-25', 'Kralja Petra I 1', 'Zoran', 'Ema', '3.50', '4.00');
			INSERT INTO student VALUES (9, 1, 'Bojan', 'Milovanovic', 'M', '2004-02-20', 'Kralja Petra I 1', 'Jovan', 'Andjela', '5.0', '3.80');
			INSERT INTO student VALUES (10, 1, 'Damir', 'Rocevic', 'M', '2004-04-21', 'Kralja Petra I 1', 'Janko', 'Snezana', '4.5', '4.25');
			INSERT INTO student VALUES (11, 1, 'Damjan', 'Jocic', 'M', '2004-08-20', 'Kralja Petra I 1', 'Dusan', 'Ljubica', '5.0', '4.45');
			INSERT INTO student VALUES (12, 1, 'Damjan', 'Kralj', 'M', '2004-03-14', 'Kralja Petra I 1', 'Tomislav', 'Dragica', '4.8', '5.00');
			INSERT INTO student VALUES (13, 1, 'Davor', 'Fodora', 'M', '2004-10-11', 'Kralja Petra I 1', 'Miodrag', 'Vera', '4.2', '3.45');
			INSERT INTO student VALUES (14, 1, 'Dejan', 'Bajic', 'M', '2004-05-22', 'Kralja Petra I 1', 'Marko', 'Slavica', '3.0', '2.45');
			INSERT INTO student VALUES (15, 1, 'Aleksandra', 'Struharik', 'F', '2004-07-16', 'Kralja Petra I 1', 'Miroslav', 'Ela', '4.6', '4.45');
			INSERT INTO student VALUES (16, 1, 'Jovan', 'Pasin', 'M', '2004-02-18', 'Kralja Petra I 1', 'Vladimir', 'Anica', '4.5', '4.45');
			INSERT INTO student VALUES (17, 1, 'Jovan', 'Radun', 'M', '2004-11-25', 'Kralja Petra I 1', 'Ljubisa', 'Dragana', '5.0', '4.45');
			INSERT INTO student VALUES (18, 1, 'Stefan', 'Karanovic', 'M', '2004-01-04', 'Kralja Petra I 1', 'Nenad', 'Sasa', '4.5', '3.35');
			INSERT INTO student VALUES (19, 1, 'Mihailo', 'Drinic', 'M', '2004-06-18', 'Kralja Petra I 1', 'Predrag', 'Verica', '3.6', '4.45');
			INSERT INTO student VALUES (20, 1, 'Milan', 'Tucev', 'M', '2004-05-12', 'Kralja Petra I 1', 'Milorad', 'Olga', '2.6', '4.00');
			INSERT INTO student VALUES (21, 1, 'Petar', 'Kirtic', 'M', '2004-02-23', 'Kralja Petra I 1', 'Dejan', 'Jasmina', '4.5', '4.50');
			INSERT INTO student VALUES (22, 1, 'Dejan', 'Polic', 'M', '2004-06-23', 'Kralja Petra I 1', 'Nebojsa', 'Milena', '3.5', '4.00');
			INSERT INTO student VALUES (23, 1, 'Radomir', 'Danic', 'M', '2004-08-11', 'Kralja Petra I 1', 'Sasa', 'Olivera', '3.0', '4.00');
			INSERT INTO student VALUES (24, 1, 'Relja', 'Djordjevic', 'M', '2004-05-05', 'Kralja Petra I 1', 'Branko', 'Ljubinka', '5.0', '4.45');
			INSERT INTO student VALUES (25, 1, 'Stefan', 'Vasiljevic', 'M', '2004-08-20', 'Kralja Petra I 1', 'Filip', 'Ruzica', '3.3', '4.50');
			INSERT INTO student VALUES (26, 1, 'Luka', 'Cavka', 'M', '2004-12-05', 'Kralja Petra I 1', 'Emil', 'Vanja', '5.0', '4.80');
			INSERT INTO student VALUES (27, 1, 'Miroslav', 'Vasic', 'M', '2004-05-15', 'Kralja Petra I 1', 'Teodor', 'Anja', '5.0', '4.45');
			INSERT INTO student VALUES (28, 1, 'Nemanja', 'Radjenovic', 'M', '2004-08-22', 'Kralja Petra I 1', 'Luka', 'Milica', '4.5', '4.00');
			INSERT INTO student VALUES (29, 1, 'Lazar', 'Adzic', 'M', '2005-08-24', 'Kralja Petra I 1', 'Danijel', 'Nikolina', '3.3', '4.00');
			INSERT INTO student VALUES (30, 2, 'Branislav', 'Curkovic', 'M', '2004-10-06', 'Kralja Petra I 1', 'Marko', 'Marija', '4.83', '4.50');
			INSERT INTO student VALUES (31, 2, 'Boris', 'Ocenas', 'M', '2004-01-12', 'Kralja Petra I 1', 'Branislav', 'Dragana', '4.83', '4.50');
			INSERT INTO student VALUES (32, 2, 'Dejan', 'Kremenovic', 'M', '2004-12-01', 'Kralja Petra I 1', 'Boban', 'Milica', '4.83', '4.50');
			INSERT INTO student VALUES (33, 2, 'Jovana', 'Bakic', 'F', '2005-02-01', 'Kralja Petra I 1', 'Cedomir', 'Jovana', '4.83', '4.50');
			INSERT INTO student VALUES (34, 2, 'Danilo', 'Mandic', 'M', '2004-02-25', 'Kralja Petra I 1', 'Srboljub', 'Ana', '4.83', '4.50');
			INSERT INTO student VALUES (35, 2, 'Uros', 'Matic', 'M', '2004-05-05', 'Kralja Petra I 1', 'Momir', 'Dragana', '4.83', '4.50');
			INSERT INTO student VALUES (36, 2, 'Ognjen', 'Marjanac', 'M', '2004-08-05', 'Kralja Petra I 1', 'Zdravko', 'Marija', '4.83', '4.50');
			INSERT INTO student VALUES (37, 2, 'Milan', 'Novakovic', 'M', '2004-10-06', 'Kralja Petra I 1', 'Ilija', 'Dragica', '4.83', '4.50');
			   
			INSERT INTO subject (subject_name) VALUES ('Srpski');
			INSERT INTO subject (subject_name) VALUES ('Engleski');
			INSERT INTO subject (subject_name) VALUES ('Matematika');
			INSERT INTO subject (subject_name) VALUES ('Fizicko');
			INSERT INTO subject (subject_name) VALUES ('Preduzetnistvo');
			INSERT INTO subject (subject_name) VALUES ('Programiranje');
			INSERT INTO subject (subject_name) VALUES ('Veb Programiranje');
			INSERT INTO subject (subject_name) VALUES ('Zastita Informacionih Sistema');
			INSERT INTO subject (subject_name) VALUES ('Racunarske Mreze i Internet Servisi');
			INSERT INTO subject (subject_name) VALUES ('Primenjene Informacione Tehnologija');
			INSERT INTO subject (subject_name) VALUES ('Administracija Racunarskih Mreza');
			INSERT INTO subject (subject_name) VALUES ('Racunarske Mreze');
			INSERT INTO subject (subject_name) VALUES ('Serveri');
			INSERT INTO subject (subject_name) VALUES ('Tehnicka Dokumentacija');
			INSERT INTO subject (subject_name) VALUES ('Osnove Racunarstva u Oblaku');
			   
			INSERT INTO subject2class (subject_id, class_id) VALUES (1, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (1, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (2, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (2, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (3, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (3, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (4, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (4, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (5, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (5, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (6, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (7, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (8, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (9, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (10, 1);
			INSERT INTO subject2class (subject_id, class_id) VALUES (11, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (12, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (13, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (14, 2);
			INSERT INTO subject2class (subject_id, class_id) VALUES (15, 2);""";

	public static void initializeDatabase() {
		File file = new File("data.db");
		if(file.exists()) {
			return;
		}
		SQLUtils sqlUtils = SQLUtils.getInstance();
		String[] sqlQueries = sqlBlock.split(";");
		for(String sql : sqlQueries) {
			sqlUtils.createTables(sql);
		}
	}

}
