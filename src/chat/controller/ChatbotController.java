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
			stupidBot.addToFile(display.collectResponse("what is your favorite meme?"),"memes.txt");
			stupidBot.buildMemesList();
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
		if (checkedInput.length() == 0)
		{
			checkedInput = "I cannot reply to nothing!";
		}
		if (stupidBot.lengthChecker(input) == false)
		{
			checkedInput = "I cannot reply to nothing!";

		}
		if (stupidBot.quitChecker(input))
		{
			System.exit(0);
		}
		int canBeRandom = (int) (Math.random() * 2);
		if (canBeRandom % 2 == 0)
		{
			checkedInput += " " + randomTopicGenerator();

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

	private String randomTopicGenerator()
	{
		String randomTopic = "";
		int random = (int) (Math.random() * 7);

		switch (random)
		{
		case 0:
			randomTopic = "Are you alive?";
			break;
		case 1:
			randomTopic = "Are you a robot?";
			break;
		case 2:
			randomTopic = "What is your favorite meme?";
			break;
		case 3:
			randomTopic = "Are you dead?";
			break;
		case 4:
			randomTopic = "Are you funny?";
			break;
		case 5:
			randomTopic = "Are you attractive?";
			break;
		case 6:
			randomTopic = "Are you bored?";
			break;
		default:
			randomTopic = "Are you breaking me?";
			break;

		}
		return randomTopic;
	}
}
