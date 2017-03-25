package dao.query;
import pojo.user;
public class userQueryParams extends queryParamsModel<user>{

	
	public user getObj() {
		// TODO Auto-generated method stub
		return user;
	}
    private String phone;//用来判断该电话号码是否注册了
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getBasicQueryCondition() {
		//return  " where user.phone=:phone and user.password=:password";
	     return null;
	}
	
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean getDetailsInBasic() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
