package com.dzc.book.stackandqueue;

import java.util.Stack;

/**
 * Created on 2020/5/17.
 * <p>
 * 汉诺塔用栈来实现.
 * 并且是有要求的. 有限制的.
 *
 * @author _Shrimp
 */
public class HanoiOfStack {


    public static void main(String[] args) {
        HanoiOfStack h = new HanoiOfStack();
//        h.hanoiProblem2(3, "柱子1", "柱子2", "柱子3");

        int count = h.hanoiProblem3(2, "left", "mid", "right");

        System.out.println("It will move " + count + " steps.");
    }

    public int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }

        return process(num, left, mid, right, left, right);
    }

    private int process(int num, String left, String mid, String right, String from, String to) {

        // 如果只剩下一层塔. 如果是直接需要越位置移动.
        // 有的时候需要返回 2 . 因为限制了不能直接 跨过中间位置移动.
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }

            // 如果还剩下 N层塔 .
            // 1. 剩下的N层全部在左， 希望全部移动到 中.
            // N-1 全部从左移动到右.
            // N 从左移动到中.
            // N-1 从右移动到中.


            // 如果剩下的N层全部在左。 希望全部移动到右
            // 1.  N-1 全部 从 左 -> 右
            // 2. 将 N 从 左 -> 中
            // 3. N-1  全部从 右 -> 左
            // 4. 将 N 从中 -> 右
            // 5. 将 N - 1 从左 -> 右
        }


        // 三步.
        if (from.equals(mid) || to.equals(mid)) {
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + "to " + to);
            int part3 = process(num - 1, left, mid, right, mid, to);
            return part1 + part2 + part3;
        } else {
            // 5步的情况.
            String another = (from.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + "to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + "to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }


    // 左、 中、 右 三个地点抽象成三个栈
    // LS \ MS \ RS
    // 不能违反小压大的原则.
    // from 栈中弹出的元素 如果想压到 to 栈中. num的值必须小于当前to 栈中的栈顶
    // 相邻不可逆原则：
    // 1. 四个动作定义为 L -> M , M -> L , M -> R, R -> M
    // 每两个互为逆过程.
    // 如果想获取到一个最小的步骤数. 则一定不能逆过程相邻.


    // 根据上述结论， 可以推倒出一个结论
    // 1. 游戏的第一个动作一定是 L -> M
    // 2. 在走出最少步数的任何时刻， 四个动作只有一个动作不违反小压大和相邻不可逆原则，另外三个动作一定都会违反.
    // 因此每次都只有一个动作才能达标，那么只要每走一步都根据这两个原则考察所有的动作就可以了。哪个动作达标就执行哪个动作

    public void hanoiProblem2(int num, String from, String buffer, String to) {
        if (num == 1) {
            System.out.println("from " + from + "to " + to);
            return;
        }

        hanoiProblem2(num - 1, from, to, buffer);
        hanoiProblem2(1, from, buffer, to);
        hanoiProblem2(num - 1, buffer, from, to);
    }

    public int hanoiProblem3(int num, String left, String mid, String right) {
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();

        ls.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);

        for (int i = num; i > 0; i--) {
            ls.push(i);
        }

        Action[] record = {Action.No};

        int step = 0;
        while (rs.size() != num + 1) {
            step += fStackTotStack(record, Action.MToL, Action.LToM, ls, ms, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, ms, ls, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MtoR, ms, rs, mid, right);
            step += fStackTotStack(record, Action.MtoR, Action.RToM, rs, ms, right, mid);
        }
        return step;
    }

    private int fStackTotStack(Action[] record, Action preNoAction, Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack, String from, String to) {
        if (record[0] != preNoAction && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }


    enum Action {
        No, LToM, MToL, MtoR, RToM
    }


}
