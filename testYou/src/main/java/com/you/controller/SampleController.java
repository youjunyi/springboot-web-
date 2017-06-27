package com.you.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.you.entity.User;
import com.you.repository.entity.SsTask;
import com.you.service.SsTaskService;
import com.you.service.SsUserService;
@Controller
public class SampleController {

	@Autowired
	private SsTaskService ssTaskService;
	@Autowired
	private SsUserService ssUserService;
	@RequestMapping("/test")
	public String home3(){
		List<User> userlist = ssUserService.findAll();		
		System.out.println();
		System.out.println();
		return null;
	}
	
	 @RequestMapping("/")
	  String home() throws ParseException, IOException {
         ObjectMapper mapper = new ObjectMapper();
		String access_token =   roidid();
		String sc = upwenjian("",access_token);
		String json  = gshsj(sc);
		String mid = cjtuxx(json,access_token);
		fstw(mid,access_token);
		/*
		//发送文本 
         Map<String,Object> map1 = new HashMap<String, Object>();
         map1.put("is_to_all", true);
         Map<String,Object> map2 = new HashMap<String, Object>();
         map2.put("content", "您好！这是一个测试消息！！！！");
         Map<String,Object> map3 = new HashMap<String, Object>();
         map3.put("filter", map1);
         map3.put("text", map2);
         map3.put("msgtype", "text");
         String cont = mapper.writeValueAsString(map3);
         String url4 = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+access_token+"";
         HttpPost httppost= new HttpPost(url4);
         StringEntity params = new StringEntity(cont,"UTF-8");  
         params.setContentType("application/json");
         httppost.setEntity(params);
 		//设置编码
         CloseableHttpClient client = HttpClients.createDefault();  
 		HttpResponse response3 = client.execute(httppost);
 		//发送Post,并返回一个HttpResponse对象
                 //Header header = response.getFirstHeader("Content-Length");
 		//String Length=header.getValue();
                 // 上面两行可以得到指定的Header
 		if(response3.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
 			String result=EntityUtils.toString(response3.getEntity());
 			//得到返回的字符串
 			System.out.println(result);
 			//打印输出
                        //如果是下载文件,可以用response.getEntity().getContent()返回InputStream
 		}
		 */
	        return "test/test.jsp";
	    }
	 //发送图文
	 public void fstw(String mid,String access_token) throws ClientProtocolException, IOException{
	     ObjectMapper mapper = new ObjectMapper();
		 Map<String,Object> map1 = new HashMap<String, Object>();
         map1.put("is_to_all", true);
         Map<String,Object> map2 = new HashMap<String, Object>();
         map2.put("media_id", mid);
         Map<String,Object> map3 = new HashMap<String, Object>();
         map3.put("filter", map1);
         map3.put("mpnews", map2);
         map3.put("msgtype", "mpnews");
         String cont = mapper.writeValueAsString(map3);
         String url4 = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+access_token+"";
         HttpPost httppost= new HttpPost(url4);
         StringEntity params = new StringEntity(cont,"UTF-8");  
         params.setContentType("application/json");
         httppost.setEntity(params);
 		//设置编码
         CloseableHttpClient client = HttpClients.createDefault();  
 		HttpResponse response3 = client.execute(httppost);
 		//发送Post,并返回一个HttpResponse对象
                 //Header header = response.getFirstHeader("Content-Length");
 		//String Length=header.getValue();
                 // 上面两行可以得到指定的Header
 		if(response3.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
 			String result=EntityUtils.toString(response3.getEntity());
 			//得到返回的字符串
 			System.out.println(result);
 			//打印输出
                        //如果是下载文件,可以用response.getEntity().getContent()返回InputStream
 		} 
	 }
	 //获得标识
	 public String  roidid() throws ClientProtocolException, IOException{
		 HttpClient httpClient = new DefaultHttpClient();  
		 String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx3e3426b6183226e6&secret=d4624c36b6795d1d99dcf0547af5443d";
		 HttpGet httpget = new HttpGet(uri);
		 HttpResponse httpresponse = httpClient.execute(httpget);  
         // 获取返回数据  
         HttpEntity entity = httpresponse.getEntity();  
         String body = EntityUtils.toString(entity); 
         if (entity != null) {  
             entity.consumeContent();  
         }  
         Map <String,String> map  = new HashMap<String,String >();
         ObjectMapper mapper = new ObjectMapper();
         map =  mapper.readValue(body, Map.class);
		return map.get("access_token");
		 
	 }
	 //上传缩略图（标题图）
	public String upwenjian(String url,String access_token1) throws ClientProtocolException, IOException{
		File file = new File("D:/20151222111532.png");
		if(file==null||access_token1==null){
            return null;
        }
        if(!file.exists()){
          //  logger.info("上传文件不存在,请检查!");
            return null;
        }
        String body= "";
        String access_token =   roidid();
        String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+access_token+"&type=image";
       // uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", access_token).replace("TYPE", "image");
        FileBody fileBody = new FileBody(file);
        CloseableHttpClient  httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(uploadMediaUrl);
        HttpEntity entity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532).addPart("media", fileBody).build();
        post.setEntity(entity);//设置请求参数  
        HttpResponse response = httpclient.execute(post);// 发起请求 并返回请求的响应  
        if (response.getStatusLine().getStatusCode()==200) {  
        	 HttpEntity entity1 = response.getEntity();  
        	 body = EntityUtils.toString(entity1); 
        }  
        System.out.println(body);
        Map <String,String> map  = new HashMap<String,String >();
        ObjectMapper mapper = new ObjectMapper();
        map =  mapper.readValue(body, Map.class);
		return map.get("media_id");
	} 
	//创建图文消息
	public String cjtuxx(String json,String access_token) throws IOException{
		String media_id = "";
		String url ="https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="+access_token+"";
		 CloseableHttpClient client = HttpClients.createDefault();  
	        HttpPost httpPost = new HttpPost(url);  
	        StringEntity params = new StringEntity(json,"UTF-8");  
	        httpPost.setEntity(params);  
	        CloseableHttpResponse httpResponse = null;  
	        try {  
	            httpResponse = client.execute(httpPost);  
	            HttpEntity entity = httpResponse.getEntity();  
	            String jsonString = EntityUtils.toString(entity);  
	            ObjectMapper mapper = new ObjectMapper();
	            Map fromObject = mapper.readValue(jsonString, Map.class);  
	            Object media_idObject = fromObject.get("media_id");  
	            if (media_idObject != null) {  
	                media_id = media_idObject.toString();  
	            }  
	            System.out.println("上传图文消息素材-返回值："  
	                    + jsonString);  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if(httpResponse != null){  
	                httpResponse.close();  
	            }  
	            if(client != null){  
	                client.close();  
	            }  
	        }  
	        return media_id;  	
	}
	//图文消息格式
	public String gshsj(String media_id) throws JsonProcessingException{
		HashMap mapUp = new HashMap();  
		HashMap SubMap = new HashMap();  
		List list = new ArrayList();  
		SubMap.put("thumb_media_id", media_id);  
		SubMap.put("author", "wangbo");  
		SubMap.put("title", "测试一下");  
		SubMap.put("content_source_url","http://www.csdn.com");  
		SubMap.put("content", "哈哈哈哈哈");  
		SubMap.put("digest", "就是测试一下");  
		SubMap.put("show_cover_pic", "1");  
		list.add(SubMap);  
		mapUp.put("articles", list);
		ObjectMapper mapper = new ObjectMapper();
		String json = 	mapper.writeValueAsString(mapUp);
		return json;  
	}
	

}
