package chat.model;

import chat.controller.ChatbotController;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;

public class CTECTwitter
	{
		private ChatbotController baseController;
		private Twitter chatbotTwitter;
		
		public CTECTwitter(ChatbotController baseController)
			{
				this.baseController = baseController;
				this.chatbotTwitter = TwitterFactory.getSingleton();
			}

		public void sendTweet(String tweet)
			{
				try
					{
						chatbotTwitter.updateStatus(tweet);
					}
				catch(TwitterException tweetError)
					{
						baseController.handleErrors(tweetError);
					}
			}

	}
