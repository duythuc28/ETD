/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author iOSDev
 */
public class IOUtility {
    public static final String INPUT_DATE_FORMAT = "dd/MM/yyyy";
	public static final String OUTPUT_DATE_FORMAT = "MMM dd,yyyy";
	public static final int VALIDATE_PRIOR_DAY = 2;
	/**  Method to display the user's prompt string 
	 * 
     * 	@param   prompt     A String which prompt for user input
     */
	public static void printPrompt(String prompt) {
		print(prompt + " ");
		System.out.flush();
	}
	/**  prints the argument passed to the method with enter.
	 * 
     * 	@param   prompt     toPrint A String that should be printed to the stdout
     */
	public static void println(String prompt) {
		System.out.println(prompt);
	}
	
	/**  prints the argument passed to the method.
	 * 
     * 	@param   prompt     toPrint A String that should be printed to the stdout
     */
	public static void print(String prompt) {
		System.out.print(prompt);
	}
	
	
	/**  prints the argument passed to the method with define format. 
	 * 
     * 	@param   prompt     toPrint A String that should be printed to the stdout
     */
	public static void format(String format, Object ... args) {
		System.out.format(format,args);
	}


	/** 
     * Method to make sure no data is available in the input stream
     */
	public static void inputFlush() {
		try {
			while ((System.in.available()) != 0)
				System.in.read();
		} catch (java.io.IOException e) {
			System.out.println("Input error");
		}
	}

	/** getString(String prompt) prompts the user for an input, 
     * reads it as a String object and returns it.
     * 
     * @param   prompt  A String which prompt for user input
     * @return  String  An object which holds the user input
     */
	public static String getString(String prompt) {
		inputFlush();
		printPrompt(prompt);
		int aChar;
		String s = "";
		boolean finished = false;
		while (!finished) {
			try {
				aChar = System.in.read();
				if (s.length() == 0 && (aChar < 0 || (char) aChar == '\n')) {
					println("Please do not input empty string");
					inputFlush();
					printPrompt(prompt);
				}
				else if (aChar < 0 || (char) aChar == '\n')
					finished = true;
				else if ((char) aChar != '\r')
					s = s + (char) aChar; // Enter into string
			}
			catch (java.io.IOException e) {
				println("Input error");
				finished = true;
			}
		}
		return s;
	}
	
	/** This methods prompts the user for an input, 
     *	 and parses the input to LocalDate object.
     * 
     * @param   prompt     A String which prompt for user input
     * @return  LocalDate  An object which holds the user input
     */
	public static LocalDate getDate(String prompt) {
		while (true) {
			try {
				inputFlush();
				printPrompt(prompt);
				// Hard code date format to dd/MM/YYYY
		        DateTimeFormatter format = DateTimeFormatter.ofPattern (INPUT_DATE_FORMAT);
		        LocalDate localDate = null;
		        localDate = LocalDate.parse (getString() , format);
		        return localDate;
			} 
			catch (DateTimeParseException ex) {
				format("Invalid input, please add correct date format %s\n",INPUT_DATE_FORMAT);
			}
		}
	}

	/** getString() prompts the user for an input, 
     * reads it as a String object and returns it.
     * 
     * @return  String  An object which holds the user input
     */
	private static String getString() {
		int aChar;
		String s = "";
		boolean finished = false;
		while (!finished) {
			try {
				aChar = System.in.read();
				if (aChar < 0 || (char) aChar == '\n')
					finished = true;
				else if ((char) aChar != '\r')
					s = s + (char) aChar; // Enter into string
			}
			catch (java.io.IOException e) {
				println("Input error");
				finished = true;
			}
		}
		return s;
	}

	/** This methods prompts the user for an input, 
     *	 and parses the input to Integer.
     * 
     * @param   prompt     A String which prompt for user input
     * @return  Integer    An object which holds the user input
     */
	public static int getInt(String prompt) {
		while (true) {
			inputFlush();
			printPrompt(prompt);
			try {
				return Integer.valueOf(getString().trim()).intValue();
			}
			catch (NumberFormatException e) {
				println("Invalid input. Not an integer");
			}
		}
	}
	/** This methods prompts the user for an input, 
     *	 and parses the input to Integer.
     * 
     * @param   prompt       A String which prompt for user input
     * @param   fromRange    A lower bound of input 
     * @param   toRange      An upper bound of input 
     * @return  Integer      An object which holds the user input
     */
	public static int getInt(String prompt, int fromRange,int toRange) {
		while (true) {
			inputFlush();
			printPrompt(prompt);
			try {
				int number = Integer.valueOf(getString().trim()).intValue();
				if (number < fromRange || number >= toRange) {
					format("Invalid input, number must"
							+ " from %d to %d\n",fromRange, toRange);
					
				} else {
					return number;
				}
			}
			catch (NumberFormatException e) {
				println("Invalid input. Not an integer");
			}
		}
	}

	/** This methods prompts the user for an input, 
     *	 and parses the input to Char.
     * 
     * @param   prompt     A String which prompt for user input
     * @return  Char       An object which holds the user input
     */
	public static char getChar(String prompt) {
		int aChar = 0;
		inputFlush();
		printPrompt(prompt);
		try {
			aChar = System.in.read();
		}
		catch (java.io.IOException e) {
			println("Input error");
		}
		inputFlush();
		return (char) aChar;
	}

	/** This methods prompts the user for an input, 
     *	 and parses the input to Double.
     * 
     * @param   prompt     A String which prompt for user input
     * @return  Double    An object which holds the user input
     */
	public static double getDouble(String prompt) {
		while (true) {
			inputFlush();
			printPrompt(prompt);
			try {
				return Double.valueOf(getString().trim()).doubleValue();
			}
			catch (NumberFormatException e) {
				println("Invalid input. Not a floating point number");
			}
		}
	}
	
	/**
	 * Check data is out of date
	 * 
	 * @return boolean true if date is prior 3 days before.
	 */
	public static boolean checkIsOutOfDated(LocalDate date) {
		return LocalDate.now().isBefore(date.minusDays(VALIDATE_PRIOR_DAY));
	}
	
	/**
	 * Convert local date to string value with hard code date format MMM dd,yyyy
	 * 
	 * @param   inDate	The date that need to be converted.
	 * @return         	Return string with format MMM dd,yyyy
	 */
	public static String dateToString(LocalDate inDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
		return inDate.format(formatter);
	}
}
