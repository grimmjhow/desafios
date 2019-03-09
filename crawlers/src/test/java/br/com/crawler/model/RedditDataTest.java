package br.com.crawler.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RedditDataTest {

	
	private Element element = Mockito.mock(Element.class,Mockito.RETURNS_DEEP_STUBS);
	
	@Before
	public void setup() {
		when(element.attr("data-score")).thenReturn("5000");
		when(element.attr("data-permalink")).thenReturn("/comments");
		when(element.attr("data-subreddit")).thenReturn("cats");
		when(element.attr("data-url")).thenReturn("wwww.subreddit.com/url");
		when(element.select("p .title").first().text()).thenReturn("Thread Title");
	}
	
	@Test
	public void testToStringFormat() {
		RedditData reddit = new RedditData(element);
		
		String expected = "RedditData: \n" + 
				"upvotes --> 5000\n" + 
				"threadTitle --> Thread Title\n" + 
				"subreddit --> cats\n" + 
				"commentsLink --> https://old.reddit.com/comments\n" + 
				"threadLink --> https://old.reddit.com/comments";
		
		assertEquals(expected, reddit.toString());
	}
	
}
