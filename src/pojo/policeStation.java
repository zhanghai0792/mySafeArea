package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

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


}
