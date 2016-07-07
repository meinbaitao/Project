package gz.dyl.shiro_ld.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "item")   
public class ItemController {

	@RequiresPermissions(value = "item:insert")
	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request){
		
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.isAuthenticated());
		
		Session session = subject.getSession();
		HttpSession httpSession = request.getSession();
		
		System.out.println("shiro会话" + session);
		System.out.println("servlet会话" + httpSession);
		
		System.out.println("shiro会话ID" + session.getId());
		System.out.println("servlet会话ID" + httpSession.getId());
		
		session.setAttribute("shiro_session", "shiro_session");
		httpSession.setAttribute("servlet_session", "servlet_session");
		
		//session.stop();
		
		return "/item/insert.jsp";
	}
	
	@RequiresPermissions(value = "item:update")
	@RequestMapping(value = "update")
	public String update(){
		return "/item/update.jsp";
	}
	
	@RequiresPermissions(value = "item:delete")
	@RequestMapping(value = "delete")
	public String delete(){
		return "/item/delete.jsp";
	}
	
	@RequiresPermissions(value = "item:query")
	@RequestMapping(value = "query")
	public String query(){
		return "/item/query.jsp";
	}
	
}
