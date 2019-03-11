package br.com.crawler.model;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

public class JSoupCrawlerImplTest {
	
	private JSoupCrawlerImpl jsoup = new JSoupCrawlerImpl();
	
	@Test
	public void getThreadsBySubredditNotEmpty() {
		
		List<RedditData> threads = jsoup.getThreads("AskReddit");
		
		assertFalse(threads.isEmpty());
	}
}
