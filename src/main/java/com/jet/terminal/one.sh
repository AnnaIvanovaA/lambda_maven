#!/usr/bin/env bash
# Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
# Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
# Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
# Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
# Vestibulum commodo. Ut rhoncus gravida arcu.
#

echo "check"
echo '\e]8;;http://example.com\e\\This is a very long line This is a very long line This is a very long line https://blog.jetbrains.com/idea/ line This is a very long line This is a very long line This is a very long line This is a very long line This is a very long line This is a very long line This is a very long line\e]8;;\e\\'
echo 'some text for a long long line https://www.jetbrains.com/help/idea/reformat-file-dialog.html'

function greeting() {
    hello="Hello, $name"
    echo "$hello"
}

echo "Enter name:"
read name

val=$(greeting)
echo "Return value of the function is $val"

