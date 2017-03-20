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
import java.text.DecimalFormat;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Paging;
import twitter4j.GeoLocation;

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

				while (ignoredScanner.hasNext())
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

				int derp = 0;
				derp = derp * 32432;
				System.out.println(derp);
			}

		private String removePunctuation(String currentString)
			{
				String punctuation = ".,'?!:;\"(){}^[]<>-";

				String scrubbedString = "";
				for (int i = 0; i < currentString.length(); i++)
					{
						if (punctuation.indexOf(currentString.charAt(i)) == -1)
							{
								scrubbedString += currentString.charAt(i);

							}
					}
				return scrubbedString;
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
								tweetedWords.add(removePunctuation(tweetWords[index]));
							}
					}
			}

		private String rankWords()
			{

				rankedWords.clear();

				int highestRank = 0;

				for (int index = 0; index < tweetedWords.size(); index++)
					{
						List<String> wordNode = new ArrayList<String>();
						wordNode.add(tweetedWords.get(index));
						wordNode.add("0");

						if (rankedWords.size() == 0)
							{
								rankedWords.add(wordNode);
							}
						boolean added = false;
						for (int position = 0; position < rankedWords.size(); position++)
							{
								if (rankedWords.get(position).get(0).equalsIgnoreCase(wordNode.get(0)))
									{

										int count = Integer.parseInt(rankedWords.get(position).get(1));

										rankedWords.get(position).set(1, (count + 1) + "");
										added = true;

									}
							}
						if (!added)
							{
								wordNode.set(1, "1");
								rankedWords.add(wordNode);
							}

					}
				String popular = "";
				String popularCount = "";
				for (int position = 0; position < rankedWords.size(); position++)
					{
						if (Integer.parseInt(rankedWords.get(position).get(1)) > highestRank)
							{
								highestRank = Integer.parseInt(rankedWords.get(position).get(1));
								popular = rankedWords.get(position).get(0);
								popularCount = rankedWords.get(position).get(1);
							}
					}

				return "The highest used word is " + popular + " and it occurs " + popularCount + " times.";
			}

		private String calculatePopularWordAndCount()
			{
				String information = "";
				String mostPopular = "";
				int popularIndex = 0;
				int popularCount = 0;

				for (int index = 0; index < tweetedWords.size(); index++)
					{
						int currentPopularity = 0;

						for (int searched = index + 1; searched < tweetedWords.size(); searched++)
							{
								if (tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)))
									{
										currentPopularity++;

									}
							}
						if (currentPopularity > popularCount)
							{
								popularIndex = index;
								popularCount = currentPopularity;
								mostPopular = tweetedWords.get(index);
							}
					}

				information = "The most popular word is: " + mostPopular + ", and it occurred " + popularCount + " times out of " + tweetedWords.size() + ", AKA  "
						+ (DecimalFormat.getPercentInstance().format(((double) popularCount) / tweetedWords.size()));

				return information;

			}

		public String sampleInvestigation()
			{
				String results = "";
				int htmlNumber = 0;

				try
					{
						Query query = new Query("href");
						query.setCount(100);
						query.setGeoCode(new GeoLocation(40.509540, -111.857691), 25, Query.MILES);
						query.setSince("2017-1-1");
						QueryResult result = chatbotTwitter.search(query);
						results += "Count: " + result.getTweets().size() + "\n";
						for (Status tweet : result.getTweets())
							{
								if(baseController.getChatbot().inputHTMLChecker(tweet.getText()))
									{
										htmlNumber++;
										
										
									}
								
								
							}
					}
				catch (TwitterException error)
					{
						error.printStackTrace();
					}
				
				return "The number of HTML tweets is: " + htmlNumber;

			}
		
		public String investigation(String search, double lat, double longitude, double miles)
			{
				String results = "";
				

				try
					{
						Query query = new Query(search);
						query.setCount(100);
						query.setGeoCode(new GeoLocation(lat, longitude), miles, Query.MILES);
						query.setSince("2017-1-1");
						QueryResult result = chatbotTwitter.search(query);
						results += "Count: " + result.getTweets().size() + "\n";
						for (Status tweet : result.getTweets())
							{
								results += "@" + tweet.getUser().getName() + ": " + tweet.getText() + "\n" + "\n";
								
								
							}
					}
				catch (TwitterException error)
					{
						error.printStackTrace();
					}
				
				return results;

			}


	}
