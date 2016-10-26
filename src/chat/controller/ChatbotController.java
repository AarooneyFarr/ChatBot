package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;

public class ChatbotController
{
	private Chatbot stupidBot;
	private ChatViewer display;
	

	public ChatbotController()
	{
		stupidBot = new Chatbot("Aaron");
		display = new ChatViewer();

	}

	public void start()
	{
		String response = "words";

		while (stupidBot.lengthChecker(response))
		{
			response = display.collectResponse("What do you want to talk about?");
			String checkedInputResponse = useChatbotCheckers(response);
			display.displayMessage(checkedInputResponse);
		}
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
		if(checkedInput.length() == 0)
		{
			checkedInput = "I have no idea what you are talking about";
		}
		
		return checkedInput;
	}
}
