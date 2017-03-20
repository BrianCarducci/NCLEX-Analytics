
public class Assessment {
	private String assessmentName;
	private double score, focusReviewTime;

	public Assessment(String assessmentName, double score, double focusReviewTime) {
		this.setAssessmentName(assessmentName);
		this.setScore(score);
		this.setFocusReviewTime(focusReviewTime);
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getFocusReviewTime() {
		return focusReviewTime;
	}

	public void setFocusReviewTime(double focusReviewTime) {
		this.focusReviewTime = focusReviewTime;
	}

}
