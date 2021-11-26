#!/bin/bash

commandA --args

# Run commandB in a subshell and collect its output in $VAR
# NOTE
#  - PATH is only modified as an example
#  - output beyond a single value may not be captured without quoting
#  - it is important to discard (or separate) virtualenv activation stdout
#    if the stdout of commandB is to be captured
#
VAR=$(
    PATH="/opt/bin/foo:$PATH"
    . /path/to/activate > /dev/null  # activate virtualenv
    commandB  # tool from /opt/bin/ which requires virtualenv
)

# Use the output from commandB later
commandC "$VAR"
