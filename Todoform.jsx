import { useState } from "react";

function TodoForm({ addTask }) {
  const [task, setTask] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (task.trim()) {
      addTask(task);
      setTask("");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input value={task} onChange={(e) => setTask(e.target.value)} />
      <button>Add</button>
    </form>
  );
}

export default TodoForm;