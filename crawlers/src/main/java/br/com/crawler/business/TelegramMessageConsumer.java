package br.com.crawler.business;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;

import com.pengrad.telegrambot.model.Update;

public class TelegramMessageConsumer implements Consumer<Update> {
	
	@Autowired
	private TelegramService service;
	
	@Override
	public void accept(Update update) {
		
	}
	
	@Override
	public Consumer<Update> andThen(Consumer<? super Update> after) {
		return Consumer.super.andThen(after);
	}

}
