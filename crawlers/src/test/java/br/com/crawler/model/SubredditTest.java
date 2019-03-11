package br.com.crawler.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SubredditTest {
	
	private Subreddit spySubreddit = new Subreddit("Teste", new ArrayList<RedditData>());
	
	@Test
	public void testToStringFormatWhenThereAreNoThreads() {
		
		String expected = "Nenhuma Thread bombando para o subreddit: Teste";
		
		assertEquals(expected, this.spySubreddit.toString());
	}
}
