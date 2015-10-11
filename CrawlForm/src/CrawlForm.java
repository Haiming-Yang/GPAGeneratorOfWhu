import java.io.File;

/**
 * @author SteveYang
 *
 */
public class CrawlForm {

	public static void main(String[] args) {
       
		//抓取成绩表单的URL		 
		String url = "http://210.42.121.133/servlet/Svlt_QueryStuScore?year=0&term=&learnType=&scoreFlag=0&t=Sun%20Sep%2020%202015%2000:10:02%20GMT+0800%20(CST)";
        
		//调用HttpRequest类内的get方法，将cookie值传入header    
		HttpRequest response = HttpRequest.get(url).header("Cookie","JSESSIONID=35F87C796F0DEBEE05661CAA0DA8A360.tomcat2");
	    
		String formName = "testForm.html";
        
		//保存抓取文件	    
        response.receive(new File(formName));

	        }
	}

