package br.com.crawler.telegram.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;

import br.com.crawler.exceptions.NoSubredditParamException;
import br.com.crawler.model.Subreddit;
import br.com.crawler.services.RedditCrawlerService;

public class NadaPraFazerTelegramCommand implements TelegramCommand {
	
	private final RedditCrawlerService redditService;
	private final TelegramBot mrCrawler;
	
	public NadaPraFazerTelegramCommand(RedditCrawlerService redditService,TelegramBot mrCrawler) {
		this.redditService = redditService;
		this.mrCrawler = mrCrawler;
	}
	
	@Override
	public void execute(Message message) {
		
		try {
			
			this.mrCrawler.execute(new SendChatAction(message.chat().id(), ChatAction.typing));
			
			String params = message.text().replace("/nadaprafazer", "").trim();
		
			List<String> keywords = params.isEmpty() ? Collections.emptyList() : Arrays.asList(params.split(";")).stream().map(s -> s.trim()).collect(Collectors.toList());
			
			List<Subreddit> subreddits = this.redditService.getSubredditsData(keywords);
			
			subreddits.forEach(s -> mrCrawler.execute(new SendMessage(message.chat().id(),s.toString())));
		
		} catch (NoSubredditParamException e) {
			System.out.println(e.getMessage());
			mrCrawler.execute(new SendMessage(message.chat().id(),e.getMessage()));
		}
	}
	
}
