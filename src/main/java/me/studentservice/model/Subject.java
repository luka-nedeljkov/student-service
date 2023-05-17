package me.studentservice.model;

public class Subject {

	private int id;
	private int classId;
	private String name;
	private String className;

	public Subject(int id, int classId, String name, String className) {
		this.id = id;
		this.classId = classId;
		this.name = name;
		this.className = className;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", classId=" + classId +
				", name='" + name + '\'' +
				", className='" + className + '\'' +
				'}';
	}

}
