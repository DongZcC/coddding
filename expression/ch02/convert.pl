#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';

print "Enter a temperature (e.g., 32F, 100C) :\n";
my $input = <STDIN>;
chomp($input);

if ($input =~ m/^([+-]?[0-9]+)\s*([CF])$/i) {
    my $InputNum = $1;
    my $type = $2;

    my $celsius;
    my $fahrenheit;
    if ($type =~ m/c/i) {
        $celsius = $InputNum;
        $fahrenheit = ($celsius * 9 / 5) + 32;
    }
    else {
        $fahrenheit = $InputNum;
        $celsius = ($fahrenheit - 32) * 5 / 9;
    }
    print "$celsius C is $fahrenheit F \n";

}
else {
    print "Excepting a number flowed by C or F";
}
