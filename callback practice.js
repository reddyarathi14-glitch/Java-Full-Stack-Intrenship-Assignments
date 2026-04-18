function processNumber(num, callback) {
  let result = num * 2;   // double the number
  callback(result);       // pass to callback
}

function printResult(value) {
  console.log("Result:", value);
}

processNumber(5, printResult);
