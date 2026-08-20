package Basics.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

/**
 * Java String Mastery & Reference Guide
 * -------------------------------------------------------------
 * - String is a reference data type representing an immutable sequence of characters.
 * - Stored in the String Constant Pool (Heap) when created as literals.
 * - Modifying a String creates a new object rather than mutating the original in-place.
 */
public class Strings {

    public static void main(String[] args) {

        // ==========================================
        // 1. Text Blocks (Java 15+ Standard)
        // ==========================================
        System.out.println("=== 1. TEXT BLOCKS ===");

        String jsonPayload = """
                {
                    "name": "Abrar",
                    "role": "Developer"
                }
                """;

        String htmlTemplate = """
                <!DOCTYPE html>
                <html>
                    <body>
                        <h1>Java Strings Reference</h1>
                    </body>
                </html>
                """;

        System.out.println("JSON Text Block:\n" + jsonPayload);
        System.out.println("HTML Text Block:\n" + htmlTemplate);


        // ==========================================
        // 2. String Instantiation & Creation
        // ==========================================
        System.out.println("=== 2. CREATING STRINGS ===");

        String literalStr = "Java";                                     // String pool literal
        String objectStr = new String("Programming");                   // Explicit Heap allocation
        String fromCharArray = new String(new char[]{'a', 'l', 'p', 'h', 'a'}); // From char array
        String fromDouble = String.valueOf(54.6);                       // Static valueOf (primitives)
        String fromInteger = Integer.toString(55);                      // Wrapper toString

        System.out.println("Literal String: " + literalStr);
        System.out.println("Object via 'new': " + objectStr);
        System.out.println("From char[]: " + fromCharArray);
        System.out.println("From double (valueOf): " + fromDouble);
        System.out.println("From int (toString): " + fromInteger);
        System.out.println();


        // ==========================================
        // 3. String Comparison
        // ==========================================
        System.out.println("=== 3. COMPARING STRINGS ===");

        String first = "Hello";
        String second = "hello";
        String pooledLiteral = "Hello";

        // .equals() -> Value comparison (standard check)
        System.out.println("first.equals(second): " + first.equals(second)); // false

        // == -> Reference comparison (checks memory address)
        System.out.println("first == pooledLiteral: " + (first == pooledLiteral)); // true (same pool ref)
        System.out.println("first == new String(\"Hello\"): " + (first == new String("Hello"))); // false

        // .equalsIgnoreCase() -> Case-insensitive value check
        System.out.println("first.equalsIgnoreCase(second): " + first.equalsIgnoreCase(second)); // true

        // .compareTo() -> Lexicographical comparison (difference of Unicode/ASCII values)
        System.out.println("first.compareTo(second) ['H' (72) - 'h' (104)]: " + first.compareTo(second)); // -32
        System.out.println("first.compareToIgnoreCase(second): " + first.compareToIgnoreCase(second)); // 0

        // .contentEquals() -> Compare with StringBuffer / StringBuilder / CharSequence
        System.out.println("first.contentEquals(StringBuilder): " + first.contentEquals(new StringBuilder("Hello"))); // true
        System.out.println();


        // ==========================================
        // 4. Searching & Inspecting
        // ==========================================
        System.out.println("=== 4. SEARCHING STRINGS ===");

        String phrase = "Programming in Java is fun.";

        System.out.println("Contains 'fun': " + phrase.contains("fun"));
        System.out.println("Index of 'Java': " + phrase.indexOf("Java"));
        System.out.println("Index of 'in' from index 15: " + phrase.indexOf("in", 15));
        System.out.println("Last index of 'n': " + phrase.lastIndexOf("n"));
        System.out.println("Starts with 'Program': " + phrase.startsWith("Program"));
        System.out.println("Ends with 'fun.': " + phrase.endsWith("fun."));
        System.out.println("Regex matches pattern: " + phrase.matches("Programming.*")); // true
        System.out.println();


        // ==========================================
        // 5. Extraction & Slicing
        // ==========================================
        System.out.println("=== 5. EXTRACTION & SUBSTRINGS ===");

        String sampleSource = "Hello, do you know about Java programming?";

        char charAtIndex = sampleSource.charAt(7); // 'd'
        String subFromIndex = sampleSource.substring(7); // From index 7 to end
        String subRange = sampleSource.substring(25, 29); // Start index (inclusive) to End index (exclusive)
        char[] characters = sampleSource.toCharArray();

        System.out.println("Character at index 7: '" + charAtIndex + "'");
        System.out.println("Substring from index 7: \"" + subFromIndex + "\"");
        System.out.println("Substring (25 to 29): \"" + subRange + "\"");
        System.out.println("Character array length: " + characters.length);
        System.out.println();


        // ==========================================
        // 6. Case Manipulation & Whitespace Stripping
        // ==========================================
        System.out.println("=== 6. CASE & WHITESPACE ===");

        String rawWhitespaceText = "   \t  Java Clean Code \u2005  \n";

        System.out.println("Upper Case: " + rawWhitespaceText.toUpperCase());
        System.out.println("Lower Case: " + rawWhitespaceText.toLowerCase());
        System.out.println("Upper Case (Root Locale): " + rawWhitespaceText.toUpperCase(Locale.ROOT));

        // trim() (ASCII <= ' ' only) vs strip() (Full Unicode whitespace aware, Java 11+)
        System.out.println("trim(): [" + rawWhitespaceText.trim() + "]");
        System.out.println("strip(): [" + rawWhitespaceText.strip() + "]");
        System.out.println("stripLeading(): [" + rawWhitespaceText.stripLeading() + "]");
        System.out.println("stripTrailing(): [" + rawWhitespaceText.stripTrailing() + "]");
        System.out.println();


        // ==========================================
        // 7. Content & Emptiness Verification
        // ==========================================
        System.out.println("=== 7. EMPTINESS & LENGTH ===");

        String emptyString = "";
        String blankString = "   \t  ";

        System.out.println("\"\".isEmpty() (length == 0): " + emptyString.isEmpty()); // true
        System.out.println("\"   \".isEmpty(): " + blankString.isEmpty()); // false (contains whitespace characters)
        System.out.println("\"   \".isBlank() (all whitespace, Java 11+): " + blankString.isBlank()); // true
        System.out.println("Blank string length: " + blankString.length());
        System.out.println();


        // ==========================================
        // 8. Replacement Operations
        // ==========================================
        System.out.println("=== 8. REPLACING CONTENT ===");

        String originalSentence = "Java is fast. java is powerful. Java is versatile.";

        // Literal character/sequence replace (replaces all matches)
        String replacedChars = originalSentence.replace('a', 'A');
        String replacedWord = originalSentence.replace("Java", "Modern Java");

        // Regex-based replacements
        String replaceAllRegex = originalSentence.replaceAll("(?i)java", "JVM"); // Case-insensitive regex
        String replaceFirstOccurrence = originalSentence.replaceFirst("Java", "Kotlin");

        System.out.println("Literal Char Replace: " + replacedChars);
        System.out.println("Literal String Replace: " + replacedWord);
        System.out.println("replaceAll (Regex): " + replaceAllRegex);
        System.out.println("replaceFirst (Regex): " + replaceFirstOccurrence);
        System.out.println();


        // ==========================================
        // 9. Splitting Strings
        // ==========================================
        System.out.println("=== 9. SPLITTING STRINGS ===");

        String csvData = "alpha,beta,,gamma,delta,";

        String[] standardSplit = csvData.split(",");
        String[] keepTrailingEmpties = csvData.split(",", -1); // Limit < 0 keeps trailing empty tokens
        String[] multiDelimiterSplit = "apple,,banana,,,orange".split(",+"); // One or more commas
        String[] limitedSplit = "first,second,third,fourth".split(",", 2); // Max 2 chunks
        String[] regexPipeSplit = "one|two|three".split("\\|"); // Escaping regex meta-character
        String[] patternQuoteSplit = "one|two|three".split(Pattern.quote("|")); // Safe dynamic delimiter

        System.out.println("Standard split: " + Arrays.toString(standardSplit));
        System.out.println("Keep trailing empty elements (limit = -1): " + Arrays.toString(keepTrailingEmpties));
        System.out.println("Regex multiple commas split (,+): " + Arrays.toString(multiDelimiterSplit));
        System.out.println("Limit split (limit = 2): " + Arrays.toString(limitedSplit));
        System.out.println("Escaped Pipe split: " + Arrays.toString(regexPipeSplit));
        System.out.println("Pattern.quote split: " + Arrays.toString(patternQuoteSplit));
        System.out.println();


        // ==========================================
        // 10. Joining Strings
        // ==========================================
        System.out.println("=== 10. JOINING STRINGS ===");

        // String.join() (Varargs and Iterable)
        String joinedVarargs = String.join(", ", "Alpha", "Beta", "Gamma");
        String joinedList = String.join(" -> ", List.of("Step 1", "Step 2", "Step 3"));

        // StringJoiner (Prefix, Suffix, and Delimiter support)
        StringJoiner formattedJsonArray = new StringJoiner(", ", "[", "]");
        formattedJsonArray.add("101").add("102").add("103");

        System.out.println("String.join (Varargs): " + joinedVarargs);
        System.out.println("String.join (List): " + joinedList);
        System.out.println("StringJoiner (with prefix & suffix): " + formattedJsonArray);
        System.out.println();


        // ==========================================
        // 11. String Interning (Pool Management)
        // ==========================================
        System.out.println("=== 11. STRING INTERNING ===");

        String heapConstructed = new String("Immutable Core");
        String internedReference = heapConstructed.intern(); // Moves/matches reference in String Constant Pool
        String directLiteral = "Immutable Core";

        System.out.println("heapConstructed == directLiteral: " + (heapConstructed == directLiteral)); // false
        System.out.println("internedReference == directLiteral: " + (internedReference == directLiteral)); // true
        System.out.println();


        // ==========================================
        // 12. Type Conversions to String
        // ==========================================
        System.out.println("=== 12. CONVERSIONS TO STRING ===");

        String doubleStr = Double.toString(55.6);
        String intStr = Integer.toString(67);
        String floatStr = Float.toString(67.89f);
        String charStr = Character.toString('C');
        String shortStr = Short.toString((short) 55);
        String byteStr = Byte.toString((byte) 1);
        String longStr = Long.toString(54902847238L);
        String boolStr = Boolean.toString(true);
        String safeNullStr = Objects.toString(null, "default_fallback");

        System.out.println("Double: " + doubleStr);
        System.out.println("Integer: " + intStr);
        System.out.println("Float: " + floatStr);
        System.out.println("Character: " + charStr);
        System.out.println("Short: " + shortStr);
        System.out.println("Byte: " + byteStr);
        System.out.println("Long: " + longStr);
        System.out.println("Boolean: " + boolStr);
        System.out.println("Objects.toString (Null-safe): " + safeNullStr);
    }
}