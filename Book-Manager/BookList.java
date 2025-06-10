package bestseller;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class BookList {
	private ArrayList<Book> bestsellers = new ArrayList<>();
	private static final int NUMCOLS = 6;

	public void loadBookList(String csvFile) throws BookException {


		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line;
			boolean isFirstLine = true;

			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}


				String[] data = parseCSVLine(line);

				if (data.length != NUMCOLS) {
					throw new BookException("Incorrect column count in CSV: " + line);
				}

				try {
					bestsellers.add(new Book(
							data[0].trim(), 
							data[1].trim(), 
							data[2].trim(), 
							Integer.parseInt(data[3].trim()), 
							Float.parseFloat(data[4].trim()), 
							data[5].trim()
							));
				} catch (NumberFormatException e) {
					throw new BookException("Invalid number format in CSV: " + line);
				}
			}
			System.out.println("Book list loaded successfully.");
		} catch (IOException e) {
			throw new BookException("File not found or could not be read: " + csvFile + " - " + e.getMessage());
		}
	}

	private String[] parseCSVLine(String line) {
		ArrayList<String> fields = new ArrayList<>();
		boolean inQuotes = false;
		StringBuilder currentField = new StringBuilder();

		for (char c : line.toCharArray()) {
			if (c == '"') {
				inQuotes = !inQuotes; 
			} else if (c == ',' && !inQuotes) {
				fields.add(currentField.toString().trim());
				currentField.setLength(0); 
			} else {
				currentField.append(c);
			}
		}
		fields.add(currentField.toString().trim()); 

		return fields.toArray(new String[0]);
	}

	public void printBookList() {
		// bestsellers.size() checks the size or number of elements in the list  
		for (int i = 0; i < bestsellers.size(); i++) {
			System.out.println("Book[" + i + "] " + bestsellers.get(i));
		}
	}

	public void addBook(Book b) throws BookException {
		if (b == null) throw new BookException("Invalid book data.");
		bestsellers.add(b);
		System.out.println("Book added successfully.");
	}

	public void editBook(int pos, Book b) throws BookException {
		if (pos < 0 || pos >= bestsellers.size()) throw new BookException("Invalid index.");
		bestsellers.set(pos, b);
		System.out.println("Book edited successfully.");
	}

	public void deleteBook(int pos) throws BookException {
		if (pos < 0 || pos >= bestsellers.size()) throw new BookException("Invalid index.");
		bestsellers.remove(pos);
		System.out.println("Book deleted successfully.");
	}

	public void saveBookList(String csvFile) throws BookException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
			for (Book book : bestsellers) {
				bw.write(String.format("%s,%s,%s,%d,%.1f,%s%n",
						book.getName(), book.getAuthor(), book.getOriginalLanguage(),
						book.getFirstPublished(), book.getMillionSales(), book.getGenre()));
			}
			System.out.println("Name of the file to save booklist: " + csvFile);
			System.out.println("File " + csvFile + "created");
		} catch (IOException e) {
			throw new BookException("Error saving book list: " + e.getMessage());
		}
	}



	public void searchBook(String data) throws BookException {
		System.out.println("Results from search... ");
		if (data.isEmpty()) throw new BookException("Search term cannot be empty.");
		boolean found = false;

		for (int i = 0; i < bestsellers.size(); i++) {
			Book book = bestsellers.get(i);
			if (book.getName().contains(data) ||         
					book.getAuthor().contains(data) ||        
					book.getOriginalLanguage().contains(data) ||
					book.getGenre().contains(data)) {

				System.out.println("[" + i + "] " + book);
				found = true;
			}
		}

		if (!found) {
			System.out.println("No books found matching: " + data);
		}
	}
}

