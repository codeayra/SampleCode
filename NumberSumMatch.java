/**
 * 
 */
package com.visa.practice;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yz09
 *
 */
public class NumberSumMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] sortedInput = { -7, -6, -5, 1, 2, 4, 4, 13, 14 };
		System.out.println(
				"result.." + hasPairToSumInSortedArray(sortedInput, 8));
		int[] unsortedInput = { 2, 1, 14, 13, 4, -7, -5, 4, -6 };
		System.out.println(
				"result.." + hasPairToSuminUnsortedArray(unsortedInput, 8));
	}

	public static String hasPairToSuminUnsortedArray(int[] input,
			int givenSum) {
		StringBuilder result = new StringBuilder();
		Set<Integer> complimentSet = new HashSet<>();
		for (int number : input) {
			if (complimentSet.contains(number)) {
				result.append((givenSum - number) + "," + number + "\n");
			} else {
				int compliment = givenSum - number;
				complimentSet.add(compliment);
			}
		}

		return result.toString();
	}

	public static String hasPairToSumInSortedArray(int[] input, int givenSum) {
		StringBuilder result = new StringBuilder();
		int low = 0;
		int high = input.length - 1;
		while (low < high) {
			int sum = input[low] + input[high];
			if (givenSum == sum) {
				result.append(input[low] + "," + input[high] + "\n");
				low++;
				high--;
			} else if (givenSum > sum) {
				low++;
			} else {
				high--;
			}
		}
		return result.toString();

	}

}
