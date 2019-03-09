package br.com.crawler.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.GetUpdates;

import br.com.crawler.business.MrCrawlerTelegramThread;
import br.com.crawler.business.NadaPraFazerTelegramCommand;
import br.com.crawler.business.StartTelegraCommand;
import br.com.crawler.business.TelegramCommand;
import br.com.crawler.model.CrawlerProvider;
import br.com.crawler.model.JSoupCrawlerImpl;
import br.com.crawler.services.RedditCrawlerService;

@Configuration
public class DependencyInjectionConfiguration {

	@Bean
	public CrawlerProvider jsoupCrawlerProvider() {
		return new JSoupCrawlerImpl();
	}
	
	@Bean
	public TelegramBot mrCrawler() {
		return new TelegramBot("680282926:AAF43kzFkWlpPiJfdXbB1FW1uC73CyMydAE");
	}
	
	@Bean
	public GetUpdates updatesLimit100() {
		return new GetUpdates().limit(100);
	}
	
	/*@Bean
	public TelegramService telegramService(TelegramBot mrCrawler,RedditCrawlerService redditService) {
		return new TelegramService(mrCrawler,redditService);
	}*/
	
	@Bean
	public Map<String, TelegramCommand> commandTelegram(TelegramBot mrCrawler,RedditCrawlerService redditService){

		Map<String, TelegramCommand> command = new HashMap<>();
		command.put("/nadaprafazer", new NadaPraFazerTelegramCommand(redditService,mrCrawler));
		command.put("/start", new StartTelegraCommand(mrCrawler));
		
		return command;
	}
	
	@Bean
	public RedditCrawlerService redditService() {
		return new RedditCrawlerService();
	}
	
	@Bean
	public Thread mrCrawlerThread() {
		return new MrCrawlerTelegramThread();
	}
}
