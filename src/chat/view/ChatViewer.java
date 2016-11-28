package chat.view;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class ChatViewer
{
	/**
	 * String displayed in the top bar of the window
	 */
	private String windowMessage;
	
	/**
	 * Picture used in the popups
	 */
	private ImageIcon chatIcon;
	
	/**
	 * instantiates variables used in the ChatViewer
	 */
	public ChatViewer()
	{
		windowMessage = "This message is brought to you by chatbot!";
		chatIcon = new ImageIcon(getClass().getResource("images/chatbot.png"));
	}

	/**
	 * Creates a popup with the supplied question and a text area for response
	 * @param question The question you would like to ask
	 * @return User response to question
	 */
	public String collectResponse(String question)
	{

		String response = "";

		response = JOptionPane.showInputDialog(null, question, windowMessage, JOptionPane.INFORMATION_MESSAGE, chatIcon, null, "Type here please")+"";

		return response;
	}

	/**
	 * Shows a Message in the form of a popup
	 * @param message Message to be shown
	 */
	public void displayMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message);

	}

	/**
	 * Shows a yes or no popup with specified question
	 * @param question Question to be asked
	 * @return Users response
	 */
	public int collectUserOption(String question)
	{

		int response = 0;

		response = JOptionPane.showConfirmDialog(null, question);

		return response;
	}
}
