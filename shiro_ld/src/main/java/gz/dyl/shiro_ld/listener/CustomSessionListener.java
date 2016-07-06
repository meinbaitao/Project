package gz.dyl.shiro_ld.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class CustomSessionListener implements SessionListener {

	@Override
	public void onStart(Session session) {
		System.out.println("创建：" + session.getId());
	}

	@Override
	public void onStop(Session session) {
		System.out.println("停止：" + session.getId());
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("过期：" + session.getId());
	}

}
