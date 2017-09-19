package com.dao;

import java.util.List;

import com.model.Article;
import com.mysqlmodel.Mumu;

/**
 *  dao
 * @author mumu
 * @date 2016-08-18
 */
public interface ArticleDao {
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
	
	
	public long addArticleBYopens();
	
	
	public List<Mumu> getMumuList();

	
	public long addMumu();


}
