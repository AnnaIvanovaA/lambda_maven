#!/bin/bash


var="$(git for-each-ref)"
commandA --args


valid=True
count=1

while [ $valid ]; do
  echo $count
  if [ $count -eq 5 ]; then
    break
  fi
  ((count++))
done

for ((counter = 10; counter > 0; counter--)); do
  echo -n "$counter "
done

printf "\n"
printf "fdsfsf"
echo "Enter Your Name"
read name
echo "Welcome $name"
echo "Enter smgdfgkljrerth ffff"
echo "fdsfsdfs"
