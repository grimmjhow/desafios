package br.com.crawler.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import br.com.crawler.configuration.DependencyInjectionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DependencyInjectionConfiguration.class)
public class TelegramServiceTest {
	
	@SpyBean
	private TelegramService spyTelegramService;

	@Test
	public void integratedTest() {
		spyTelegramService.getUpdatesFromTelegram();
	}
	
	@Test
	public void testebot() {
		
		TelegramBot bot = new TelegramBot("680282926:AAF43kzFkWlpPiJfdXbB1FW1uC73CyMydAE");
		
		int m = 0;
		
		while(true) {
			
			GetUpdatesResponse newMessages = bot.execute(new GetUpdates().limit(100).offset(m));
			
			List<Update> updates = newMessages.updates();
			
			for (Update update : updates) {
				
				m = update.updateId()+1;
				
				Message message = update.message();
				String text = message.text();
				
				if(text.equalsIgnoreCase("laisla")) {
					bot.execute(new SendMessage(update.message().chat().id(),"Quer casar comigo pepa?"));
					bot.execute(new SendMessage(update.message().chat().id(),"Eu te amo!"));
				}
				
			}
		}
	}
	
}
