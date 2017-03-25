package pojo;

public class summaryPojo implements pojoModel{
  private Integer areaID;//小区的Id
  private String areaName;//小区的名字
  private long historyTotal;//历史采集重量
  private long currentMonth;//当月采集量

public Integer getAreaID() {
	return areaID;
}
public void setAreaID(Integer areaID) {
	this.areaID = areaID;
}
public String getAreaName() {
	return areaName;
}
public void setAreaName(String areaName) {
	this.areaName = areaName;
}
public long getHistoryTotal() {
	return historyTotal;
}
public void setHistoryTotal(long historyTotal) {
	this.historyTotal = historyTotal;
}
public long getCurrentMonth() {
	return currentMonth;
}
public void setCurrentMonth(long currentMonth) {
	this.currentMonth = currentMonth;
}


public Integer getId() {
	// TODO Auto-generated method stub
	return areaID;
}

public void setId(Integer id) {
	// TODO Auto-generated method stub
	
}
public summaryPojo(Object[] objs) {
	super();
	this.areaID = (Integer)objs[0];
	this.areaName = (String)objs[1];
	this.historyTotal = (Long)objs[2];
	this.currentMonth = (Long)objs[3];
}


  
  
  
}
