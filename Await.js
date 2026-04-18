function getData() {
  return new Promise((resolve) => {
    resolve("Data received");
  });
}

async function fetchData() {
  const res = await getData();
  console.log(res);
}

fetchData();
