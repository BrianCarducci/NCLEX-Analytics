
public class PracticeAssessment {
	String date, percentage, proficiencyLevel, focusedReviewTime;

	public PracticeAssessment(String date, String percentage, String proficiencyLevel, String focusedReviewTime) {
		this.date = date;
		this.percentage = percentage;
		this.proficiencyLevel = proficiencyLevel;
		this.focusedReviewTime = focusedReviewTime;
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

}
