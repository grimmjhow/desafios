package br.com.crawler.business;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import br.com.crawler.telegram.commands.TelegramCommand;
import br.com.crawler.telegram.commands.UnknowTelegramCommand;

@Service
public class TelegramService {
	
	private static final boolean READ_UPDATES_FOREVER = true;

	@Autowired
	private Map<String, TelegramCommand> commandTelegram;
	
	@Autowired
	private TelegramBot mrCrawler;
	
	@Autowired
	private GetUpdates updatesLimit100;
	
	private UnknowTelegramCommand defaultCommand;
	
	public TelegramService(TelegramBot mrCrawler) {
		this.defaultCommand = new UnknowTelegramCommand(mrCrawler);
	}
	
	public void getUpdatesFromTelegram() {
		
		int offset = 0;
		
		while(READ_UPDATES_FOREVER) {
			
			GetUpdatesResponse response = this.mrCrawler.execute(updatesLimit100.offset(offset));
			
			for(Update update : response.updates()) {
				
				offset = update.updateId()+1;
				
				Message message = update.message();
				
				if(message == null)
					continue;
				
				String command = extractCommand(message.text());
				
				this.commandTelegram.getOrDefault(command,defaultCommand)
							.execute(message);
			}
		}
		
	}
	
	protected String extractCommand(String command) {
		
		if(StringUtils.isNotBlank(command) && StringUtils.startsWith(command, "/"))
			return StringUtils.split(command," ")[0];
		
		return "";
	}
}
