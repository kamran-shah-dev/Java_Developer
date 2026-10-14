package CollectionsFramework.List;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        // Extends vector but only supports Lifo functionalities and is thread safe

        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(5);
        stack.push(6); // at at top
        System.out.println(stack);

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);

        // We can use linkedList as stack, arrayList as stack but recommended is to use stack.
    }
}
