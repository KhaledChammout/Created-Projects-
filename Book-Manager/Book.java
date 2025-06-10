package bestseller;

public class Book {
	private String name, author, originalLanguage, genre;
	private int firstPublished;
	private float millionSales;

	public Book(String name, String author, String language, int year, float sales, String genre) {
		this.name = name;
		this.author = author;
		this.originalLanguage = language;
		this.firstPublished = year;
		this.millionSales = sales;
		this.genre = genre;
	}

	public String getName() { return name; }
	public String getAuthor() { return author; }
	public String getOriginalLanguage() { return originalLanguage; }
	public int getFirstPublished() { return firstPublished; }
	public float getMillionSales() { return millionSales; }
	public String getGenre() { return genre; }

	@Override
	public String toString() {
		return String.format("{Book='%s', Author(s)='%s', Original language='%s', First published=%d, Approximate sales=%.1fM, Genre='%s'}",
				name, author, originalLanguage, firstPublished, millionSales, genre);
	}
}
