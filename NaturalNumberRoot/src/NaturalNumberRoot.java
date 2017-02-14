import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @JakeAlvord
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        NaturalNumber tester = new NaturalNumber2(1);
        // used to test the while loop
        NaturalNumber divisor = new NaturalNumber2(2);
        // divides the guess
        NaturalNumber copy = new NaturalNumber2(n);
        // copy of n
        NaturalNumber lowEnough = new NaturalNumber2(0);
        // lower number of guess interval
        NaturalNumber tooHigh = new NaturalNumber2(n);
        // higher number of guess interval

        tooHigh.increment();

        while (!tester.isZero()) {
            NaturalNumber guess = new NaturalNumber2(tooHigh);
            // guess used to compare to the given n
            guess.add(lowEnough);
            guess.divide(divisor);
            guess.power(r);
            // sets the given guess

            n.copyFrom(copy);
            // resets n

            if (guess.compareTo(n) > 0) {

                // if the guess is bigger than n, tooHigh is set to the number

                tooHigh.add(lowEnough);
                tooHigh.divide(divisor);
            } else {

                // if the guess is smaller than n, lowEnough is set to the number

                lowEnough.add(tooHigh);
                lowEnough.divide(divisor);
            }

            tester.copyFrom(tooHigh);
            tester.subtract(lowEnough);
            tester.decrement();

            // tests if the interval is small enough

            n.copyFrom(lowEnough);

            // will eventually become the correct number for the root
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final int[] numbers = { 0, 1, 13, 1024, 189943527, 0, 1, 13, 4096,
                189943527, 0, 1, 13, 1024, 189943527, 82, 82, 82, 82, 82, 9,
                27, 81, 243, 143489073 };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15 };
        final int[] results = { 0, 1, 3, 32, 13782, 0, 1, 2, 16, 574, 0, 1, 1,
                1, 3, 9, 4, 3, 2, 1, 3, 3, 3, 3, 3 };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}