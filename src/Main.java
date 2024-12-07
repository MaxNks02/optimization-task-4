
public class Main {
    public static double fBisection(double x) {
        return Math.pow(x, 3) - 6 * Math.pow(x, 2) + 11 * x - 6;
    }

    public static double bisection(double a, double b, double epsilon) {
        double c = a;
        while ((b - a) / 2 > epsilon) {
            c = (a + b) / 2;
            if (fBisection(c) == 0.0)
                break;
            else if (fBisection(a) * fBisection(c) < 0)
                b = c;
            else
                a = c;
        }
        return c;
    }

    public static double fGoldenSection(double x) {
        return Math.pow(x - 2, 2) + 3;
    }

    public static double goldenSection(double a, double b, double epsilon) {
        double phi = (1 + Math.sqrt(5)) / 2; // Golden ratio
        double resPhi = 2 - phi; // 1 / phi
        double x1 = a + resPhi * (b - a);
        double x2 = b - resPhi * (b - a);
        double f1 = fGoldenSection(x1), f2 = fGoldenSection(x2);

        while ((b - a) > epsilon) {
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + resPhi * (b - a);
                f1 = fGoldenSection(x1);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = b - resPhi * (b - a);
                f2 = fGoldenSection(x2);
            }
        }
        return (a + b) / 2;
    }


    public static double fGradientAscent(double x) {
        return -Math.pow(x, 2) + 4 * x + 1;
    }

    public static double df(double x) {
        return -2 * x + 4;
    }

    public static double gradientAscent(double x0, double alpha, int iterations) {
        double x = x0;
        for (int i = 0; i < iterations; i++) {
            x += alpha * df(x);
        }
        return x;
    }

    public static void main(String[] args) {
        double a1 = 1, b1 = 2, epsilon1 = 1e-6;
        double root = bisection(a1, b1, epsilon1);
        System.out.println("--------------- Bisection Method ----------------");
        System.out.println("Approximate root: " + root);

        double a2 = 0, b2 = 5, epsilon2 = 1e-4;
        double xmin = goldenSection(a2, b2, epsilon2);
        System.out.println("--------------- Golden Section Method ------------------");
        System.out.println("Approximate xmin: " + xmin);
        System.out.println("f(xmin): " + fGoldenSection(xmin));

        double x0 = 0, alpha = 0.1;
        int iterations = 100;
        double xmax = gradientAscent(x0, alpha, iterations);
        System.out.println("------------- Gradient Ascent Method ---------------------");
        System.out.println("Approximate xmax: " + xmax);
        System.out.println("f(xmax): " + fGradientAscent(xmax));
    }


}