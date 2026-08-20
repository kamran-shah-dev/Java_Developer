package Basics.Strings;

public class StringBuildersAndStringBuffers {
    public static void main(String[] args) {
        /*
            String with the memory optimization benefit of having string pool has a drawback in scenarios where
            there are many strings to be concatenated into one. Immutability of Strings will not allow this to
            happen.
            Here come String Builder and String buffer in rescue. Both are strings but
         */

        StringBuilder newString = new StringBuilder("Hello Programming ");
        for (int i = 0; i < 500; i++) {
            newString.append(i);
        }
        System.out.println(newString);

        // if we use string here, 500 new string literals will be created in string pool, but StringBuilder solves this problem

        StringBuffer newStringBuffer = new StringBuffer("Hello Programming ");
        for (int i = 0; i < 500; i++) {
            newStringBuffer.append(i);
        }
        System.out.println(newStringBuffer);
    }
}
