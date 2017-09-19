package com.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;


import com.model.Article;
import com.mysqlmodel.Mumu;
import com.service.ArticleService;
import com.util.PageBean;

import org.apache.log4j.Logger;



@Controller
@RequestMapping(value = "/article2")
public class Article2Controller {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(Article2Controller.class);

	@Autowired
	ArticleService articleService ;
	
	@RequestMapping(value = "api-get/article-list.html")
	public ModelAndView articleList() {
		ModelAndView mv = new ModelAndView();
		
	long a =	articleService.addArticle();
		
	System.out.println("hibernate 插入ID：" + a);
	
	
	//long b =	articleService.addArticleBYopen();
	
//	System.out.println(" hibernate  openssison 插入ID：" + b);
	
	
			List<Article> list = articleService.getArticleList();
	
			int i = 1;
			for (Article article : list) {
				
				System.out.println("[第"+i +"条数据 - id :" +article.getId() + "]");
				i++;
			}
		//	mv.addObject("list", list);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "api-get/article-list2.html")
	public ModelAndView articleList2() {
		ModelAndView mv = new ModelAndView();
		

	
	
	long b =	articleService.addArticleBYopen();
	
	System.out.println(" hibernate  openssison 插入ID：" + b);
	
	
			List<Article> list = articleService.getArticleList();
	
			int i = 1;
			for (Article article : list) {
				
				System.out.println("[第"+i +"条数据 - name :" +article.getName() + "]");
				i++;
			}
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "api-get/mumu-list.html")
	public ModelAndView mumuList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
	long a =	articleService.addMumu();
		
	System.out.println("hibernate 插入ID：" + a);
	
	System.out.println(request.getParameter("k"));
	System.out.println(request.getParameter("d"));

int startPage = 1 ;
	// start Page
if(request.getParameter("k") != null && !(request.getParameter("k").equals("")) ){
	try {
		startPage  = Integer.valueOf((String)request.getParameter("k")) ;
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}
}
// page size
int pageSize =10 ;
if(request.getParameter("d") != null && !(request.getParameter("d").equals("")) ){
	try {
		pageSize  = Integer.valueOf((String)request.getParameter("d")) ;
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}
}
	
	PageBean<Mumu> list = articleService.getMumuByPage(startPage, pageSize);
				
	
			int i = 1;
		
			List<Mumu> ab = list.getList() ;
			for (Mumu article : ab) {
				
				System.out.println("[第"+i +"条数据 - id :" +article.getId() + "]");
				i++;
			}
		mv.setViewName("index");
		return mv;
	}
	
}
