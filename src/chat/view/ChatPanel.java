package chat.view;

import javax.swing.*;
import chat.controller.ChatbotController;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import chat.model.Chatbot;

public class ChatPanel extends JPanel
{
	private ChatbotController baseController;
	private Chatbot dumbBot;
	private JButton chatButton;
	public JTextField chatField;
	private JLabel chatIcon;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	

	public ChatPanel(ChatbotController baseController)
	{
		super();

		this.baseController = baseController;
		baseLayout = new SpringLayout();
		dumbBot = new Chatbot("Jack");

		chatField = new JTextField(25);
		chatIcon = new JLabel(new ImageIcon(ChatPanel.class.getResource("/chat/view/images/chatbot.png")), JLabel.CENTER);
		
		chatButton = new JButton("enter");
		

		chatDisplay = new JTextArea(5, 25);
		
		setupChatDisplay();

		setupPanel();
		setupLayout();
		setupListeners();

	}

	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
		chatDisplay.setWrapStyleWord(true);
		chatDisplay.setLineWrap(true);

	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, chatIcon, 34, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatIcon, 318, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 479, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -34, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 360, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 0, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatDisplay, -71, SpringLayout.NORTH, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 241, SpringLayout.WEST, this);



	}

	private void setupPanel()
	{
		this.setBackground(Color.GREEN);
		setLayout(baseLayout);

		this.add(chatButton);

		this.add(chatField);

		this.add(chatDisplay);

		this.add(chatIcon);

	}

	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				if (dumbBot.lengthChecker(chatField.getText()))
				{

					String userResponse = chatField.getText();
					chatDisplay.setText(baseController.respondToUser(userResponse));
					System.out.println(userResponse);

				}
			}
		});

		chatField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				String userResponse = chatField.getText();
				chatDisplay.setText(baseController.respondToUser(userResponse));
				System.out.println(userResponse);
			}

		});

	}

}