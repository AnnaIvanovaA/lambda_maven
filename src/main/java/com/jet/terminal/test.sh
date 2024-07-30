#!/usr/bin/zsh
# !/bin/bash
# This script checks if a file exists

# Get filename
echo -n "Enter file name:"
# shellcheck disable=SC2162
read fileName

  arg="${arg//\\/\\\\}"
  printf "%s\n" "${arg}"

# Make sure file exists for reading
if [ ! -f $fileName ]; then echo "Filename $fileName does not exist"
exit 1
    fi

#Sample comment
let "a=16 << 2";b="Sample text";

function foo() {  if [ $string1 == $string2 ]; then
for url in `cat example.txt`
do
curl $url > result.html
done
fi
}

rm -f $(find / -name core) &> /dev/null
# shellcheck disable=SC2082
mkdir -p "${AGENT_USER_HOME_${PLATFORM}}"

multiline='first line
           second line
           third line'
cat << EOF
 Sample text
EOF


