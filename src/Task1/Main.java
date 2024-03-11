package src.Task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x;
        while ((x=scanner.nextDouble()) != 0){
            for (int i=0; i<20; i++){
                double mySin = mySin(x, i);
                double realSin = Math.sin(x);
                System.out.println(i + " " + mySin + " " + realSin + " " + (mySin-realSin));
            }
        }
    }

    /**
     * Approximation of sin(x). Approximation error theoretically should be getting lower with larger values of n, but
     * due to the rounding errors of calculations performed by computer, this might not always be the case.
     * @param x
     * @param n degree of Maclaurin polynomial approximating sin(x)
     * @return Approximation of sin(x) by Maclaurin series
     */
    public static double mySin(double x, int n){
        if (n == 0) {
            return 0;
        }
        x = shift(x);
        double result = x;
        int fact = 1;
        int sign = 1;
        double powerX = x;
        for (int i = 3; i <= n; i += 2) {
            fact = fact * (i-1) * i;
            powerX = powerX * x * x;
            sign = -sign;
            result = result + sign * powerX / fact;
        }
        return result;
    }

    /**
     * This function shifts the value of any real number x to the region [-pi/2, pi/2),
     * without changing the value of sin(x).
     * @param x
     * @return x shifted to [-pi/2, pi/2)
     */
    private static double shift(double x) {
        if (x < 0) {
            x = -x + Math.PI; // sin(x) = sin (-x + pi)
        }
        // here x>0;
        if (x >= 2*Math.PI) {
            int k = (int) (x / (2 * Math.PI));
            x = x - 2*Math.PI * k;
        }
        // here x belongs to [0, 2*pi)
        if (x >= 0.5 * Math.PI && x <  1.5 * Math.PI) {
            x = Math.PI - x;
        } else if (x >= 1.5 * Math.PI) {
            x = x - 2*Math.PI;
        }
        // here x belongs to [-pi/2, pi/2)
        return x;
    }
}
