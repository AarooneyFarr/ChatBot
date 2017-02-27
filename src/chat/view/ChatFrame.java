package chat.view;

<<<<<<< HEAD
public class ChatFrame
{

=======
import javax.swing.JFrame;

import chat.controller.ChatbotController;

import java.awt.Dimension;

public class ChatFrame extends JFrame
{
	/**
	 * instance of the ChatbotController used in ChatFrame
	 */
	private ChatbotController baseController;
	
	/**
	 * instance of the Panel used in ChatFrame
	 */
	private ChatPanel appPanel;
	
	/**
	 * Constructor for Chatframe, instantiates all variables
	 * @param baseController Controller used in ChatFrame
	 */
	public ChatFrame(ChatbotController baseController)
	{
		super();
		this.baseController = baseController;
		appPanel = new ChatPanel(baseController);
	
		setupFrame(800, 800);
		setupListeners();
	}

	/**
	 * Sets up the initial Frame
	 * @param frameWidth Sets width of the Frame
	 * @param frameHeight Sets Height of the Frame
	 */
	private void setupFrame(int frameWidth, int frameHeight)
	{
		this.setContentPane(appPanel);
		this.setTitle("JackBot");
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setVisible(true);
		
	}
	
	/**
	 * Adds all Listeners
	 */
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
	
	
>>>>>>> origin/master
}
