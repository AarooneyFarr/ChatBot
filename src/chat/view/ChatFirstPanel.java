package chat.view;

import javax.swing.JPanel;
import chat.controller.ChatbotController;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JTextArea;

public class ChatFirstPanel extends JPanel
{
	private ChatbotController baseController;
	private SpringLayout baseLayout;
	//private JTextArea input;
	private JButton enterButton;
	private JButton dumbButton;
	private JButton dumbButton2;
	private JButton dumbButton3;
	
	public ChatFirstPanel(ChatbotController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		//input = new JTextArea("type here");
		dumbButton2 = new JButton("dumb2");
		enterButton = new JButton("enter");
		
		
		dumbButton = new JButton("dumb");
		dumbButton3 = new JButton("dumb3");
		
		
		
		setupListeners();
		setupPanel();
		setupLayout();
	}
	
	public void setupLayout()
	{
		
		baseLayout.putConstraint(SpringLayout.WEST, enterButton, 6, SpringLayout.EAST, dumbButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, enterButton, 0, SpringLayout.SOUTH, dumbButton);
		baseLayout.putConstraint(SpringLayout.WEST, dumbButton, 83, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, dumbButton, -51, SpringLayout.SOUTH, this);
		
		baseLayout.putConstraint(SpringLayout.NORTH, dumbButton2, 0, SpringLayout.NORTH, enterButton);
		baseLayout.putConstraint(SpringLayout.WEST, dumbButton2, 6, SpringLayout.EAST, enterButton);
		baseLayout.putConstraint(SpringLayout.WEST, dumbButton3, 0, SpringLayout.WEST, enterButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, dumbButton3, -10, SpringLayout.SOUTH, this);
		
	}

	public void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GREEN);
		//this.add(input);
		this.add(enterButton);
		this.add(dumbButton);
		this.add(dumbButton2);
		this.add(dumbButton3);
	}
	
	public void setupListeners()
	{
		enterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				setBackground(Color.BLUE);
			}
		});
	}
}
