package logic;

import java.util.ArrayList;

public class outPutGenerator {

	public static String generate(ArrayList<String> arrayList){
		String returnString="F=";
		for (String string : arrayList) {
			returnString=returnString+string+"+";
		}
		return returnString.substring(0,returnString.length()-1);
	}
}
