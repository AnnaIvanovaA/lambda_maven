#!/bin/bash
function get_completion() {
  # https://stackoverflow.com/a/32523040
  echo -en "set -o emacs\necho start\n$1\e*\x01\ed\edecho" | bash -i 2>/dev/null | awk '/start/ {f=1; next} f'
}

get_completion "git c"