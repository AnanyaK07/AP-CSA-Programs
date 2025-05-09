import java.util.List;
import java.util.ArrayList;

/**
 * This program is a simple calculator that evaluates arithmetic expressions entered 
 * by the user. It supports basic operations such as addition, subtraction, multiplication, 
 * division, modulus, and exponentiation. The implementation uses stacks to handle operator
 * precedence and parentheses correctly. Additionally, the calculator allows users to define
 * and store variables, supporting assignments and retrieval of values.
 *
 *	@author	Ananya Kotla
 *	@since	February 28, 2024
 */

public class SimpleCalc {
    private ExprUtils utils; // expression utilities
    private ArrayStack<Double> valueStack;	// expression utilities
    private ArrayStack<String> operatorStack;	// operator stack
    private List<Identifier> variables;

	// constructor
    public SimpleCalc() {
        utils = new ExprUtils();
        valueStack = new ArrayStack<>();
        operatorStack = new ArrayStack<>();
        variables = new ArrayList<Identifier>();
        variables.add(new Identifier("e", Math.E));
        variables.add(new Identifier("pi", Math.PI));
    }

    public static void main(String[] args) {
        SimpleCalc sc = new SimpleCalc();
        sc.run();
    }

    public void run() {
        System.out.println("\nWelcome to SimpleCalc!!!\n");
        runCalc();
        System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
    }

	/**    Prompt the user for expressions, run the expression evaluator,
     *    and display the answer.
     */
    public void runCalc() {
        boolean continueOn = true;
        while (continueOn) {
            String userInput = Prompt.getString("");
            if (userInput.equalsIgnoreCase("H"))
                printHelp();
            else if (userInput.equalsIgnoreCase("Q"))
                continueOn = false;
            else if (userInput.equalsIgnoreCase("L"))
                listVariables();
            else {
                List<String> tokens = utils.tokenizeExpression(userInput);
                if (tokens.size() > 2 && tokens.get(1).equals("=")) 
                    assignVariable(tokens);
                else {
                    double value = evaluateExpression(tokens);
                    System.out.println(value);
                }
            }
        }
    }

	/**    Print help */
    public void printHelp() {
        System.out.println("Help:");
        System.out.println("  h - this message\n  q - quit\n");
        System.out.println("Expressions can contain:");
        System.out.println("  integers or decimal numbers");
        System.out.println("  arithmetic operators +, -, *, /, %, ^");
        System.out.println("  parentheses '(' and ')'\n");
    }

    /**  List all identifiers/variables */
    public void listVariables() {
        System.out.println("Identifiers:");
        for (Identifier id : variables) 
            System.out.println("  " + id.getName() + " = " + id.getValue());
    }

    /** Assign a variable with an expression
     * @param tokens    a List of String tokens making up an arithmetic expression
     */
    public void assignVariable(List<String> tokens) {
        String varName = tokens.get(0);
        List<String> exprTokens = tokens.subList(2, tokens.size());
        double value = evaluateExpression(exprTokens);
        
        // Check if variable already exists
        Identifier var = findVariable(varName);
        if (var == null) 
            variables.add(new Identifier(varName, value));
        else
            var.setValue(value);
        System.out.println( "  " + varName + " = " + value);
    }

    /** Find variable by name
     * @return  the var if it already exits, otherwise null
     */
    private Identifier findVariable(String name) {
        for (Identifier id : variables)
        {
            if (id.getName().equals(name)) 
                return id;
        }
        return null;
    }

	/**
     *    Evaluate expression and return the value
     *    @param tokens    a List of String tokens making up an arithmetic expression
     *    @return            a double value of the evaluated expression
     */
    public double evaluateExpression(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            char c = token.charAt(0);

            if (Character.isDigit(c)) 
                valueStack.push(Double.parseDouble(token));
            else if (token.equals("(")) 
                operatorStack.push(token);
            else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    String op = operatorStack.pop();
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    if (op.equals("+")) valueStack.push(a + b);
                    else if (op.equals("-")) valueStack.push(a - b);
                    else if (op.equals("*")) valueStack.push(a * b);
                    else if (op.equals("/")) valueStack.push(a / b);
                    else if (op.equals("%")) valueStack.push(a % b);
                    else if (op.equals("^")) valueStack.push(Math.pow(a, b));
                }
                operatorStack.pop(); 
            } 
            else if (utils.isOperator(c)) {
                while (!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek())) {
                    String op = operatorStack.pop();
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    if (op.equals("+")) valueStack.push(a + b);
                    else if (op.equals("-")) valueStack.push(a - b);
                    else if (op.equals("*")) valueStack.push(a * b);
                    else if (op.equals("/")) valueStack.push(a / b);
                    else if (op.equals("%")) valueStack.push(a % b);
                    else if (op.equals("^")) valueStack.push(Math.pow(a, b));
                }
                operatorStack.push(token);
            } 
            else
            {
                Identifier var = findVariable(token);
                if (var != null) 
                    valueStack.push(var.getValue());
                else 
                    valueStack.push(0.0);  
            }
        }
        while (!operatorStack.isEmpty()) {
            String op = operatorStack.pop();
            double b = valueStack.pop();
            double a = valueStack.pop();
            if (op.equals("+")) valueStack.push(a + b);
            else if (op.equals("-")) valueStack.push(a - b);
            else if (op.equals("*")) valueStack.push(a * b);
            else if (op.equals("/")) valueStack.push(a / b);
            else if (op.equals("%")) valueStack.push(a % b);
            else if (op.equals("^")) valueStack.push(Math.pow(a, b));
        }
        return valueStack.pop();
    }

	/**
     *    Precedence of operators
     *    @param op1    operator 1
     *    @param op2    operator 2
     *    @return        true if op2 has higher or same precedence as op1; false otherwise
     *    Algorithm:
     *        if op1 is exponent, then false
     *        if op2 is either left or right parenthesis, then false
     *        if op1 is multiplication or division or modulus and 
     *                op2 is addition or subtraction, then false
     *        otherwise true
     */
    private boolean hasPrecedence(String op1, String op2) {
        if (op2.equals("(") || op2.equals(")")) return false;
        if (op1.equals("^") && !op2.equals("^")) return false;
        if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) && 
            (op2.equals("+") || op2.equals("-"))) return false;
        return true;
    }
}
