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
		private Scanner ignoredScanner;

		public CTECTwitter(ChatbotController baseController)
			{
				this.baseController = baseController;
				searchedTweets = new ArrayList<Status>();
				ignoredWords = new ArrayList<String>();
				try
					{
						ignoredScanner = new Scanner(new File("commonWords.txt"));
					}
				catch(FileNotFoundException e)
					{
						
					}
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
		
		private String[] createIgnoredWordList()
		{
			
			ignoredWords.clear();

			while (ignoredScanner.hasNextLine())
				{
					ignoredWords.add(ignoredScanner.nextLine());
				}
			return null;
		}

		private void collectTweets(String username)
		{
			//chatbotTwitter.searchUsers(username, arg1)
		}
		
		public String getMostCommonWord(String username)
		{
			String popularWord = "";
			//TODO add full use method
			return popularWord;
		}
	}
