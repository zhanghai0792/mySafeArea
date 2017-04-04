package factory;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

public class qiyunThread extends Thread{
	Logger logger=Logger.getLogger(qiyunThread.class);
 public static Vector<String> deletesImgs=new Vector<String>(0);
 public static boolean isRun=true;
 public void run(){
	 String[] deleteTemp=null;
	 while(isRun){	
		 synchronized (deletesImgs) {
			 if(deletesImgs.size()>0){
				 deleteTemp=new String[deletesImgs.size()];
			 deleteTemp=deletesImgs.toArray(deleteTemp);
			deletesImgs.clear();
			}else{
				try {
					deletesImgs.wait();
					System.out.println("进入休眠期");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 
		 if(deleteTemp!=null&&deleteTemp.length>0){
			 for(String s:deleteTemp){
				 System.out.println("删除 "+s);
				 logger.info("删除图片"+s);
				 try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 deleteTemp=null;
		 }
		 
	 }
 }
 public static void addDeleteImgs(List<String> imgs){
	  if(imgs!=null&&imgs.size()>0)
	 synchronized (deletesImgs) {
		 deletesImgs.addAll(imgs);
		 deletesImgs.notifyAll();
	}
 }
 public static void stopRun(){
	 isRun=false;
 }
 
}
