function checkNumber(num) {
  return new Promise((resolve, reject) => {
    if (num > 10) {
      resolve("Success");
    } else {
      reject("Fail");
    }
  });
}

checkNumber(15)
  .then((res) => console.log(res))
  .catch((err) => console.log(err));
