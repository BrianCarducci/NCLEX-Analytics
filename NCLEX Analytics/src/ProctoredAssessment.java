
public class ProctoredAssessment {
	String date, percentage, proficiencyLevel, focusedReviewTime, probOfPassing;

	public ProctoredAssessment(String date, String percentage, String proficiencyLevel, String focusedReviewTime,
			String probOfPassing) {
		this.date = date;
		this.percentage = percentage;
		this.proficiencyLevel = proficiencyLevel;
		this.focusedReviewTime = focusedReviewTime;
		this.probOfPassing = probOfPassing;
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
