#!/bin/bash
# This script checks if a file exists

# Get filename
echo -n "Enter file name:"
# shellcheck disable=SC2162
read fileName

# Make sure file exists for reading
if [ ! -f $fileName ]; then
  echo "Filename $fileName does not exist"
  exit 1
fi

