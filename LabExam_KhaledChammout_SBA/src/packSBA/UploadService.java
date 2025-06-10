package packSBA;

public class UploadService {

	public boolean uploadFile(String studentId, String assignmentId, String filePath) {
		System.out.println("Upload Service: Uploading " + filePath + " for student "
				+ studentId + ", assignment " + assignmentId + " - Interfaces...")	;
		return true;
	}
}
