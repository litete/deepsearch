var errorimg = "";

var h3 = "";//新闻标题

var newsSource ="";//新闻来源
var cmts = "";//评论数
var userid = "";




//检查userid  
function ckeckUserId(){
		
		userid = localStorage.getItem("ds_userId");
		if( userid == null||userid == ""){
			//localStorage.setItem("ds_userId", "0000");
			userid = "0000";
		}
}

//请求文章列表
function refresh(userid) {
		$.ajax({
			type:'GET',
			url:'../user/userid.do?userid='+1,
			async:'false',
			
			success:function(data){
				data = JSON.parse(data);
				//console.log(JSON.parse(data));
		    
				article_list = JSON.parse(data);
				
				for (var i = 0; i < article_list.arctile.length; i++) {							//第一次加载页面，循环生成
					newSection(article_list.arctile[i]); //传入的是某一条
				}
				$('.middle_mode').click(function(){
					var a = $(this).attr("articleid");
					//console.log(a);
					//console.log(userid);
					var date = new Date();
					var seperator1 = "-";
					var seperator2 = ":";
					var year = date.getFullYear();
					var month = date.getMonth() + 1;
					var strDate = date.getDate();
					if (month >= 1 && month <= 9) {
						month = "0" + month;
					}
					if (strDate >= 0 && strDate <= 9) {
						strDate = "0" + strDate;
					}
					var currentdate = year + seperator1 + month + seperator1 + strDate
							+ " " + date.getHours() + seperator2 + date.getMinutes()
							+ seperator2 + date.getSeconds();
					//console.log(currentdate);												//当期那时间
					var datasend2 = {
							 userid: 1,
							 arctileid: a,
							 updatetime: currentdate
							};
							 
					console.log(JSON.stringify(datasend2))
					$.post("../user/uaid.do",{test:JSON.stringify(datasend2)},function(data){
						console.log("return data:"+data);
					})
					
	/*				$.ajax({
						type:'POST',
						url:'../user/uaid.do',																//发用户id、文章ID，时间的地址
						//data:JSON.stringify(datasend2),
						data:datasend2,
						//contentType: 'application/x-www-form-urlencoded',
						//contentType : 'application/json',
						//dataType:'json',
						success:function(data,status){
							if(status=='success'){
								alert('请求成功');
							}
						}
					})*/
				}			
				) 
			
			}
		})	
}
//
$(document).ready(function(){
	   
	
	ckeckUserId();										//检查设置id	

	refresh(userid);	 //根据id请求到文章列表
	
});
//文章添加到最前面
function newBeforeSection (oneArticle) {

	if(oneArticle.thumbnail_url == null||oneArticle.thumbnail_url.length<3){
		//console.log("1图的样式");
		var articleId = oneArticle.arctileid;
		//console.log(articleId);
		var a_href = oneArticle.url; //文章的跳转链接												//暂时写死
		var h3  = oneArticle.title;
		var img1 = ""; //图片的资源地址
		if(oneArticle.thumbnail_url!=null){
			img1 = oneArticle.thumbnail_url;
		}
		//var section = $(".list_content").prepend($("<section data-is-video = 'true' style='height:100px' class='middle_mode has_action' articleid='"+ articleId +"'/>"));
		var section = $("<section data-is-video = 'true' style='height:100px' class='middle_mode has_action' articleid='"+ articleId +"'/>").prependTo($(".list_content"));


		var topBorder = $("<div class='one-px-border'/>").appendTo(section);
		//var a_href = "http://m.toutiao.com/item/"+articleId;
		var main = $("<a class='article_link clearfix' data-action-label='click_headline' />").appendTo(section);//a
		var desc = $("<div class='desc' style='vertical-align:middle;'/>").appendTo(main);
		var list_img_holder = $("<div class='list_img_holder' style='overflow:visible' style='vertical-align: middle; background: none;'/>").appendTo(main);
		var h3_text = $("<h3 class='dotdot line3 image-margin-right' />").appendTo(desc);
		h3_text.text(h3);
		var item_info = $("<div class='item_info' style='margin-top: 6px;'/>").appendTo(desc);
		var source = $("<span class='src space' />").appendTo(item_info);
		//source.text(newsSource);
		var cmt_space = $("<span class='cmt space' />").appendTo(item_info);
		cmt_space.text(" "+cmts);
		//var img1 = './search/' + imgSrc[0];
		var img = $("<img src='./search/defaultimg.png' style='opacity: 1;'/>").appendTo(list_img_holder);
		//var video_btn = $("<span class='video-btn'/>").appendTo(list_img_holder);
	}else{
		//console.log("3图的样式");
		var articleId = oneArticle.arctile_id;
		var a_href = oneArticle.url; //文章的跳转链接
		var h3  = oneArticle.title;


		var section = $("<section data-is-video = 'False' class='has_action' data_item_id='" + articleId+ "/>").appendTo($(".list_content"));
		var topBorder = $("<div class='one-px-border'/>").appendTo(section);
		//var a_href = "http://m.toutiao.com/item/"+articleId;
		var a = $("<a href='"+a_href+"' class='article_link clearfix' data-action-label='click_headline' />").appendTo(section);
		var h3_text = $("<h3 class='dotdot line3'/>").appendTo(a);
		h3_text.text(h3);
		var list_image = $("<div class='list_image'/>").appendTo(a);
		var ul = $("<ul class='clearfix'/>").appendTo(list_image);
		var li1 = $("<li class='list_img_holder' style='background:none'/>").appendTo(ul);
		var img1 = $("<img src='./search/"+oneArticle.thumbnail_url[0]+"style='opacity: 1;' />").appendTo(li1);
		var li2 = $("<li class='list_img_holder' style='background:none'/>").appendTo(ul);
		var img2 = $("<img src='./search/"+oneArticle.thumbnail_url[1]+"style='opacity: 1;' />").appendTo(li2);
		var li3 = $("<li class='list_img_holder' style='background:none'/>").appendTo(ul);
		var img3 = $("<img src='./search/"+oneArticle.thumbnail_url[2]+"style='opacity: 1;' />").appendTo(li3);

		var item_info = $("<div class='item_info'/>").appendTo(a);
		var source = $("<span class='src space' />").appendTo(item_info);
		//source.text(newsSource);
		var cmt_space = $("<span class='cmt_space' />").appendTo(item_info)
		cmt_space.text(" "+cmts);
	}
}

	//创建一个文章节点
	function newSection (oneArticle) {	
		
		if(oneArticle.thumbnail_url == null||oneArticle.thumbnail_url.length<3){
			//console.log("1图的样式");
			var articleId = oneArticle.arctileid;
			//console.log(articleId);
			var a_href = oneArticle.url; //文章的跳转链接												//暂时写死
			var h3  = oneArticle.title;
			var img1 = ""; //图片的资源地址
			if(oneArticle.thumbnail_url!=null){
				img1 = oneArticle.thumbnail_url;
			}

			var section = $("<section data-is-video = 'true' style='height:100px' class='middle_mode has_action' articleid='"+ articleId +"'/>").appendTo($(".list_content"));
		
			
			var topBorder = $("<div class='one-px-border'/>").appendTo(section);
			//var a_href = "http://m.toutiao.com/item/"+articleId;		
			var main = $("<a class='article_link clearfix' data-action-label='click_headline' />").appendTo(section);//a
			var desc = $("<div class='desc' style='vertical-align:middle;'/>").appendTo(main);
			var list_img_holder = $("<div class='list_img_holder' style='overflow:visible' style='vertical-align: middle; background: none;'/>").appendTo(main);
			var h3_text = $("<h3 class='dotdot line3 image-margin-right' />").appendTo(desc);
			h3_text.text(h3);
			var item_info = $("<div class='item_info' style='margin-top: 6px;'/>").appendTo(desc);
			var source = $("<span class='src space' />").appendTo(item_info);
			//source.text(newsSource);
			var cmt_space = $("<span class='cmt space' />").appendTo(item_info);
			cmt_space.text(" "+cmts);
			//var img1 = './search/' + imgSrc[0];
			var img = $("<img src='./search/defaultimg.png' style='opacity: 1;'/>").appendTo(list_img_holder);
			//var video_btn = $("<span class='video-btn'/>").appendTo(list_img_holder);
		}else{
			//console.log("3图的样式");
			var articleId = oneArticle.arctile_id;
			var a_href = oneArticle.url; //文章的跳转链接
			var h3  = oneArticle.title;


			var section = $("<section data-is-video = 'False' class='has_action' data_item_id='" + articleId+ "/>").appendTo($(".list_content"));
			var topBorder = $("<div class='one-px-border'/>").appendTo(section);
			//var a_href = "http://m.toutiao.com/item/"+articleId;
			var a = $("<a href='"+a_href+"' class='article_link clearfix' data-action-label='click_headline' />").appendTo(section);
			var h3_text = $("<h3 class='dotdot line3'/>").appendTo(a);
			h3_text.text(h3);
			var list_image = $("<div class='list_image'/>").appendTo(a);
			var ul = $("<ul class='clearfix'/>").appendTo(list_image);
			var li1 = $("<li class='list_img_holder' style='background:none'/>").appendTo(ul);
			var img1 = $("<img src='./search/"+oneArticle.thumbnail_url[0]+"style='opacity: 1;' />").appendTo(li1);
			var li2 = $("<li class='list_img_holder' style='background:none'/>").appendTo(ul);
			var img2 = $("<img src='./search/"+oneArticle.thumbnail_url[1]+"style='opacity: 1;' />").appendTo(li2);
			var li3 = $("<li class='list_img_holder' style='background:none'/>").appendTo(ul);
			var img3 = $("<img src='./search/"+oneArticle.thumbnail_url[2]+"style='opacity: 1;' />").appendTo(li3);

			var item_info = $("<div class='item_info'/>").appendTo(a);
			var source = $("<span class='src space' />").appendTo(item_info);
			//source.text(newsSource);
			var cmt_space = $("<span class='cmt_space' />").appendTo(item_info)
			cmt_space.text(" "+cmts);
		} 
	}

//滚动条滚动高度
function getDocumentTop() {
	var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
	if (document.body) {
		bodyScrollTop = document.body.scrollTop;
	}
	if (document.documentElement) {
		documentScrollTop = document.documentElement.scrollTop;
	}
	scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
	return scrollTop;
}

//可视窗口高度
function getWindowHeight() {
	var windowHeight = 0;
	if (document.compatMode == "CSS1Compat") {
		windowHeight = document.documentElement.clientHeight;
	} else {
		windowHeight = document.body.clientHeight;
	}
	return windowHeight;
}

//文档高度
function getScrollHeight() {
	var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
	if (document.body) {
		bodyScrollHeight = document.body.scrollHeight;
	}
	if (document.documentElement) {
		documentScrollHeight = document.documentElement.scrollHeight;
	}
	scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
	return scrollHeight;
}


	//刷新按钮旋转
	// var a = 0;
	// var zhuan = function() {
	// 	var degree = 'rotate('+a+'deg)';
	// 	$("#top_refresh").css("transform",degree);
	// 	a = a + 10;
	// 	console.log(a);
	// 	if(a==360){
	// 		window.clearInterval(turn);
	// 	 	a=0;
	// 	}
	// }
	// var turn = window.setInterval(zhuan(),50);
	
	// var turn = function(){	
	// 	var degree = 'rotate('+a+'deg)';
	// 	setInterval()
	// 	$("#top_refresh").css("transform",degree);
	// 	a = a+10;	//一次转10度
	// 	console.log(a);
	// 	if(a==360){
		 	
	// 	 	a=0;
	// 	}

	// }
window.onscroll = function () {
	//console.log(getDocumentTop());
	//console.log(getWindowHeight());
	//console.log(getScrollHeight());
	//监听事件内容
	if(getScrollHeight() == getWindowHeight() + getDocumentTop()){
		//console.log('滚动到底部')
		//当滚动条到底时,这里是触发内容
		//异步请求数据,局部刷新dom
		refresh(1);
		// turn();																			//旋转
		//refresh(userid);																//请求数据
		//删除原数据
		// for (var i = 0; i < article_list.arctile.length; i++) {							//循环生成
		// 	newSection(article_list.arctile[i]); //传入的是某一条
		// }
	}
}
	
	
	
	
	
$("#refresh").click(function() {//点击刷新按钮：
if (document.body) {
		document.body.scrollTop = 0;
	}
	if (document.documentElement) {
		document.documentElement.scrollTop = 0;
	}

	$.ajax({
		type:'GET',
		//url:'http://27.148.153.187:3306/deepsearch/user/userid.do?userid=' + 1,
		//url:'http://localhost:8080/deepsearch/user/userid.do?userid=' + 1,
		url:'../user/userid.do?userid='+1,
		async:'false',

		success:function(data){
			data = JSON.parse(data);
			

			article_list = JSON.parse(data);
			//console.log(article_list);
			for (var i = 0; i < article_list.arctile.length; i++) {							//第一次加载页面，循环生成
				newBeforeSection(article_list.arctile[i]); //传入的是某一条
			}
			$('.middle_mode').click(function(){
					var a = $(this).attr("articleid");
					//console.log(a);
					//console.log(userid);
					var date = new Date();
					var seperator1 = "-";
					var seperator2 = ":";
					var year = date.getFullYear();
					var month = date.getMonth() + 1;
					var strDate = date.getDate();
					if (month >= 1 && month <= 9) {
						month = "0" + month;
					}
					if (strDate >= 0 && strDate <= 9) {
						strDate = "0" + strDate;
					}
					var currentdate = year + seperator1 + month + seperator1 + strDate
						+ " " + date.getHours() + seperator2 + date.getMinutes()
						+ seperator2 + date.getSeconds();
					//console.log(currentdate);												//当期那时间
					var datasend2 = {
						userid: 1,
						arctileid: a,
						updatetime: currentdate
					};


					$.ajax({
						type:'POST',
						//url:'http://27.148.153.187:3306/deepsearch/user/uaid.do',																//发用户id、文章ID，时间的地址
						url:'../user/uaid.do',
						data:JSON.stringify(datasend2),
						contentType: 'application/json',
						dataType:'json',
						success:function(data,status){
							if(status=='success'){
								alert('请求成功');
							}
						}
					})
				}
			)

		}
	})

		// turn();																			//旋转
		//refresh(userid);																//请求数据
		//删除原数据
		// for (var i = 0; i < article_list.arctile.length; i++) {							//循环生成
		// 	newSection(article_list.arctile[i]); //传入的是某一条
		// }
});