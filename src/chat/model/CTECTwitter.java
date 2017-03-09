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
import twitter4j.Paging;

public class CTECTwitter
	{
		private ChatbotController baseController;
		private Twitter chatbotTwitter;
		private List<Status> searchedTweets;

		private List<String> tweetedWords;

		private List<List<String>> rankedWords;

		public CTECTwitter(ChatbotController baseController)
			{
				this.baseController = baseController;
				searchedTweets = new ArrayList<Status>();

				tweetedWords = new ArrayList<String>();
				rankedWords = new ArrayList<List<String>>();

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
				String[] boringWords;

				int wordCount = 0;

				Scanner ignoredScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));

				while (ignoredScanner.hasNextLine())
					{
						ignoredScanner.nextLine();
						wordCount++;
					}
				boringWords = new String[wordCount];
				ignoredScanner.close();

				ignoredScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
				for (int index = 0; index < boringWords.length; index++)
					{
						boringWords[index] = ignoredScanner.nextLine();
					}

				ignoredScanner.close();
				return boringWords;
			}

		private void collectTweets(String username)
			{
				searchedTweets.clear();
				tweetedWords.clear();

				Paging statusPage = new Paging(1, 100);
				int page = 1;

				while (page <= 10)
					{
						statusPage.setPage(page);
						try
							{
								searchedTweets.addAll(chatbotTwitter.getUserTimeline(username, statusPage));
							}
						catch (TwitterException searchTweetError)
							{
								baseController.handleErrors(searchTweetError);
							}

						page++;
					}

			}

		public String getMostCommonWord(String username)
			{
				String popularWord = "";
				collectTweets(username);
				turnStatusesToWords();

				removeAllBoringWords();
				removeEmptyText();
				popularWord = rankWords();
				
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
				String[] boringWords = createIgnoredWordArray();
				for (int index = 0; index < tweetedWords.size(); index++)
					{
						for (int boringIndex = 0; boringIndex < boringWords.length; boringIndex++)
							{
								if (tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
									{
										tweetedWords.remove(index);
										index--;
										boringIndex = boringWords.length;
									}
							}
					}
			}

		private void turnStatusesToWords()
			{
				for (Status currentStatus : searchedTweets)
					{
						String tweetText = currentStatus.getText();
						String[] tweetWords = tweetText.split(" ");
						for (int index = 0; index < tweetWords.length; index++)
							{
								tweetedWords.add(tweetWords[index]);
							}
					}
			}

		private String rankWords()
		{
			List<String> wordNode = new ArrayList<String>();
			wordNode.add("");
			wordNode.add("0");
			int highestRank = 0;
			
			for(int index = 0; index < tweetedWords.size(); index++)
				{
					for(List<String> currentWordNode : rankedWords)
						{
							if(currentWordNode.contains(tweetedWords.get(index)))
								{
								
									int count = Integer.parseInt(currentWordNode.get(1));
									currentWordNode.set(1, count + 1 + "");
								}
							else{
									wordNode.set(0, tweetedWords.get(index));
									int count = Integer.parseInt(currentWordNode.get(1));
									wordNode.set(1, count + 1 + "");
									rankedWords.add(wordNode);
								}
						}
				}
			
			for(List<String> currentWordNode : rankedWords)
			{
				if(Integer.parseInt(currentWordNode.get(1)) > highestRank)
				{
					highestRank = Integer.parseInt(currentWordNode.get(1)); 
				}
			}
			
			return "The highest used word is: " + highestRank;
		}
		
		private String calculatePopularWordAndCount()
		{
			String information = "";
			String mostPopular = "";
			int popularIndex = 0;
			int popularCount = 0;
			
			for(int index = 0; index < tweetedWords.size(); index++)
				{
					int currentPopularity = 0;
					
					for(int searched = index + 1; searched < tweetedWords.size(); searched++)
						{
							popularCount++;
							currentPopularity ++;
						}
					if(currentPopularity > popularCount)
						{
							popularIndex = index;
							popularCount = currentPopularity;
							mostPopular = tweetedWords.get(index);
						}
				}
			
			information = "The most popular word is: " + mostPopular + ", and it occurred " + popularCount + " times out of " + tweetedWords.size() + ", AKA" + ((double) popularCount)/tweetedWords.size() + "%";
			
			return information;
			
		}

	}
