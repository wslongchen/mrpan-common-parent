package com.mrpan.wechat.bean.resp;


import com.mrpan.wechat.bean.resp.bean.Article;

import java.util.List;

/**
 * 回复图文消息
 */
public class NewsMessage extends BaseMessage {
	private int ArticleCount; // 图文消息个数，限制为10条以内
	private List<Article> Articles;

	@Override
	public String setMsgType() {
		return "news";
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		this.Articles = articles;
	}
}
