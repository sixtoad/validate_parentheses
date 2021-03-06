/**
 * 
 */
package es.sixto.validparenthesses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @author Sixto Given a string comprising just of the characters (,),{,},[,]
 *         determine if it is well-formed or not. Your program should accept as
 *         its first argument a path to a filename. Each line in this file
 *         contains a string comprising of the characters mentioned above. e.g.
 *         () ([)] Print out True or False if the string is well-formed e.g.
 *         True False
 */
public class ValidParenthesses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new ValidParenthesses().run(args));
	}
	
	public String run (String[] args) {
		String result = null;
		if (args.length>0) {
			String[] resultArray = this.isValidFromFile(args[0]);
			StringBuffer tempString = new StringBuffer();
			for (int i = 0; i < resultArray.length; i++) {
				tempString.append(resultArray[i]+"\n");
			}
			result = tempString.toString();
		} else {
			result = "You must to pass a valid path for a file to process";
		}
		return result;
	}

	public String isValid(String stringToTest) {
		ArrayDeque<Character> stackBrackets = new ArrayDeque<Character>();
		
		for (int i = 0; i < stringToTest.length(); i++) {
			char charToValidate = stringToTest.charAt(i);
			if (charToValidate == '{' || charToValidate == '('
					|| charToValidate == '[') {
				stackBrackets.push(charToValidate);
			} else if (stackBrackets.isEmpty() != true && charToValidate == '}'
					&& stackBrackets.peek() == '{') {
				stackBrackets.pop();
			} else if (stackBrackets.isEmpty() != true && charToValidate == ']'
					&& stackBrackets.peek() == '[') {
				stackBrackets.pop();
			} else if (stackBrackets.isEmpty() != true && charToValidate == ')'
					&& stackBrackets.peek() == '(') {
				stackBrackets.pop();
			} else {
				return "False";
			}
		}
		if (stackBrackets.isEmpty() != true) {
			return "False";
		}
		return "True";
	}

	public String[] isValidFromFile(String pathToFile) {
		BufferedReader br = null;
		ArrayList<String> processesLines = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(pathToFile));
			String line = br.readLine();
			while (line != null) {
				processesLines.add(this.isValid(line));
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			processesLines.add("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br!=null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return processesLines.toArray(new String[processesLines.size()]);
	}

}
