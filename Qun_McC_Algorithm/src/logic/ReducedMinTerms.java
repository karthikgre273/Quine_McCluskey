package logic;

import java.util.ArrayList;
import java.util.HashMap;

import binaryTools.BinaryToDecimalCOnverter;
import binaryTools.CalculatePossibleBinaries;
import statics.StaticVariables;

public class ReducedMinTerms {

	public static ArrayList<String> calculate(ArrayList<String> reducedMinTerms){
		ArrayList<String> returnList= new ArrayList<>();
		HashMap<String, ArrayList<Integer>> binaryPossibilitiesMap=new HashMap<>();
		for (String minTerm : reducedMinTerms) {
			ArrayList<String> AllPossibleBinaries=CalculatePossibleBinaries.getBinaryTerms(minTerm);
			ArrayList<Integer> decimalValues=BinaryToDecimalCOnverter.convert(AllPossibleBinaries);
			binaryPossibilitiesMap.put(minTerm, decimalValues);
		}
		System.out.println("All Possibilities");
		System.out.println(binaryPossibilitiesMap);

		System.out.println("Removing Dont Cares");
		HashMap<String, ArrayList<Integer>> MapFreeFromDontCares=RemoveDOntCares(binaryPossibilitiesMap);
		System.out.println(MapFreeFromDontCares);

		HashMap<String, ArrayList<Integer>> primeImplicants = getPrimeImplicants(binaryPossibilitiesMap);
		System.out.println("primeImplicants");
		System.out.println(primeImplicants);

		System.out.println("uncovered minterms");
		ArrayList<Integer> uncoveredMinTerms = getUncoveredMinTerms(primeImplicants);
		System.out.println(uncoveredMinTerms);

		if(uncoveredMinTerms.size()!=0){
			System.out.println("unused Implicant");
			HashMap<String, ArrayList<Integer>> unusedImplicants = getUnusedImplicants(binaryPossibilitiesMap,primeImplicants);
			System.out.println(unusedImplicants);

			HashMap<String, ArrayList<Integer>> dominantImplicants=FindDominantImplicant(uncoveredMinTerms,unusedImplicants);
			System.out.println("dominant Implicant");
			System.out.println(dominantImplicants);

			returnList.add((String) dominantImplicants.keySet().toArray()[0]);
		}
		returnList.addAll(primeImplicants.keySet());
		return returnList;
	}

	private static HashMap<String, ArrayList<Integer>> RemoveDOntCares(		
			HashMap<String, ArrayList<Integer>> inputMap) {
		HashMap<String, ArrayList<Integer>> returnMap=inputMap;
		Object[] keySet = returnMap.keySet().toArray();
		for (int i = 0; i < keySet.length; i++) {
			returnMap.get(keySet[i]).removeAll(StaticVariables.getDontCareTerms());
		}
		return returnMap;
	}


	private static HashMap<String, ArrayList<Integer>> FindDominantImplicant(ArrayList<Integer> uncoveredMinTerms,
			HashMap<String, ArrayList<Integer>> unusedImplicants) {
		HashMap<String,ArrayList<Integer> > DominantScores=new HashMap<>();
		HashMap<Integer,ArrayList<String> > ImplicantScores=new HashMap<>();
		Object[] keySet = unusedImplicants.keySet().toArray();
		int maxScore=0;
		for (int i = 0; i < unusedImplicants.size(); i++) {
			ArrayList<Integer> result = new ArrayList(uncoveredMinTerms);
			result.removeAll(unusedImplicants.get(keySet[i])); 
			int score=(uncoveredMinTerms.size()-result.size());
			if(!ImplicantScores.containsKey(score))
				ImplicantScores.put(score,new ArrayList<>());
			ImplicantScores.get(score).add((String) keySet[i]);	
			if(maxScore<score)
				maxScore=score;
		}
		System.out.println("Implicant Scores");
		System.out.println(ImplicantScores);

		for (String term : ImplicantScores.get(maxScore)) {
			DominantScores.put(term,unusedImplicants.get(term) );
		}
		return DominantScores;
	}

	private static HashMap<String, ArrayList<Integer>> getUnusedImplicants(HashMap<String, ArrayList<Integer>> binaryPossibilitiesMap, HashMap<String, ArrayList<Integer>> primeImplicants) {
		HashMap<String, ArrayList<Integer>> returnList=new HashMap<>();
		Object[] binaryPossibilitiesArray = binaryPossibilitiesMap.keySet().toArray();
		Object[] primeImplicantsArray = primeImplicants.keySet().toArray();
		boolean flag=true;
		for (int i = 0; i < binaryPossibilitiesArray.length; i++) {
			flag=true;
			for (int j = 0; j < primeImplicantsArray.length; j++) {
				if(binaryPossibilitiesArray[i]==primeImplicantsArray[j]){
					flag=false;
					break;		
				}
			}
			if (flag==true){
				returnList.put((String) binaryPossibilitiesArray[i],binaryPossibilitiesMap.get(binaryPossibilitiesArray[i]));
			}
		}
		return returnList;
	}

	private static ArrayList<Integer> getUncoveredMinTerms(HashMap<String, ArrayList<Integer>> primeImplicants) {
		ArrayList<Integer> listOfDecimalsUnoccupied=new ArrayList<>();

		ArrayList<Integer> listOfDecimalsOccupiedByPrimeImplicants=new ArrayList<>();
		Object[] keySet = primeImplicants.keySet().toArray();
		for (int i = 0; i < primeImplicants.size(); i++) {
			listOfDecimalsOccupiedByPrimeImplicants.addAll(primeImplicants.get(keySet[i]));
		}
		for (Integer integer : StaticVariables.getMinTerms()) {
			if(!listOfDecimalsOccupiedByPrimeImplicants.contains(integer)){
				listOfDecimalsUnoccupied.add(integer);
			}
		}
		return listOfDecimalsUnoccupied;

	}

	private static HashMap<String, ArrayList<Integer>> getPrimeImplicants(HashMap<String, ArrayList<Integer>> binaryPossibilitiesMap) {
		HashMap<String, ArrayList<Integer>> primeImplicats=new HashMap<String, ArrayList<Integer>>();
		Object[] keySet = binaryPossibilitiesMap.keySet().toArray();
		for (int i = 0; i < binaryPossibilitiesMap.size(); i++) {
			boolean hasANumberWhichIsNotInOthers=false; 
			for (Integer decimalForComparision : binaryPossibilitiesMap.get(keySet[i])) {	
				hasANumberWhichIsNotInOthers=true;
				outerloop : for (int j = 0; j < binaryPossibilitiesMap.size(); j++) {	
					if(i!=j){
						for (Integer decimalToBeComparedTo : binaryPossibilitiesMap.get(keySet[j])) {
							if(decimalForComparision==decimalToBeComparedTo){
								hasANumberWhichIsNotInOthers=false;
								break outerloop;
							}
						}
					}					
				}	
				if(hasANumberWhichIsNotInOthers){
					primeImplicats.put((String) keySet[i],binaryPossibilitiesMap.get(keySet[i]));
				}
			}

		}
		return primeImplicats;

	}
}
