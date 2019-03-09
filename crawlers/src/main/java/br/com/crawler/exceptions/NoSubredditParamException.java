package br.com.crawler.exceptions;

public class NoSubredditParamException extends RuntimeException {
	
	private static final long serialVersionUID = 7943956737218275813L;

	public NoSubredditParamException(String message) {
		super(message);
	}
		
}
