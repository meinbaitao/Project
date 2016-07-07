package gz.dyl.shiro_ld.credential;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
	private Cache<String, AtomicInteger> passwordRetryLimitCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
		this.passwordRetryLimitCache = cacheManager.getCache("passwordRetryLimitCache");
	}
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		String username = (String) token.getPrincipal();
		
		AtomicInteger atomicInteger = passwordRetryLimitCache.get(username);
		
		if(null == atomicInteger){
			atomicInteger = new AtomicInteger(0);
			passwordRetryLimitCache.put(username, atomicInteger);
		}
		if(atomicInteger.incrementAndGet() > 5){
			throw new ExcessiveAttemptsException();
		}
		
		boolean matches =  super.doCredentialsMatch(token, info);
		if(matches){
			passwordRetryLimitCache.remove(username);
		}
		return matches;
	}
	
}
