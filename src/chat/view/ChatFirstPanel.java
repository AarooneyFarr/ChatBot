package chat.view;

import chat.view.ChatFrame;
import javax.swing.JPanel;
import chat.controller.ChatbotController;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JLabel;
import chat.model.Chatbot;

public class ChatFirstPanel extends JPanel
{
	private ChatbotController baseController;
	private Chatbot dumbBot;
	private JButton enterButton;
	public JTextField input;
	private String userResponse;
	public JLabel responseText;

	public ChatFirstPanel(ChatbotController baseController)
	{
		super();
		this.baseController = baseController;
		userResponse = "";
		dumbBot = new Chatbot("Jack");
		input = new JTextField( "",10);
		responseText = new JLabel("What do you want to talk about?");

		enterButton = new JButton("enter");

		setupListeners();
		setupPanel();
		setupLayout();
	}

	public void setupLayout()
	{
		responseText.setBounds(310, 243, 200, 50);
		input.setBounds(338, 286, 122, 28);
		enterButton.setBounds(362, 386, 75, 28);

	}

	public void setupPanel()
	{
		this.setBackground(Color.GREEN);
		setLayout(null);
		
		this.add(enterButton);

		this.add(input);
		input.setFocusable(true);

		this.add(responseText);

	}

	public void setupListeners()
	{
		enterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				if(dumbBot.lengthChecker(input.getText()))
				{
					
				
				userResponse = input.getText();
				baseController.respondToUser(userResponse);
				}
			}
		});

		input.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				

				if(dumbBot.lengthChecker(input.getText()))
				{
				baseController.respondToUser(input.getText());
				}
			}
		});

	}

	public String getUserResponse()
	{
		return userResponse;
	}
}
