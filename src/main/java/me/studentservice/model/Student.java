package me.studentservice.model;

public class Student {

	private int id;
	private int classId;
	private String name;
	private String surname;
	private String gender;
	private String birthDate;
	private String address;
	private String father;
	private String mother;
	private String gpa;
	private String previousGpa;

	public Student() {}

	public Student(int id, int classId, String name, String surname, String gender, String birthDate, String address, String father, String mother, String gpa, String previousGpa) {
		this.id = id;
		this.classId = classId;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthDate = birthDate;
		this.address = address;
		this.father = father;
		this.mother = mother;
		this.gpa = gpa;
		this.previousGpa = previousGpa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public String getPreviousGpa() {
		return previousGpa;
	}

	public void setPreviousGpa(String previousGpa) {
		this.previousGpa = previousGpa;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", classId=" + classId +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", gender='" + gender + '\'' +
				", birthDate=" + birthDate +
				", address='" + address + '\'' +
				", father='" + father + '\'' +
				", mother='" + mother + '\'' +
				", gpa=" + gpa +
				", previousGpa='" + previousGpa + '\'' +
				'}';
	}

}