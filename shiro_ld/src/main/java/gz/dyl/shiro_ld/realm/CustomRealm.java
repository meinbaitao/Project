package gz.dyl.shiro_ld.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealm extends AuthorizingRealm {

	//认证信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		
		String username = usernamePasswordToken.getUsername();
		if("jack".equals(username)){
			String salt = "abc";
			String password = "b106dc6352e5ec1f8aafd8c406d34d92";//明文密码是123
			return new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt.getBytes()), getName());
		}
		
		return null;
	}
	
	//权限信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		String username = (String) principals.getPrimaryPrincipal();
		System.out.println(username);
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		info.addStringPermission("item:insert");
		info.addStringPermission("item:update");
		
		return info;
	}
	
	@Override
	public void setName(String name) {
		super.setName(this.getClass().getName());
	}
	
	public void clearCache(){
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

}
