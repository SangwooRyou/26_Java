package BookMarket;

public abstract class Item {
	protected String bookId;
	protected String name;
	protected int unitPrice;

	public Item(String bookId, String name, int unitPrice) {
		this.bookId = bookId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	abstract String getBookID();

	abstract String getName();

	abstract int getUnitPrice();

	abstract void setBookID(String bookId);

	abstract void setName(String name);

	abstract void setUnitPrice(int unitPrice);
}
