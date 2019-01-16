package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 1). 找到合适的单词构成一行
 * 2). 构成一行的单词分成几种情况
 * 2.1) 只有一个单词，放在最左边， 后面补空格
 * 2.2) 多个单词，空格需要特殊处理
 */
public class TextJustification {


    // ["This", "is", "an", "example", "of", "text", "justification."]
    // 16
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0)
            return Collections.emptyList();

        if (maxWidth == 0) {
            result.add(" ");
            return result;
        }
        int wordLen = 0;
        int wordNum = 0;
        int last = 0;
        List<String> subWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String currWord = words[i];
            // 能放就一直放 .. 想错了 根本不需要递归 直接for循环就可以搞定了
            if (currWord.length() + wordLen + wordNum <= maxWidth) {
                subWords.add(currWord);
                wordLen += currWord.length();
                wordNum++;
                continue;
            }
            last = i; // 最后一行的起点坐标
            StringBuilder sb = new StringBuilder();
            if (wordNum == 1) {
                sb.append(subWords.get(0));
                appendMultiExtraSpace(sb, maxWidth - wordLen);
            } else {
                // 计算空格的长度
                // This    is    an  // 17 - 8 / 2 = 4  ... 1
                // 如果分配不均匀， 就需要取出来余数 增加到前面几个中去
                int spaceNum = (maxWidth - wordLen) / (wordNum - 1);
                int remain = (maxWidth - wordLen) % (wordNum - 1);
                // 最后一个不需要添加空格
                for (int j = 0; j < subWords.size() - 1; j++) {
                    sb.append(subWords.get(j));
                    appendMultiExtraSpace(sb, spaceNum);
                    if (remain-- > 0)
                        appendMultiExtraSpace(sb, 1);
                }
                sb.append(subWords.get(subWords.size() - 1));
            }
            result.add(sb.toString());

            i--;
            subWords.clear();
            wordLen = 0;
            wordNum = 0;
        }
        StringBuilder sb = new StringBuilder();
        // 处理最后一行的数据
        for (int i = last; i < words.length; i++) {
            sb.append(words[i]);
            if (i != words.length - 1)
                sb.append(" ");
        }

        while (sb.length() < maxWidth)
            sb.append(" ");

        result.add(sb.toString());
        return result;
    }

    private void appendMultiExtraSpace(StringBuilder sb, int num) {
        for (int i = 0; i < num; i++) {
            sb.append(" ");
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{
                "What", "must", "be", "acknowledgment", "shall", "be"
        };

        TextJustification textJustification = new TextJustification();
        textJustification.fullJustify(words, 14);
    }
}
