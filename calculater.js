function calculate(a, b, operation, callback) {
    let result;

    if (operation === "add") {
        result = a + b;
    } else if (operation === "sub") {
        result = a - b;
    } else if (operation === "mul") {
        result = a * b;
    } else if (operation === "div") {
        result = a / b;
    }

    callback(result);
}

function displayResult(result) {
    console.log("Result is: " + result);
}

calculate(10, 5, "add", displayResult);
