package dao.query;

import pojo.policeStation;

public class policeStationQueryParams  extends queryParamsModel<policeStation>{
    private policeStation policeStation;
	
	public policeStation getObj() {
		// TODO Auto-generated method stub
		return policeStation;
	}

	
	public String getOrderBy() {
		
		return "policeStation.id";
	}

	
	public String getBasicQueryCondition() {
		return " where (:policeID IS NULL or policeStation.id=:policeID)";
	}

	
	public String getDetailQueryHQL() {
		// TODO Auto-generated method stub
		return "from pojo.policeStation policeStation";
	}

	
	public boolean getDetailsInBasic() {
		return false;
	}


	public policeStation getPoliceStation() {
		return policeStation;
	}


	public void setPoliceStation(policeStation policeStation) {
		this.policeStation = policeStation;
	}

	

}
