package logic;

import java.util.ArrayList;
import java.util.HashMap;

import binaryTools.AddAdditionalZeros;
import binaryTools.DecimalToBinaryConverter;
import binaryTools.NumberOfBitsCalculator;
import sortingAlgorithm.SortAccordingToWeights;
import statics.StaticVariables;

public class FilterMinTerms {

	public static ArrayList<String> filter(ArrayList<Integer> minTermsInDeccimal){

		ArrayList<Integer> inputMinTermsList=new ArrayList<>(minTermsInDeccimal);
		StaticVariables.setNumberOfVariables(NumberOfBitsCalculator.getNumberOfBits(inputMinTermsList));
		ArrayList<String> minTermsInBinary=DecimalToBinaryConverter.convert(inputMinTermsList);
		System.out.println("min terms");
		System.out.println(minTermsInBinary);


		ArrayList<String> minTermsWithAddedZeros=AddAdditionalZeros.compute(StaticVariables.getNumberOfVariables(), minTermsInBinary);
		StaticVariables.addBinaryMinTerm(minTermsWithAddedZeros);
		System.out.println("min terms after adding extra zeroes");
		System.out.println(minTermsWithAddedZeros);


		HashMap<Integer, ArrayList<String>> sortedMap = SortAccordingToWeights.sort(minTermsWithAddedZeros);
		System.out.println("sorted map");
		System.out.println(sortedMap);

		HashMap<Integer, ArrayList<String>> filteredColumn=sortedMap;
		int i=0;
		while(StaticVariables.filter()){
			System.out.println("filter "+i);
			HashMap<Integer, ArrayList<String>> filteredMap = LevelFilter.filter(filteredColumn);
			System.out.println(filteredMap);
			filteredColumn=filteredMap;
			i++;
		}

		System.out.println("used bin terms");
		System.out.println(StaticVariables.getUsedBinTerms());

		System.out.println("unused Bin Terms");
		System.out.println(StaticVariables.getUnUsedBinTerms());


		return StaticVariables.getUnUsedBinTerms();
	}
}
