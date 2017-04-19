package pojo;

public class version1 implements pojoModel{
    private Integer id;
    private Integer name;
	private String url;
	
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getPoliceID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPoliceID(Integer policeID) {
		// TODO Auto-generated method stub
		
	}



	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
