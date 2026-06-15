package BookMarket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
	private final List<Book> items = new ArrayList<>();

	public List<Book> getItems() {
		return Collections.unmodifiableList(items);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void insertBook(Book book) {
		Book existingBook = findBook(book.getBookID());
		if (existingBook != null) {
			existingBook.setQuantity(existingBook.getQuantity() + 1);
			return;
		}

		Book cartBook = new Book(book);
		cartBook.setQuantity(1);
		items.add(cartBook);
	}

	public boolean decrementBook(String bookId) {
		Book book = findBook(bookId);
		if (book == null) {
			return false;
		}

		if (book.getQuantity() > 1) {
			book.setQuantity(book.getQuantity() - 1);
		} else {
			items.remove(book);
		}
		return true;
	}

	public boolean removeBook(String bookId) {
		Book book = findBook(bookId);
		if (book == null) {
			return false;
		}

		items.remove(book);
		return true;
	}

	public void clear() {
		items.clear();
	}

	public int getTotalPrice() {
		int totalPrice = 0;
		for (Book book : items) {
			totalPrice += book.getUnitPrice() * book.getQuantity();
		}
		return totalPrice;
	}

	private Book findBook(String bookId) {
		for (Book book : items) {
			if (book.getBookID().equalsIgnoreCase(bookId)) {
				return book;
			}
		}
		return null;
	}
}
