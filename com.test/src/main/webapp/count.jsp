<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Click volume</title>
<script  src="js/jquery-1.9.1.min.js"></script>

<script type="text/javascript">

self.setInterval("queryCount()",2000)
function queryCount(){
	
	$.ajax({ url: "article/api-get/showCount.html", data: '', success: function(ret){
       
		
		var obj = jQuery.parseJSON(ret);
		
		if(obj.result ="success"){
	        $("#count").val(obj.count);
		}
      }});
	

}
</script>
</head>
<body>
     <span>文章点击量为：<input type="text" id="count" value="0"></span>
</body>
</html>