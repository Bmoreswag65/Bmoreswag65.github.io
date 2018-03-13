package calculator;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;



public class InterestGUI extends JFrame implements ActionListener{
	double principal;
	double year;
	double rate;



	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new InterestGUI();
			}
		});



	}
	//Tier1
	JFrame interestWindow;

	//Tier 2
	JPanel boxInsertButton;
	JPanel boxTextInsert;
	JPanel boxOutputText = new JPanel();
	JPanel out;
	/*JPanel horzSpace = new JPanel();
	JPanel vertSpace = new JPanel();*/


	//Tier 3
	JButton boxSim = new JButton("Compute Simple Interest");
	JButton boxComp = new JButton("Compute Compund Interest");

	JLabel prinField = new JLabel("Principal: ");
	JLabel rateField = new JLabel("Rate(Percentage): ");
	JLabel yearField = new JLabel("Years: ");
	JLabel simOrCompString =  new JLabel("FIND YOUR INTEREST NUMBER IF YOU DARE >:()");

	JTextField prinInput = new JTextField();
	JTextField rateInput = new JTextField();
	JTextField yearInput = new JTextField();


	//Dimensional Creations
	int[] dimMyHeight = {500, 200, 600, 750};
	int[] dimMyWidth = {50, 550};

	Dimension creatingWindowDis = new Dimension(dimMyHeight[3], dimMyWidth[0]);
	Dimension creatingUsedButtons = new Dimension(dimMyHeight[2], dimMyWidth[0]);

	//Style Usage




	public InterestGUI(){
		interestWindow = new JFrame("Interest Super Calculator");
		//interestWindow.setSize(300, 150);
		boxInsertButton = new JPanel();
		boxTextInsert = new JPanel();
		interestWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Making Buttons...
		boxInsertButton.setLayout(new FlowLayout());
		boxInsertButton.add(boxSim);
		boxInsertButton.add(boxComp);
		//Make the Buttons on Top of each other 
		Box box = Box.createVerticalBox();
		box.add(boxSim);
		box.add(Box.createVerticalStrut(10)); 
		box.add(boxComp);
		boxInsertButton.add(box);
		//Setting font for buttons 
		boxSim.setPreferredSize(creatingWindowDis);
		boxSim.setFont(new Font("Castellar", Font.BOLD, 30));
		boxComp.setPreferredSize(creatingWindowDis);
		boxComp.setFont(new Font("Castellar", Font.BOLD, 30));
		//Making Text Portion...
		boxTextInsert.add(prinField);
		boxTextInsert.add(prinInput);
		boxTextInsert.add(rateField);
		boxTextInsert.add(rateInput);
		boxTextInsert.add(yearField);
		boxTextInsert.add(yearInput);
		//Adding Action to the Buttons
		boxComp.addActionListener(new yearIn());
		//Correcting JFrame Alignment 
		out = new JPanel(new BorderLayout ());
		boxTextInsert.setBackground(Color.GREEN);
		out.add(boxTextInsert, BorderLayout.NORTH);
		boxInsertButton.setBackground(Color.BLACK);
		out.add(boxInsertButton, BorderLayout.CENTER);
		simOrCompString.setFont(new Font("Chiller", Font.BOLD, 72));
		boxOutputText.add(simOrCompString);
		simOrCompString.setForeground(Color.RED);
		out.add(boxOutputText, BorderLayout.SOUTH);


		boxSim.setAlignmentX(CENTER_ALIGNMENT);
		boxComp.setAlignmentX(CENTER_ALIGNMENT);


		interestWindow.add(out);

		//Setting Size of JTextField and JLabel 
		prinInput.setPreferredSize(creatingUsedButtons);
		prinField.setForeground(Color.YELLOW);
		prinInput.setFont(new Font("Elephant", Font.BOLD, 50));
		prinField.setFont(new Font("Elephant", Font.BOLD, 56));
		prinField.setHorizontalAlignment(SwingConstants.RIGHT);
		rateInput.setPreferredSize(creatingUsedButtons);
		rateField.setForeground(Color.YELLOW);
		rateInput.setFont(new Font("Elephant", Font.BOLD, 50));
		rateField.setFont(new Font("Elephant", Font.BOLD, 56));
		rateField.setHorizontalAlignment(SwingConstants.RIGHT);
		yearInput.setPreferredSize(creatingUsedButtons);
		yearField.setForeground(Color.YELLOW);
		yearInput.setFont(new Font("Elephant", Font.BOLD, 50));
		yearField.setFont(new Font("Elephant", Font.BOLD, 56));
		yearField.setHorizontalAlignment(SwingConstants.RIGHT);


		interestWindow.pack();
		interestWindow.setVisible(true);


		boxSim.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				String prinText = prinInput.getText();
				principal = Double.parseDouble(prinText);
				String rateText = rateInput.getText();
				rate = Double.parseDouble(rateText);
				String yearText = yearInput.getText();
				year = Double.parseDouble(yearText);

				double result = principal + (principal*(rate/100)*year);
				String formatNum = NumberFormat.getCurrencyInstance().format(result);

				simOrCompString.setText("Computed Simple Interest: " + formatNum);

			}
		});









	}





	private class yearIn implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String prinText = prinInput.getText();
			principal = Double.parseDouble(prinText);
			String rateText = rateInput.getText();
			rate = Double.parseDouble(rateText);
			String yearText = yearInput.getText();
			year = Double.parseDouble(yearText);

			double result = principal*Math.pow(1+(rate/100), year);

			String formatNum = NumberFormat.getCurrencyInstance().format(result);
			simOrCompString.setText("Computed Compound Interest: " + formatNum);


		}






	}






















	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}


