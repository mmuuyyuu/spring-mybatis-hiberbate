package com.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ArticleDao;
import com.dao.BaseDao;
import com.model.Article;
import com.mysqlmodel.Mumu;

@Repository
public class ArticleDaoImpl extends BaseDao implements ArticleDao{

	@SuppressWarnings("unchecked")
	public List<Article> getArticleList() {
		
		//return getSession().createQuery(" from Article").list();
		List<Article> list = null ;
		
		try {
			//list = 	getOpenSession().createQuery(" from Article").list();
			list =  getSession().createQuery(" from Article order by id desc").list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
		return list;

	}
//点击量+1
	public long modifyArticleCount() {
	
		Transaction transaction = getOpenSession().beginTransaction();
		
		long ret =-1 ;
		try {
			ret =	getOpenSession().createSQLQuery(" update article set count = count +1 ").executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return -1;
		}
		
		transaction.commit();
		getOpenSession().flush();
		getOpenSession().close();

		return 	ret ;
	}
	public long modifyArticleCount(long count) {
		Transaction transaction = getOpenSession().beginTransaction();
		
		long ret =-1 ;
		try {
			ret =	getOpenSession().createSQLQuery(" update article set count = " + count).executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return -1;
		}
		
		transaction.commit();
		getOpenSession().flush();
		getOpenSession().close();

		return 	ret ;
	}
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Transactional
	public long addArticle() {
		

		Article a = new Article();
		a.setName(sdf.format(System.currentTimeMillis()));
		a.setCount(1L);

		long aa =-1;
		try {
			aa = (Long) getSession().save(a);
			
	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return aa;
		}
		return	 aa;
			
	}
	public long addArticleBYopens() {
		Transaction transaction = getOpenSession().beginTransaction();
		
		Article article = new Article();
		long ret =-1 ;
		try {
			article.setCount(1L);
			article.setName(sdf.format(System.currentTimeMillis()));
			
			
			ret=	(Long) getOpenSession().save(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return -1;
		}
		
		transaction.commit();
		getOpenSession().flush();
		getOpenSession().close();

		return 	ret ;
	}
	@SuppressWarnings("unchecked")
	public List<Mumu> getMumuList() {
		
		
		return super.getMySqlSession().createQuery(" from Mumu").list();
	}
	public long addMumu() {

Mumu mumu = new Mumu();
mumu.setName(sdf.format(System.currentTimeMillis()));



		return (Long) super.getMySqlSession().save(mumu);
	}




}
