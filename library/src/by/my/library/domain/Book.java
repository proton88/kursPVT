package by.my.library.domain;

public class Book {
	private String author;
	private String title;
	private int price;
	private int id;
	private String genre;
	private long isbn;



	public Book(){
		this.title = "no title";
		this.price = 0;
		this.author="no name";
		this.id=0;
		this.genre="no genre";
	}

	
	public Book(int id, String title, String author, String genre, int price,  long isbn){
		this.title = title;
		this.price = price;
		this.author=author;
		this.id=id;
		this.genre=genre;
		this.isbn=isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	public long getIsbn() {
		return isbn;
	}


	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}


	@Override
	public String toString() {
		return	"Author="+author+", title=" + title + ", price=" + price+", genre="+genre+ ", isbn="+isbn+"\n";
	}
	
	
}
