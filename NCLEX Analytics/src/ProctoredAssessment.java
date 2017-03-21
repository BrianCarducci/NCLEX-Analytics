
public class ProctoredAssessment {
	String name, date, percentage, proficiencyLevel, focusedReviewTime, probOfPassing;

	public ProctoredAssessment(String name, String date, String percentage, String proficiencyLevel,
			String focusedReviewTime, String probOfPassing) {
		this.name = name;
		this.date = date;
		this.percentage = percentage;
		this.proficiencyLevel = proficiencyLevel;
		this.focusedReviewTime = focusedReviewTime;
		this.probOfPassing = probOfPassing;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public String getPercentage() {
		return percentage;
	}

	public String getProficiencyLevel() {
		return proficiencyLevel;
	}

	public String getFocusedReviewTime() {
		return focusedReviewTime;
	}

	public String getProbOfPassing() {
		return probOfPassing;
	}
}
