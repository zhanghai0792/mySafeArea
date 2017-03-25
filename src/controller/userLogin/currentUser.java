package controller.userLogin;

import pojo.user;

public class currentUser {
	private static ThreadLocal<user> users=new ThreadLocal<user>();
 private currentUser(){}
 public static void login(user u){
	 users.set(u);
 }
 public static user getCurrentUser(){
	 return users.get();
 }
 public static void exit(){
	 users.remove();
 }
}
