import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class PasswordGenerator {

	public static void main(String[] args) {
		
		JFrame vindu = new JFrame("Password generator");
		JTextField passwordField = new JTextField();
		JCheckBox checkBoxNums = new JCheckBox();
		JCheckBox checkBoxUpperCase = new JCheckBox();
		JCheckBox checkSpecial = new JCheckBox();
		JButton generatePassword = new JButton("Generate");
		JLabel labelNums = new JLabel("Include numbers");
		JLabel labelUpperCase = new JLabel("Include uppercase");
		JLabel labelSpecial = new JLabel("Include symbols");
		JLabel labelCount = new JLabel("Length of password (8-30)");
		SpinnerModel value = new SpinnerNumberModel(15,8,30,1); 
		JSpinner spinnerPasswordLength = new JSpinner(value);
		
		checkBoxNums.setBounds(20,20,20,20);
		labelNums.setBounds(50, 20, 150, 20);
		
		checkBoxUpperCase.setBounds(20, 45, 20, 20);
		labelUpperCase.setBounds(50, 45, 150, 20);
		checkSpecial.setBounds(20, 70, 20, 20);
		labelSpecial.setBounds(50, 70, 100, 20);
		labelCount.setBounds(20,110,200,20);
		spinnerPasswordLength.setBounds(20, 140, 70, 25);
		passwordField.setBounds(20, 200, 200, 25);
		generatePassword.setBounds(20, 230, 100, 30);
		
		vindu.add(checkBoxNums);
		vindu.add(labelNums);
		vindu.add(checkBoxUpperCase);
		vindu.add(labelUpperCase);
		vindu.add(checkSpecial);
		vindu.add(labelSpecial);
		vindu.add(labelCount);
		vindu.add(spinnerPasswordLength);
		vindu.add(passwordField);
		vindu.add(generatePassword);
		vindu.setSize(320,320);
		vindu.setResizable(false);
		vindu.setLayout(null);
		vindu.setVisible(true);
		vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		generatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean nums = checkBoxNums.isSelected();
				boolean upper = checkBoxUpperCase.isSelected();
				boolean symbs = checkSpecial.isSelected();
				int length = 0;
				
				try {
					spinnerPasswordLength.commitEdit();
				} catch(java.text.ParseException pe) {
					JOptionPane.showMessageDialog(vindu,"Error!");
				}
				length = (Integer) spinnerPasswordLength.getValue();
				
				StringBuilder passwordIn = new StringBuilder();
				passwordIn = randomSymbol(length, nums, upper, symbs);
				
				String passwordOut = passwordIn.toString();
				passwordField.setText(passwordOut);
			}
		});
	}
	
	public static StringBuilder randomSymbol (int length, boolean nums, boolean upper, boolean symbs) {

		String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8" ,"9"};
		String[] lowerCase = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
				"k", "l","m", "n", "o", "p", "q", "r", "s","t", "u", "v", "w", 
				"x", "y", "z"};
		String[] upperCase = new String[lowerCase.length];
		for (int i = 0; i < upperCase.length; i++) {
			upperCase[i] = lowerCase[i].toUpperCase();
		}
		String[] specialChars = {"!", "#", "%", "&", "$","@","?"};
		Random ran = new Random();
		StringBuilder output = new StringBuilder();
		ArrayList<String> charCollection = new ArrayList<String>();
		
		int extraCount = 0;
		
		// initialize arraylist with lower case letters
		for (int i = 0; i < lowerCase.length; i++) {
			charCollection.add(lowerCase[i]);
		}
		
		//add numbers to list of characters if selected
		//also adds one random number to guarantee password will contain at least one number
		if (nums == true) {
			for (int i = 0; i < numbers.length; i++) {
				charCollection.add(numbers[i]);
			}
			output.append(numbers[ran.nextInt(numbers.length)]);
			extraCount++;
		}
		
		// add upper case letters to characters if selected
		//also adds one random upper case to guarantee password will at least one upper case
		if (upper == true) {
			for (int i = 0; i < upperCase.length; i++) {
				charCollection.add(upperCase[i]);
			}
			output.append(upperCase[ran.nextInt(upperCase.length)]);
			extraCount++;
		}
		
		// add symbols to characters if selected
		//also adds one random symbol to guarantee password will contain at least one symbol
		if (symbs == true) {
			for (int i = 0; i < specialChars.length; i++) {
				charCollection.add(specialChars[i]);
			}
			output.append(specialChars[ran.nextInt(specialChars.length)]);
			extraCount++;
		}
		
		// choose random characters from the arraylist of length times and append output
		
		for (int i = 0; i < length-extraCount; i++) {
			output.append(charCollection.get(ran.nextInt(charCollection.size())));
		}		
		return output;
	}

}
