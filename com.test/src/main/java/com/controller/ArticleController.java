package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.model.Article;
import com.service.ArticleService;
import com.timetask.TimeTaska;

import org.apache.log4j.Logger;



@Controller
@RequestMapping(value = "/article")
public class ArticleController extends TimeTaska{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(ArticleController.class);

	@Autowired
	ArticleService articleService ;
	
	@RequestMapping(value = "api-get/article-list.html")
	public ModelAndView articleList() {
		ModelAndView mv = new ModelAndView();
		

		if(articleCount.get("count") == null){
			// 服务器重启之后 map 中没有数据，从数据库中拉取
			List<Article> list = articleService.getArticleList();
			// 点击量 +1 
			articleCount.put("count", list.get(0).getCount()+1);
			
			System.out.println("当前点击量：" + articleCount.get("count"));

		}else{
			
			long count = (Long) articleCount.get("count") ;
			System.out.println("当前点击量：" + count);

			// 点击量 +1
			articleCount.put("count", count + 1 );

			System.out.println("增加后的点击量：" + articleCount.get("count"));

		}
	
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "api-get/showCount.html")
	@ResponseBody
	public String showCount(HttpServletResponse response) throws Exception {
		
		long newCount = -1 ;
		
		if(articleCount.get("count") == null){
			// 服务器重启之后 map 中没有数据，从数据库中拉取
			List<Article> list = articleService.getArticleList();

			newCount = list.get(0).getCount();
		}else{
			
			newCount = (Long) articleCount.get("count") ;

		}
		
		JSONObject json = new JSONObject();
		json.put("count", newCount);
		json.put("result", "success");

		
		return ajaxText(response,json.toString());
		
	}

	
	@RequestMapping(value = "api-get/toCount.html")
	public ModelAndView toCount(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
	
		mv.addObject(request.getParameter("count"));
		mv.setViewName("count");
		return mv ;
	}
	
	// AJAX输出，返回null
	public String ajax(HttpServletResponse response ,String content, String type) {
		
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// AJAX输出文本，返回null
	public String ajaxText(HttpServletResponse response ,String text) {
		return ajax(response,text, "text/plain");
	}
}
