package com.mrpan.wechat.bean.resp;


import com.mrpan.wechat.bean.resp.bean.Articles;

/**
 * 回复图文消息
 */
public class NewsMessage extends BaseMessage {
	private int ArticleCount; // 图文消息个数，限制为10条以内
	private Articles articles;

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

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

}
