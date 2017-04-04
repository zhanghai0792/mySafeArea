package factory;

import java.util.List;

import org.apache.log4j.Logger;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.util.Auth;

import util.JsonUtil;
import util.ListUtil;

public class imagesFactory {
     private static Logger log=Logger.getLogger(imagesFactory.class);
	private static String accessKey = "your access key";
	private static String secretKey = "your secret key";
	private static String bucket = "your bucket name";
	private static Auth auth;
	private static String zoneName="华东";
	private static Configuration cfg;
	private static Zone currentZone;
	private static void init(){
		cfg=new Configuration(currentZone);
		auth = Auth.create(accessKey, secretKey);
	}
	
	public static void config(String accessKey,String secretKey,String bucket,String zoneName){
		imagesFactory.accessKey=accessKey;
		imagesFactory.secretKey=secretKey;
		imagesFactory.bucket=bucket;
		if(zoneName!=null&&!"".equals(zoneName))
			imagesFactory.zoneName=zoneName;
		if("华东".equals(imagesFactory.zoneName)){
			currentZone=Zone.zone0();
		}else if("华北".equals(imagesFactory.zoneName)){
			currentZone=Zone.zone1();
		}else if("华南".equals(imagesFactory.zoneName)){
			currentZone=Zone.zone2();
		}else{
			currentZone=Zone.zoneNa0();			
		}
		init();
	}
	
	public static void deleteImages(List<String> photos){
		 if(cfg==null&&auth==null)
			 init();
		
		if(ListUtil.isEmpty(photos))
			return;
		BucketManager bucketManager = new BucketManager(auth, cfg);
		log.info("待删除图片集:"+JsonUtil.getJsonString(photos));
		String[] keyList=(String[])photos.toArray(new String[photos.size()]);
		 BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
		    batchOperations.addDeleteOp(bucket, keyList);
		    try {
				Response response = bucketManager.batch(batchOperations);
				 BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
				    for (int i = 0; i < keyList.length; i++) {
				        BatchStatus status = batchStatusList[i];
				        String key = keyList[i];
				        System.out.print(key + "\t");
				        if (status.code == 200) {
				            log.info("图片删除:"+keyList[i]+"成功");
				        } else {
				        	log.error("图片删除:"+keyList[i]+"失败");
				        }
				    }
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		   
	}
	
	public static BucketManager getBucketManager(){
		return new BucketManager(auth, cfg);
	}
	
}
