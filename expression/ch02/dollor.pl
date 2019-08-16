#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';

# 顺序环视和逆序环视相结合
print "Enter a price: \n";
my $price = <STDIN>;
#$price =~ s/(?<=\d)(?=(?:\d\d\d)+\b)/,/g;
$price =~ s/(\d)(?=(\d\d\d)+(?!\d))/$1,/g;
# 这个表达式不可以，正则表达式在一次迭代完成后，会从上一次匹配的终点开始尝试
# 使用顺序环视的意义在于，检查某个位置，但检查时匹配的字符并不算在（最终）“匹配的字符串内”
#$price =~ s/(\d)((\d\d\d)+\b)/$1,$2/g;
print "The US population is $price"
