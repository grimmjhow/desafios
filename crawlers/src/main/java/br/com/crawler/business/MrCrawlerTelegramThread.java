package br.com.crawler.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MrCrawlerTelegramThread extends Thread {

	private static final boolean READ_UPDATES_FOREVER = true;
	
	@Autowired
	private TelegramService telegramService;

	@Override
	public void run() {
		
		while(READ_UPDATES_FOREVER)
			telegramService.startingReadTelegramChat();
		
	}
}
