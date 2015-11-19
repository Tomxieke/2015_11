package CatServelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebTestServelet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");  //防止中文乱码
		String name = req.getParameter("username");
		//name = new String(name.getBytes("ISO-8859-1"),"UTF-8");   //解决乱码问题方式一
		System.out.println(name);
		String password = req.getParameter("password");
		resp.getWriter().print("password:" + password +"\n" +"username:"+name);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//设定请求编码
		resp.setContentType("text/html;charset=utf-8");  //设定响应编码 防止中文乱码
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(name);
		resp.getWriter().print("password:" + password +"\n" +"username:"+name);
	}
}
