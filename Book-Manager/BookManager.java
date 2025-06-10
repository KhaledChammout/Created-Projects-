package bestseller;

import java.util.Scanner;

public class BookManager {
	private BookList booklist = new BookList();
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.manageBookList();
	}

	private void manageBookList() {
		int option;
		do {
			showMenu();
			option = getUserOption();
			try {
				switch (option) {
				case 1:
					System.out.print("Enter CSV file name to load: ");
					String filename = scanner.nextLine().trim();
					if (filename.isEmpty()) {
						System.out.println("Error: Filename cannot be empty.");
						break;
					}
					booklist.loadBookList(filename);
					break;
				case 2:
					booklist.printBookList();
					break;
				case 3:
					booklist.addBook(createBook());
					break;
				case 4:
					booklist.editBook(getValidIndex(), createBook());
					break;
				case 5:
					booklist.deleteBook(getValidIndex());
					break;
				case 6:
					System.out.print("Enter the name of the file to save booklist: ");
					String csvFile = scanner.nextLine().trim();
					if (csvFile.isEmpty()) {
						System.out.println("Error: Filename cannot be empty.");
						break;
					}
					booklist.saveBookList(csvFile);
					break;
				case 7:
					booklist.searchBook(getSearchString());
					break;
				case 8:
					System.out.println("===============================");
					System.out.println("||    [Application Ended]    ||");
					System.out.println("===============================");
					break;
				default:
					System.out.println("BookException: Invalid entry: enter an integer between 1 and 8");
				}
			} catch (BookException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 8);
	}

	private void showMenu() {
		System.out.println("=====================================");
		System.out.println("||  Menu - Best Sellers OOP/A1     ||");
		System.out.println("||  Team: Khaled Chammout and god  ||");
		System.out.println("=====================================");
		System.out.println("1. Load Booklist");
		System.out.println("2. Show Booklist");
		System.out.println("3. Add book");
		System.out.println("4. Edit a book");
		System.out.println("5. Delete a book");
		System.out.println("6. Save booklist");
		System.out.println("7. Search in the list");
		System.out.println("8. Exit");
		System.out.print("Choose an option: ");
	}

	private int getUserOption() {
		try {
			int option = Integer.parseInt(scanner.nextLine().trim());
			return option;
		} catch (NumberFormatException e) {
			System.out.println("Error: Please enter a valid number.");
			return -1;
		}
	}

	private int getValidIndex() {
		int index = -1;
		while (index < 0) {
			try {
				System.out.print("Enter book index: ");
				index = Integer.parseInt(scanner.nextLine().trim());
				if (index < 0) {
					System.out.println("Error: Index cannot be negative.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Please enter a valid index.");
			}
		}
		return index;
	}

	private String getSearchString() {
		System.out.print("Enter one string for search in the list: ");
		return scanner.nextLine().trim();
	}

	private Book createBook() throws BookException {
		System.out.print("Name: ");
		String name = scanner.nextLine().trim();
		if (name.isEmpty()) throw new BookException("Book name cannot be empty.");

		System.out.print("Author: ");
		String author = scanner.nextLine().trim();
		if (author.isEmpty()) throw new BookException("Author name cannot be empty.");

		System.out.print("Original Language: ");
		String language = scanner.nextLine().trim();
		if (language.isEmpty()) throw new BookException("Language cannot be empty.");

		int year = getValidYear();

		float sales = getValidSales();

		System.out.print("Genre: ");
		String genre = scanner.nextLine().trim();
		if (genre.isEmpty()) throw new BookException("Genre cannot be empty.");

		return new Book(name, author, language, year, sales, genre);
	}

	private int getValidYear() {
		while (true) {
			try {
				System.out.print("First Published: ");
				int year = Integer.parseInt(scanner.nextLine().trim());
				if (year > 0 && year < 2026) {
					return year;
				} else {
					System.out.println("BookException: The year must be positive between 0 and 2025");

				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Please enter a valid year.");
			}
		}
	}

	private float getValidSales() {
		while (true) {
			try {
				System.out.print("Million of Sales: ");
				float sales = Float.parseFloat(scanner.nextLine().trim());
				if (sales >= 0) {
					return sales;
				} else {
					System.out.println("Error: Sales must be a positive number.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Please enter a valid number for sales.");
			}
		}
	}
}
