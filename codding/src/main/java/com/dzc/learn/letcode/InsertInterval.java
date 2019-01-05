package com.dzc.learn.letcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * <p>
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * <p>
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 * @see https://leetcode.com/problems/insert-interval/
 */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals.isEmpty()) {
            result.add(newInterval);
            return result;
        }
        intervals.add(newInterval);
        // [1,3],[2,5],[6,9]
        intervals.sort((o1, o2) -> (o1.start == o2.start ? Integer.compare(o1.end, o2.end) : Integer.compare(o1.start, o2.start)));

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
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
    
    
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        for (Interval interval : intervals) {
            if (newInterval == null || interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                // 由于给定的序列是有序的 并且不重复的
                result.add(newInterval);
                result.add(interval);
                newInterval = null;
            } else {
                // 区间重合了
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }

        if (newInterval != null) {
            result.add(newInterval);
        }

        return result;
    }
    


}
