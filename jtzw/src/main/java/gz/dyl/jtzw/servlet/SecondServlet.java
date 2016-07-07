package gz.dyl.jtzw.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
    	
    	Properties props = new Properties();
    	props.load(inputStream);
    	
    	OutputStream out = resp.getOutputStream();
    	out.write(props.getProperty("abc").getBytes());
    	out.write(props.getProperty("def").getBytes());
    	
    }

}
