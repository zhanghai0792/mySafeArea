package dao.query;

import pojo.version1;

public class versionQueryParams extends queryParamsModel<version1> {
   private version1 version;
	private Integer interactionID;//
	private Integer agreeID;//点赞的用户
	@Override
	public pojo.version1 getObj() {
		// TODO Auto-generated method stub
		return version;
	}
	@Override
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getBasicQueryCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean getDetailsInBasic() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
