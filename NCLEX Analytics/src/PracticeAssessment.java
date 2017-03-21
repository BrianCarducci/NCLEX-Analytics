
public class PracticeAssessment {
	String name, date, percentage, focusedReviewTime;

	public PracticeAssessment(String name, String date, String percentage, String focusedReviewTime) {
		this.name = name;
		this.date = date;
		this.percentage = percentage;
		this.focusedReviewTime = focusedReviewTime;
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

	public String getFocusedReviewTime() {
		return focusedReviewTime;
	}

}
