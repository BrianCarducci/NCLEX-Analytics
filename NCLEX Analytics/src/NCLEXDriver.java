import java.io.IOException;
import java.util.Hashtable;

public class NCLEXDriver {
	Hashtable<String, Student> hash;
	ExcelParsing excelParsing;

	public NCLEXDriver() throws IOException {
		excelParsing = new ExcelParsing("nclex.xls");
		hash = excelParsing.parse();
		printStudents(hash);

	}

	public static void main(String[] args) throws IOException {
		new NCLEXDriver();

	}

	public void printStudents(Hashtable<String, Student> students) {
		for (String key : hash.keySet()) {
			System.out.println(hash.get(key).getFirstName() + " " + hash.get(key).getLastName() + ", id: "
					+ hash.get(key).getStudentId());
			for (ProctoredAssessment proctored : hash.get(key).getProctoredAssessment()) {
				System.out.print("\t");
				System.out.print(proctored.getName());
				System.out.print(" \t Score: " + proctored.getPercentage());
				System.out.print(" \t Taken on: " + proctored.getDate());
				System.out.print(" \t ProficiencyLevel: " + proctored.getProficiencyLevel());
				System.out.print(" \t Focused Review Time: " + proctored.getFocusedReviewTime());
				System.out.print(" \t Probability of Passing NCLEX: " + proctored.getProbOfPassing());
				System.out.println("");
			}
			for (PracticeAssessment practice : hash.get(key).getPracticeAssessment()) {
				System.out.print("\t");
				System.out.print(practice.getName());
				System.out.print(" \t Score: " + practice.getPercentage());
				System.out.print(" \t Taken on: " + practice.getDate());
				System.out.print(" \t Focused Review Time: " + practice.getFocusedReviewTime());
				System.out.println("");
			}
			System.out.println("--------------------------------------------------------------------------");

		}
	}

}
