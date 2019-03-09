package br.com.crawler.business;

import com.pengrad.telegrambot.model.Message;

public interface TelegramCommand {
	
	default void execute(Message message) {
		System.out.println("Mensagem não reconhecida");
	}
	
}
