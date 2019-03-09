package br.com.crawler.telegram.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;

public class UnknowTelegramCommand implements TelegramCommand {
	
	private TelegramBot mrCrawler;
	
	public UnknowTelegramCommand(TelegramBot mrCrawler) {
		this.mrCrawler = mrCrawler;
	}

	@Override
	public void execute(Message message) {
		this.mrCrawler.execute(new SendMessage(message.chat().id(), "Sorry, I don't recognize this command. =["));
	}
}
