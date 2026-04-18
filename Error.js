function riskyPromise() {
  return new Promise((resolve, reject) => {
    reject("Error occurred!");
  });
}

async function handleError() {
  try {
    const res = await riskyPromise();
    console.log(res);
  } catch (error) {
    console.log("Something went wrong");
  }
}

handleError();
