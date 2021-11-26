#!/bin/bash
# comment

var="$(git for-each-ref)"
commandA --args

echo "The current directory is:"
pwd
echo "The user logged in is:"
whoami

echo "dsds it'\\''s"

for VAR in "foo"; do
  echo ${VAR}
done

# ${VAR} should be highlighted on the last line
VAR="bar"
echo ${VAR}

# display user home
echo "Home for the current user is: $HOME"

# testing variables
grade=5
person="Adam"
echo "$person is a good boy, he is in grade $grade"

var1=$((5 + 5))
echo $var1
var2=$((var1 * 2))
echo $var2

if pwd; then
  echo "It works"
fi

user=anotherUser
if grep $user /etc/passwd; then
  echo "The user $user Exists"
else
  echo "The user $user doesn’t exist"
fi
