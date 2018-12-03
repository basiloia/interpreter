package com.numbers.interpreter.natural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.numbers.interpreter.helper.Helper;

/**
 * An abstract class that contains methods to compute interpretations of number
 * sequence for any language. Abstract methods have to be implemented in derived
 * classes to compute number sequence interpretation for each language.
 *
 */
abstract class NumbersInterpretation {
	final static Logger log = LogManager.getLogger(NumbersInterpretation.class);

	/**
	 * Computes all interpretations of a sequence of numbers.
	 * 
	 * @param sequence_number
	 * @return all interpretations
	 */
	public abstract List<List<String>> findforAllNumbers(List<Integer> sequence_number);

	/**
	 * Computes all interpretations of a number based on {@code current_number} and
	 * {@code next_number}
	 * 
	 * @param current_number the number whose interpretations are to be computed
	 * @param next_number    the next number in sequence
	 * @return all interpretations of current_number
	 */
	List<String> findAllForNumber(int current_number, int next_number) {
		// 534 -> 534,50034,500304
		List<String> number_interpretation = findForNumber(current_number);
		// 500 35 -> 535
		int additional_number = findForNumberBasedonNext(current_number, next_number);
		if (additional_number != 0) {
			List<String> additional_number_interpretation = findForNumber(additional_number);
			number_interpretation.addAll(additional_number_interpretation);
		}
		log.info("Number: " + current_number + ", has interpretations: " + Helper.toString(number_interpretation));
		return number_interpretation;
	}

	/**
	 * Computes all interpretations of a number
	 * 
	 * @param number
	 * @return {@code List<String>} of interpretations
	 * 
	 */
	List<String> findForNumber(int number) {
		List<List<String>> number_interpretation = new ArrayList<>();
		int i = 0, less_significant_digit = 0;
		do {
			number_interpretation.add(findForDigit(number % 10, less_significant_digit, i));

			less_significant_digit = number % 10;
			number /= 10;
			++i;
		} while (number > 0);
		Collections.reverse(number_interpretation);
		return Helper.toStringList(Helper.computeCartesianProduct(number_interpretation));
	}

	/**
	 * Checks if a digit has multiple interpretations
	 * 
	 * @param current
	 * @param less_significant
	 * @return
	 */
	abstract boolean hasDigitMultiple(int current, int less_significant);

	/**
	 * Computes another interpretation based on next number. (Absrtact because the
	 * interpretation differs for each language ex: in French : 4 20 can be
	 * interpreted as 80 :) )
	 * 
	 * @param current_number
	 * @param next_number
	 * @return alternative interpretation of number based on next.
	 */
	abstract int findForNumberBasedonNext(int current_number, int next_number);

	/**
	 * Computes all interpretations of a digit
	 * 
	 * @param current          the digit whose interpretations are to be found
	 * @param less_significant the next digit
	 * @param position         the position of digit in the number
	 * @return
	 */
	List<String> findForDigit(int current, int less_significant, int position) {
		List<String> digit_interpretation = new ArrayList<>();
		digit_interpretation.add(String.valueOf(current));
		if (hasDigitMultiple(current, less_significant))
			digit_interpretation.add(String.valueOf(current + String.join("", Collections.nCopies(position, "0"))));

		return digit_interpretation;
	}
}
