
package oop.week11.assignment;

import java.util.Stack;

public class P3_QuizScoringFormula {

    static int precedence(char op) {
        if (op == '*' || op == '/') {
            return 2;
        }
        return 1;
    }

    static void calculate(Stack<Integer> values, Stack<Character> operators) {
        int b = values.pop();
        int a = values.pop();
        char op = operators.pop();

        if (op == '+') {
            values.push(a + b);
        }
        else if (op == '-') {
            values.push(a - b);
        }
        else if (op == '*') {
            values.push(a * b);
        }
        else if (op == '/') {
            values.push(a / b);
        }
    }

    public static int evaluate(String expr) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;

        while (i < expr.length()) {
            char ch = expr.charAt(i);

            if (ch == ' ') {
                i++;
            }
            else if (Character.isDigit(ch)) {
                int number = 0;

                while (i < expr.length() &&
                       Character.isDigit(expr.charAt(i))) {
                    number = number * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                values.push(number);
            }
            else if (ch == '(') {
                operators.push(ch);
                i++;
            }
            else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    calculate(values, operators);
                }

                operators.pop();
                i++;
            }
            else {
                while (!operators.isEmpty() &&
                       operators.peek() != '(' &&
                       precedence(operators.peek()) >= precedence(ch)) {
                    calculate(values, operators);
                }

                operators.push(ch);
                i++;
            }
        }

        while (!operators.isEmpty()) {
            calculate(values, operators);
        }

        return values.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));
    }
}
