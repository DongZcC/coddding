package com.dzc.learn.letcode;

import java.util.*;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
 * number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * <p>
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * <p>
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries
 * , where equations.size() == values.size(), and the values are positive. This represents the equations. Return
 * vector<double>.
 * <p>
 * According to the example above:
 * <p>
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 */
public class EvaluateDivision {

    class Pair {
        String k;
        Double v;

        public Pair(String k, Double v) {
            this.k = k;
            this.v = v;
        }
    }

    Map<String, List<Pair>> map = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        double[] results = new double[queries.size()];

        // 初始化距离.
        for (int i = 0; i < equations.size(); i++) {
            String k1 = equations.get(i).get(0);
            String k2 = equations.get(i).get(1);
            if (map.get(k1) == null) {
                map.put(k1, new ArrayList<>());
            }

            if (map.get(k2) == null) {
                map.put(k2, new ArrayList<>());
            }

            map.get(k1).add(new Pair(k2, values[i]));
            map.get(k2).add(new Pair(k1, 1 / values[i]));
        }

        for (int i = 0; i < queries.size(); i++) {
            if (map.get(queries.get(i).get(0)) == null || map.get(queries.get(i).get(1)) == null) {
                results[i] = -1.0;
                continue;
            }
            LinkedList<String> visited = new LinkedList<>();
            visited.addLast(queries.get(i).get(0));
            results[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), visited);
        }

        return results;
    }

    double dfs(String A, String B, LinkedList<String> visited) {
        if (A.equals(B)) return 1.0;

        for (Pair p : map.get(A)) {
            String C = p.k;
            Double val = p.v;
            if (visited.contains(C)) {
                continue;
            }
            visited.addLast(C);
            Double val2 = dfs(C, B, visited);
            visited.removeLast();
            if (val2 == -1) {
                continue;
            }
            return val * val2;
        }

        return -1;
    }


    public static void main(String[] args) {
        List<List<String>> in = new ArrayList<>();
        List<String> r = new ArrayList<>();
        r.add("x1");
        r.add("x2");
        in.add(0, r);

        List<String> r1 = new ArrayList<>();
        r1.add("x2");
        r1.add("x3");
        in.add(1, r1);

        List<String> r2 = new ArrayList<>();
        r2.add("x3");
        r2.add("x4");
        in.add(2, r2);

        List<String> r3 = new ArrayList<>();
        r3.add("x4");
        r3.add("x5");
        in.add(3, r3);

        EvaluateDivision ev = new EvaluateDivision();

        double[] v = new double[]{3.0, 4.0, 5.0, 6.0};
        List<List<String>> query = new ArrayList<>();
        List<String> q = new ArrayList<>();
        q.add("x2");
        q.add("x4");
        query.add(q);
        ev.calcEquation(in, v, query);
    }
}
