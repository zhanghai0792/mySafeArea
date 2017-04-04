package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import factory.imagesFactory;
import placeholder.DESUtils;
import util.AppConfig;

/**
 * Application Lifecycle Listener implementation class initListener
 *
 */
public class initListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public initListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
       String fjName=event.getServletContext().getInitParameter("fnName");
     // System.out.println(fjName);
       AppConfig.fjName=fjName;
       
       
       String accessKey=event.getServletContext().getInitParameter("ack");
       accessKey=DESUtils.getDecryptString(accessKey);
    		   
       String secretKey=event.getServletContext().getInitParameter("sek");
       secretKey=DESUtils.getDecryptString(secretKey);
       
       String bucket=event.getServletContext().getInitParameter("buk");
       bucket=DESUtils.getDecryptString(bucket);
       
       String zoneName=event.getServletContext().getInitParameter("zone");
       imagesFactory.config(accessKey, secretKey, bucket, zoneName);

   /*    String log4jdir = event.getServletContext().getRealPath("/");  
       //System.out.println("log4jdir:"+log4jdir);  
       System.setProperty("mysafeareaPath", log4jdir); */
    }
	
}
