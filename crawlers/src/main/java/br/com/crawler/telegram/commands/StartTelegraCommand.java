package br.com.crawler.telegram.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;

public class StartTelegraCommand implements TelegramCommand {
	
	private TelegramBot mrCrawler;
	
	public StartTelegraCommand(TelegramBot mrCrawler) {
		this.mrCrawler = mrCrawler;
	}



	@Override
	public void execute(Message message) {
		
		StringBuilder welcomeMessage = new StringBuilder("Hi! To get top subreddits for today").append(System.lineSeparator())
											.append("Just type: /nadaprafazer subreddit1;subreddit2").append(System.lineSeparator())
											.append("and I'll send you the data");
		
		this.mrCrawler.execute(new SendMessage(message.chat().id(),welcomeMessage.toString()));
	}
}
