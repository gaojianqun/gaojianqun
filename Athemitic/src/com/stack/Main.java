package com.stack;

public class Main {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push("天才");
        stack.push("a");
        stack.push("am");
        stack.push("I");

        for(int i=0;i< 10;i++){
            System.out.print(stack.pop()+" ");
        }

        System.out.println();

        LinkedStack stack1 = new LinkedStack();
        stack1.push("天才");
        stack1.push("a");
        stack1.push("am");
        stack1.push("I");

        for(int i=0;i< 10;i++){
            System.out.print(stack1.pop()+" ");
        }

    }
}
