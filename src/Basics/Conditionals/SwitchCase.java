package Basics.Conditionals;

public class SwitchCase {
    public static void main(String[] args) {
        String person = "ADMIN";
        switch (person) {
            case "ADMIN" -> System.out.println("Full Access (Reaad, Write, Update, Delete");
            case "AUTHOR" , "EDITOR" -> System.out.println("Write Access");
            case "VIEWER" -> System.out.println("Read Access");
            default -> System.out.println("Read Access To All Users");
        }

        int statusCode = 500;
        String status = switch (statusCode) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 500 -> {
                System.out.println("Logging Status");
                yield "Internal Server Error";
            }
            default -> "Temporary or Permaanent Redirect";
        };
        System.out.println(status);

        String fruit = "BANANA";
        String furitCoolor = switch (fruit) {
            case "MANGO" , "BANANA" , "LEMON" , "PINEAPPLE" -> "Yello";
            case "AVOCADO" , "KIWI" -> "Green";
            case "CHERRY" , "APPLE" , "WATERMELON"-> "RED";
            default -> "Invalid Fruit Name";
        };

        Object data = 150;
        switch (data) {
            case null -> System.out.println("Null Value");
            case Integer i when i > 100 -> System.out.println("Large Int");
            case Integer i -> System.out.println("Normal Int");
            case Double d -> System.out.println("Double Value");
            case Float f -> System.out.println("Float Value");
            case String s -> System.out.println("String Value (Text)");
            default -> System.out.println("Any Data value");
        }

    }
}
