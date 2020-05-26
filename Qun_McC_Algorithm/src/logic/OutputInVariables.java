package logic;

import java.util.ArrayList;

public class OutputInVariables {
	
	public static ArrayList<String> get(ArrayList<String> finalMinTerms){
		ArrayList<String> MinTermVariables=new ArrayList<>();
		
		for (String minTerm : finalMinTerms) {
			MinTermVariables.add(getVariable(minTerm));
		}
		
		return MinTermVariables;
		
	}

	public static String getVariable(String minTerm) {
		char[] minTermcharArray = minTerm.toCharArray();
		String outputString="";
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for (int i = 0; i < minTermcharArray.length; i++) {
			switch (""+minTermcharArray[i]) {
			case "0":
				outputString+=""+alphabet[i]+"'";				
				break;
			case "1":
				outputString+=""+alphabet[i];				
				break;
			case "-":			
				break;

			default:
				break;
			}
		}
		return outputString;
		
	}
	
	public static void main(String[] args) {
		System.out.println(getVariable("1-001"));
	}

}
