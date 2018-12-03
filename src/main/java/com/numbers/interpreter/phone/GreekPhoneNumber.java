package com.numbers.interpreter.phone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.numbers.interpreter.helper.Helper;
import com.numbers.interpreter.natural.GreekNumbersInterpretion;

/**
 * Contains methods to interpret and validate a greek phone number
 *
 */
public class GreekPhoneNumber extends GreekNumbersInterpretion implements PhoneNumber {
	final static Logger log = LogManager.getLogger(GreekPhoneNumber.class);

	/**
	 * All interpretations of a greek phone number
	 */
	private List<String> interpretations;

	enum prefix {
		national, local, mobile, none
	}

	private static final Map<prefix, String> prefixesMap;
	static {
		prefixesMap = new HashMap<prefix, String>();
		prefixesMap.put(prefix.national, "0030");
		prefixesMap.put(prefix.local, "2");
		prefixesMap.put(prefix.mobile, "69");
		prefixesMap.put(prefix.none, "");
	}

	private static final int localNumberLength = 10;
	private static final int nationalNumberLength = 14;

	public GreekPhoneNumber(List<String> input_number) {
		interpretations = findAllInterpretations(input_number);
		log.info("--------------Interpretations--------------------");
		interpretations.forEach(inter -> log.info(inter));

	}

	@Override
	public boolean isPhoneNumberValid(String phone_number) {
		boolean isValid = false;
		switch (getValidPrefix(phone_number)) {
		case national:
			if (phone_number.length() == nationalNumberLength)
				isValid = true;
			break;
		case local:
		case mobile:
			if (phone_number.length() == localNumberLength)
				isValid = true;
			break;
		case none:
		default:
		}
		log.info("Phone number: " + phone_number + " is " + (isValid ? "valid" : "not valid"));
		return isValid;
	}

	@Override
	public List<String> getInterpretations() {
		return interpretations;
	}

	/**
	 * @param phone_number
	 * @return the prefix of the {@code phone_number}
	 */
	private prefix getValidPrefix(String phone_number) {
		prefix phone_prefix = prefix.none;
		if (phone_number.startsWith(prefixesMap.get(prefix.national) + prefixesMap.get(prefix.local)))
			phone_prefix = prefix.national;
		else if (phone_number.startsWith(prefixesMap.get(prefix.national) + prefixesMap.get(prefix.mobile)))
			phone_prefix = prefix.national;
		else if (phone_number.startsWith(prefixesMap.get(prefix.local)))
			phone_prefix = prefix.local;
		else if (phone_number.startsWith(prefixesMap.get(prefix.mobile)))
			phone_prefix = prefix.mobile;
		else
			phone_prefix = prefix.none;

		return phone_prefix;
	}

	/**
	 * @param input_number
	 * @return all possible interpretations of a greek phone number
	 */
	private List<String> findAllInterpretations(List<String> input_number) {
		try {
			List<String> interpretation = Helper.toStringList(this.findforAllNumbers(
					input_number.stream().map(n -> Integer.parseInt(n)).collect(Collectors.toList())));
			if (interpretation.stream().filter(element -> getValidPrefix(element) != prefix.none).count() > 0) {
				return interpretation.stream().filter(element -> getValidPrefix(element) != prefix.none)
						.collect(Collectors.toList());
			}

			else {
				return interpretation;

			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("input does not contain only numbers or number too long");
		}
	}

}
