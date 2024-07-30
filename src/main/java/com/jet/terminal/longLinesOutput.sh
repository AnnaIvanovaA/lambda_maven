#!/bin/zsh

# long-line.sh

TWO_WIDTH=$(($COLUMNS + 10))
printf 'a%.0s' {1..$TWO_WIDTH}
printf '\n'
