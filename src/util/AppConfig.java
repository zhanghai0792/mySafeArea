package util;

import java.io.File;

public class AppConfig {
	/*
	 * /sysimg/residentImg 居住人头像根目录 
	 * /sysimg/areaImg 小区图片 
	 * /sysimg/userImg 用户头像
	 * /sysimg/interImg 交互上传图片
	 * 
	 */
	public static final boolean fileSaveInApp=false;
	public static final String ResidentRelative = "/sysimg/residentImg/";
	public static final String AreaRelative="/sysimg/areaImg/";
	public static final String UserRelative="/sysimg/userImg";
	public static final String InteractionRelative="/sysimg/interImg";
	public static final String NewsRelative="/sysimg/newsImg";
	public static String RootPath;// 物理路径
    public static String residentRootPath;
    public static String areaRootPath;
    public static String userRootPath;
    public static String interactionRootPath;
    public static String newsRootPath;
	public static String getRootPath() {
		return RootPath;
	}
	public static void setRootPath(String rootPath) {
		RootPath = rootPath;
		residentRootPath=rootPath+ResidentRelative;
		areaRootPath=rootPath+AreaRelative;
		userRootPath=rootPath+UserRelative;
		interactionRootPath=rootPath+InteractionRelative;
		newsRootPath=rootPath+NewsRelative;
		File file=null;
		file=new File(residentRootPath);
		if(!file.exists())
			file.mkdirs();
		file=new File(areaRootPath);
		if(!file.exists())
			file.mkdirs();
		file=new File(userRootPath);
		if(!file.exists())
			file.mkdirs();
		file=new File(interactionRootPath);
		if(!file.exists())
			file.mkdirs();
		file=new File(newsRootPath);
		if(!file.exists())
			file.mkdirs();
	}
    
}
