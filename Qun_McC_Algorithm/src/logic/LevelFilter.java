package logic;

import java.util.ArrayList;
import java.util.HashMap;

import binaryTools.CheckForSimilarityInWeights;
import binaryTools.CommonTermGenerator;
import statics.StaticVariables;

public class LevelFilter {

	public static HashMap<Integer, ArrayList<String>> filter(HashMap<Integer, ArrayList<String>> inputMinTermsInBinary){
		
		StaticVariables.setFilterFlag(false);

		HashMap<Integer, ArrayList<String>> returnMap=new HashMap<>();
		Object[] keySet = inputMinTermsInBinary.keySet().toArray();
		for (int i = 0; i < inputMinTermsInBinary.size()-1; i++) {
			for (String selectedBinary : inputMinTermsInBinary.get(keySet[i])) {
				for (String comparingBinary : inputMinTermsInBinary.get(keySet[i+1])) {
					if(CheckForSimilarityInWeights.check(selectedBinary,comparingBinary)){
						StaticVariables.setFilterFlag(true);
						if(!returnMap.containsKey(i)){
							returnMap.put(i, new ArrayList<>());
						}
						String generatedTerm = CommonTermGenerator.generate(selectedBinary,comparingBinary);
						if(!returnMap.get(i).contains(generatedTerm))
							returnMap.get(i).add(generatedTerm);
						StaticVariables.addUsedBinTerms(selectedBinary);
						StaticVariables.addUsedBinTerms(comparingBinary);
						StaticVariables.addBinaryMinTerm(generatedTerm);
					}
				}
			}
		}
		return returnMap;
	}

}
