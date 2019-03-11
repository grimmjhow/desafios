package br.com.crawler.model;

import org.jsoup.nodes.Element;

public class RedditData {
	
	private static final long INDICE_BOMBANDO = 5000;
	private long upvotes;
	private String threadTitle;
	private String subreddit;
	private String commentsLink;
	private String threadLink;
	
	public RedditData(Element element) {
		this.upvotes = Long.parseLong(element.attr("data-score"));
		this.commentsLink = "https://old.reddit.com".concat(element.attr("data-permalink"));
		this.subreddit = element.attr("data-subreddit");
		this.threadLink = this.commentsLink;
		this.threadTitle = element.select("p .title").first().text();
	}
	
	/*
	 * Just for tests!
	 */
	protected RedditData(long upvotes, String threadTitle, String subreddit, String commentsLink, String threadLink) {
		super();
		this.upvotes = upvotes;
		this.threadTitle = threadTitle;
		this.subreddit = subreddit;
		this.commentsLink = commentsLink;
		this.threadLink = threadLink;
	}



	public long getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(long upvotes) {
		this.upvotes = upvotes;
	}
	
	public boolean isThreadBombando() {
		return this.upvotes >= INDICE_BOMBANDO;
	}
	
	public String getThreadTitle() {
		return threadTitle;
	}
	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}
	public String getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	public String getCommentsLink() {
		return commentsLink;
	}
	public void setCommentsLink(String commentsLink) {
		this.commentsLink = commentsLink;
	}
	public String getThreadLink() {
		return threadLink;
	}
	public void setThreadLink(String threadLink) {
		this.threadLink = threadLink;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentsLink == null) ? 0 : commentsLink.hashCode());
		result = prime * result + ((subreddit == null) ? 0 : subreddit.hashCode());
		result = prime * result + ((threadLink == null) ? 0 : threadLink.hashCode());
		result = prime * result + ((threadTitle == null) ? 0 : threadTitle.hashCode());
		result = prime * result + (int) (upvotes ^ (upvotes >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RedditData))
			return false;
		RedditData other = (RedditData) obj;
		if (commentsLink == null) {
			if (other.commentsLink != null)
				return false;
		} else if (!commentsLink.equals(other.commentsLink))
			return false;
		if (subreddit == null) {
			if (other.subreddit != null)
				return false;
		} else if (!subreddit.equals(other.subreddit))
			return false;
		if (threadLink == null) {
			if (other.threadLink != null)
				return false;
		} else if (!threadLink.equals(other.threadLink))
			return false;
		if (threadTitle == null) {
			if (other.threadTitle != null)
				return false;
		} else if (!threadTitle.equals(other.threadTitle))
			return false;
		if (upvotes != other.upvotes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return 
		new StringBuilder("RedditData: ").append(System.lineSeparator())
					.append("upvotes --> ").append(upvotes).append(System.lineSeparator())
					.append("threadTitle --> ").append(threadTitle).append(System.lineSeparator())
					.append("subreddit --> ").append(subreddit).append(System.lineSeparator())
					.append("commentsLink --> ").append(commentsLink).append(System.lineSeparator())
					.append("threadLink --> ").append(threadLink).toString();
	}
	
}
