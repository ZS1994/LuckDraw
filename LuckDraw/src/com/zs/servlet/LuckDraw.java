package com.zs.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.swing.internal.plaf.basic.resources.basic;
import com.zs.dao.BaseDaoOfJDBC;
import com.zs.model.Users;

public class LuckDraw extends HttpServlet{

	BaseDaoOfJDBC db=new BaseDaoOfJDBC();
	Users u=null;
	List<Users> close=null;//�ر��б�
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String cz=req.getParameter("cz");
		if (cz!=null && cz.equals("clear")) {
			clear();
			getServletContext().removeAttribute("close");
			getServletContext().removeAttribute("close1");
			getServletContext().removeAttribute("close2");
			getServletContext().removeAttribute("close3");
			getServletContext().removeAttribute("close4");
			req.getRequestDispatcher("goto").forward(req, resp);
			return;
		}else if(cz!=null && cz.equals("query")){
			resp.sendRedirect("index.jsp");
			return;
		}else {
			boolean b = isRepeatSubmit(req);//判断用户是否是重复提交
			if(b==true){
			    System.out.println("请不要重复提交");
			    resp.sendRedirect("index.jsp");
			    return;
			}
			req.getSession().removeAttribute("token");//移除session中的token
			System.out.println("处理用户提交请求！！");
			try {
				close=new ArrayList<Users>();
				List<Map> lic=db.query("select * from close");
				for (int i = 0; i < lic.size(); i++) {
					close.add(new Users((Integer)lic.get(i).get("id_num"), (String)lic.get(i).get("name")));
				}
				List<Map> list=db.query("select * from users");
				int c=circle();
				if (c!=0) {
					db.aud("insert into times(time) value('"+new Date().toLocaleString()+"')");
				}else {
					u=null;
				}
				List<Users> li=new ArrayList<Users>();
				for (int j = 0; j < c; j++) {
					do {
						int i=(int)(Math.random()*list.size());
						u=new Users((Integer)list.get(i).get("id"), (String)list.get(i).get("name"));
					} while (isHave(u) && close.size()<list.size());
					if (add(u)) {
						li.add(u);
					}
				}
				getServletContext().setAttribute("close", li);
				if (close.size()==list.size()) {
					u=null;
				}
				//�����������ʾ����
				List<Map> list1 =db.query("select * from close limit 15");
				List<Users> close1 = new ArrayList<Users>();
				for (int i = 0; i < list1.size(); i++) {
					close1.add(new Users((Integer)list1.get(i).get("id_num"), (String)list1.get(i).get("name")));
				}
				getServletContext().setAttribute("close1", close1);
				
				List<Map> list2 =db.query("select * from close limit 15,10");
				List<Users> close2 = new ArrayList<Users>();
				for (int i = 0; i < list2.size(); i++) {
					close2.add(new Users((Integer)list2.get(i).get("id_num"), (String)list2.get(i).get("name")));
				}
				getServletContext().setAttribute("close2", close2);
				
				List<Map> list3 =db.query("select * from close limit 25,4");
				List<Users> close3 = new ArrayList<Users>();
				for (int i = 0; i < list3.size(); i++) {
					close3.add(new Users((Integer)list3.get(i).get("id_num"), (String)list3.get(i).get("name")));
				}
				getServletContext().setAttribute("close3", close3);
				
				List<Map> list4 =db.query("select * from close limit 29,2");
				List<Users> close4 = new ArrayList<Users>();
				for (int i = 0; i < list4.size(); i++) {
					close4.add(new Users((Integer)list4.get(i).get("id_num"), (String)list4.get(i).get("name")));
				}
				getServletContext().setAttribute("close4", close4);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	private boolean add(Users u) {
		if (u!=null && close!=null) {
			if (isHave(u)==false) {
				try {
					close.add(u);
					db.aud("insert into close(id_num,name) value('"+u.getId()+"','"+u.getName()+"')");
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	
	private boolean isHave(Users u) {
		if (u!=null && close!=null) {
			for (int i = 0; i < close.size(); i++) {
				if (u.equals(close.get(i))) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void clear(){
		try {
			db.aud("delete from close");
			db.aud("delete from times");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int circle() {
		try {
			List<Map> times = db.query("select * from times");
			int circle=0;
			switch (times.size()+1) {
			case 1:
				circle=15;
				break;
			case 2:
				circle=10;				
				break;
			case 3:
				circle=4;
				break;
			case 4:
				circle=2;
				break;
			default:
				break;
			}
			return circle;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
     * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
     * @param request
     * @return 
     *         true 用户重复提交了表单 
     *         false 用户没有重复提交表单
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String client_token = request.getParameter("token");
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if(client_token==null){
            return true;
        }
        //取出存储在Session中的token
        String server_token = (String) request.getSession().getAttribute("token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if(server_token==null){
            return true;
        }
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if(!client_token.equals(server_token)){
            return true;
        }
        
        return false;
    }
}
