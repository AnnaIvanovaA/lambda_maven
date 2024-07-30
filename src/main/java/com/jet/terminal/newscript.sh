#!/usr/bin/env bash


echo "Hello, World"
sleep 1
echo "Bye!"


function greeting() {
    hello="Hello, $name"
    echo "$hello"
}

echo "Enter name:"
read name

val=$(greeting)
echo "Return value of the function is $val"
#
#echo "Error!" > logfile.log
#exit 125

function backtrace() {
    local depth=${#FUNCNAME[@]}

    for ((i=1; i<$depth; i++)); do
        local func="${FUNCNAME[$i]}"
        local line="${BASH_LINENO[$((i-1))]}"
        local src="${BASH_SOURCE[$((i-1))]}"
       printf '%*s' $i '' # indent
        echo "at: $func(), $src, line $line"

    done
}

function trace_top_caller () {
    local func="${FUNCNAME[1]}"
    local line="${BASH_LINENO[0]}"
    local src="${BASH_SOURCE[0]}"
    echo "  called from: $func(), $src, line $line"
}

set -o errtrace
trap 'trace_top_caller' ERR

echo "Error!"
exit 1

