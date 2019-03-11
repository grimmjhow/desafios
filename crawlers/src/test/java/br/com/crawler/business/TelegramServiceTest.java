package br.com.crawler.business;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import br.com.crawler.configuration.DependencyInjectionConfiguration;
import br.com.crawler.services.RedditCrawlerService;
import br.com.crawler.telegram.commands.TelegramCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DependencyInjectionConfiguration.class)
public class TelegramServiceTest {
	
	@SpyBean
	private TelegramService spyTelegramService;
	
	private TelegramService mockTelegramService;
	
	@MockBean
	private TelegramBot mockCrawlerBot;
	
	@MockBean
	private RedditCrawlerService mockRedditService;
	
	@MockBean
	private GetUpdatesResponse mockResponse;
	
	@MockBean
	private Update mockUpdate;
	
	private GetUpdates mockUpdatesLimit100 = Mockito.mock(GetUpdates.class,Mockito.RETURNS_DEEP_STUBS);
	
	@Before
	public void setup() {
		Map<String, TelegramCommand> commandTelegram = new HashMap<>();
		
		this.mockTelegramService = Mockito.spy(new TelegramService(commandTelegram, mockCrawlerBot,mockUpdatesLimit100,null));
	}
	
	@Test
	public void verifyExtractCommand() {
		
		String command1 = spyTelegramService.extractCommand("idWall");
		assertEquals(command1,"");
		
		String command2 = spyTelegramService.extractCommand("/nadaprafazer worldnews;cats");
		assertEquals("/nadaprafazer",command2);
	}
	
	@Test
	public void returnValues() {
		//--> Objetos de apoio
		List<Update> updates = Arrays.asList(this.mockUpdate);		
		
		//--> Definindo comportamento
		Mockito.when(this.mockCrawlerBot.execute(Mockito.any(GetUpdates.class))).thenReturn(mockResponse);
		Mockito.when(this.mockResponse.updates()).thenReturn(updates);
		
		//--> Comportamento a ser testado
		this.mockTelegramService.startingReadTelegramChat();
		
		Mockito.verify(mockTelegramService, Mockito.times(0)).extractCommand(Mockito.anyString());
	}
}
