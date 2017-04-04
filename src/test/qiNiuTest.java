package test;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.FileInfo;

import factory.imagesFactory;

public class qiNiuTest {

	public static void main(String[] args) {
		imagesFactory.config("Bv6YMexamG1xyMcos8sHjhWE6MwyBGbWMfeotjFu", "oDMUveZbL1lEOeZZ5lWbPgfL9FFPFLWptj-cQVxU", "safeareaimage", "华东");
		
		BucketManager bucketManager = imagesFactory.getBucketManager();
		//文件名前缀
		String prefix = "";
		//每次迭代的长度限制，最大1000，推荐值 1000
		int limit = 1000;
		//指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
		String delimiter = "";
		//列举空间文件列表
		BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator("safeareaimage", prefix, limit, delimiter);
		while (fileListIterator.hasNext()) {
		    //处理获取的file list结果
		    FileInfo[] items = fileListIterator.next();
		    for (FileInfo item : items) {
		        System.out.println(item.key);
		        System.out.println(item.hash);
		        System.out.println(item.fsize);
		        System.out.println(item.mimeType);
		        System.out.println(item.putTime);
		        System.out.println(item.endUser);
		        System.out.println("-------------");
		    }

	}

}
	}
