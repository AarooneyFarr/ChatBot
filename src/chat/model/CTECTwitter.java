package chat.model;

import chat.controller.ChatbotController;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.Status;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class CTECTwitter
	{
		private ChatbotController baseController;
		private Twitter chatbotTwitter;
		private List<Status> searchedTweets;
		private List<String> ignoredWords;
		private List<String> tweetedWords;
		private Scanner ignoredScanner;

		public CTECTwitter(ChatbotController baseController)
			{
				this.baseController = baseController;
				searchedTweets = new ArrayList<Status>();
				ignoredWords = new ArrayList<String>();
				tweetedWords = new ArrayList<String>();

				this.chatbotTwitter = TwitterFactory.getSingleton();
			}

		public void sendTweet(String tweet)
			{
				try
					{
						chatbotTwitter.updateStatus(tweet);
					}
				catch (TwitterException tweetError)
					{
						baseController.handleErrors(tweetError);
					}
				catch (Exception authError)
					{
						baseController.handleErrors(authError);
					}
			}

		private String[] createIgnoredWordArray()
			{
				try
					{
						ignoredScanner = new Scanner(new File("commonWords.txt"));
					}
				catch (FileNotFoundException e)
					{

					}

				ignoredWords.clear();

				while (ignoredScanner.hasNextLine())
					{
						ignoredWords.add(ignoredScanner.nextLine());
					}
				return null;
			}

		private void collectTweets(String username)
			{
				// chatbotTwitter.searchUsers(username, arg1)
			}

		public String getMostCommonWord(String username)
			{
				collectTweets(username);
				turnStatusesToWords();
				
				removeAllBoringWords();
				removeEmptyText();
				
				String popularWord = "";
				
				return popularWord;
			}

		private void removeEmptyText()
			{
				for (int index = 0; index < tweetedWords.size(); index++)
					{
						if (tweetedWords.get(index).trim().equals(""))
							{
								tweetedWords.remove(index);
								index--;
							}
					}
			}

		private void removeAllBoringWords()
			{
				for (int index = 0; index < tweetedWords.size(); index++)
					{
						for (String boring : createIgnoredWordArray())
							{
								if (tweetedWords.get(index).equalsIgnoreCase(boring))
									{
										tweetedWords.remove(index);
										index--;
									}
							}
					}
			}

		private void turnStatusesToWords()
		{
			for(Status currentStatus : searchedTweets)
				{
					String tweetText = currentStatus.getText();
					String [] tweetWords = tweetText.split(" ");
					for(int index = 0; index < tweetWords.length; index++)
						{
							tweetedWords.add(tweetWords[index]);
						}
				}
		}
		
		
	}
