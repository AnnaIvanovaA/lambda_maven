package com.jet.breakpoints;

import com.jet.breakpoints.library.BreakpointTargetLibrary;
import com.jet.breakpoints.library.BreakpointTargetLibrary.LookupRequest;

import java.util.List;

/**
 * Manual debugger playground where user code calls library code.
 *
 * Verification flow:
 * 1. Run this class under the debugger.
 * 2. Open BreakpointTargetLibrary and set a conditional breakpoint on the marked line.
 * 3. Suggested condition: request.id().equals("vip-404")
 * 4. Expected: the breakpoint is hit only for the "vip-404" request, even though the library method is called for every item.
 */
public class ConditionalBreakpointInLibrarySource {

    public static void main(String[] args) {
        BreakpointTargetLibrary library = new BreakpointTargetLibrary();
        List<LookupRequest> requests = List.of(
                new LookupRequest("user-101", "standard", 1),
                new LookupRequest("user-202", "priority", 2),
                new LookupRequest("vip-404", "priority", 5),
                new LookupRequest("user-505", "standard", 3)
        );

        for (LookupRequest request : requests) {
            String lane = library.resolveLane(request);
            System.out.println(request.id() + " -> " + lane);
        }
    }
}
