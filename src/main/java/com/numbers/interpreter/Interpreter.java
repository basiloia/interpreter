package com.numbers.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.numbers.interpreter.helper.Helper;
import com.numbers.interpreter.phone.GreekPhoneNumber;

/**
 * Finds all possible greek phone number interpretations and prints them in
 * standard output
 * 
 */
public class Interpreter {
	final static Logger log = LogManager.getLogger(Interpreter.class);

	/**
	 * Given the input phone number, finds and validates all possible
	 * interpretations
	 * 
	 * @param {@code String[]} args the input phone number
	 */
	public Interpreter(String[] args) {
		try {
			List<String> input = new ArrayList<>(Arrays.asList(args));
			log.debug("Input number: " + Helper.toString(input));
			GreekPhoneNumber grphone_number = new GreekPhoneNumber(input);
			List<String> grphone_interpretations = grphone_number.getInterpretations();
			String format = "%1$-20s %2$-40s %3$-23s\n";
			for (int i = 0; i < grphone_interpretations.size(); i++) {
				log.debug("Interpretation " + (i + 1) + ":" + grphone_interpretations.get(i) + "[phone number: "
						+ (grphone_number.isPhoneNumberValid(grphone_interpretations.get(i)) ? "VALID" : "INVALID")
						+ "]");
				System.out.format(format, "Interpretation " + (i + 1) + ":", grphone_interpretations.get(i),
						"[phone number: " + (grphone_number.isPhoneNumberValid(grphone_interpretations.get(i)) ? "VALID"
								: "INVALID") + "]");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage(), e);
		}
	}

}
