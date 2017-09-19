package com.timetask;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;


import com.model.Article;
import com.service.ArticleService;
import com.util.ApplicationContextUtil;


public class TimeTaska   extends  HttpServlet  implements  ServletContextListener{

	
	
	private static final long serialVersionUID = 1L;
	
	
	
	private static ArticleService articleService = ApplicationContextUtil.getBean("articleServiceImpl");
	static int count = 0;
	
	// 用于保存文章点击量
	protected static  Map<String,Object> articleCount = new HashMap<String, Object>();


	    public static void showTimer() {

	        TimerTask task = new TimerTask() {

	            @Override

	            public void run() {

	                ++count;

	          	  //更新数据
	    			updata();
	                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1��

	            }

	        };

	        //设置任务时间

	        Calendar calendar = Calendar.getInstance();

	        int year = calendar.get(Calendar.YEAR);

	        int month = calendar.get(Calendar.MONTH);

	        int day = calendar.get(Calendar.DAY_OF_MONTH);//

	        //每天21:09:00 执行

	        calendar.set(year, month, day, 16, 20, 00);

	        Date date = calendar.getTime();

	        Timer timer = new Timer();

	        System.out.println(date);

	        

	       // int period = 2 * 1000;

	        //每天 date 每隔 period 执行一次

	       // timer.schedule(task, date, period);

	        //每天date 执行 task 一次

	        timer.schedule(task, date);

	    }

private static void testTxt(){
	
	File file = new File("f:\\"+System.currentTimeMillis()+".txt");
	
	try {
		file.createNewFile();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
//
		public void contextDestroyed(ServletContextEvent arg0) {
			// TODO Auto-generated method stub
			
			System.out.println("服务器关闭了...");
			
		  //更新数据
			updata();
			testTxt();
		}




		public void contextInitialized(ServletContextEvent arg0) {
			// TODO Auto-generated method stub
			
			System.out.println("服务器启动,开始执行定时任务");
			
		//	testTxt();
			showTimer();
			
			// 获取数据库中文章的点击量。
		   List<Article> list =	articleService.getArticleList();
		  
		   // 存放在 map 中
		  articleCount.put("count", list.get(0).getCount());

		}
	
	
		
		private static void updata(){
			
			// 将数据存入数据库中
			//对比数据是否错误
			   List<Article> list =	articleService.getArticleList();

			  long newData = -1;
			  if(articleCount.get("count") != null){
				 
				  newData =  (Long)articleCount.get("count") ;
			  }
			 if(newData > list.get(0).getCount()){
				 
					articleService.modifyArticleCount(newData);

			 }
			
		}
	public static void main(String[] args) {
		

		testTxt();
	}
}
