package br.com.crawler.configuration;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TelegramCommandFactoryTest {

	
	@Spy
	private TelegramCommandFactory factory;
	
	@Test
	public void getTelegramDefaultInstanceWhereNoCommandFound() {
		
		assertSame(factory.getCommand("knowcommand"),null);
	}
}
