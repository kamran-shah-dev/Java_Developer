package OOP.InnterClasses;

public class MethodLocalInnerClass {
    public static void MethodLocalInnerClassDemo (String first, String last) {
        class inner {
            private String firstMessage;
            private String lastMessage;

            public inner(String fM, String lM) {
                this.firstMessage = fM;
                this.lastMessage = lM;
            }
        }
        inner obj = new inner(first, last);
        System.out.println(obj.firstMessage + " " + obj.lastMessage);
    }

    public static void main(String[] args) {
        MethodLocalInnerClassDemo("hello" , "world");
    }
}
