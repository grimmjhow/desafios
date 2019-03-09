package br.com.crawler.model;

import java.util.List;

public interface CrawlerProvider {
	
	public List<RedditData> getThreads(String subreddit);
	
}
