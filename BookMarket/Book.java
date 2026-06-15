package BookMarket;

public class Book extends Item {
	private String author;
	private String description;
	private String category;
	private String releaseDate;
	private int quantity;

	public Book(String bookId, String name, int unitPrice) {
		super(bookId, name, unitPrice);
	}

	public Book(String bookId, String name, int unitPrice, String author, String description, String category, String releaseDate) {
		super(bookId, name, unitPrice);
		this.author = author;
		this.description = description;
		this.category = category;
		this.releaseDate = releaseDate;
		this.quantity = 0;
	}

	public Book(Book book) {
		this(
			book.getBookID(),
			book.getName(),
			book.getUnitPrice(),
			book.getAuthor(),
			book.getDescription(),
			book.getCategory(),
			book.getReleaseDate()
		);
		this.quantity = book.getQuantity();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	String getBookID() {
		return bookId;
	}

	@Override
	String getName() {
		return name;
	}

	@Override
	int getUnitPrice() {
		return unitPrice;
	}

	@Override
	void setBookID(String bookId) {
		this.bookId = bookId;
	}

	@Override
	void setName(String name) {
		this.name = name;
	}

	@Override
	void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
}
