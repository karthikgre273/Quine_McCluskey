package statics;

import java.util.ArrayList;
import java.util.HashMap;

public class StaticVariables {

	static int NumberOfVariables=0;
	static ArrayList<Integer> InputDecimalminTerms=new ArrayList<>();
	static ArrayList<Integer> InputDontCareDecimalterms=new ArrayList<>();
	static ArrayList<String> BinaryminTerms=new ArrayList<>();
	static HashMap<Integer,ArrayList<String>> binaryAccToWeight=new HashMap<>();
	static ArrayList<String> usedBinTerms=new ArrayList<>();
	static boolean filterFlag=true;
	
	
	public static boolean filter() {
		return filterFlag;
	}
	public static void setFilterFlag(boolean filterFlag) {
		StaticVariables.filterFlag = filterFlag;
	}
	public static int getNumberOfVariables() {
		return NumberOfVariables;
	}
	public static void setNumberOfVariables(int numberOfVariables) {
		NumberOfVariables = numberOfVariables;
	}
	public static ArrayList<Integer> getDontCareTerms() {
		return InputDontCareDecimalterms;
	}
	public static void addDontCareTerms(Integer DontCareTerm) {
		InputDontCareDecimalterms.add(DontCareTerm);
	}
	public static void addDontCareTerms(Integer[] DontCareTerm) {
		for (int i = 0; i < DontCareTerm.length; i++) {
			InputDontCareDecimalterms.add(DontCareTerm[i]);
		}
	}
	
	public static ArrayList<Integer> getMinTerms() {
		return InputDecimalminTerms;
	}
	public static void addMinTerms(Integer minTerm) {
		InputDecimalminTerms.add(minTerm);
	}
	public static void addMinTerms(Integer[] minTerm) {
		for (int i = 0; i < minTerm.length; i++) {
			InputDecimalminTerms.add(minTerm[i]);
		}
	}
	
	public static HashMap<Integer, ArrayList<String>> getBinaryAccToWeight() {
		return binaryAccToWeight;
	}
	public static void setBinaryAccToWeight(HashMap<Integer, ArrayList<String>> binaryAccToWeight) {
		StaticVariables.binaryAccToWeight = binaryAccToWeight;
	}
	public static ArrayList<String> getUsedBinTerms() {
		return usedBinTerms;
	}
	public static void addUsedBinTerms(String usedBinTerm) {
		if(!usedBinTerms.contains(usedBinTerm))
			usedBinTerms.add(usedBinTerm);
	}
	public static ArrayList<String> getBinaryMinTerms() {
		return BinaryminTerms;
	}
	public static void addBinaryMinTerm(String minTerm) {
		if(!BinaryminTerms.contains(minTerm))
			BinaryminTerms.add(minTerm);
	}
	public static void addBinaryMinTerm(ArrayList<String> minTermsInBinary) {
		BinaryminTerms.addAll(minTermsInBinary);

	}
	public static ArrayList<String> getUnUsedBinTerms() {
		ArrayList<String> returnList=new ArrayList<>();
		for (String term : BinaryminTerms) {
			if(!usedBinTerms.contains(term)){
				returnList.add(term);
			}
		}
		return returnList;
	}



}
