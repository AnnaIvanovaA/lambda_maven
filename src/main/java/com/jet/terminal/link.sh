#!/usr/bin/env bash

echo "test zwj emoji 👩‍🚀"

echo -e '----> file:///home/Anna.Ivanova/IdeaProjects/lambda_maven/src/main/java/com/jet/terminal/UtfCheck.java:27:9'

echo -e '\e]8;;http://example.com\e\\This is a very long line This is a very long line This is a very long line https://blog.jetbrains.com/idea/ line This is a very long line This is a very long line This is a very long line This is a very long line This is a very long line This is a very long line This is a very long line\e]8;;\e\\'
echo -e 'some text for a long long line https://www.jetbrains.com/help/idea/reformat-file-dialog.html'
echo -e ' https://git.chocodev.kz/arman/chocoadmin/merge_requests/new?merge_request%5Bsource_branch%5D=cube.cases-for-sales.marat_pos_teminal_fix'

echo -e 'some text for a long long line'


var="$(git for-each-ref)"
commandA --args

commandB "${var}"

commandA | sponge | { IFS= read -r x; { printf "%s\n" "$x"; cat; } | commandB;