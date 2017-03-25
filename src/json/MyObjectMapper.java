package json;

import java.text.SimpleDateFormat;

import org.eclipse.core.internal.propertytester.ProjectPropertyTester;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyObjectMapper extends ObjectMapper {  
	  
    private static final long serialVersionUID = 4402127997078513582L;  
  
    public MyObjectMapper() {
    	//  this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //设置null值不参与序列化(字段不被显示)    
       // this.setSerializationInclusion(Include.NON_NULL);  
        /*
         还有当使用hibernate的时候，查询数据库后产生的实体类是个代理类，这时候转换JSON会报错；
         解决方法有两种：
     1）设置FAIL_ON_EMPTY_BEANS属性，告诉Jackson空对象不要抛异常；
       mapper.disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
         * */
        // 禁用空对象转换json校验  
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);  
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,  
                true);
        this.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,  
                true); 
       
       // SerializationFeature 表示将对象转换成json字符
        //DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT
        //驼峰命名法转换为小写加下划线  
       // this.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);  
    }  
}  
