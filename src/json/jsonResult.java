package json;

import java.util.ArrayList;
import java.util.List;

public class jsonResult {
	private boolean success=true;
	private String msg;
	private long total;
	private int pageSize;
	private List datas=new ArrayList(0);
	
	
	
	public jsonResult(boolean success, String msg, long total, int pageSize, List datas) {
		super();
		this.success = success;
		this.msg = msg;
		this.total = total;
		this.pageSize = pageSize;
		this.datas = datas;
	}
    public jsonResult(Object pojo,String msg){
    	this.msg=msg;
    	this.datas.add(pojo);
    	this.total=1;
    }
	public void setErrMsg(String msg){
		this.success=false;
		this.msg=msg;
	}
	
   public void add(List datas){
	   if(datas!=null){
	   this.datas.addAll(datas);
	   this.total=this.datas.size();
	   }
   }
	
	
	

	public jsonResult(String msg) {
		super();
		this.msg = msg;
	}



	public jsonResult(List datas) {
		super();
		this.datas = datas;
		if(datas!=null)
		this.total=datas.size();
	}

 

	public jsonResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public jsonResult() {
	}



	public boolean getSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public long getTotal() {
		return total;
	}



	public void setTotal(long total) {
		this.total = total;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public List getDatas() {
		return datas;
	}



	public void setDatas(List datas) {
		this.datas = datas;
	}
	
	
	
	
}
