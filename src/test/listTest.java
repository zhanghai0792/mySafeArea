package test;

import java.util.ArrayList;
import java.util.List;

public class listTest {

	public static void main(String[] args) {
		List<String> old=new ArrayList<String>(0);
		old.add("1.jpg");
		old.add("2.png");
		old.add("3.jpg");
		old.add("4.gif");
		List<String> oldNew=new ArrayList<String>(0);
		oldNew.add("3.jpg");
		old.removeAll(oldNew);
		for(String o:old)
			System.out.println(o);
	}

}
