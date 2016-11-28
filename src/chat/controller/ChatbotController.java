package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;
import chat.view.ChatPanel;

public class ChatbotController
{
	/**
	 * instance of Chatbot used in the controller
	 */
	private Chatbot stupidBot;
	
	/**
	 * instance of chatViewer used in the controller
	 */
	private ChatViewer display;
	
	/**
	 * instance of ChatFrame used in the controller
	 */
	private ChatFrame appFrame;

	/**
	 * Instantiates all variables for the Chatbot Controller
	 */
	public ChatbotController()
	{
		stupidBot = new Chatbot("Jack");
		display = new ChatViewer();
		appFrame = new ChatFrame(this);

	}

	/**
	 * Method called in the runner
	 */
	public void start()
	{
		
	}

	/**
	 * Returns a pre-determined response based on the supplied string
	 * @param input User input
	 * @return Chatbot response
	 */
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

	/**
	 * Sets up a public response method
	 * @param userInput 
	 * @return Chatbot response
	 */
	public String respondToUser(String userInput)
	{
		String response = "";

		if (stupidBot.lengthChecker(userInput))
		{

			response = useChatbotCheckers(userInput);

		}

		return response;

	}

	/**
	 * Generates a random topic 
	 * @return random topic
	 */
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

	/**
	 * Getter for the ChatFrame
	 * @return ChatFrame
	 */
	public ChatFrame getBaseFrame(){
		return appFrame;
	}
	
	/**
	 * Getter for the Chatbot
	 * @return
	 */
	public Chatbot getChatbot()
	{
		return stupidBot;
	}


}
