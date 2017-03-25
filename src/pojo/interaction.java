package pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import util.ListUtil;

public class interaction implements pojoModel{
public final static int photoType=4;
private Integer id;//主键
private String title;//标题
private String content;//内容
private List<String> photos=new ArrayList<String>(0);//新闻中包含的图片
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
private Date releaseTime;//发布时间
private int agreeNum;//点赞数
private Boolean isDelete;//是否已经删除

private Set<reply> replies=new HashSet<reply>(0);


private Integer releaseID;
private String releaseName;
//private Set<agree> agrees=new HashSet<agree>(0);
private boolean isAgree;//用户取出时是否对该互动点赞,true表示点赞，false表示没有点赞
private int replyNum;//回复数
private int commentNum;//评论数

public Integer getReleaseID() {
	return releaseID;
}
public void setReleaseID(Integer releaseID) {
	this.releaseID = releaseID;
}
public String getReleaseName() {
	return releaseName;
}
public void setReleaseName(String releaseName) {
	this.releaseName = releaseName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}


public Date getReleaseTime() {
	return releaseTime;
}
public void setReleaseTime(Date releaseTime) {
	this.releaseTime = releaseTime;
}
public int getAgreeNum() {
	return agreeNum;
}
public void setAgreeNum(int agreeNum) {
	this.agreeNum = agreeNum;
}
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}

public List<String> getPhotos() {
	return photos;
}
public void setPhotos(List<String> photos) {
	this.photos = photos;
}

public void setReplies(Set<reply> replies) {
	this.replies = replies;
}

public boolean getIsAgree() {
	return isAgree;
}
public void setIsAgree(boolean isAgree) {
	this.isAgree = isAgree;
}
public interaction() {
	super();
	// TODO Auto-generated constructor stub
}
public interaction(interaction interaction, String releaseName) {
	super();
	if(interaction!=null){
	this.id = interaction.id;
	this.title = interaction.title;
	this.content = interaction.content;
	this.photos = interaction.photos;
	this.releaseTime = interaction.releaseTime;
	this.agreeNum = interaction.agreeNum;
	this.isDelete = interaction.isDelete;
	this.replies = interaction.replies;
	this.releaseID = interaction.releaseID;
	this.releaseName = releaseName;
	this.isAgree = interaction.isAgree;}
}
public int getReplyNum() {
	return replyNum;
}
public void setReplyNum(int replyNum) {
	this.replyNum = replyNum;
}
public int getCommentNum() {
	return commentNum;
}
public void setCommentNum(int commentNum) {
	this.commentNum = commentNum;
}

public interaction(interaction interaction) {
	super();
	if(interaction!=null){
	this.id = interaction.id;
	this.title = interaction.title;
	this.content = interaction.content;
	this.photos = interaction.photos;
	this.releaseTime = interaction.releaseTime;
	this.agreeNum = interaction.agreeNum;
	this.isDelete = interaction.isDelete;
	//this.replies = interaction.replies;
	this.releaseID = interaction.releaseID;
	this.releaseName = releaseName;
	this.isAgree = interaction.isAgree;
	this.commentNum=interaction.commentNum;
	this.replyNum=interaction.replyNum;
	}
}
public Set<reply> getReplies() {
	return replies;
}



}
