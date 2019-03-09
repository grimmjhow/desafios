package br.com.crawler.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crawler.exceptions.NoSubredditParamException;
import br.com.crawler.model.CrawlerProvider;
import br.com.crawler.model.Subreddit;

@Service
public class RedditCrawlerService {

	@Autowired
	private CrawlerProvider crawlerProvider;
	
	public List<Subreddit> getSubredditsData(List<String> subrredits) throws NoSubredditParamException{
		
		if(subrredits.isEmpty())
			throw new NoSubredditParamException("Did follow my instructions? I see no subreddits...");
		
		return
		subrredits.stream()
				  .map(subreddit -> new Subreddit(subreddit,crawlerProvider.getThreads(subreddit)))
				  .collect(Collectors.toList());
	}
	
}
