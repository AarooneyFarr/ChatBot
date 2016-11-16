package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;
import chat.view.ChatPanel;

public class ChatbotController
{
	private Chatbot stupidBot;
	private ChatViewer display;
	private ChatFrame appFrame;

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

		if (stupidBot.memeChecker(input))
		{
			checkedInput = "You like memes!";
		}
		if (stupidBot.politicalTopicChecker(input))
		{
			checkedInput = "You like politics!";
		}
		if (stupidBot.contentChecker(input))
		{
			checkedInput = "You found my secret topic!";

		}
		if (stupidBot.keyboardMashChecker(input))
		{
			checkedInput = "You are just smashing the keyboard!";
		}
		if (stupidBot.inputHTMLChecker(input))
		{
			checkedInput = "You are typing in HTML!";
		}
		if (stupidBot.twitterChecker(input))
		{
			checkedInput = "You are trying to tweet using my dialog box!";
		}
		if(checkedInput.length() == 0)
		{
			checkedInput = "I cannot reply to nothing!";
		}
		if(stupidBot.lengthChecker(input))
		{
			checkedInput = "I cannot reply to nothing!";

		}
		if (stupidBot.quitChecker(input))
		{
			System.exit(0);
		}

		return checkedInput;
	}

	public String respondToUser(String userInput)
	{
		String response = "";

		if (stupidBot.lengthChecker(userInput))
		{

			response = useChatbotCheckers(userInput);

		}

		return response;

	}
	
}
