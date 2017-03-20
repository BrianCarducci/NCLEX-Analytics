import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
	private String firstName, lastName;
	private String studentId, cohort;
	private ArrayList<PracticeAssessment> practiceList;
	private ArrayList<ProctoredAssessment> proctoredList;

	public Student(String firstName, String lastName,String studentId ,String cohort) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentId = studentId;
		this.cohort = cohort;
		practiceList = new ArrayList<PracticeAssessment>();
		proctoredList = new ArrayList<ProctoredAssessment>();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStudentId() {
		return studentId;
	}
	
	public String getCohort() {
		return cohort;
	}

	public ArrayList<ProctoredAssessment> getProctoredAssessment() {
		return proctoredList;
	}

	public void setProctoredAssessment(ProctoredAssessment proctoredAssessment) {
		proctoredList.add(proctoredAssessment);
	}

	public ArrayList<PracticeAssessment> getPracticeAssessment() {
		return practiceList;
	}

	public void setPracticeAssessment(PracticeAssessment practiceAssessment) {
		practiceList.add(practiceAssessment);
	}
}
