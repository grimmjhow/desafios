package br.com.crawler.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MrCrawlerTelegramThread extends Thread {

	@Autowired
	private TelegramService telegramService;

	@Override
	public void run() {
		telegramService.getUpdatesFromTelegram();
	}
}
