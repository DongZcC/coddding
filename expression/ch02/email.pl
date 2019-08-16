#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';

# my $text = ~s/\b{username regex\@hostname regex}\b/ <a href="mailto: $1><a\/a>"/g;
# # 使用 / 会需要转义，  perl允许自定义分隔符
# my $test2 = ~s{\b{username regex\@hostname regex}\b}{ <a href="mailto: $1></a>}gi;
# # 使用 x 优化正则表达式， 用户可以宽松排列编排表达式，增强可读性， 并且允许出现#开头的注释
# my $test2 = ~s{
#     \b
#         #邮件地址写入 $1
#         (username regex
#         \@
#         hostname regex
#         )
#         \b
# }{<a href = "mailto:$1>$1</a>}gix;


undef $/;      # 进入文件读取模式
my $text = <>; # 读取命令行中制定的第一个文件名
$text =~ s/&/&amp;/g;
$text =~ s/</&lt;/g;
$text =~ s/>/&gt;/g;

# m增强的行锚点模式，在这种模式下 ^ $ 会从字符串模式切换到逻辑行模式匹配
$text =~ s/^\S*$/<p>/mg;

$text =~ s{
    \b
        (
        \w[-.\w]* #username
        \@
        [-a-z0-9]+(\.[-a-z0-9]+)*\.(com|edu|info) #hostname
        )
    \b
}{<a href="mailto:$1>$1</a>}gix;

print $text;
