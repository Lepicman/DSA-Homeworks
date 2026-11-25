// ---------------------------------
// Symbolic and Numerical Calculator
// ---------------------------------

import java.util.*;

public class HW2_Calculator {
    // Is it operator or operand.
    public static String what(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")
                || s.equals("^") || s.equals("(") || s.equals(")"))
            return "opt";
        else
            return "op";
    }

    public static boolean hasPrecedence(String s1, String s2) {
        int n1 = 0, n2 = 0;
        switch (s1) {
            case "^" -> n1 = 3;
            case "*", "/" -> n1 = 2;
            case "+", "-" -> n1 = 1;
        }
        switch (s2) {
            case "^" -> n2 = 3;
            case "*", "/" -> n2 = 2;
            case "+", "-" -> n2 = 1;
        }

        if (s2.equals("("))
            return true;

        // Because a second ^ has precedence over the first one.
        return n1 > n2 || s1.equals("^");
    }

    public static double calculate(Double op1, String opt, Double op2) {
        switch (opt) {
            case "^" -> { return Math.pow(op1, op2); }
            case "*" -> { return op1 * op2; }
            case "/" -> { return op1 / op2; }
            case "+" -> { return op1 + op2; }
            case "-" -> { return op1 - op2; }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Sybolic or Numerical? (S/N): ");
        String type = input.nextLine();
        System.out.println("Enter experssion: ");
        String str = input.nextLine();
        String[] s = str.split(" ");

        // Part 1 - Infix to Postfix
        Stack<String> op = new Stack<>();
        Stack<String> opt = new Stack<>();

        for (String x: s) {
            if (what(x).equals("op"))
                op.push(x);

            else if (x.equals("("))
                opt.push(x);

            else if (x.equals(")")) {
                while (!opt.peek().equals("(")) {
                    String op2 = op.pop(), op1 = op.pop(), optS = opt.pop();
                    op.push(op1 + " " + op2 + " " + optS);
                }
                opt.pop();
            }

            else {
                while (!opt.isEmpty() && !hasPrecedence(x, opt.peek())) {
                    String op2 = op.pop(), op1 = op.pop(), optS = opt.pop();
                    op.push(op1 + " " + op2 + " " + optS);
                }
                opt.push(x);
            }
        }

        while (!opt.isEmpty()) {
            String op2 = op.pop(), op1 = op.pop(), optS = opt.pop();
            op.push(op1 + " " + op2 + " " + optS);
        }

        String PostFix = op.pop();
        System.out.println("The Postfix expression: \n" + PostFix);

        if (type.equals("S"))
            return;

        // Now, the if Statement passed, so the expression is Numerical and should be calculated.
        s = PostFix.split(" ");
        Stack<Double> St = new Stack<>();

        for (String x: s) {
            if (what(x).equals("op")) {
                try {
                    St.push(Double.parseDouble(x));
                }
                catch (Exception e) {
                    return;
                }
            }
            else {
                double op2 = St.pop(), op1 = St.pop();
                St.push(calculate(op1, x, op2));
            }
        }

        System.out.println("The result: " + St.pop());
    }
}