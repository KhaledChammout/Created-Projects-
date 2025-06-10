package packSBA;

public class GradingSystem {

	public boolean recordGrade(String studentId, String assignmentId, double score) {
		System.out.println( "Grading System: Recording grade " + score + 
				" for student " + studentId + ", assignment " + assignmentId + " - Interfaces...");
		return true;
	}
}
