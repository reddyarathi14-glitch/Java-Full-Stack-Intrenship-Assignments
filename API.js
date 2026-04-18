async function getPosts() {
  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/posts");
    const data = await response.json();

    data.forEach(post => {
      console.log(post.title);
    });
  } catch (error) {
    console.log("Error fetching data");
  }
}

getPosts();
