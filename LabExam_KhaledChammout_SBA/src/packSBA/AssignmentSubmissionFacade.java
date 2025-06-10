package packSBA;

public class AssignmentSubmissionFacade {

	private UploadService uploadService;
	private PlagiarismChecker plagiarismChecker;
	private AutomatedTester automatedTester;
	private GradingSystem gradingSystem;
	
	public AssignmentSubmissionFacade(UploadService uploadService, PlagiarismChecker plagiarismChecker,
			AutomatedTester automatedTester, GradingSystem gradingSystem) {
		this.uploadService = uploadService;
		this.plagiarismChecker = plagiarismChecker;
		this.automatedTester = automatedTester;
		this.gradingSystem = gradingSystem;
	}
	public boolean submitAssignment(String studentId, String assignmentId, String filePath) {
		System.out.println( "Facade: Starting submission process for assignment " + assignmentId + " - Interfaces...");
		
		boolean file = uploadService.uploadFile(studentId, assignmentId, filePath);
		if (file) {
			double scaner = plagiarismChecker.scan(assignmentId, filePath);
			//System.out.println(scaner);
			
			double tester = automatedTester.runTests(assignmentId, filePath);
			if (tester == 0 ) return true;
			
			boolean grade = gradingSystem.recordGrade(studentId, assignmentId, tester);
			
			System.out.println("Facade: Submission process completed.");
			System.out.println("Submission successful. ");
			
		}
		else {
			System.out.println("Facade: Submission process isn't completed.");
			return false;
		}return true;
	}
	
	
	
}
