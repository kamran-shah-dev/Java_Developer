package Basics.Strings;

import java.util.*;
import java.util.regex.Pattern;

public class Strings {
    public static void main(String[] args) {
        /*
            String is a reference data type which is a sequence of immutable characters. Unlike primitive data types
            String is a full defined class that comes from java.lang package. It's packed with it's built in functions
            Strings are immutable, if a string is created, it's kept in a special memory location in heap which is called,
            String Constant Pool, changing the string value will create a new string literal in the pool and the
            Object remains unmodified.
         */

        // For long texts we used to format and concatenate, but now with text blocks we are good to create
        // long text strings
        String jsonFile = """
                {
                    "name" : "Abrar"
                }
                """;

        String htmlPage = """
                <!DOCTYPE html>
                <html>
                    <head>
                    </head>
                    <body>
                        <div> This is first div </div>
                    </body>
                </html>
                """;

        System.out.println(jsonFile);
        System.out.println();
        // Creating Strings
        String text = "Java"; // From literals
        String text1 = new String("Programming"); // Using new keyword
        String text2 = new String(new char[] {'a' , 'l' , 'p' , 'h' , 'a'}); // from a character array
        String text3 = String.valueOf(54.6);  // from any primitive type (int, float, double etc.)
        String text5 = Integer.toString(55); // from integers

        // Comparing Strings
        String a = "Hello";
        String b = "hello";

        System.out.println(a.equals(b)); // Compares values (standard way)
        System.out.println(a == b); // Not to be used - Compares literals (only true if both string objects point to same literal
                                    // string pool) so if two strings with same literal are created, they both point to single memory location)
        System.out.println(a.equalsIgnoreCase(b)); // Ignoring case of characters

        System.out.println(a.compareTo(b)); // compares both strings character by character
        /*
            if the first character of both strings after comparison were not same then Ascii of both will be compared
            it will return difference of their ascii, otherwise it will check for second character of both strings
            and so on until length of smallest string reaches.
            Here H != h (false in case of compareToIgnoreCase(), they are not same -> returns (72 - 104)
        */
        System.out.println(a.compareToIgnoreCase(b)); // Returns 0, because case is ignored, all characters will be compared of both strings and
                                                     // finally it will return length1 - length2

        System.out.println(a.contentEquals(new StringBuilder("Hello"))); // Compare with StringBuilder and StringBuffer


        // Searching Inside Strings
        String sentence = "Programming is fun.";
        System.out.println(sentence.contains("fun"));
        System.out.println(sentence.indexOf("fun"));
        System.out.println(sentence.indexOf("fun" , 4)); // starting from index 4
        System.out.println(sentence.lastIndexOf("n"));
        System.out.println(sentence.startsWith("Programming"));
        System.out.println(sentence.endsWith("fun."));
        System.out.println(sentence.matches("Programming.*")); // true - matches whole string


        // Extraction from strings and substrings
        String newStringText = "Hello do you know about java programming?";
        char character = newStringText.charAt(6); // get's the character at index 6 -> 'd'
        String substring = newStringText.substring(9); // from index 9 to end
        String newSubstring = newStringText.substring(13 , 27); // Start index , end index
        char[] characterArray = newStringText.toCharArray(); // Converts string to character Array


        // Changing Case and trimming
        String newStringText1 = "   Do you know Java    ";
        String upperCase = newStringText1.toUpperCase();
        String lowerCase = newStringText1.toLowerCase();
        String upperCaseWithLocale = newStringText1.toUpperCase(Locale.ROOT); // Avoids language conflicts

        String trimmed = newStringText1.trim(); // Old way, removes ascii whitespaces only
        String striped = newStringText1.strip(); // new way, Unicode Aware

        String removeLeading = newStringText1.stripLeading();
        String removeTrailing = newStringText1.stripTrailing();
        String removeIndent = newStringText1.stripIndent();


        // Checking content
        String c = "";
        System.out.println(c.isEmpty()); // true

        System.out.println("   ".isEmpty()); // false - spaces are characters
        System.out.println("  ".isBlank()); // true - has character but all are spaces (Blank) (java 11+)
        System.out.println(c.length()); // checking size of string


        // Replacing content
        String d = "This is a new string , strings are text in java,  this is a new content";
        String changedD = d.replace('i' , 'I'); // Replace i with I (character replacement)
        String updatedD = d.replace("string" , "String"); // Replace word with word
        String updatedWithReplaceAll = d.replaceAll("string" , "String"); // Regex based - replace all occurrence
        String updateWithReplaceFirst = d.replaceFirst("This" , "These"); // Regex based - replace first occurrence


        // Splitting strings (regex)
        String[] splitted = "a,b,c,d".split(","); // Splitting strings will result in a string array
        String[] splitted1 = "a,,b,c,,d,".split(",");
        String[] splitted2 = "a,b,,c,d,".split(",", -1); // keep trailing empties
        String[] splitted3 = "a,,b,c,,,d".split(",+"); // Split based on one or more ","
        String[] splitted4 = "a,b,c,d".split("," , 2); // Will split in two parts only
        String[] splitted5 = "a|b|c|d".split("\\|"); // Split based on special character
        String[] splitted6 = "a|b|c|d".split(Pattern.quote("|")); // This method is safe for dynamic delimeters

        // Joining Strings
        String joined = String.join("," , "a" , "b" , "C" , "d"); // {a,b,C,d}
        String joined1 = String.join("-" , List.of("a" , "b" , "C"));  // {a-b-c}

        StringJoiner stringJoiner = new StringJoiner("," , "{" , "}");
        stringJoiner.add("a");
        stringJoiner.add("b");
        stringJoiner.add("c");

        System.out.println(stringJoiner); // {a-b-c}


        // The intern method
        String newString = new String("Alpha is Not Beta").intern();
        // We know that with new keyword, a brand new string will be created and it will not by in the string pool
        // to force add it to string pool we use the intern() method of string



        // Converting other types to String
        String doubleToString = Double.toString(55.6);
        String inttoString = Integer.toString(67);
        String floattoString = Float.toString(67.89f);
        String charToString = Character.toString('C');
        String shortToString = Short.toString((short) 55);
        String byteToString = Byte.toString((byte) 1);
        String longToString = Long.toString(54902847238L);
        String booleanToString = Boolean.toString(true);
        String objectToString = Objects.toString(null);

    }
}
