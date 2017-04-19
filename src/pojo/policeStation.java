package pojo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import util.StringUtil;

public class policeStation implements pojoModel{
private Integer id;
private String name;
public Integer getId() {
	return id;
}
public String getName() {
	return name;
}
public void setId(Integer id) {
	this.id = id;
}
public void setName(String name) {
	this.name = name;
}
@JsonIgnore
public Integer getPoliceID() {
	
	return id;
}

public void setPoliceID(Integer policeID) {

}
public static String[] excelFormate=new String[]{"name","八里湖分局辖区派出所信息导入表"};
public static policeStation dataChange(Map<String,String> data){
	policeStation c=null;
	 if(data!=null)
		 c=new policeStation();
	 c.setName(data.get("name"));
		 
	return c;
}

}
