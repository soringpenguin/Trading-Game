import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GUI extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6300871629154863889L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GUI(true);
	}
	
	
	JScrollPane scrollPane;
	JTextArea text;
	JTextField input_text;
	String input = "";
	JButton input_button;
	JPanel panel;
	boolean isFirst = true;
	
	public GUI(boolean run_gui) {
		this.setLocation(100, 100);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(null);
	    
	    input_button = new JButton("Enter");
	    input_button.addActionListener(new Enter());
	    input_button.setSize(200, 40);
	    input_button.setLocation(220, 15);
	    this.add(input_button);
	    
	    input_text = new JTextField("");
	    input_text.setSize(200, 20);
	    input_text.setLocation(10, 25);
	    this.add(input_text);

	    text = new JTextArea();
		scrollPane = new JScrollPane(text);
		scrollPane.setSize(480, 500);
		scrollPane.setLocation(10, 70);
		text.setEditable(true);
		this.add(scrollPane);
		
	    this.setSize(500, 600);
		if(run_gui)
			this.setVisible(true);
	}
	
	private class Enter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!input_text.getText().equals("")) {
				input = input_text.getText();
				input_text.setText("");
			}
			else
				System.out.println(input_text.getText() + ", " + input);
		}
		
	}
	
	public void print(String str) {
		String temp = text.getText();
		if(isFirst) {
			text.setText(temp + str);
			isFirst = false;
		} else
			text.setText(str + "\n" + temp);
	}
	
	public String get_input() {
		return input;
	}

}
