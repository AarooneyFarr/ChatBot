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
		/**
		 * List of memes
		 */
		private ArrayList<String> memesList;

		/**
		 * list of political topics
		 */
		private ArrayList<String> politicalTopicList;

		/**
		 * list of Strings that could be interpreted as keyboard mashing
		 */
		private ArrayList<String> keyboardMashList;

		/**
		 * list of Strings that could be interpreted as HTML
		 */
		private ArrayList<String> HTMLList;

		/**
		 * list of Strings that could be interpreted as twitter jargon
		 */
		private ArrayList<String> twitterList;

		/**
		 * variable indicating the current username
		 */
		private String userName;

		/**
		 * variable for specialized content
		 */
		private String content;

		/**
		 * File storing current memes
		 */
		private File memes;

		/**
		 * Scanner for the meme file
		 */
		private Scanner memeScanner;

		/**
		 * Variable for the meme file location
		 */
		private String memeFileName;

		/**
		 * Variables For the saving the conversations of chatbot.
		 **/

		private String conversationsFileName;
		private Scanner conversationsScanner;
		private File conversations;
		private ArrayList<String> conversationsList;

		private Scanner customScanner;
		private ArrayList<String> customConversationsList;

		/**
		 * 
		 * * Creates an instance of the Chatbot with the supplied username.
		 * * @param userName The username for the chatbot.
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
				politicalTopicList = new ArrayList<String>();
				buildPoliticalTopicsList();
				customConversationsList = new ArrayList<String>();

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

				conversationsList = new ArrayList<String>();
				conversationsFileName = "conversations.txt";
				conversations = new File(conversationsFileName);
				try
					{
						conversationsScanner = new Scanner(conversations);
					}
				catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
				buildConversationsList();

			}

		public ArrayList<String> getConversationsList()
			{
				return conversationsList;
			}

		/**
		 * Reads the conversation into a list
		 */
		public void buildConversationsList()
			{
				conversationsList.clear();

				while (conversationsScanner.hasNextLine())
					{
						conversationsList.add(conversationsScanner.nextLine());
					}

			}

		/**
		 * Reads a list from the memes.txt file
		 */
		public void buildMemesList()
			{
				memesList.clear();

				while (memeScanner.hasNextLine())
					{
						memesList.add(memeScanner.nextLine());
					}

			}

		/**
		 * Adds a word/phrase to a file list
		 * 
		 * @param item
		 *            word/phrase to be added
		 * @param fileName
		 *            The name of the file the item is top be added to
		 */
		public void addToFile(String item, String fileName)
			{
				if (!item.equals("null"))
					{
						try (FileWriter fw = new FileWriter(fileName, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw))
							{
								out.println(item);

							}
						catch (IOException e)
							{
								// exception handling left as an exercise for
								// the reader
							}
					}
				else
					{

					}

			}

		public ArrayList<String> scanToList(File customFile)
			{
				customConversationsList.clear();

				try
					{
						customScanner = new Scanner(customFile);

					}
				catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
				
				while(customScanner.hasNextLine())
					{
						customConversationsList.add(customScanner.nextLine());
					}

				return customConversationsList;
			}

		/**
		 * builds the static political topics list
		 */
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

		/**
		 * builds the static keyboardMashList
		 */
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

		/**
		 * builds the static HTML list
		 */
		private void buildHTMLList()
			{
				HTMLList.add("<B>  </B>");
				HTMLList.add("<I> sdadas </i>");
				HTMLList.add("<P>");
				HTMLList.add("<A HREF=\"sdfs.html\"> </a>");

			}

		/**
		 * builds the static twitter list
		 */
		private void buildTwitterList()
			{
				twitterList.add("#dw35 f");
				twitterList.add("@d4d sretsf");

			}

		/**
		 * * Checks the length of the supplied string. Returns false if the
		 * supplied String is empty or null, otherwise returns true. * @param
		 * currentInput * @return A true or false based on the length of the
		 * supplied String.
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
		 * * Checks if the supplied String matches the content area for this
		 * Chatbot instance.
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
		 * politicalTopicsList. Returns true if it does find a match and false
		 * if it does not match.
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
						if (currentInput.equals(currentMeme))
							{
								hasMeme = true;
							}
					}

				return hasMeme;
			}

		/**
		 * * Returns the username of this Chatbot instance. * @return The
		 * username of the Chatbot.
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
		 * * Getter method for the memesList object. * @return The reference to
		 * the meme list.
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
		 * Returns true if input matches an item from the keyboard mash checker
		 * list
		 * 
		 * @param input
		 * @return Returns boolean checking if item is in keyboardMashList
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

		/**
		 * Returns true if input matches an item from the twitterList
		 * 
		 * @param input
		 * @return Returns a boolean checking if an item is in twitterList
		 */
		public boolean twitterChecker(String input)
			{
				boolean looksTweetable = false;
				if (input == null)
					{
						return false;
					}
				int indexOfHash = input.indexOf("#");
				int indexOfAt = input.indexOf("@");

				if (indexOfHash >= 0 || indexOfAt >= 0)
					{
						if (indexOfHash != -1)
							{
								if (!input.substring(indexOfHash + 1, indexOfHash + 2).equals(" "))
									{
										looksTweetable = true;
									}
							}
						if (indexOfAt > -1)
							{
								if (input.indexOf(" ", indexOfAt) != indexOfAt + 1)
									{
										looksTweetable = true;
									}
							}
					}
				return looksTweetable;
			}

		/**
		 * Returns true if input matches an item from the HTMLList
		 * 
		 * @param currentInput
		 * @return Returns a boolean checking if an item is in HTMLList
		 */
		public boolean inputHTMLChecker(String input)
			{
				boolean containsHTML = false;
				if (input == null || !input.contains("<"))
					{
						return containsHTML;
					}
				int firstOpen = input.indexOf("<");
				int firstClose = input.indexOf(">", firstOpen);
				int secondOpen = -9;
				int secondClose = -9;
				String tagText = "";
				if (input.contains("<>") || input.indexOf("< >") > -1)
					{
						containsHTML = false;
					}
				if (input.toUpperCase().contains("<P>") || input.toLowerCase().contains("<br>"))
					{
						containsHTML = true;
					}
				else if (firstClose > firstOpen)
					{
						tagText = input.substring(firstOpen + 1, firstClose).toLowerCase();
						secondOpen = input.toLowerCase().indexOf("</" + tagText, firstClose);

						if (tagText.contains("a href=\""))
							{
								if (tagText.indexOf("\"", firstOpen + 10) >= 0)
									{
										containsHTML = true;
									}
							}
						else if (secondOpen >= 0)
							{
								secondClose = input.indexOf(">", secondOpen + tagText.length());
								String closingTag = input.toLowerCase().substring(secondOpen + 2, secondClose);
								if (secondClose > +0 && closingTag.equals(tagText))
									{
										containsHTML = true;
									}
								else
									{
										containsHTML = false;
									}
							}
						else
							{
								containsHTML = false;
							}
					}
				else
					{
						containsHTML = false;
					}

				return containsHTML;
			}

		/**
		 * Checks to see if User would like to quit the program
		 * 
		 * @param input
		 * @return Returns a boolean checking if the user has entered "quit"
		 */
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
		 * * Updates the content area for this Chatbot instance. * @param
		 * content The updated value for the content area.
		 */
		public void setContent(String content)
			{
				this.content = content;
			}
	}
