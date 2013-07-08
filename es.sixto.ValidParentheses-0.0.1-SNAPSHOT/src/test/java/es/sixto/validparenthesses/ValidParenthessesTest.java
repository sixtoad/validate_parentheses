package es.sixto.validparenthesses;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidParenthessesTest {

	@Test
	public void testSimpleBrackets() {
		String stringToTest = "()";
		ValidParenthesses checker = new ValidParenthesses();
		String result = checker.isValid (stringToTest);
		assertSame("Not valid ()", "True", result);
		stringToTest = "[]";
		result = checker.isValid (stringToTest);
		assertSame("Not valid []", "True", result);
		stringToTest = "{}";
		result = checker.isValid (stringToTest);
		assertSame("Not valid {}", "True", result);
	}
	
	@Test
	public void testSimpleBracketsFail() {
		String stringToTest = "(";
		ValidParenthesses checker = new ValidParenthesses();
		String result = checker.isValid (stringToTest);
		assertSame("Valid (", "False", result);
		stringToTest = "[";
		result = checker.isValid (stringToTest);
		assertSame("Valid [", "False", result);
		stringToTest = "{";
		result = checker.isValid (stringToTest);
		assertSame("Valid {", "False", result);
	}
}
