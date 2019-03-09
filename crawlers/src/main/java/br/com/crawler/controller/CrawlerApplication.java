package br.com.crawler.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.crawler.business.MrCrawlerTelegramThread;
import br.com.crawler.model.Subreddit;
import br.com.crawler.services.RedditCrawlerService;

@SpringBootApplication
@ComponentScan(basePackages= {"br.com.crawler.*"})
public class CrawlerApplication implements ApplicationRunner{

	@Autowired
	private RedditCrawlerService redditService;
	
	@Autowired
	private MrCrawlerTelegramThread thread;
	
	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		thread.start();
		
		List<String> nonOptionArgs = new ArrayList<>(args.getNonOptionArgs());
		
		if(!nonOptionArgs.isEmpty()) {
			
			boolean telegram = nonOptionArgs.stream().anyMatch(p -> p.equals("/NadaPraFazer"));
			nonOptionArgs.remove("/NadaPraFazer");

			List<String> subreddits =
			nonOptionArgs.stream()
						 .map(e -> e.split(":"))
						 .map(array -> Arrays.asList(array))
						 .flatMap(List::stream)
						 .collect(Collectors.toList());
			
			List<Subreddit> subredditsData = redditService.getSubredditsData(subreddits);
			
			subredditsData.stream().forEach(s -> System.out.println(s));
		}
	}
}
