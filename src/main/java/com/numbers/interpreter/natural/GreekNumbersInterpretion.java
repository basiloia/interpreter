package com.numbers.interpreter.natural;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains methods to compute interpretations of numbers sequence for greek language.
 *
 */
import com.numbers.interpreter.helper.Helper;

public class GreekNumbersInterpretion extends NumbersInterpretation {
	/**
	 * All numbers >10 that have only one interpretation
	 */
	private static final List<Integer> notAmbiguousInterpretation = new ArrayList<>(Arrays.asList(11, 12));

	@Override
	boolean hasDigitMultiple(int current, int less_significant) {
		// A digit in greek has multiple interpretations only if less significant digit
		// is not 0 and is not included in notAmbiguousInterpretation list
		if ((notAmbiguousInterpretation.contains(current)) || (less_significant == 0))
			return false;
		else
			return true;
	}

	@Override
	int findForNumberBasedonNext(int current_number, int next_number) {
		// Finds the number of zero least significant digits and checks if next number
		// is smaller than them
		// ex: 400 50 -> 450 // 400 500 -> 0
		int zerodigits = 0;
		while (current_number > 0 && current_number % 10 == 0) {
			++zerodigits;
			current_number /= 10;
		}
		if ((next_number) > 0 && ((int) (next_number / Math.pow(10, zerodigits)) == 0)) {
			return current_number;

		} else
			return 0;
	}

	@Override
	public List<List<String>> findforAllNumbers(List<Integer> sequence_number) {
		if (sequence_number.isEmpty())
			throw new NullPointerException("null input");
		List<List<String>> numbers_interpretation = new ArrayList<>();
		for (int i = 0; i < sequence_number.size(); i++) {
			int next_number = 0;
			if (i < sequence_number.size() - 1)
				next_number = sequence_number.get(i + 1);
			numbers_interpretation.add(findAllForNumber(sequence_number.get(i), next_number));

		}
		return Helper.computeCartesianProduct(numbers_interpretation);

	}
}
