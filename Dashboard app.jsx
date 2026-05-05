import { useState } from "react";

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  return (
    <div>
      {!loggedIn ? (
        <button onClick={() => setLoggedIn(true)}>Login</button>
      ) : (
        <div>
          <h1>Dashboard</h1>
          <button onClick={() => setLoggedIn(false)}>Logout</button>
        </div>
      )}
    </div>
  );
}

export default App;