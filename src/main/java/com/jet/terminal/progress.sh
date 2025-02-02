#!/bin/bash

total=50  # Total length of the progress bar
for ((i = 1; i <= total; i++)); do
  # Calculate percentage
  percent=$((i * 100 / total))
  
  # Print the progress bar
  printf "\r["
  for ((j = 1; j <= i; j++)); do
    printf "\033[42m \033[0m"  # Green block
  done
  for ((j = i + 1; j <= total; j++)); do
    printf " "  # Empty space
  done
  printf "] %d%%" "$percent"  # Print percentage
  
  # Simulate progress
  sleep 0.1
done
echo