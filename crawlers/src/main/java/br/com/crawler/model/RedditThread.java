package br.com.crawler.model;

import java.util.List;

public class RedditThread {
	
	private int upvotes;
	private int downvotes;
	private String subbredit;
	private String threadTitle;
	private String threadLink;
	private List<String> threadComments;
	public int getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
	public int getDownvotes() {
		return downvotes;
	}
	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}
	public String getSubbredit() {
		return subbredit;
	}
	public void setSubbredit(String subbredit) {
		this.subbredit = subbredit;
	}
	public String getThreadTitle() {
		return threadTitle;
	}
	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}
	public String getThreadLink() {
		return threadLink;
	}
	public void setThreadLink(String threadLink) {
		this.threadLink = threadLink;
	}
	public List<String> getThreadComments() {
		return threadComments;
	}
	public void setThreadComments(List<String> threadComments) {
		this.threadComments = threadComments;
	}
	
	
	
}
