import { useState } from "react";

function App() {
  const [students, setStudents] = useState([]);
  const [name, setName] = useState("");

  const addStudent = () => {
    if (name.trim()) {
      setStudents([...students, name]);
      setName("");
    }
  };

  return (
    <div>
      <h1>Student Manager</h1>

      <input
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <button onClick={addStudent}>Add</button>

      {students.length === 0 ? (
        <p>No students</p>
      ) : (
        <ul>
          {students.map((s, i) => (
            <li key={i}>{s}</li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default App;