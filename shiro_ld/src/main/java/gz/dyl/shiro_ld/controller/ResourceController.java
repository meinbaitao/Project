package gz.dyl.shiro_ld.controller;

import gz.dyl.shiro_ld.realm.CustomRealm;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 在git仓库中修改文件，测试ResourceController
 * 在git仓库中修改文件，测试ResourceController2
	github01;
	github02;
	github03;
	github04;
 */
@Controller
@RequestMapping(value = "resource")
public class ResourceController {
	
	@Autowired
	private CustomRealm customRealm;
	
	//file
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request){
		
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "/resource/home.jsp";
		}
		
		request.setAttribute("error", "你还没有登录！");
		
		return "/resource/login.jsp";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request){
		
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		
		if(null != exceptionClassName){
			if(UnknownAccountException.class.getName().equals(exceptionClassName)){
				request.setAttribute("error", "账号不存在！");
			}else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
				request.setAttribute("error", "密码错误！");
			}else{
				request.setAttribute("error", "认证错误！");
			}
			return "/resource/login.jsp";
		}
		
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "/resource/home.jsp";
		}
		
		request.setAttribute("error", "系统忙！");
		return "/resource/login.jsp";
	}
	
	@RequestMapping(value = "clearcache")
	public String clearCache(){
		customRealm.clearCache();
		return "/resource/success.jsp";
	}
	
	@RequestMapping(value = "tologin")
	public String toLogin(){
		return "/resource/login.jsp";
	}
	
	@RequestMapping(value = "unauthorized")
	public String unauthorized(){
		return "/resource/unauthorized.jsp";
	}
	
	@RequestMapping(value = "home")
	public String home(){
		return "/resource/home.jsp";
	}
	
}
