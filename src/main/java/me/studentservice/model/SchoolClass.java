package me.studentservice.model;

public class SchoolClass {

	private int id;
	private int teacherId;
	private String name;

	public SchoolClass() {}

	public SchoolClass(int id, int teacherId, String name) {
		this.id = id;
		this.teacherId = teacherId;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
