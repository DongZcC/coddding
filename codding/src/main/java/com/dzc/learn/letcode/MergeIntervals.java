package com.dzc.learn.letcode;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

/**
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        // 先排序让这个数组有序
        intervals.sort(Comparator.comparingInt(v -> (v.start)));

        List<Interval> result = new LinkedList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            // 下一组数据在这组数据的区间之中
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        result.add(new Interval(start, end));
        return result;
    }


    @Test
    public void testMerge() {
//        [[1,3],[2,6],[8,10],[15,18]]
        List<Interval> input = new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18)));
        MergeIntervals merge = new MergeIntervals();
        List<Interval> output = merge.merge(input);

        assertEquals(output.size(), 3);
    }
}
