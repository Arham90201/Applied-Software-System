

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 
 public class GUI implements ActionListener, ItemListener {
	 // create objects
	 static SimpleBoard board = new SimpleBoard();
	 static JFrame frameMainWindow;
	 static JFrame frameWin;
	 static JFrame frameCredits;
 
	 static JPanel panelBoardNumbers;
	 static JLayeredPane layeredGameBoard;
 
	 private static Player p1 = new HumanPlayer();
	 private static Player p2 = new HumanPlayer();
 
	 private static int p1TypeFlag = 0;
	 private static int p2TypeFlag = 0;
 
	 public JMenuBar createMenuBar() {
		 JMenuBar menuBar;
		 JMenu menu, submenu, subsubmenu;
		 JMenuItem menuItem;
		 JRadioButtonMenuItem rbMenuItem;
		 int[][] boardView;
 
		 // create and build first menu
		 menuBar = new JMenuBar();
		 menu = new JMenu("File");
		 menu.setMnemonic(KeyEvent.VK_F);
		 menuBar.add(menu);
		 // add items to menu
		 menuItem = new JMenuItem("New game", KeyEvent.VK_N);
		 menuItem.setName("NewGame");
		 menuItem.addActionListener(this);
		 menu.add(menuItem);
		 menu.addSeparator();
		 menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
		 menuItem.setName("QuitGame");
		 menuItem.addActionListener(this);
		 menu.add(menuItem);
 
		 // create and build second menu
		 menu = new JMenu("Players");
		 menu.setMnemonic(KeyEvent.VK_P);
		 menuBar.add(menu);
		 // add items to menu
		 // Submenu one
		 submenu = new JMenu("Player 1");
			 ButtonGroup groupPlayers1 = new ButtonGroup();
			 rbMenuItem = new JRadioButtonMenuItem("Human");
			 if (p1.getType() == 0) rbMenuItem.setSelected(true);
			 rbMenuItem.setName("P1Human");
			 rbMenuItem.addItemListener(this);
			 groupPlayers1.add(rbMenuItem);
			 submenu.add(rbMenuItem);
			 subsubmenu = new JMenu("Computer");
				 rbMenuItem = new JRadioButtonMenuItem("Easy");
				 if (p1.getType() == 1) rbMenuItem.setSelected(true);
				 rbMenuItem.setName("P1Easy");
				 rbMenuItem.addItemListener(this);
				 groupPlayers1.add(rbMenuItem);
				 subsubmenu.add(rbMenuItem);
				 rbMenuItem = new JRadioButtonMenuItem("Moderate");
				 if (p1.getType() == 2) rbMenuItem.setSelected(true);
				 rbMenuItem.setName("P1Moderate");
				 rbMenuItem.addItemListener(this);
				 groupPlayers1.add(rbMenuItem);
				 subsubmenu.add(rbMenuItem);
				 rbMenuItem = new JRadioButtonMenuItem("Hard");
				 if (p1.getType() == 3) rbMenuItem.setSelected(true);
				 rbMenuItem.setName("P1Hard");
				 rbMenuItem.addItemListener(this);
				 groupPlayers1.add(rbMenuItem);
				 subsubmenu.add(rbMenuItem);
			 submenu.add(subsubmenu);
		 menu.add(submenu);
		 // submenu 2
		 submenu = new JMenu("Player 2");
			 ButtonGroup groupPlayers2 = new ButtonGroup();
			 subsubmenu = new JMenu("Computer");
				 rbMenuItem = new JRadioButtonMenuItem("Easy");
				 if (p2.getType() == 1) rbMenuItem.setSelected(true);
				 rbMenuItem.setName("P2Easy");
				 rbMenuItem.addItemListener(this);
				 groupPlayers2.add(rbMenuItem);
				 subsubmenu.add(rbMenuItem);
				 rbMenuItem = new JRadioButtonMenuItem("Moderate");
				 if (p2.getType() == 2) rbMenuItem.setSelected(true);
				 rbMenuItem.setName("P2Moderate");
				 rbMenuItem.addItemListener(this);
				 groupPlayers2.add(rbMenuItem);
				 subsubmenu.add(rbMenuItem);
				 rbMenuItem = new JRadioButtonMenuItem("Hard");
				 if (p2.getType() == 3) rbMenuItem.setSelected(true);
				 rbMenuItem.setName("P2Hard");
				 rbMenuItem.addItemListener(this);
				 groupPlayers2.add(rbMenuItem);
				 subsubmenu.add(rbMenuItem);
			 submenu.add(subsubmenu);
			 rbMenuItem = new JRadioButtonMenuItem("Human");
			 if (p1.getType() == 0) rbMenuItem.setSelected(true);
			 rbMenuItem.setName("P2Human");
			 rbMenuItem.setSelected(true);
			 rbMenuItem.setMnemonic(KeyEvent.VK_H);
			 groupPlayers2.add(rbMenuItem);
			 rbMenuItem.addItemListener(this);
			 submenu.add(rbMenuItem);
		 menu.add(submenu);
 
		 
 
		 return menuBar;
	 }
 
	 public static JLayeredPane createLayeredBoard() {
		 layeredGameBoard = new JLayeredPane();
		 layeredGameBoard.setPreferredSize(new Dimension(570, 490));
		 layeredGameBoard.setBorder(BorderFactory.createTitledBorder("Connect 4"));
 
		 ImageIcon imageBoard = new ImageIcon("images/Board.gif");
		 JLabel imageBoardLabel = new JLabel(imageBoard);
 
		 imageBoardLabel.setBounds(20, 20, imageBoard.getIconWidth(), imageBoard.getIconHeight());
		 layeredGameBoard.add(imageBoardLabel, new Integer (0), 1);
 
		 return layeredGameBoard;
	 }
 
	 public static void createNewGame() {
		 board = new SimpleBoard();
				 board.out=false;
				 
		 if (frameMainWindow != null) frameMainWindow.dispose();
		 frameMainWindow = new JFrame("Smart Connect Four - v1.0");
		 frameMainWindow.setBounds(100, 100, 400, 300);
		 GUI conFour = new GUI();
		 frameMainWindow.setJMenuBar( conFour.createMenuBar() );
		 Component compMainWindowContents = createContentComponents();
		 frameMainWindow.getContentPane().add(compMainWindowContents, BorderLayout.CENTER);
 
		 frameMainWindow.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
			 System.exit(0);
			 }
		 });
 
		 // show window
		 frameMainWindow.pack();
		 frameMainWindow.setVisible(true);
 
		 if (p1TypeFlag == 1) {
			 p1.go(board);
			 redrawBoard();
		 }
 
		 if ( (p1TypeFlag == 1) && (p2TypeFlag == 1) ) {
					 panelBoardNumbers.setEnabled(false);
					 panelBoardNumbers.repaint();
			 while (!board.over() ){
				if (board.next() == 1) p1.go(board);
				else p2.go(board);
				redrawBoard();
			 }
			 panelBoardNumbers.setVisible(false);
			 panelBoardNumbers.repaint();
		 }
	 }
 
	 public static void paintRed(int row, int col) {
		 int xOffset = 75 * col;
		 int yOffset = 75 * row;
		 ImageIcon redIcon = new ImageIcon("images/Red.gif");
		 JLabel redIconLabel = new JLabel(redIcon);
		 redIconLabel.setBounds(27 + xOffset, 27 + yOffset, redIcon.getIconWidth(),redIcon.getIconHeight());
		 layeredGameBoard.add(redIconLabel, new Integer(0), 0);
		 frameMainWindow.paint(frameMainWindow.getGraphics());
	 }
 
	 public static void paintBlack(int row, int col) {
		 int xOffset = 75 * col;
		 int yOffset = 75 * row;
		 ImageIcon blackIcon = new ImageIcon("images/Black.gif");
		 JLabel blackIconLabel = new JLabel(blackIcon);
		 blackIconLabel.setBounds(27 + xOffset, 27 + yOffset, blackIcon.getIconWidth(),blackIcon.getIconHeight());
		 layeredGameBoard.add(blackIconLabel, new Integer(0), 0);
		 frameMainWindow.paint(frameMainWindow.getGraphics());
	 }
 
	 public static void redrawBoard() {
 
		 int[][] boardView = board.view();
		 int r = board.m_x;
		 int c = board.m_y;
 
		 int playerPos = boardView[r][c];
		 if (playerPos == 1) {
			 // paint red at [r][c]
			 paintRed(r, c);
		 } else if (playerPos == 2) {
			 // paint black at [r][c]
			 paintBlack(r, c);
		 }
		 if (board.over() == true) {
			 gameOver();
		 }
 
	 }
 
	 public static void game(){
 
		 if (board.next() == 1) p1.go(board);
		 else p2.go(board);
		 redrawBoard();
				 
				 if (!board.over()) {
		 int nextTypeFlag = 0;
		 if (board.next() == 1) nextTypeFlag = p1TypeFlag;
		 else nextTypeFlag = p2TypeFlag;
				 if (nextTypeFlag == 1) {
			 if (board.next() == 1) p1.go(board);
			 else p2.go(board);
			 redrawBoard();
						 
				 }
				 
		 }
 
	 }
 
	 
	 public static Component createContentComponents() {
 
		 // create panels to hold and organize board numbers
		 panelBoardNumbers = new JPanel();
		 panelBoardNumbers.setLayout(new GridLayout(1, 7, 4, 4));
		 JButton buttonCol0 = new JButton("1");
		 buttonCol0.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (board.next() == 1) p1.setMove(0);
				 else p2.setMove(0);
				 game();
			 }
		 });
		 JButton buttonCol1 = new JButton("2");
		 buttonCol1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (board.next() == 1) p1.setMove(1);
				 else p2.setMove(1);
				 game();
			 }
		 });
		 JButton buttonCol2 = new JButton("3");
		 buttonCol2.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (board.next() == 1) p1.setMove(2);
				 else p2.setMove(2);
				 game();
			 }
		 });
		 JButton buttonCol3 = new JButton("4");
		 buttonCol3.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (board.next() == 1) p1.setMove(3);
				 else p2.setMove(3);
				 game();
			 }
		 });
		 JButton buttonCol4 = new JButton("5");
		 buttonCol4.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (board.next() == 1) p1.setMove(4);
				 else p2.setMove(4);
				 game();
			 }
		 });
		 JButton buttonCol5 = new JButton("6");
		 buttonCol5.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (board.next() == 1) p1.setMove(5);
				 else p2.setMove(5);
				 game();
			 }
		 });
		 JButton buttonCol6 = new JButton("7");
		 buttonCol6.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (board.next() == 1) p1.setMove(6);
				 else p2.setMove(6);
				 game();
			 }
		 });
		 panelBoardNumbers.add(buttonCol0);
		 panelBoardNumbers.add(buttonCol1);
		 panelBoardNumbers.add(buttonCol2);
		 panelBoardNumbers.add(buttonCol3);
		 panelBoardNumbers.add(buttonCol4);
		 panelBoardNumbers.add(buttonCol5);
		 panelBoardNumbers.add(buttonCol6);
 
		 // create game board with pieces
		 layeredGameBoard = createLayeredBoard();
 
		 // create panel to hold all of above
		 JPanel panelMain = new JPanel();
		 panelMain.setLayout(new BorderLayout());
		 panelMain.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );
		 //panelMain.setLayout(new GridLayout(1, 2, 4, 4));
 
		 // add objects to pane
		 panelMain.add(panelBoardNumbers, BorderLayout.NORTH);
		 panelMain.add(layeredGameBoard, BorderLayout.CENTER);
 
		 return panelMain;
	 }
 
	 // Returns just the class name -- no package info.
	 protected String getClassName(Object o) {
		 String classString = o.getClass().getName();
		 int dotIndex = classString.lastIndexOf(".");
		 return classString.substring(dotIndex+1);
	 }
 
	 public void actionPerformed(ActionEvent e) {
		 JMenuItem source = (JMenuItem)(e.getSource());
		 String s = source.getName();
 
		 if ( s.equals("NewGame") ) {
			 // create new game
			 createNewGame();
		 } else if ( s.equals("QuitGame")) {
			 System.exit(0);
		 } 
 
	 }
 
	 public void itemStateChanged(ItemEvent e) {
		 JMenuItem source = (JMenuItem)(e.getSource());
		 String s = source.getName() +
					((e.getStateChange() == ItemEvent.SELECTED) ?
					  "-selected":"-unselected");
 
		 if (s.equals("P1Easy-selected")) {
			 p1 = new Easy();
			 p1TypeFlag = 1;
		 } else if (s.equals("P1Human-selected")) {
			 p1 = new HumanPlayer();
			 p1TypeFlag = 0;
		 } else if (s.equals("P1Moderate-selected")) {
			 p1 = new Moderate();
			 p1TypeFlag = 1;
		 } else if (s.equals("P1Hard-selected")) {
			 p1 = new Hard();
			 p1TypeFlag = 1;
		 } else if (s.equals("P2Human-selected")) {
			 p2 = new HumanPlayer();
			 p2TypeFlag = 0;
		 } else if (s.equals("P2Easy-selected")) {
			 p2 = new Easy();
			 p2TypeFlag = 1;
		 } else if (s.equals("P2Moderate-selected")) {
			 p2 = new Moderate();
			 p2TypeFlag = 1;
		 } else if (s.equals("P2Hard-selected")) {
			 p2 = new Hard();
			 p2TypeFlag = 1;
		 }
 
	 }
 
	 public static void gameOver() {
			 
				 //System.out.println(board.movelist);
				 
				 panelBoardNumbers.setVisible(false);
		 frameWin = new JFrame("You Win!");
		 frameWin.setBounds(300, 300, 220, 120);
		 JPanel winPanel = new JPanel(new BorderLayout());
		 winPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
 
		 //ImageIcon winIcon = new ImageIcon("images/win.gif");
		 //JLabel winLabel = new JLabel(winIcon);
		 JLabel winLabel;
		 if (board.winner == 1) {
			 winLabel = new JLabel("Player 1 wins!");
			 winPanel.add(winLabel);
		 } else if (board.winner == 2) {
			 winLabel = new JLabel("Player 2 wins!");
			 winPanel.add(winLabel);
		 } else {
			 winLabel = new JLabel("Nobody Win! - You both lose!");
			 winPanel.add(winLabel);
		 }
		 winPanel.add(winLabel, BorderLayout.NORTH);
		 JButton okButton = new JButton("Ok");
		 okButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 frameWin.setVisible(false);
			 }
		 });
		 winPanel.add(okButton, BorderLayout.SOUTH);
		 frameWin.getContentPane().add(winPanel, BorderLayout.CENTER);
		 frameWin.setVisible(true);
	 }
 
	 
 
	 public static void main(String[] args) {
		 // Set look and feel to the java look
		 try {
			 UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		 } catch (Exception e) { }
 
		 createNewGame();
	 }
 
 }
 