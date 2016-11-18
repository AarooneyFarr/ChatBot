package chat.model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

/**
 * Base version of the 2015 Chatbot class. Only stub methods are provided.
 * Students will complete methods as part * of the project. * @author Cody
 * Henrichsen * @version 1.0 10/14/15
 */
public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private ArrayList<String> keyboardMashList;
	private ArrayList<String> HTMLList;
	private ArrayList<String> twitterList;
	private String userName;
	private String content;
	private File memes;
	private Scanner memeScanner;
	private String memeFileName;

	/**
	 * * Creates an instance of the Chatbot with the supplied username. * @param
	 * userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		this.userName = userName;
		this.content = "outdoors";
		twitterList = new ArrayList<String>();
		buildTwitterList();
		HTMLList = new ArrayList<String>();
		buildHTMLList();
		keyboardMashList = new ArrayList<String>();
		buildKeyboardMashList();
		memesList = new ArrayList<String>();
		memeFileName = "memes.txt";
		memes = new File(memeFileName);
		try
		{
			memeScanner = new Scanner(memes);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildMemesList();
		politicalTopicList = new ArrayList<String>();
		buildPoliticalTopicsList();

	}

	public void buildMemesList()
	{
		memesList.clear();
		
			while (memeScanner.hasNextLine())
			{
				memesList.add(memeScanner.nextLine());
			}
			for (String currentMeme : memesList)
			{
				System.out.println(currentMeme);
			}
			
	}

	public void addToFile(String item, String fileName )
	{
		if (!item.equals("null"))
		{
			try (FileWriter fw = new FileWriter(fileName, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw))
			{
				out.println("\n"+item);

			}
			catch (IOException e)
			{
				// exception handling left as an exercise for the reader
			}
		}
		else
		{
			
		}

	}

	private void buildPoliticalTopicsList()
	{
		politicalTopicList.add("Democrat");
		politicalTopicList.add("Republican");
		politicalTopicList.add("11/8/16");
		politicalTopicList.add("conservative");
		politicalTopicList.add("Clinton");
		politicalTopicList.add("Trump");
		politicalTopicList.add("Kaine");
		politicalTopicList.add("Pence");
		politicalTopicList.add("Stein");
		politicalTopicList.add("Johnson");
		politicalTopicList.add("election");
		politicalTopicList.add("liberal");
		politicalTopicList.add("socialism");
		politicalTopicList.add("democracy");
		politicalTopicList.add("theocracy");
		politicalTopicList.add("oligarchy");
		politicalTopicList.add("debate");
		politicalTopicList.add("presidential");
		politicalTopicList.add("Hillary");
	}

	private void buildKeyboardMashList()
	{
		keyboardMashList.add("sgh");
		keyboardMashList.add("dfg");
		keyboardMashList.add("sdf");
		keyboardMashList.add("cvb");
		keyboardMashList.add(",./");
		keyboardMashList.add("asdf");
		keyboardMashList.add("sdfg");
		keyboardMashList.add("dgh");
		keyboardMashList.add("vbn");
		keyboardMashList.add("tyoi");
		keyboardMashList.add("qwerty");
		keyboardMashList.add("zxcv");
	}

	private void buildHTMLList()
	{
		HTMLList.add("<B>  </B>");
		HTMLList.add("<I> sdadas </i>");
		HTMLList.add("<P>");
		HTMLList.add("<A HREF=\"sdfs.html\"> </a>");

	}

	private void buildTwitterList()
	{
		twitterList.add("#dw35 f");
		twitterList.add("@d4d sretsf");

	}

	/**
	 * * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true. * @param currentInput * @return
	 * A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;

		if (currentInput != null && !currentInput.equals(""))
		{
			hasLength = true;
		}

		return hasLength;
	}

	/**
	 * * Checks if the supplied String matches the content area for this Chatbot
	 * instance.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether it
	 *            matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean hasContent = false;

		String tempInput = currentInput.toLowerCase();

		if (tempInput.contains(content.toLowerCase()))
		{
			hasContent = true;
		}

		return hasContent;
	}

	/**
	 * * Checks if supplied String matches ANY of the topics in the
	 * politicalTopicsList. Returns true if it does find a match and false if it
	 * does not match.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean hasPoliticalTopic = false;

		for (String currentPoliticalTopic : politicalTopicList)
		{
			if (currentInput.contains(currentPoliticalTopic))
			{
				hasPoliticalTopic = true;
			}
		}

		return hasPoliticalTopic;
	}

	/**
	 * * Checks to see that the supplied String value is in the current
	 * memesList variable.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean hasMeme = false;

		for (String currentMeme : memesList)
		{
			if (currentInput.contains(currentMeme))
			{
				hasMeme = true;
			}
		}

		return hasMeme;
	}

	/**
	 * * Returns the username of this Chatbot instance. * @return The username
	 * of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * * Returns the content area for this Chatbot instance. * @return The
	 * content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * * Getter method for the memesList object. * @return The reference to the
	 * meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}

	/**
	 * * Getter method for the politicalTopicList object. * @return The
	 * reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicList;
	}

	/**
	 * empty parameter right now
	 * 
	 * @param input
	 * @return
	 */
	public boolean keyboardMashChecker(String currentInput)
	{
		boolean hasKeyboardMash = false;

		for (String currentMashCheck : keyboardMashList)
		{
			if (currentInput.contains(currentMashCheck))
			{
				hasKeyboardMash = true;
			}
		}

		return hasKeyboardMash;
	}

	public boolean twitterChecker(String input)
	{
		boolean hasTwitter = false;

		for (String currentTwitterCheck : twitterList)
		{
			if (input.contains(currentTwitterCheck))
			{
				hasTwitter = true;
			}
		}

		return hasTwitter;
	}

	public boolean inputHTMLChecker(String currentInput)
	{
		boolean hasHTML = false;

		for (String currentHTMLCheck : HTMLList)
		{
			if (currentHTMLCheck.equals(currentInput))
			{
				hasHTML = true;
			}
		}

		return hasHTML;
	}

	public boolean quitChecker(String input)
	{
		boolean hasQuit = false;

		if (input.equals("quit"))
		{
			hasQuit = true;
		}

		return hasQuit;
	}

	/**
	 * * Updates the content area for this Chatbot instance. * @param content
	 * The updated value for the content area.
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
}
