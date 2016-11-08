package chat.view;

import javax.swing.JPanel;
import chat.controller.ChatbotController;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatFirstPanel extends JPanel
{
	private ChatbotController baseController;
	private SpringLayout baseLayout;
	
	
	public ChatFirstPanel(ChatbotController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		
		setupListeners();
		setupPanel();
		setupLayout();
	}
	
	public void setupLayout()
	{
		
	}

	public void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GREEN);
	}
	
	public void setupListeners()
	{
		
	}
}
