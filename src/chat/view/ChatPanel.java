package chat.view;

import javax.swing.*;
import chat.controller.ChatbotController;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import chat.model.Chatbot;
import java.awt.Color;
import javax.swing.filechooser.*;
import java.io.*;
import java.io.FileFilter;

public class ChatPanel extends JPanel
	{
		
		private JFileChooser customFileChooser;
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
		 * JTextArea used in the panel
		 */
		private JTextArea chatDisplay;

		/**
		 * Scroll panel used in the panel
		 */
		private JScrollPane chatScroll;

		private JButton loadButton;
		private JButton saveButton;
		private JButton sendButton;
		private JButton analyzeButton;
		private JButton searchButton;
		private SearchPanel searchPanel;

		/**
		 * Constructor for the type JPanel
		 * 
		 * @param baseController
		 *            ChabotController used by the Panel
		 */
		public ChatPanel(ChatbotController baseController)
			{
				super();

				this.baseController = baseController;
				dumbBot = new Chatbot("Jack");

				chatField = new JTextField(25);
				chatIcon = new JLabel(new ImageIcon(ChatPanel.class.getResource("/chat/view/images/chatbot.png")), JLabel.CENTER);
				searchPanel = new SearchPanel(baseController);
				chatButton = new JButton("enter");
				loadButton = new JButton("load");
				saveButton = new JButton("save");
				sendButton = new JButton("send");
				analyzeButton = new JButton("analyze");
				searchButton = new JButton("search");

				chatDisplay = new JTextArea("What do you want to talk about?");
				chatScroll = new JScrollPane(chatDisplay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

				customFileChooser = new JFileChooser();
				
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
				analyzeButton.setBounds(656, 34, 86, 71);
				chatScroll.setBounds(241, 253, 310, 109);
				chatField.setBounds(241, 419, 310, 26);
				chatIcon.setBounds(318, 34, 159, 182);
				chatButton.setBounds(360, 479, 76, 29);
				loadButton.setBounds(656, 556, 75, 71);
				saveButton.setBounds(656, 382, 75, 71);
				sendButton.setBounds(656, 208, 75, 71);
				searchButton.setBounds(656,684,75,71);

			}

		/**
		 * Sets up the Panel and adds gui components
		 */
		private void setupPanel()
			{
				this.setBackground(Color.LIGHT_GRAY);
				setLayout(null);
				this.add(loadButton);
				this.add(saveButton);
				this.add(sendButton);
				this.add(analyzeButton);
				this.add(chatButton);

				this.add(chatField);

				this.add(chatScroll);

				this.add(chatIcon);
				this.add(searchButton);

			}

		/**
		 * Adds Listeners for gui components and the JTextArea
		 */
		private void setupListeners()
			{
				loadButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent clickLoad)
							{
								// TODO add choice for custom conversation file

								Object[] options = { "Custom File", "Previous File" };
								int response = JOptionPane.showOptionDialog(getParent(), "Load Custom File or previous Conversations", "Load Options", JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.INFORMATION_MESSAGE, null, options, "Custom File");
								if (response == 0)
									{
										loadFileConversation();
									}
								if (response == 1)
									{
										loadConversation();
									}
							}
					});

				saveButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent clickSave)
							{
								saveConversation();
							}
					});
				
				searchButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent clickSearch)
							{
								baseController.getSearchFrame().setContentPane(searchPanel);
								baseController.getSearchFrame().setVisible(true);
							}
					});
				
				sendButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent click)
							{
								baseController.useTwitter(chatField.getText());
							}
					});
				
				analyzeButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent clickAnalyze)
							{
								String results = baseController.searchTwitterUser(chatField.getText());
								String results1 = baseController.useSampleInvestigation();
								chatDisplay.setText(results + "\n" + results1);
								
								
							}
					});

				chatButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent clickChat)
							{
								if (dumbBot.lengthChecker(chatField.getText()))
									{

										String userResponse = chatField.getText();
										String chatbotResponse = baseController.respondToUser(userResponse);

										chatDisplay.append("\n" + "You said: " + userResponse + "\n" + "Chatbot says: " + chatbotResponse);
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

								chatDisplay.append("\n" + "You said: " + userResponse + "\n" + "Chatbot says: " + chatbotResponse);
								System.out.println(userResponse);
								chatField.setText("");
							}

					});

			}

		private void loadConversation()
			{

				chatDisplay.setText("");

				for (String currentLine : dumbBot.getConversationsList())
					{
						chatDisplay.append(currentLine + "\n");

					}
			}
		
		private void loadFileConversation()
		{
			FileNameExtensionFilter customFilter = new FileNameExtensionFilter("Text Files", "txt"); 
			customFileChooser.setFileFilter(customFilter);
			
			int returnVal = customFileChooser.showOpenDialog(getParent());
			
			
			
			if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File customFile = customFileChooser.getSelectedFile();
					for (String currentLine : dumbBot.scanToList(customFile))
						{
							chatDisplay.append(currentLine + "\n");

						}
				}
			else
				{
					
				}
		}

		private void saveConversation()
			{
				int confirm = JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to save this conversation to the previous conversations file?");
				if (confirm == 0)
					{
						dumbBot.addToFile(chatDisplay.getText(), "conversations.txt");
						JOptionPane.showMessageDialog(getParent(), "File Saved");
						
					}
				else
					{

					}
			}

	}
