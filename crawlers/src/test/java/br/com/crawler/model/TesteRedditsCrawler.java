package br.com.crawler.model;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

public class TesteRedditsCrawler {

	private RedditsCrawler reddits;
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testaCrawlerJSoup() throws IOException {
		Document document = Jsoup.connect("https://old.reddit.com/r/cats").userAgent("Crawlerbot/1.0").timeout(0).get();
		
		Elements elements = document.select(".thing");
		
		Iterator<Element> iterator = elements.iterator();
		
		while(iterator.hasNext()) {
			Element element = iterator.next();
			
			Element els = element.select("p .title").first();
			
			System.out.println(els.text());
			System.out.println(element.attr("data-rank").isEmpty());
			System.out.println("=========================================");
		}
	}
	
}
