package dao.query;

import java.util.List;

import factory.applicationFactory;
import pojo.pojoModel;
import pojo.user;
public abstract class queryParamsModel<T extends pojoModel>{
protected user user;
protected Integer page;
protected Integer pageSize;
protected Integer recordIndex;
protected String orderBy;
protected List<T> pojos;
protected Integer policeID;
protected Integer fjid=applicationFactory.fjId;//七里湖分局的id
protected String condition;


public String getCondition() {
	return condition;
}
public void setCondition(String condition) {
	this.condition = condition;
}
public abstract T getObj();
public user getUser() {
	return user;
}
public void setUser(user user) {
	this.user = user;
}
public Integer getPage() {
	return page;
}
public void setPage(Integer page) {
	if(page!=null&&page>0){
	this.page = page;
	}
}
public Integer getPageSize() {
	if(pageSize==null||pageSize==0)
		pageSize=3;
	return pageSize;
}
public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
}
protected Integer setNullId(Integer id){
	if(id!=null&&id>0)
		return id;
	return null;
}
public Integer getRecordIndex() {
	if(setNullId(page)!=null){
		if(setNullId(pageSize)==null)
			pageSize=3;
		recordIndex=(page-1)*pageSize;
	}
	return null;
}
public List<T> getPojos() {
	return pojos;
}
public void setPojos(List<T> pojos) {
	this.pojos = pojos;
}
public abstract String getOrderBy();


public abstract String getBasicQueryCondition();
public abstract String getDetailQueryHQL();
//是否详细查询在基本查询的基础上
public abstract boolean getDetailsInBasic();
public Integer getPoliceID() {
	return policeID;
}

public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
}
public void setPoliceID(Integer policeID) {
	this.policeID = policeID;
}
public Integer getFjid() {
	return fjid;
}
public void setFjid(Integer fjid) {
	this.fjid = fjid;
}

}
