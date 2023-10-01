import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        double[] coefficients1 = {1, 2, -5};
        int[] exponents1 = {0, 2, 4};
        double[] coefficients2 = {-1, 5, 6, 7, -5};
        int[] exponents2 = {2, 3, 8, 6, 7};

        double[] coefficients3 = {};
        int[] exponents3 = {};

        Polynomial polynomial1 = new Polynomial(coefficients1, exponents1);
        Polynomial polynomial2 = new Polynomial(coefficients2, exponents2);
        Polynomial polynomial3 = new Polynomial(coefficients3, exponents3);

        Polynomial sum12 = polynomial1.add(polynomial2);
        Polynomial product12 = polynomial1.multiply(polynomial2);
        System.out.println("polynomial1: " + polynomial1);
        System.out.println("polynomial2: " + polynomial2);
        System.out.println("Sum12: " + sum12);
        System.out.println("Product12: " + product12);

        Polynomial sum13 = polynomial1.add(polynomial3);
        Polynomial product13 = polynomial1.multiply(polynomial3);
        System.out.println("Sum13: " + sum13);
        System.out.println("Product13: " + product13);

        System.out.println("Evaluate polynomial1 at x=-1: " + polynomial1.evaluate(-0.99));
        System.out.println("Evaluate polynomial2 at x=2: " + polynomial2.evaluate(1.2));
        double rootValue = 2.1;
        System.out.println("Poly1 has root at x=" + rootValue + ": " + polynomial1.hasRoot(rootValue));  

        double[] coefficients = {1.0, -2.5, 3.0, 3};
        int[] exponents = {0, 1, 7, 724};
        Polynomial polynomial = new Polynomial(coefficients, exponents);
        polynomial.saveToFile("polynomial1.txt");
        System.out.println("polynomial1.txt saved");
        File inputFile = new File("polynomial1.txt");
        Polynomial readFile = new Polynomial(inputFile);
		System.out.println("Polynomial reads from file: " + readFile);
    }
}