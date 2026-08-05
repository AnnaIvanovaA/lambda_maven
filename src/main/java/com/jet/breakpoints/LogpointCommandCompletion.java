package com.jet.breakpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LogpointCommandCompletion {


    private static final Logger log = LoggerFactory.getLogger(LogpointCommandCompletion.class);

    public static void main(String[] args) throws IOException {
        List<String> myList = List.of("alpha", "beta", "gamma");
        byte[] bytes = "logpoint command completion".getBytes();

        keepLineWhenCompletionIsInsideAnotherStatement(bytes);
        keepLineWhenCompletionIsAfterStatement(bytes);
        LogpointAnotherClass.keepLineWhenCompletionIsBeforeStatementSemicolon(myList);
        keepLineWhenCompletionIsAfterStatementSemicolon(myList);
        keepLineWhenCompletionIsInsideQualifiedCall(myList);
        replaceLineOnlyWhenWholeExpressionIsLogged(myList);
        keepLineWhenOnlyPartOfExpressionIsLogged();
    }

    private static void keepLineWhenCompletionIsInsideAnotherStatement(byte[] bytes) throws IOException {
        Path path = Path.of("bytes.txt");

        // command completion after "bytes": Files.write(path, bytes..);
        //   Files.write("bytes.txt", bytes..<logpoint>); // line should stay
        Files.write(path, bytes);


    }

    private static void keepLineWhenCompletionIsAfterStatement(byte[] bytes) throws IOException {

        log.info(String.valueOf(Path.of("Logpoint")));

        Path path = Path.of("bytes-after-statement.txt");
        Path smth = Path.of("smth.txt");


        // Files.write(path, bytes);..
        //  empty logpoint is added, and this line stays in the editor.

        Files.write(path, bytes);
    }

    private static void keepLineWhenCompletionIsAfterStatementSemicolon(List<String> myList) {
        // QA: invoke command completion after the semicolon in the next line: myList.size();..
        // Expected: logpoint is added, and this line stays in the editor.
        myList.size();
    }

    private static void keepLineWhenCompletionIsInsideQualifiedCall(List<String> myList) {
        // QA: invoke command completion between the qualifier and the method call in the next line: myList...size()
        // Expected: logpoint is added, and this line stays in the editor.
        myList.size();
    }

    private static void replaceLineOnlyWhenWholeExpressionIsLogged(List<String> myList) {
        // QA: remove the semicolon, invoke command completion after the expression: myList.size()..
        // Expected: logpoint is added, and the invoked line is removed from the editor.
        myList.size();
    }

    private static void keepLineWhenOnlyPartOfExpressionIsLogged() {
        // QA: select only "67" and invoke command completion after it in the next line: 42 + 67..
        // Expected: logpoint is added, and this line stays in the editor.

        int result = 42 + 67;

        // QA: replace the next line with "42 + 67", select the whole expression, and invoke command completion.
        // Expected: logpoint is added, and the invoked expression line is removed from the editor.
        System.out.println(result);

    }
}
