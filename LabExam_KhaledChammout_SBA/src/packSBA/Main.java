package packSBA;

public class Main {

	public static void main(String[] args) {

		UploadService upload = new UploadService();
		PlagiarismChecker checker = new PlagiarismChecker();
		AutomatedTester tester = new AutomatedTester();
		GradingSystem grading = new GradingSystem();
		
System.out.println("CST8132 Assignment Submission Client (April 7, 2025)");
AssignmentSubmissionFacade facade = new AssignmentSubmissionFacade(upload, checker, tester, grading);

	facade.submitAssignment("041150663", "Lab 3", "D:/MyWork/CST8132/Lab3.java");
	//String studentId, String assignmentId, String filePath
	}
}
