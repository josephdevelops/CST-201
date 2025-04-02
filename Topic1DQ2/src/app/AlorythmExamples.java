package app;

public class AlorythmExamples {

    public static void findQuadraticRoots(double a, double b, double c) {
        if (a == 0) { 
            // If a is 0, then it's not a quadratic equation.
            if (b != 0) {
                double root = -c / b;
                System.out.println("Not a quadratic equation. Linear root: " + root);
            } else {
                System.out.println("Invalid equation. a and b are both zero.");
            }
            return;
        }
        
        // Calculate the discriminant.
        double discriminant = b * b - 4 * a * c;
        
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Two distinct real roots: " + root1 + " and " + root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println("One repeated real root: " + root);
        } else {
            System.out.println("No real roots (discriminant < 0).");
            }
    }
    
    public static String convertToBinary(int A) {
        if (A == 0) {
            return "0";
        }
        
        StringBuilder binaryRepresentation = new StringBuilder();
        while (A > 0) {
            int remainder = A % 2;
            // Prepend the remainder (0 or 1) to the binary representation.
            binaryRepresentation.insert(0, remainder);
            A = A / 2;
        }
        return binaryRepresentation.toString();
    }
    
    public static void main(String[] args) {
        // Example 1: Finding Real Roots of a Quadratic Equation
        System.out.println("Quadratic Equation Example:");
        double a = 1;
        double b = -3;
        double c = 2;
        // Equation: x^2 - 3x + 2 = 0 (Roots should be 1 and 2)
        findQuadraticRoots(a, b, c);
        
        System.out.println();
        
        // Example 2: Converting a Decimal Integer to Binary
        System.out.println("Binary Conversion Example:");
        int decimalNumber = 13;
        // The binary representation of 13 should be "1101".
        String binaryResult = convertToBinary(decimalNumber);
        System.out.println("The binary representation of " + decimalNumber + " is: " + binaryResult);

    }
}