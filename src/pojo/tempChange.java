package pojo;

public class tempChange {
 private Integer areaID;
 private String areaName;
 private int sourceData=1;
 private int currentMonth;
 private Integer policeID;
	private String policeName;
	
	public Integer getPoliceID() {
		return policeID;
	}
	public String getPoliceName() {
		return policeName;
	}
	public void setPoliceID(Integer policeID) {
		this.policeID = policeID;
	}
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
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
public int getSourceData() {
	return sourceData;
}
public void setSourceData(int sourceData) {
	this.sourceData = sourceData;
}
public int getCurrentMonth() {
	return currentMonth;
}
public void setCurrentMonth(int currentMonth) {
	this.currentMonth = currentMonth;
}
public tempChange(Integer areaID, String areaName, int currentMonth) {
	super();
	this.areaID = areaID;
	this.areaName = areaName;
	this.currentMonth = currentMonth;
}
 
}
