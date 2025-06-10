package bestseller;
public class BookException extends Exception {
	public BookException(String message) {
		super("BookException: " + message);
	}
}
