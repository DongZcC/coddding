#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';

my $line;
while ($line = <>) {
    # 如果是一个空行
    if ($line =~ m/^\s*$/) {
        last; #停止while 循环内的处理
    }

    my $subject;
    if ($line =~ m/^Subject: (.*)/i) {
        $subject = $1;
    }

    my $date;
    if ($line =~ m/^Date: (.*)/i) {
        $date = $1;
    }

    my $reply;
    if ($line =~ m/^Reply-To: (.*)/i) {
        $reply = $1;
    }

    my $email;
    my $name;
    if ($line =~ m/^From: (\S+) \(([^()]*)\)/i) {
        $email = $1;
        $name = $2;
    }
}