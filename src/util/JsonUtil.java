package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
  private static ObjectMapper objMapper=new ObjectMapper();
  static{
	  //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	//JSON中的字段多余pojo类的字段设置
	  objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
   public static String getJsonString(Object obj){
	   try {
		return objMapper.writeValueAsString(obj);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
		return null;
	}
   }
   
   public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
       return objMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
   }
   
   public static List getListObjects(String json,Class pojoClass){
	   JavaType type=getCollectionType(ArrayList.class, pojoClass);
	   try {
		return objMapper.readValue(json,type);
	}  catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
   }
   
   public static List getListObjects(InputStream is,Class pojoClass){
	   JavaType type=getCollectionType(ArrayList.class, pojoClass);
	   try {
		return objMapper.readValue(is,type);
	}  catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
   }
   
   public static Object toObject(String content,Class clasz){
	   try {
		return objMapper.readValue(content, clasz);
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	} 
   }
   
   public static Object toObject(InputStream content,Class clasz){
	   try {
		return objMapper.readValue(content, clasz);
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	} 
   }
}
