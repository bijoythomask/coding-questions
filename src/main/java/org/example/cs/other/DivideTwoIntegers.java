package org.example.cs.other;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
 */
public class DivideTwoIntegers {

        public static int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
            long dvd = Math.abs((long) dividend);
            long dvs = Math.abs((long) divisor);
            int result = 0;
            while (dvd >= dvs) {
                long temp = dvs;
                long multiple = 1;
                while (dvd >= (temp << 1)) {
                    temp <<= 1;
                    multiple <<= 1;
                }
                dvd -= temp;
                result += multiple;
            }
            return sign == 1 ? result : -result;
        }

        public static void main(String[] args) {
            System.out.println(divide(10, 3));
        }
}
