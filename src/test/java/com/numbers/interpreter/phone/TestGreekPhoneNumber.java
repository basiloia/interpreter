package com.numbers.interpreter.phone;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class TestGreekPhoneNumber {
	@Test
	public void testGrPhoneNumberValidation1() {
		GreekPhoneNumber grphone_number = new GreekPhoneNumber(Arrays.asList("0"));
		assertTrue(!grphone_number.isPhoneNumberValid("302558"));
	}

	@Test
	public void testGrPhoneNumberValidation2() {
		GreekPhoneNumber grphone_number = new GreekPhoneNumber(Arrays.asList("0"));
		assertTrue(grphone_number.isPhoneNumberValid("2106930664"));
	}

	@Test
	public void testGrPhoneNumberValidation3() {
		GreekPhoneNumber grphone_number = new GreekPhoneNumber(Arrays.asList("0"));
		assertTrue(grphone_number.isPhoneNumberValid("00306974092252"));
	}

	@Test
	public void testGrPhoneNumberValidation4() {
		GreekPhoneNumber grphone_number = new GreekPhoneNumber(Arrays.asList("0"));
		assertTrue(!grphone_number.isPhoneNumberValid("69740922521"));
	}

	@Test
	public void testGrPhoneNumberValidation5() {
		GreekPhoneNumber grphone_number = new GreekPhoneNumber(Arrays.asList("0"));
		assertTrue(grphone_number.isPhoneNumberValid("00302106930664"));
	}

	@Test(expected = NumberFormatException.class)
	public void testInvalidInput() {
		new GreekPhoneNumber(Arrays.asList("2", "10e"));
	}

	@Test(expected = NullPointerException.class)
	public void testNullInput() {
		new GreekPhoneNumber(Arrays.asList());
	}

	@Test
	public void testGrPhoneNumberInterpretation1() {
		GreekPhoneNumber grphone_number = new GreekPhoneNumber(Arrays.asList("2", "10", "6", "9", "30", "6", "6", "4"));
		assertTrue(grphone_number.getInterpretations()
				.equals(Arrays.asList("2106930664", "210693664", "216930664", "21693664")));
	}

	@Test
	public void testGrPhoneNumberInterpretation2() {
		GreekPhoneNumber grphone_number = new GreekPhoneNumber(Arrays.asList("2", "10", "69", "30", "6", "6", "4"));
		assertTrue(grphone_number.getInterpretations()
				.equals(Arrays.asList("2106930664", "210693664", "21060930664", "2106093664")));
	}

	@Test
	public void testGrPhoneNumberInterpretation3() {
		GreekPhoneNumber grphone_number = new GreekPhoneNumber(
				Arrays.asList("0", "0", "30", "69", "700", "24", "1", "3", "50", "2"));
		assertTrue(grphone_number.getInterpretations()
				.equals(Arrays.asList("0030697002413502", "003069700241352", "00306970020413502", "0030697002041352",
						"00306972413502", "0030697241352", "003069720413502", "00306972041352")));
	}

}
