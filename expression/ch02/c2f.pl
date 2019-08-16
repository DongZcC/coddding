#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';

print "Enter a temperature in Celsius: \n";
my $celsius = <STDIN>;
chomp($celsius);

if ($celsius =~ m/^[+-]?[0-9]+(\.[0-9]*)?$/) {
    my $f = ($celsius * 9 / 5 ) + 32;
    print "$celsius C is $f F\n";
} else {
    print "Expecting a number, so I don't  understand \"$celsius\". \n";
}