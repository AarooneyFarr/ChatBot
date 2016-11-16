package chat.view;

import javax.swing.JFrame;

import chat.controller.ChatbotController;

import java.awt.Dimension;

public class ChatFrame extends JFrame
{
	private ChatbotController baseController;
	private ChatPanel appPanel;
	

	public ChatFrame(ChatbotController baseController)
	{
		super();
		this.baseController = baseController;
		appPanel = new ChatPanel(baseController);
	
		setupFrame(800, 800);
		setupListeners();
	}

	private void setupFrame(int frameWidth, int frameHeight)
	{
		this.setContentPane(appPanel);
		this.setTitle("JackBot");
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setVisible(true);
		
	}
	
	private void setupListeners()
	{
		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			
		@Override
		public void windowClosing(java.awt.event.WindowEvent windowEvent)
		{
			System.exit(0);
		}
				
			
				
		});
	}
	
	
}
