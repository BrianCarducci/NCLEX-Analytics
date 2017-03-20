import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
	private StringProperty firstName, lastName, cohort;

	public Student(String firstName, String lastName, String cohort) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.cohort = new SimpleStringProperty(cohort);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getCohort() {
		return cohort.get();
	}

	public void setCohort(String cohort) {
		this.cohort.set(cohort);;
	}

	public StringProperty cohortProperty() {
		return cohort;
	}
}
