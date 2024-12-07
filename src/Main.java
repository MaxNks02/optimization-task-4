public class Main {

    // Function definition for the Bisection Method
    public static double fBisection(double x) {
        // The function to find the root for: f(x) = x^3 - 6x^2 + 11x - 6
        return Math.pow(x, 3) - 6 * Math.pow(x, 2) + 11 * x - 6;
    }

    // Implementation of the Bisection Method
    public static double bisection(double a, double b, double epsilon) {
        double c = a; // Initial midpoint
        // Iterate until the interval is smaller than the tolerance epsilon
        while ((b - a) / 2 > epsilon) {
            c = (a + b) / 2; // Compute midpoint
            if (fBisection(c) == 0.0) // Check if the midpoint is the root
                break;
            else if (fBisection(a) * fBisection(c) < 0) // Root lies in [a, c]
                b = c;
            else // Root lies in [c, b]
                a = c;
        }
        return c; // Return the approximate root
    }

    // Function definition for the Golden Section Method
    public static double fGoldenSection(double x) {
        // The function to find the minimum for: f(x) = (x - 2)^2 + 3
        return Math.pow(x - 2, 2) + 3;
    }

    // Implementation of the Golden Section Method
    public static double goldenSection(double a, double b, double epsilon) {
        double phi = (1 + Math.sqrt(5)) / 2; // The golden ratio
        double resPhi = 2 - phi; // Reciprocal of the golden ratio
        double x1 = a + resPhi * (b - a); // First test point
        double x2 = b - resPhi * (b - a); // Second test point
        double f1 = fGoldenSection(x1), f2 = fGoldenSection(x2); // Function values at test points

        // Iterate until the interval length is smaller than epsilon
        while ((b - a) > epsilon) {
            if (f1 < f2) { // Update interval to [a, x2]
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + resPhi * (b - a); // Recalculate x1
                f1 = fGoldenSection(x1); // Recalculate f(x1)
            } else { // Update interval to [x1, b]
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = b - resPhi * (b - a); // Recalculate x2
                f2 = fGoldenSection(x2); // Recalculate f(x2)
            }
        }
        return (a + b) / 2; // Return the approximate minimum point
    }

    // Function definition for the Gradient Ascent Method
    public static double fGradientAscent(double x) {
        // The function to maximize: f(x) = -x^2 + 4x + 1
        return -Math.pow(x, 2) + 4 * x + 1;
    }

    // Derivative of the function f(x) for the Gradient Ascent Method
    public static double df(double x) {
        // Derivative: f'(x) = -2x + 4
        return -2 * x + 4;
    }

    // Implementation of the Gradient Ascent Method
    public static double gradientAscent(double x0, double alpha, int iterations) {
        double x = x0; // Initialize the starting point
        // Perform the specified number of iterations
        for (int i = 0; i < iterations; i++) {
            x += alpha * df(x); // Update x using the gradient and learning rate
        }
        return x; // Return the approximate maximum point
    }

    public static void main(String[] args) {
        // Test the Bisection Method
        double a1 = 1, b1 = 2, epsilon1 = 1e-6; // Interval and tolerance
        double root = bisection(a1, b1, epsilon1); // Find the root
        System.out.println("--------------- Bisection Method ----------------");
        System.out.println("Approximate root: " + root);

        // Test the Golden Section Method
        double a2 = 0, b2 = 5, epsilon2 = 1e-4; // Interval and tolerance
        double xmin = goldenSection(a2, b2, epsilon2); // Find the minimum
        System.out.println("--------------- Golden Section Method ------------------");
        System.out.println("Approximate xmin: " + xmin);
        System.out.println("f(xmin): " + fGoldenSection(xmin));

        // Test the Gradient Ascent Method
        double x0 = 0, alpha = 0.1; // Initial guess and learning rate
        int iterations = 100; // Number of iterations
        double xmax = gradientAscent(x0, alpha, iterations); // Find the maximum
        System.out.println("------------- Gradient Ascent Method ---------------------");
        System.out.println("Approximate xmax: " + xmax);
        System.out.println("f(xmax): " + fGradientAscent(xmax));
    }
}
