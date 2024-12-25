function runForSomeTime() {
    let secondsPassed = 0;
    const intervalId = setInterval(() => {
        secondsPassed++;
        console.log(`Seconds passed: ${secondsPassed}`);

        // If 30 seconds have passed, clear the interval to stop the thread
        if (secondsPassed >= 30) {
            clearInterval(intervalId);
            console.log("30 seconds have passed. Stopping the thread.");
        }
    }, 1000); // 1000ms = 1 second
}

// Start the thread
runForSomeTime();
