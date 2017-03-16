package chat.view;

import javax.swing.*;
import java.awt.*;
import chat.controller.ChatbotController;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import chat.model.*;

public class SearchPanel extends JPanel
	{
		private ChatbotController baseController;
		private JTextField mileField;
		private JTextField searchField;
		private JTextField lattitudeField;
		private JTextField longitudeField;
		private JLabel mileLabel;
		private JLabel searchLabel;
		private JLabel latLongLabel;
		private JLabel lattitudeLabel;
		private JLabel longitudeLabel;
		private JButton searchButton;
		private JTextArea resultsDisplay;
		private JScrollPane resultsScroll;
		
		public SearchPanel(ChatbotController baseController)
			{
				super();
				this.baseController = baseController;
				
				mileField = new JTextField();
				searchField = new JTextField();
				lattitudeField = new JTextField();
				longitudeField = new JTextField();
				mileLabel = new JLabel("Search with a radius of: ");
				searchLabel = new JLabel("Search for the word: ");
				latLongLabel = new JLabel("Start search from: ");
				lattitudeLabel = new JLabel("Lattitude: ");
				longitudeLabel = new JLabel("Longitude: ");
				searchButton = new JButton("Search");
				resultsDisplay = new JTextArea();
				resultsScroll = new JScrollPane(resultsDisplay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
				
				setupPanel();
				setupLayout();
				setupListeners();
			}
		private void setupChatDisplay()
			{
				resultsDisplay.setEditable(false);
				resultsDisplay.setEnabled(false);
				resultsDisplay.setWrapStyleWord(true);
				resultsDisplay.setLineWrap(true);
				resultsDisplay.setDisabledTextColor(Color.BLACK);
				

			}
		
		public void setupPanel()
		{
			this.add(mileField);
			this.add(searchField);
			this.add(lattitudeField);
			this.add(longitudeField);
			this.add(mileLabel);
			this.add(searchLabel);
			this.add(latLongLabel);
			this.add(lattitudeLabel);
			this.add(longitudeLabel);
			this.add(searchButton);
			this.add(resultsScroll);
		}
		
		public void setupLayout()
			{
				this.setBackground(Color.LIGHT_GRAY);
				this.setLayout(null);
				mileField.setBounds(27,198,145,33);
				searchField.setBounds(27,108,145,33);
				lattitudeField.setBounds(27,310,145,33);
				longitudeField.setBounds(27,398,145,33);
				mileLabel.setBounds(10,147,176,39);
				searchLabel.setBounds(10,57,176,39);
				latLongLabel.setBounds(27,243,176,39);
				lattitudeLabel.setBounds(10,274,176,39);
				longitudeLabel.setBounds(10,355,176,39);
				searchButton.setBounds(171,443,120,33);
				resultsScroll.setBounds(200,57,471,374);;
				
			}
			
		public void setupListeners()
		{
			searchButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent clickSearch)
						{
							if(isDouble(mileField.getText()) && isDouble(lattitudeField.getText()) && isDouble(longitudeField.getText()))
								{
									String results = baseController.useInvestigation(searchField.getText(), Double.parseDouble(lattitudeField.getText()), Double.parseDouble(longitudeField.getText()), Double.parseDouble(mileField.getText()));
									resultsDisplay.setText(results);
								}
						}
				});
		}
		
		private boolean isDouble(String input)
		{
			boolean isDouble = false;
			
			try
				{
					Double.parseDouble(input);
					isDouble = true;
				}
			catch(NumberFormatException e)
				{
					e.printStackTrace();
					isDouble = false;
					JOptionPane.showMessageDialog(getParent(), "Please make sure to enter a valid number for miles, lattitude, and longitude.");
				}
			return isDouble;			
			
		}
	}
