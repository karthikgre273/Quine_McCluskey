package test;

import java.util.ArrayList;
import java.util.Arrays;

import logic.FilterMinTerms;
import logic.OutputInVariables;
import logic.ReducedMinTerms;
import logic.outPutGenerator;
import statics.StaticVariables;

public class MainMethod {
	
	public static void main(String[] args) {
		
		Integer[] inputMinTerms={0,16,32,48,72};
		Integer[] DontCares={};

		StaticVariables.addMinTerms(inputMinTerms);
		StaticVariables.addDontCareTerms(DontCares);
		
		ArrayList<Integer> inputTerms=new ArrayList<>(Arrays.asList(inputMinTerms));
		inputTerms.addAll(Arrays.asList(DontCares));
		
		ArrayList<String> filter = FilterMinTerms.filter(inputTerms);
		ArrayList<String> finalMinTerms = ReducedMinTerms.calculate(filter);
		
		System.out.println("final Min Terms");
		System.out.println(finalMinTerms);
		
		ArrayList<String> arrayList = OutputInVariables.get(finalMinTerms);
		System.out.println("output Varibles");
		System.out.println(arrayList);
		
		System.out.println("\n\nFinal Expression");
		String generate = outPutGenerator.generate(arrayList);
		System.out.println(generate);
		
		
		
	}

}
