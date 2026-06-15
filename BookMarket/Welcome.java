package BookMarket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Welcome {
	private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance(Locale.KOREA);
	private static final Book[] BOOK_LIST = createBookList();
	private static final Cart CART = new Cart();
	private static final Admin ADMIN = new Admin("관리자", "010-0000-0000");

	private final User user = new User("", "", "");

	private JFrame frame;
	private DefaultTableModel catalogModel;
	private DefaultTableModel cartModel;
	private JTable catalogTable;
	private JTable cartTable;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextArea detailArea;
	private JTextArea receiptArea;
	private JLabel totalPriceLabel;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {
		}

		SwingUtilities.invokeLater(() -> new Welcome().show());
	}

	// 도서 목록 보강 부분 - AI 활용
	private static Book[] createBookList() {
		Book[] books = new Book[8];

		books[0] = new Book("ISBN1234", "쉽게 배우는 JSP 웹 프로그래밍", 27000);
		books[0].setAuthor("한빛아카데미");
		books[0].setDescription("JSP 기초 문법부터 폼 처리, 세션, 게시판 흐름까지 다루는 웹 프로그래밍 입문서입니다.");
		books[0].setCategory("IT / 웹 개발");
		books[0].setReleaseDate("2018-10-08");

		books[1] = new Book("ISBN1235", "안드로이드 프로그래밍", 33000);
		books[1].setAuthor("우재남");
		books[1].setDescription("안드로이드 앱 구조, 액티비티, 레이아웃, 이벤트 처리를 실습 중심으로 익히는 책입니다.");
		books[1].setCategory("모바일 개발");
		books[1].setReleaseDate("2022-01-22");

		books[2] = new Book("ISBN1236", "스크래치", 22000);
		books[2].setAuthor("고광일");
		books[2].setDescription("블록 코딩을 통해 프로그래밍 사고와 컴퓨팅 기초를 익히는 학습서입니다.");
		books[2].setCategory("컴퓨팅 입문");
		books[2].setReleaseDate("2019-06-10");

		books[3] = new Book("ISBN1237", "자료구조와 알고리즘", 32000);
		books[3].setAuthor("천인국");
		books[3].setDescription("배열, 연결 리스트, 트리, 그래프와 같은 핵심 자료구조와 알고리즘을 다룹니다.");
		books[3].setCategory("컴퓨터 공학");
		books[3].setReleaseDate("2020-03-02");

		books[4] = new Book("ISBN1238", "자바의 정석", 30000);
		books[4].setAuthor("남궁성");
		books[4].setDescription("자바 문법, 객체지향, 예외 처리, 컬렉션 프레임워크까지 폭넓게 익히는 기본서입니다.");
		books[4].setCategory("프로그래밍 언어");
		books[4].setReleaseDate("2019-01-30");

		books[5] = new Book("ISBN1239", "Do it! 점프 투 파이썬", 18800);
		books[5].setAuthor("박응용");
		books[5].setDescription("파이썬 기초 문법과 실전 예제를 따라가며 프로그래밍을 시작할 수 있는 입문서입니다.");
		books[5].setCategory("프로그래밍 언어");
		books[5].setReleaseDate("2023-06-15");

		books[6] = new Book("ISBN1240", "혼자 공부하는 SQL", 24000);
		books[6].setAuthor("우재남");
		books[6].setDescription("데이터베이스 기초와 SQL 질의를 실습 중심으로 학습할 수 있도록 구성했습니다.");
		books[6].setCategory("데이터베이스");
		books[6].setReleaseDate("2021-11-05");

		books[7] = new Book("ISBN1241", "컴퓨터 네트워크", 35000);
		books[7].setAuthor("James F. Kurose");
		books[7].setDescription("인터넷 프로토콜, 전송 계층, 응용 계층 등 네트워크 핵심 개념을 체계적으로 설명합니다.");
		books[7].setCategory("네트워크");
		books[7].setReleaseDate("2018-08-20");

		return books;
	}

	private void show() {
		frame = new JFrame("Book Market");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(buildRootPanel());
		frame.setSize(1360, 860);
		frame.setMinimumSize(new Dimension(1180, 760));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		catalogTable.setRowSelectionInterval(0, 0);
		updateBookDetail(0);
		refreshCartView();
	}

	private JPanel buildRootPanel() {
		JPanel root = new JPanel(new BorderLayout(20, 20));
		root.setBorder(new EmptyBorder(20, 20, 20, 20));
		root.setBackground(new Color(243, 238, 228));

		root.add(buildHeaderPanel(), BorderLayout.NORTH);
		root.add(buildMainPanel(), BorderLayout.CENTER);
		root.add(buildReceiptPanel(), BorderLayout.SOUTH);
		return root;
	}

	private JComponent buildHeaderPanel() {
		JPanel header = new JPanel(new BorderLayout(16, 12));
		header.setBackground(new Color(48, 61, 48));
		header.setBorder(new EmptyBorder(24, 28, 24, 28));

		JLabel title = new JLabel("Welcome to Book Market");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Malgun Gothic", Font.BOLD, 28));

		JLabel subtitle = new JLabel("온라인 서점 프로젝트");
		subtitle.setForeground(new Color(219, 227, 214));
		subtitle.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));

		JPanel textPanel = new JPanel();
		textPanel.setOpaque(false);
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(title);
		textPanel.add(Box.createVerticalStrut(6));
		textPanel.add(subtitle);

		JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		actionPanel.setOpaque(false);
		actionPanel.add(createHeaderButton("주문 / 결제", e -> openCheckoutDialog()));
		actionPanel.add(createHeaderButton("관리자 로그인", e -> showAdminDialog()));
		actionPanel.add(createHeaderButton("종료", e -> exitApplication()));

		header.add(textPanel, BorderLayout.WEST);
		header.add(actionPanel, BorderLayout.EAST);
		return header;
	}

	private JComponent buildMainPanel() {
		JPanel main = new JPanel(new GridLayout(1, 3, 20, 20));
		main.setOpaque(false);
		main.add(buildCustomerPanel());
		main.add(buildCatalogPanel());
		main.add(buildCartPanel());
		return main;
	}

	private JComponent buildCustomerPanel() {
		JPanel panel = createCardPanel("고객 정보");
		panel.setLayout(new BorderLayout(0, 16));

		JPanel form = new JPanel(new GridBagLayout());
		form.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new java.awt.Insets(6, 0, 6, 0);
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		nameField = new JTextField();
		phoneField = new JTextField();
		addressField = new JTextField();

		form.add(labeledField("이름", nameField), gbc);
		gbc.gridy++;
		form.add(labeledField("연락처", phoneField), gbc);
		gbc.gridy++;
		form.add(labeledField("주소", addressField), gbc);

		JPanel buttonRow = new JPanel(new GridLayout(1, 2, 10, 0));
		buttonRow.setOpaque(false);
		buttonRow.add(createPrimaryButton("고객 정보 저장", e -> saveUserInfo(true)));
		buttonRow.add(createSecondaryButton("입력 초기화", e -> clearUserFields()));

		JTextArea guide = new JTextArea(
			"1. 고객 이름과 연락처를 먼저 입력합니다.\n" +
			"2. 도서를 선택하고 장바구니에 담습니다.\n" +
			"3. 상단의 '주문 / 결제' 버튼으로 주문 / 결제 화면을 엽니다.\n" +
			"4. 두 번째 화면에서 최종 주문서를 생성합니다."
		);
		guide.setEditable(false);
		guide.setLineWrap(true);
		guide.setWrapStyleWord(true);
		guide.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
		guide.setBackground(new Color(248, 244, 236));
		guide.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(221, 209, 190)),
			new EmptyBorder(14, 14, 14, 14)
		));

		panel.add(form, BorderLayout.NORTH);
		panel.add(buttonRow, BorderLayout.CENTER);
		panel.add(guide, BorderLayout.SOUTH);
		return panel;
	}

	// 도서 목록 화면 배치 조정 부분 - AI 활용
	private JComponent buildCatalogPanel() {
		JPanel panel = createCardPanel("도서 목록");
		panel.setLayout(new BorderLayout(0, 14));

		catalogModel = new DefaultTableModel(new Object[] { "도서 ID", "도서명", "가격" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (Book book : BOOK_LIST) {
			catalogModel.addRow(new Object[] {
				book.getBookID(),
				book.getName(),
				formatPrice(book.getUnitPrice())
			});
		}

		catalogTable = new JTable(catalogModel);
		catalogTable.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
		catalogTable.setRowHeight(28);
		catalogTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		catalogTable.getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting() && catalogTable.getSelectedRow() >= 0) {
				updateBookDetail(catalogTable.getSelectedRow());
			}
		});

		detailArea = new JTextArea();
		detailArea.setEditable(false);
		detailArea.setLineWrap(true);
		detailArea.setWrapStyleWord(true);
		detailArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
		detailArea.setBackground(new Color(248, 244, 236));
		detailArea.setBorder(new EmptyBorder(12, 12, 12, 12));
		detailArea.setRows(6);

		JPanel controlPanel = new JPanel(new BorderLayout(10, 0));
		controlPanel.setOpaque(false);
		controlPanel.setBorder(new EmptyBorder(8, 0, 0, 0));

		JComboBox<Integer> quantityBox = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5 });
		quantityBox.setRenderer(new QuantityRenderer());
		controlPanel.add(quantityBox, BorderLayout.WEST);
		controlPanel.add(
			createPrimaryButton("장바구니에 추가", e -> addSelectedBook((Integer) quantityBox.getSelectedItem())),
			BorderLayout.CENTER
		);

		JScrollPane catalogScrollPane = new JScrollPane(catalogTable);
		catalogScrollPane.setPreferredSize(new Dimension(0, 320));

		JScrollPane detailScrollPane = new JScrollPane(detailArea);
		detailScrollPane.setBorder(BorderFactory.createTitledBorder("도서 상세"));

		JPanel bottomPanel = new JPanel(new BorderLayout(0, 10));
		bottomPanel.setOpaque(false);
		bottomPanel.add(detailScrollPane, BorderLayout.CENTER);
		bottomPanel.add(controlPanel, BorderLayout.SOUTH);

		panel.add(catalogScrollPane, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.CENTER);
		return panel;
	}

	private JComponent buildCartPanel() {
		JPanel panel = createCardPanel("장바구니");
		panel.setLayout(new BorderLayout(0, 14));

		cartModel = new DefaultTableModel(new Object[] { "도서 ID", "도서명", "수량", "금액" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		cartTable = new JTable(cartModel);
		cartTable.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
		cartTable.setRowHeight(28);
		cartTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel actionGrid = new JPanel(new GridLayout(2, 2, 10, 10));
		actionGrid.setOpaque(false);
		actionGrid.add(createPrimaryButton("수량 1 감소", e -> decrementSelectedCartItem()));
		actionGrid.add(createSecondaryButton("선택 도서 삭제", e -> removeSelectedCartItem()));
		actionGrid.add(createSecondaryButton("장바구니 비우기", e -> clearCart()));
		actionGrid.add(createPrimaryButton("주문 / 결제", e -> openCheckoutDialog()));

		totalPriceLabel = new JLabel("총액: " + formatPrice(0), SwingConstants.RIGHT);
		totalPriceLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 16));

		panel.add(totalPriceLabel, BorderLayout.NORTH);
		panel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
		panel.add(actionGrid, BorderLayout.SOUTH);
		return panel;
	}

	private JComponent buildReceiptPanel() {
		JPanel panel = createCardPanel("주문서 / 영수증");
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(0, 180));

		receiptArea = new JTextArea();
		receiptArea.setEditable(false);
		receiptArea.setLineWrap(true);
		receiptArea.setWrapStyleWord(true);
		receiptArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
		receiptArea.setText("주문 / 결제 화면에서 주문서를 생성하면 이 영역에도 결과가 반영됩니다.");

		panel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);
		return panel;
	}

	private JPanel createCardPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 252, 247));
		panel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(208, 198, 181)),
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(
					BorderFactory.createEmptyBorder(),
					title,
					0,
					0,
					new Font("Malgun Gothic", Font.BOLD, 16),
					new Color(67, 79, 50)
				),
				new EmptyBorder(16, 16, 16, 16)
			)
		));
		return panel;
	}

	private JComponent labeledField(String labelText, JTextField field) {
		JPanel wrapper = new JPanel(new BorderLayout(0, 6));
		wrapper.setOpaque(false);

		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Malgun Gothic", Font.BOLD, 13));

		field.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
		field.setPreferredSize(new Dimension(220, 34));

		wrapper.add(label, BorderLayout.NORTH);
		wrapper.add(field, BorderLayout.CENTER);
		return wrapper;
	}

	// 버튼 가시성 스타일 조정 부분 - AI 활용
	private JButton createHeaderButton(String text, java.awt.event.ActionListener listener) {
		JButton button = new JButton(text);
		button.setFocusPainted(false);
		button.setBackground(new Color(227, 196, 122));
		button.setForeground(new Color(37, 37, 37));
		button.setOpaque(true);
		button.setContentAreaFilled(true);
		button.setBorderPainted(false);
		button.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		button.addActionListener(listener);
		return button;
	}

	private JButton createPrimaryButton(String text, java.awt.event.ActionListener listener) {
		JButton button = new JButton(text);
		button.setBackground(new Color(122, 150, 102));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setContentAreaFilled(true);
		button.setBorderPainted(false);
		button.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		button.addActionListener(listener);
		return button;
	}

	private JButton createSecondaryButton(String text, java.awt.event.ActionListener listener) {
		JButton button = new JButton(text);
		button.setBackground(new Color(170, 150, 116));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setContentAreaFilled(true);
		button.setBorderPainted(false);
		button.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
		button.addActionListener(listener);
		return button;
	}

	private void saveUserInfo(boolean showMessage) {
		String name = nameField.getText().trim();
		String phone = phoneField.getText().trim();
		String address = addressField.getText().trim();

		if (name.isEmpty() || phone.isEmpty()) {
			if (showMessage) {
				showMessage("고객 이름과 연락처를 입력하세요.");
			}
			return;
		}

		user.setName(name);
		user.setPhone(phone);
		user.setAddress(address);

		if (showMessage) {
			showMessage("고객 정보가 저장되었습니다.");
		}
	}

	private void clearUserFields() {
		nameField.setText("");
		phoneField.setText("");
		addressField.setText("");
		user.setName("");
		user.setPhone("");
		user.setAddress("");
	}

	private void updateBookDetail(int rowIndex) {
		Book book = BOOK_LIST[rowIndex];
		detailArea.setText(
			"도서 ID: " + book.getBookID() + "\n\n" +
			"도서명: " + book.getName() + "\n" +
			"저자: " + book.getAuthor() + "\n" +
			"분류: " + book.getCategory() + "\n" +
			"출간일: " + book.getReleaseDate() + "\n" +
			"가격: " + formatPrice(book.getUnitPrice()) + "\n\n" +
			"소개\n" + book.getDescription()
		);
	}

	private void addSelectedBook(int quantity) {
		int row = catalogTable.getSelectedRow();
		if (row < 0) {
			showMessage("도서를 먼저 선택하세요.");
			return;
		}

		Book selected = BOOK_LIST[row];
		for (int i = 0; i < quantity; i++) {
			CART.insertBook(selected);
		}

		refreshCartView();
		showMessage(selected.getName() + " 도서를 장바구니에 담았습니다.");
	}

	private void decrementSelectedCartItem() {
		int row = cartTable.getSelectedRow();
		if (row < 0) {
			showMessage("장바구니에서 항목을 선택하세요.");
			return;
		}

		String bookId = (String) cartModel.getValueAt(row, 0);
		CART.decrementBook(bookId);
		refreshCartView();
	}

	private void removeSelectedCartItem() {
		int row = cartTable.getSelectedRow();
		if (row < 0) {
			showMessage("장바구니에서 항목을 선택하세요.");
			return;
		}

		String bookId = (String) cartModel.getValueAt(row, 0);
		CART.removeBook(bookId);
		refreshCartView();
	}

	private void clearCart() {
		CART.clear();
		refreshCartView();
	}

	private void refreshCartView() {
		cartModel.setRowCount(0);
		for (Book book : CART.getItems()) {
			cartModel.addRow(new Object[] {
				book.getBookID(),
				book.getName(),
				book.getQuantity(),
				formatPrice(book.getUnitPrice() * book.getQuantity())
			});
		}

		totalPriceLabel.setText("총액: " + formatPrice(CART.getTotalPrice()));
		if (cartModel.getRowCount() > 0) {
			cartTable.setRowSelectionInterval(0, 0);
		}
	}

	private void openCheckoutDialog() {
		saveUserInfo(false);

		if (user.getName().isBlank() || user.getPhone().isBlank()) {
			showMessage("주문 / 결제 화면으로 이동하기 전에 고객 정보를 입력하세요.");
			return;
		}

		JDialog dialog = new CheckoutDialog(frame);
		dialog.setVisible(true);
	}

	private void showAdminDialog() {
		JDialog dialog = new JDialog(frame, "관리자 로그인", true);
		dialog.setLayout(new BorderLayout(12, 12));
		dialog.getContentPane().setBackground(new Color(255, 252, 247));
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel form = new JPanel(new GridLayout(2, 1, 0, 10));
		form.setBorder(new EmptyBorder(16, 16, 0, 16));
		form.setOpaque(false);

		JTextField idField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		form.add(labeledField("아이디", idField));
		form.add(labeledPasswordField("비밀번호", passwordField));

		JButton loginButton = createPrimaryButton("로그인", e -> {
			String id = idField.getText().trim();
			String password = new String(passwordField.getPassword());

			if (ADMIN.getId().equals(id) && ADMIN.getPasswd().equals(password)) {
				JOptionPane.showMessageDialog(dialog, "관리자 로그인에 성공했습니다.");
				dialog.dispose();
			} else {
				JOptionPane.showMessageDialog(dialog, "관리자 정보가 일치하지 않습니다.");
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 12));
		buttonPanel.setOpaque(false);
		buttonPanel.add(loginButton);

		dialog.add(form, BorderLayout.CENTER);
		dialog.add(buttonPanel, BorderLayout.SOUTH);
		dialog.setSize(360, 220);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}

	private JComponent labeledPasswordField(String labelText, JPasswordField field) {
		JPanel wrapper = new JPanel(new BorderLayout(0, 6));
		wrapper.setOpaque(false);

		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Malgun Gothic", Font.BOLD, 13));

		field.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
		field.setPreferredSize(new Dimension(220, 34));

		wrapper.add(label, BorderLayout.NORTH);
		wrapper.add(field, BorderLayout.CENTER);
		return wrapper;
	}

	private void exitApplication() {
		int answer = JOptionPane.showConfirmDialog(
			frame,
			"프로그램을 종료하시겠습니까?",
			"종료",
			JOptionPane.YES_NO_OPTION
		);
		if (answer == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private void renderReceipt(String receiptText) {
		receiptArea.setText(receiptText);
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	private String formatPrice(int amount) {
		return CURRENCY.format(amount);
	}

	private String buildReceiptText(String shippingAddress) {
		StringBuilder builder = new StringBuilder();
		builder.append("--------------- 주문서 ---------------\n");
		builder.append("고객명: ").append(user.getName()).append('\n');
		builder.append("연락처: ").append(user.getPhone()).append('\n');
		builder.append("배송지: ").append(shippingAddress).append("\n\n");

		for (Book book : CART.getItems()) {
			builder.append(book.getBookID())
				.append(" / ")
				.append(book.getName())
				.append(" / 수량 ")
				.append(book.getQuantity())
				.append(" / ")
				.append(formatPrice(book.getUnitPrice() * book.getQuantity()))
				.append('\n');
		}

		builder.append("\n총 결제 금액: ").append(formatPrice(CART.getTotalPrice())).append('\n');
		builder.append("--------------------------------------");
		return builder.toString();
	}

	private static class QuantityRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus
		) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			label.setText("수량 " + value);
			return label;
		}
	}

	private class CheckoutDialog extends JDialog {
		private final JTextField receiverField = new JTextField();
		private final JTextField phoneField = new JTextField();
		private final JTextField addressField = new JTextField();
		private final JTextArea orderSummaryArea = new JTextArea();
		private final JTextArea finalReceiptArea = new JTextArea();
		private final JLabel paymentTotalLabel = new JLabel("", SwingConstants.RIGHT);

		CheckoutDialog(Frame owner) {
			super(owner, "주문 / 결제", true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setContentPane(buildDialogPanel());
			setSize(980, 720);
			setMinimumSize(new Dimension(900, 660));
			setLocationRelativeTo(owner);
			loadUserState();
			refreshOrderSummary();
		}

		private JComponent buildDialogPanel() {
			JPanel root = new JPanel(new BorderLayout(18, 18));
			root.setBorder(new EmptyBorder(18, 18, 18, 18));
			root.setBackground(new Color(238, 233, 223));

			root.add(buildDialogHeader(), BorderLayout.NORTH);
			root.add(buildDialogCenter(), BorderLayout.CENTER);
			root.add(buildDialogFooter(), BorderLayout.SOUTH);
			return root;
		}

		private JComponent buildDialogHeader() {
			JPanel header = new JPanel(new BorderLayout());
			header.setOpaque(false);

			JLabel title = new JLabel("주문 / 결제");
			title.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
			title.setForeground(new Color(52, 64, 46));

			JLabel subtitle = new JLabel("배송 정보 확인, 결제 금액 검토, 최종 주문서 생성");
			subtitle.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
			subtitle.setForeground(new Color(91, 97, 85));

			JPanel textPanel = new JPanel();
			textPanel.setOpaque(false);
			textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
			textPanel.add(title);
			textPanel.add(Box.createVerticalStrut(4));
			textPanel.add(subtitle);

			header.add(textPanel, BorderLayout.WEST);
			return header;
		}

		private JComponent buildDialogCenter() {
			JPanel center = new JPanel(new GridLayout(1, 2, 18, 18));
			center.setOpaque(false);
			center.add(buildShippingPanel());
			center.add(buildPaymentPanel());
			return center;
		}

		private JComponent buildShippingPanel() {
			JPanel panel = createCardPanel("배송 정보");
			panel.setLayout(new BorderLayout(0, 12));

			JPanel form = new JPanel(new GridLayout(3, 1, 0, 10));
			form.setOpaque(false);
			form.add(labeledField("수령인", receiverField));
			form.add(labeledField("연락처", phoneField));
			form.add(labeledField("배송 주소", addressField));

			orderSummaryArea.setEditable(false);
			orderSummaryArea.setLineWrap(true);
			orderSummaryArea.setWrapStyleWord(true);
			orderSummaryArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
			orderSummaryArea.setBackground(new Color(248, 244, 236));

			panel.add(form, BorderLayout.NORTH);
			panel.add(new JScrollPane(orderSummaryArea), BorderLayout.CENTER);
			return panel;
		}

		private JComponent buildPaymentPanel() {
			JPanel panel = createCardPanel("결제 요약");
			panel.setLayout(new BorderLayout(0, 12));

			finalReceiptArea.setEditable(false);
			finalReceiptArea.setLineWrap(true);
			finalReceiptArea.setWrapStyleWord(true);
			finalReceiptArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 13));
			finalReceiptArea.setBackground(new Color(248, 244, 236));
			finalReceiptArea.setText("아래 버튼으로 최종 주문서를 생성하세요.");

			paymentTotalLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
			paymentTotalLabel.setForeground(new Color(52, 64, 46));

			panel.add(paymentTotalLabel, BorderLayout.NORTH);
			panel.add(new JScrollPane(finalReceiptArea), BorderLayout.CENTER);
			return panel;
		}

		private JComponent buildDialogFooter() {
			JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
			footer.setOpaque(false);

			footer.add(createSecondaryButton("닫기", e -> dispose()));
			footer.add(createPrimaryButton("최종 주문서 생성", e -> finalizeOrder()));
			return footer;
		}

		private void loadUserState() {
			receiverField.setText(user.getName());
			phoneField.setText(user.getPhone());
			addressField.setText(user.getAddress() == null ? "" : user.getAddress());
		}

		private void refreshOrderSummary() {
			StringBuilder builder = new StringBuilder();
			builder.append("주문 도서 목록\n");
			builder.append("------------------------------\n");

			if (CART.isEmpty()) {
				builder.append("장바구니가 비어 있습니다.");
			} else {
				for (Book book : CART.getItems()) {
					builder.append(book.getName())
						.append(" / ")
						.append(book.getQuantity())
						.append("권 / ")
						.append(formatPrice(book.getUnitPrice() * book.getQuantity()))
						.append('\n');
				}
			}

			orderSummaryArea.setText(builder.toString());
			paymentTotalLabel.setText("총 결제 금액: " + formatPrice(CART.getTotalPrice()));
		}

		// 주문 완료 후처리 보완 부분 - AI 활용
		private void finalizeOrder() {
			if (CART.isEmpty()) {
				JOptionPane.showMessageDialog(this, "장바구니가 비어 있습니다.");
				return;
			}

			String receiver = receiverField.getText().trim();
			String phone = phoneField.getText().trim();
			String address = addressField.getText().trim();

			if (receiver.isEmpty() || phone.isEmpty() || address.isEmpty()) {
				JOptionPane.showMessageDialog(this, "수령인, 연락처, 배송 주소를 모두 입력하세요.");
				return;
			}

			user.setName(receiver);
			user.setPhone(phone);
			user.setAddress(address);

			nameField.setText(receiver);
			Welcome.this.phoneField.setText(phone);
			Welcome.this.addressField.setText(address);

			String receipt = buildReceiptText(address);
			finalReceiptArea.setText(receipt);
			renderReceipt(receipt);

			CART.clear();
			refreshCartView();
			refreshOrderSummary();
			JOptionPane.showMessageDialog(this, "주문이 완료되었습니다.");
			dispose();
		}
	}
}
