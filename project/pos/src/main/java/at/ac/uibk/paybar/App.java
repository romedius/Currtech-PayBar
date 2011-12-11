package at.ac.uibk.paybar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.apache.soap.*;

/**
 * A very simple java swing application. 
 * Contains button and checkbox. Responds
 * to manipulations with these controls by
 * changing text in the main text area.
 * 
 * @author Michael Dejori
 */
public class App extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	JButton submitPayment = new JButton("Send Payment");
	JTextField barCode = new JTextField("aaa");
	JLabel barCodeLabel = new JLabel();
	JTextField amount = new JTextField("bb");
	JLabel amountLabel = new JLabel();
	JPanel bottomPanel = new JPanel(); // the bottom panel which holds button.
	JPanel holdAll = new JPanel(); // top level Panel


	public App()
	{
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(submitPayment);
		
		barCodeLabel.setLabelFor(barCode);
		barCodeLabel.setText("Bar Code: ");
		
		amountLabel.setLabelFor(amount);
		amountLabel.setText("Amount Payment: ");
		
		holdAll.setLayout(new GridLayout(0,2));
		
		holdAll.add(barCodeLabel);
		holdAll.add(barCode);
		holdAll.add(amountLabel);
		holdAll.add(amount);
		holdAll.add(bottomPanel, BorderLayout.SOUTH);
		
		getContentPane().add(holdAll, BorderLayout.CENTER);
 
		submitPayment.addActionListener(this);

 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	
	public static void main(String[] args)
	{
		App myApplication = new App();
		 
		// Specify where will it appear on the screen:
		myApplication.setLocation(10, 10);
		myApplication.setSize(300, 150);
		 
		// Show it!
		myApplication.setVisible(true);
	}
 

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == submitPayment){
			
		}
	}
}