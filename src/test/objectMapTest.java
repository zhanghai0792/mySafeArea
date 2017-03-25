package test;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import json.MyObjectMapper;
import pojo.user;

public class objectMapTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String params="{\"phone\":\"18323234567\",\"password\":\"000000\"}";
        MyObjectMapper objM=new MyObjectMapper();
		user user=objM.readValue(params, user.class);
		System.out.println(user.getPhone()+";"+user.getPassword());
	}

}
