#!/bin/bash

total=100  # Total percentage (100%)

# Get the terminal width dynamically
term_width=$(tput cols)

# Print terminal width
echo "Terminal Width: $term_width columns"

for line in {1..5}; do  # Loop to create 5 progress bars
  for ((i = 0; i <= total; i += 20)); do
    # Get the terminal width dynamically
    term_width=$(tput cols)

    # Calculate the bar length (leave space for "Progress: [XX%] []■")
    bar_length=$((term_width - 20))

    # Ensure the bar length is reasonable
    if ((bar_length < 10)); then
      bar_length=10  # Minimum length for the bar
    fi

    # Calculate the number of '#' for the green bar
    green_count=$((i * bar_length / total))
    spaces_count=$((bar_length - green_count))

    # Print the progress
    printf "\rProgress: \033[1;32m[%3d%%]\033[0m [" "$i"  # Green percentage
    for ((j = 1; j <= green_count; j++)); do
      printf "\033[42m#\033[0m"  # Green bar
    done
    for ((j = 1; j <= spaces_count; j++)); do
      printf "."
    done
    printf "]\033[41m■\033[0m"  # Add a red block at the end of the line

    # Simulate progress
    sleep 0.2
  done

  # Move to the next line after each progress bar finishes
  echo
done
