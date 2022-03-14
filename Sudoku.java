import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.HashMap;

public class Sudoku extends JPanel {

	public static int boardSize = 9;
	public static String[][] solvedBoard = new String[boardSize][boardSize];
	public static HashMap<String[][], String[][]> puzzles9x9 = new HashMap<>();
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawLine(500, 20, 500, 400);
		g2.setColor(Color.black);
		
	}
	
	public static void main(String[] args) {
	
		drawUi();

	}
	

	
	public static void drawUi() {
		
		JFrame mainFrame = new JFrame("Sudoku");
		
		//set up menu bar
		JMenuBar mainMenuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem rules = new JMenuItem("Rules");
		JMenuItem addPuzzle = new JMenuItem("Add new puzzle");
		JMenuItem howItWorks = new JMenuItem("How the app works");
		mainMenuBar.add(menu);
		menu.add(rules);
		menu.add(addPuzzle);
		menu.add(howItWorks);
		mainFrame.setJMenuBar(mainMenuBar);
		rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame,"- Numbers 1-9 must by placed within each 3x3 grid.\n"
						+ "- Each row and column must contain numbers 1-9.\n"
						+ "- There is only one unique solution to each puzzle.\n"
						+ "- Etc");
			}
		});
		addPuzzle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add");
			}
		});
		howItWorks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "Check-button will check current input to a known solution.\n"
						+ "Reset-button will rest entire board"
						+ "New-button will select a brand new puzzle.\n" 
						+ "Solve-button will solve puzzle for you if a solution exists\n");
			}
		});
		
		// dimensions for spacing of grid and textfields
		int horizontalDistance = 25;
		int verticalDistance = 50;
		int textFieldSize = 30;
		
		// size of main window
		int frameWidth = 700;
		int frameHeight = 600;
		
		// get resolution to center window when app opens
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		
		// create the grid
		JTextField[][] squares = new JTextField[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				squares[i][j] = new JTextField();
				squares[i][j].setBounds(horizontalDistance, verticalDistance, textFieldSize, textFieldSize);
				mainFrame.add(squares[i][j]);
				horizontalDistance += 50;
			}
			verticalDistance += 50;
			horizontalDistance = 25;
		}
		

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().add(new Sudoku());
		mainFrame.setSize(frameWidth,frameHeight);
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);
		mainFrame.setLocation((screenWidth / 2) - (frameWidth/2),(screenHeight / 2) - (frameHeight / 2));
		mainFrame.setVisible(true);
		
		String[][] puzzleOne = {
				{"","7","","","2","","","4","6"},
				{"","6","","","","","8","9",""},
				{"2","","","8","","","7","1","5"},
				{"","8","4","","9","7","","",""},
				{"7","1","","","","","","5","9"},
				{"","","","1","3","","4","8",""},
				{"6","9","7","","","2","","","8"},
				{"","5","8","","","","","6",""},
				{"4","3","","","8","","","7",""}};
		
		String[][] puzzleOneSolution = {
				{"8","7","5","9","2","1","3","4","6"},
				{"3","6","1","7","5","4","8","9","2"},
				{"2","4","9","8","6","3","7","1","5"},
				{"5","8","4","6","9","7","1","2","3"},
				{"7","1","3","2","4","8","6","5","9"},
				{"9","2","6","1","3","5","4","8","7"},
				{"6","9","7","4","1","2","5","3","8"},
				{"1","5","8","3","7","9","2","6","4"},
				{"4","3","2","5","8","6","9","7","1"}};
		
		puzzles9x9.put(puzzleOne, puzzleOneSolution);
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (puzzleOne[i][j].length() != 0) {
				squares[i][j].setText(puzzleOne[i][j]);
				}
			}
		}
		
		JButton buttonCheck = new JButton("Check");
		buttonCheck.setBounds(530, 50, 80, 30);
		buttonCheck.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				String[][] numbersCheck = new String[boardSize][boardSize];
				for (int i = 0; i < numbersCheck.length; i++) {
					for (int j = 0; j < numbersCheck.length; j++) {
						numbersCheck[i][j] = squares[i][j].getText();
						
					}
				}
				
				boolean check = check(numbersCheck, puzzleOneSolution);
				if (check == false) {
					JOptionPane.showMessageDialog(mainFrame, "Incorrect");
				}
				else {
					JOptionPane.showMessageDialog(mainFrame, "Correct");
				}
			}
		});
		
		
		JButton buttonReset = new JButton("Reset");
		buttonReset.setBounds(530, 100, 80, 30);
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirm = JOptionPane.showConfirmDialog(mainFrame, "This will clear the entire board. Continue?", "Confirm",JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					for (int i = 0; i < boardSize; i++) {
						for (int j = 0; j < boardSize; j++) {
							squares[i][j].setText("");
							
						}
					}	
				} else if (confirm == JOptionPane.NO_OPTION) {
					return;
				}
			}
		});
		
		JButton buttonNew = new JButton("New");
		buttonNew.setBounds(530, 150, 80, 30);
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(mainFrame, "Move on to new puzzle?", "Confirm",JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					//code for choosing new puzzle from database
				} else if (confirm == JOptionPane.NO_OPTION) {
					return;
				}
			}
		});
		
		JButton buttonSolve = new JButton("Solve");
		buttonSolve.setBounds(530, 200, 80, 30);
		buttonSolve.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				String[][] getInput = new String[boardSize][boardSize];
				for (int i = 0; i < boardSize; i++) {
					for (int j = 0; j < boardSize; j++) {
						getInput[i][j] = squares[i][j].getText();
					}
				}
				if (solve(getInput)) {
					for (int i = 0; i < boardSize; i++) {
						for (int j = 0; j < boardSize; j++) {
							squares[i][j].setText(solvedBoard[i][j]);
						}
					}	
				} else {
					JOptionPane.showMessageDialog(mainFrame, "Could not solve board");
				}
				
				
			}
		});	
		
		mainFrame.add(buttonNew);
		mainFrame.add(buttonCheck);
		mainFrame.add(buttonReset);
		mainFrame.add(buttonSolve);
	}
	// check  current input to known solution
	public static boolean check(String[][] userInput, String[][] solution) {
		for (int i = 0; i  < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (userInput[i][j].length() != 0 ) {
					if (!userInput[i][j].equals(solution[i][j])) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	// check if input number exists in row
	public static boolean isInRow(String[][] board, String number, int row) {
		for (int i = 0; i < boardSize; i++) {
			if (board[row][i].equals(number)) {
				return true;
			}
		}
		return false;
	}
	
	//check if input number exists in column
	public static boolean isInColumn(String[][] board, String number, int column) {
		for (int i = 0; i < boardSize; i++) {
			if (board[i][column].equals(number)) {
				return true;
			}
		}
		return false;
		
	}
	
	// check if number exists in square
	public static boolean isInSquare(String[][] board, String number, int row, int column) {
		int startRow = row - row % 3;
		int startColumn = column - column % 3;
		for (int i = startRow; i < startRow + 3; i++) {
				for (int j = startColumn; j < startColumn + 3; j++) {
					if (board[i][j].equals(number))
						return true;
				}
			}
		return false;
	}
	
	// checks all conditions (row, column and square)
	public static boolean canPlace(String[][] board, int numIn, int row, int column) {

		String number = Integer.toString(numIn);
		if (isInRow(board, number, row) || isInColumn(board,number,column)  || isInSquare(board,number,row,column)) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	// add puzzle
	public static void addPuzzle(String[][] puzzle, String[][] solution) {
		puzzles9x9.put(puzzle, solution);
	}
	
	// solve puzzle
	public static boolean solve(String[][] input) {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (input[i][j].length() == 0) {
					for (int k = 1; k <= boardSize; k++) {
						if (canPlace(input,k,i,j)) {
							input[i][j] = Integer.toString(k);
							
							if (solve(input)) {
								return true;
							}
							else {
								input[i][j] = "";
							}
						}
					}
					return false;
				}
			}
		}
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				solvedBoard[i][j] = input[i][j];
			}
		}
		return true;
	}
}