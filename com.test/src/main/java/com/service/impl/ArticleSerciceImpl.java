package com.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ArticleDao;
import com.github.pagehelper.PageHelper;
import com.mybatisdao.MumuMybatisMapper;
import com.model.Article;
import com.mysqlmodel.Mumu;
import com.service.ArticleService;
import com.util.PageBean;

@Service(value="articleServiceImpl")
@Transactional
public class ArticleSerciceImpl implements ArticleService {

	@Autowired
	ArticleDao articleDao ;
	
	
    @Autowired
    private MumuMybatisMapper   mumuMybatis;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Article> getArticleList() {
		// TODO Auto-generated method stub
	
		return articleDao.getArticleList();
	}

	public long modifyArticleCount() {
		return articleDao.modifyArticleCount();
	}

	public long modifyArticleCount(long count) {
		return articleDao.modifyArticleCount(count);
	}

 @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED)
	public long addArticle() {
		
		return articleDao.addArticle();
	}

	public long addArticleBYopen() {
		return articleDao.addArticleBYopens();
	}

	public List<Mumu> getMumuList() {
		return articleDao.getMumuList();
	}

	public long addMumu() {
		return articleDao.addMumu();
	}

	public List<Mumu> getMumuListMybatis() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		//params.put("id", 1);
		return mumuMybatis.getMumuList(params);
	}

	@Override
	public PageBean<Mumu> getMumuByPage(int startPage,int pageSize) {
		
		PageHelper.startPage(startPage, pageSize);
		HashMap<String, Object> params = new HashMap<String, Object>();

	    List<Mumu> mumu = mumuMybatis.getMumuList(params);
	    
        if (null != mumu && mumu.size() > 0) {
    		return new PageBean<Mumu>(mumu);
        }else{
    		return null;
        }
	}


	
	

}
