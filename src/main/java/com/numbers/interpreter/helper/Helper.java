package com.numbers.interpreter.helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains static Helper functions for the application
 *
 */
public class Helper {
	/**
	 * Computes all possible combinations of all List<T> of the lists
	 * 
	 * @param lists a {@code List} of {@code List<T>}
	 * @return the cartesian product of all {@code List<T>} of lists
	 */
	public static <T> List<List<T>> computeCartesianProduct(List<List<T>> lists) {
		List<List<T>> combinations = new LinkedList<List<T>>();
		List<List<T>> newCombinations;

		int index = 0;

		// extract each of T of the first list
		// and add it each to Ts as a new list
		for (T i : lists.get(0)) {
			List<T> newList = new ArrayList<T>();
			newList.add(i);
			combinations.add(newList);
		}
		index++;
		while (index < lists.size()) {
			List<T> nextList = lists.get(index);
			newCombinations = new LinkedList<List<T>>();
			for (List<T> first : combinations) {
				for (T second : nextList) {
					List<T> newList = new ArrayList<T>();
					newList.addAll(first);
					newList.add(second);
					newCombinations.add(newList);
				}
			}
			combinations = newCombinations;

			index++;
		}

		return combinations;
	}

	/**
	 * Converts all {@code List<T>} of oldList to {@code String}
	 * 
	 * @param oldList the {@code List} of {@code List<T>} that are converted to
	 *                {@code String}
	 * @return
	 */
	public static <T> List<String> toStringList(List<List<T>> oldList) {
		/* Specify the size of the list up front to prevent resizing. */
		List<String> newList = new ArrayList<String>(oldList.size());
		for (List<T> aList : oldList) {
			StringBuilder str = new StringBuilder(aList.size());
			for (T n : aList)
				str.append(String.valueOf(n));

			newList.add(str.toString());
		}
		return newList;
	}

	/**
	 * @param list
	 * @return a {@code String} with all elements of {@code list} concatenated
	 */
	public static String toString(List<String> list) {
		StringBuilder str = new StringBuilder(list.size());
		for (String s : list)
			str.append(s + " ");
		return str.toString();

	}

}
