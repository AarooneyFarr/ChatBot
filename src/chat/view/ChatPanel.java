package chat.view;

import javax.swing.*;
import chat.controller.ChatbotController;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import chat.model.Chatbot;
import java.awt.Color;

public class ChatPanel extends JPanel
{
	/**
	 * ChatbotController used by the Panel
	 */
	private ChatbotController baseController;
	
	/**
	 * Chatbot used by the Panel
	 */
	private Chatbot dumbBot;
	
	/**
	 * Button used to enter
	 */
	private JButton chatButton;
	
	/**
	 * text field used by the panel
	 */
	public JTextField chatField;
	
	/**
	 * Icon used in the panel
	 */
	private JLabel chatIcon;
	
	/**
	 * Layout used by the panel
	 */
	private SpringLayout baseLayout;
	
	/**
	 * JTextArea used in the panel
	 */
	private JTextArea chatDisplay;
	
	/**
	 * Scroll panel used in the panel
	 */
	private JScrollPane chatScroll;

	/**
	 * Constructor for the type JPanel
	 * @param baseController ChabotController used by the Panel
	 */
	public ChatPanel(ChatbotController baseController)
	{
		super();

		this.baseController = baseController;
		baseLayout = new SpringLayout();
		dumbBot = new Chatbot("Jack");

		chatField = new JTextField(25);
		chatIcon = new JLabel(new ImageIcon(ChatPanel.class.getResource("/chat/view/images/chatbot.png")), JLabel.CENTER);
		
		chatButton = new JButton("enter");
		

		chatDisplay = new JTextArea("What do you want to talk about?");
		chatScroll = new JScrollPane(chatDisplay,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		setupChatDisplay();

		setupPanel();
		setupLayout();
		setupListeners();

	}

	/**
	 * Sets up JTextArea named chatDisplay
	 */
	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
		chatDisplay.setWrapStyleWord(true);
		chatDisplay.setLineWrap(true);
		chatDisplay.setDisabledTextColor(Color.BLACK);
	
	}

	/**
	 * Sets up the layout of the Panel
	 */
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
		baseLayout.putConstraint(SpringLayout.NORTH, chatScroll, 37, SpringLayout.SOUTH, chatIcon);
		baseLayout.putConstraint(SpringLayout.WEST, chatScroll, 0, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatScroll, 146, SpringLayout.SOUTH, chatIcon);
		baseLayout.putConstraint(SpringLayout.EAST, chatScroll, 0, SpringLayout.EAST, chatField);



	}

	/**
	 * Sets up the Panel and adds gui components
	 */
	private void setupPanel()
	{
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(baseLayout);

		this.add(chatButton);

		this.add(chatField);

		this.add(chatScroll);

		this.add(chatIcon);

	}

	/**
	 * Adds Listeners for gui components and the JTextArea
	 */
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				if (dumbBot.lengthChecker(chatField.getText()))
				{

					String userResponse = chatField.getText();
					String chatbotResponse = baseController.respondToUser(userResponse);
					
					chatDisplay.append("\n"+"You said: " + userResponse +"\n"+ "Chatbot says: " + chatbotResponse);
					System.out.println(userResponse);
					chatField.setText("");

				}
			}
		});

		chatField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				String userResponse = chatField.getText();
				String chatbotResponse = baseController.respondToUser(userResponse);
				
				chatDisplay.append("\n"+"You said: " + userResponse +"\n"+ "Chatbot says: " + chatbotResponse);
				System.out.println(userResponse);
				chatField.setText("");
			}

		});

	}

}
