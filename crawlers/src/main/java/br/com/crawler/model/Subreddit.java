package br.com.crawler.model;

import java.util.ArrayList;
import java.util.List;

public class Subreddit {
	
	private String title;
	private List<RedditData> threads = new ArrayList<>();
	
	public Subreddit(String title, List<RedditData> threads) {
		this.title = title;
		this.threads = new ArrayList<>(threads);
	}
	
	public List<RedditData> getThreads() {
		return threads;
	}

	public void setThreads(List<RedditData> threads) {
		this.threads = threads;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((threads == null) ? 0 : threads.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Subreddit))
			return false;
		Subreddit other = (Subreddit) obj;
		if (threads == null) {
			if (other.threads != null)
				return false;
		} else if (!threads.equals(other.threads))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(threads.isEmpty())
			return "Nenhuma Thread bombando para o subreddit: "+this.title;
		
		StringBuilder toString = new StringBuilder("Subreddit: ")
										   .append(title)
										   .append(System.lineSeparator());
		
		for(RedditData reddit : this.threads)
			toString.append(reddit)
					.append(System.lineSeparator())
					.append("------------------------------------------")
					.append(System.lineSeparator());
		
		return
		toString.append("====================").append(System.lineSeparator()).toString();
	}
}
