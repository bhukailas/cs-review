package com.mmnaseri.cs.clrs.ch15.s2;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 5:42 AM)
 */
public class MatrixParenthesization {

    private final Map<Integer, Map<Integer, SplitSpecification>> splits = new HashMap<>();

    public MatrixParenthesization() {
    }

    public void note(int i, int j, SplitSpecification specification) {
        if (!splits.containsKey(i)) {
            splits.put(i, new HashMap<Integer, SplitSpecification>());
        }
        splits.get(i).put(j, specification);
    }

    public SplitSpecification get(int i, int j) {
        if (!splits.containsKey(i)) {
            return null;
        }
        return splits.get(i).get(j);
    }

    public void print(PrintStream out) {
        final int digits = (int) Math.ceil(Math.log10(get(1, splits.size()).getOperations()));
        for (int i = splits.size(); i > 0; i --) {
            //left padding
            for (int j = 0; j < i - 1; j ++) {
                out.print(pad(digits, ""));
            }
            for (int j = 1, k = i; j <= splits.size() - i + 1; j ++, k ++) {
                if (j > 1) {
                    out.print(pad(digits, ""));
                }
                out.print(pad(digits, get(j, k).getOperations()));
            }
            out.println();
        }
    }

    public void print() {
        print(System.out);
    }

    private void sample(PrintStream out, int i, int j) {
        if (i == j) {
            out.print("A" + i);
        } else {
            out.print("(");
            sample(out, i, get(i, j).getSplit());
            if (j - i == 1) {
                out.print(".");
            }
            sample(out, get(i, j).getSplit() + 1, j);
            out.print(")");
        }
    }

    public void sample(PrintStream out) {
        sample(out, 1, splits.size());
        out.println();
    }

    public void sample() {
        sample(System.out);
    }

    private String pad(int digits, String source) {
        String string = source;
        boolean left = false;
        while (string.length() < digits) {
            if (left) {
                string = " " + string;
                left = false;
            } else {
                string = string + " ";
                left = true;
            }
        }
        return string;
    }

    private String pad(int digits, int number) {
        return pad(digits, Integer.toString(number));
    }

}
