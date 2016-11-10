package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;
import chat.view.ChatFirstPanel;

public class ChatbotController
{
	private Chatbot stupidBot;
	private ChatViewer display;
	private ChatFrame appFrame;
	private ChatFirstPanel appPanel;

	public ChatbotController()
	{
		stupidBot = new Chatbot("Jack");
		display = new ChatViewer();
		appFrame = new ChatFrame(this);
		
	}

	public void start()
	{
		

		
	}
	
	private String useChatbotCheckers(String input)

	{
		String checkedInput = "";
		
		if(stupidBot.memeChecker(input))
		{
			checkedInput = "You like memes!";
		}
		if(stupidBot.politicalTopicChecker(input))
		{
			checkedInput = "You like politics!";
		}
		if(stupidBot.contentChecker(input))
		{
			checkedInput = "You found my secret topic!";
			
		}
		if(stupidBot.keyboardMashChecker(input))
		{
			checkedInput = "You are just smashing the keyboard!";
		}
		if(stupidBot.inputHTMLChecker(input))
		{
			checkedInput = "You are typing in HTML!";
		}
		if(stupidBot.twitterChecker(input)){
			checkedInput = "You are trying to tweet using my dialog box!";
		}
		if(stupidBot.quitChecker(input))
		{
			System.exit(0);
		}
		if(checkedInput.length() == 0)
		{
			checkedInput = "I have no idea what you are talking about";
		}
		else
		{
			checkedInput = "I have no idea what you are talking about.";
		}
		
		return checkedInput;
	}
	
	public void respondToUser(String userInput)
	{
		
		if(stupidBot.lengthChecker(userInput))
		{
			
			String response = useChatbotCheckers(userInput);
			appPanel.responseText.setText(response);

		
		}
		else
		{
			appPanel.responseText.setText("What do you want to talk about?");

		}
		
		
	}
	/*public Chatbot getChatbot()
	{
		return Chatbot;
	}
	
	public ChatFrame getBaseFrame()
	{
		return ChatFrame;
	}*/
}
