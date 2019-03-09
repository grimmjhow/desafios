package br.com.crawler.model;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupCrawlerImpl implements CrawlerProvider {

	private static final String THREAD_CLASS_ID = ".thing";
	private final String REDDIT_HOST = "https://old.reddit.com/r/";
	private final String CRAWLER_BOT_USER_AGENT = "Crawlerbot/1.0";
	
	@Override
	public List<RedditData> getThreads(String subreddit){
		
		try {
			Document document = this.getDocumentPage(subreddit);
			
			Elements threads = document.select(THREAD_CLASS_ID);
			
			return
			threads.stream()
				   .filter(removeAnnouncementsAndInvalidUpVotes())
				   .map(element -> new RedditData(element))
				   .filter(r -> r.isThreadBombando())
				   .sorted(Comparator.comparingLong(RedditData::getUpvotes).reversed())
				   .collect(Collectors.toList());
			
		} catch (IOException ex) {
			//When something goes wrong trying get the page elements
			ex.printStackTrace();
			
			return Collections.emptyList();
		}
	}
	
	/**
	 * <p><b>Announcements:</b> Sometimes the second or third first threads without ranking.
	 * <p><b>InvalidUpVotes:</b> When there's no number to indicate the upvotes. 
	 */
	private Predicate<? super Element> removeAnnouncementsAndInvalidUpVotes() {
		return e -> StringUtils.isNumeric(e.attr("data-score")) &&
				    !e.attr("data-rank").isEmpty();
	}

	protected Document getDocumentPage(String subreddit) throws IOException {
		
		return Jsoup.connect(REDDIT_HOST.concat(subreddit))
					.userAgent(CRAWLER_BOT_USER_AGENT)
					.timeout(0)
					.get(); 
	}
}
