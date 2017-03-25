package util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	static Logger log = Logger.getLogger(FileUtil.class);

	/*
	 * foldAbsolutePath 待保存文件目录的的绝对路径，格式 c:/aa/bb newFileName 新的文件文件，不含扩展名 如
	 * “我的照片” oldName 旧的文件名，含扩展名 如 “mypicture.jpg”
	 * 返回的是基本的文件名，没有相对路径
	 */
	public static String saveFileReturnFileName(String foldAbsolutePath, String newFileName, String oldName, InputStream inputStream)
			throws Exception {
		File fold = new File(foldAbsolutePath);
		if (fold.isDirectory() && !fold.exists())
			fold.mkdirs();
		String extFileName = oldName.substring(oldName.lastIndexOf("."));
		try {
			OutputStream outputStream = new FileOutputStream(new File(fold, newFileName + extFileName));
             copyFile(inputStream, outputStream);
             return newFileName+extFileName;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
	}
	/*
	 * 保存临时文件后，得到该文件相对rootPath的 路径+文件.扩展名
	 * */
	public static String saveFileReturnRelativePath(String rootPath, String relativePath,String newFileName, String oldName, InputStream inputStream)
			throws Exception {
             return relativePath+"/"+saveFileReturnFileName(rootPath+"/"+relativePath, newFileName, oldName, inputStream);	
	}
	//返回文件在硬盘上的完整路径
	public static String saveFileReturnAbsolutPath(String rootPath, String relativePath,String newFileName, String oldName, InputStream inputStream)
			throws Exception {
             return rootPath+"/"+relativePath+"/"+saveFileReturnFileName(rootPath+"/"+relativePath, newFileName, oldName, inputStream);	
	}
	
   /*将输入流的内容拷贝到输出流中，拷贝完成关闭输入流和输出流*/
	public static boolean copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
		byte[] buffer = new byte[256];
		BufferedOutputStream bos=null;
		if(outputStream!=null)
			bos=new BufferedOutputStream(outputStream);
		int size = -1;
		try {
			while ((size = inputStream.read(buffer)) > 0) {
				bos.write(buffer, 0, size);
			}
			bos.flush();
			return true;
		} catch (IOException e) {
			throw e;
		} finally {
			if (inputStream != null)
				inputStream.close();
			if(bos!=null)
				bos.close();
			if (outputStream != null)
				outputStream.close();
		}
	
	}
	/*
	 * 删除目录或者文件
	 * */
	public static boolean delete(File file){
		 if(file.exists()){
			  if(file.isDirectory()){
				  File[] fileList=file.listFiles();
				   if(fileList!=null&&fileList.length>0){
					 for(File f:fileList)
						 delete(f);
				   }
				   file.deleteOnExit();
				   return true;
			  }else{
				  file.delete();
				  return true;
				  }
		 }
		return false;
	}
	

}
