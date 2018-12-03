package com.numbers.interpreter.phone;

import java.util.List;

/**
 * Phone Number contains methods to get interpretations of a phone number and
 * validate phone numbers
 *
 */
public interface PhoneNumber {

	/**
	 * Checks if a phone number is valid
	 * 
	 * @param phone_number
	 * @return
	 */
	boolean isPhoneNumberValid(String phone_number);

	/**
	 * Returns all interpretations of the {@code PhoneNumber}
	 * 
	 */
	List<String> getInterpretations();

}
