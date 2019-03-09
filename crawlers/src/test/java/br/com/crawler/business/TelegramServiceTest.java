package br.com.crawler.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.crawler.configuration.DependencyInjectionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DependencyInjectionConfiguration.class)
public class TelegramServiceTest {
	
	@SpyBean
	private TelegramService spyTelegramService;

	@Test
	public void integratedTest() {
		spyTelegramService.getUpdatesFromTelegram();
	}
		
}
