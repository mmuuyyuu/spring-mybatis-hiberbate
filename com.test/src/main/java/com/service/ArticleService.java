package com.service;

import java.util.List;

import com.model.Article;
import com.mysqlmodel.Mumu;
import com.util.PageBean;

public interface ArticleService {
    /**
     * 获取所有文章
     * @return list
     */
	public List<Article> getArticleList();
	
	/**
	 * 更改文章点击量
	 * @return 成功 文章id 失败 -1
	 */
	public long modifyArticleCount ();
	
	public long modifyArticleCount (long count);
	
	
	public long addArticle();
	
	
	public long addArticleBYopen();


	public List<Mumu> getMumuList();

	public long addMumu();
	
	
	public List<Mumu> getMumuListMybatis();
	
	
	public PageBean<Mumu> getMumuByPage(int startPage,int pageSize);

}
