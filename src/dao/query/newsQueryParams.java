package dao.query;

import controller.userLogin.currentUser;
import pojo.news;

public class newsQueryParams extends queryParamsModel<news>{
    private news news;
    private Integer newsId;
	
	public news getObj() {
		// TODO Auto-generated method stub
		return news;
	}
	public news getNews() {
		return news;
	}
	public void setNews(news news) {
		this.news = news;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	
	public String getOrderBy() {
		return " news.releaseTime desc";
	}
	
	public String getBasicQueryCondition() {
		if(policeID==null)
		       policeID=currentUser.getCurrentUser().getPoliceID();
		return " where (:newsId IS NULL or news.id=:newsId) and (:policeID = :fjid or :policeID=news.policeID)";
	}
	
	public String getDetailQueryHQL() {
		
		return "select distinct news from news news left join fetch news.photos";
	}
	
	public boolean getDetailsInBasic() {
		
		return true;
	}

}
