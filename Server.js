const express = require("express");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(express.json());

// Questions (Backend data)
const questions = [
  {
    id: 1,
    question: "What is 2 + 2?",
    options: ["2", "4", "6", "8"],
    answer: "4"
  },
  {
    id: 2,
    question: "Capital of India?",
    options: ["Mumbai", "Delhi", "Chennai", "Hyderabad"],
    answer: "Delhi"
  },
  {
    id: 3,
    question: "JavaScript is?",
    options: ["Language", "Database", "Server", "OS"],
    answer: "Language"
  }
];

// API to get questions
app.get("/questions", (req, res) => {
  res.json(questions);
});

// API to calculate score
app.post("/submit", (req, res) => {
  const userAnswers = req.body;
  let score = 0;

  questions.forEach((q) => {
    if (userAnswers[q.id] === q.answer) {
      score++;
    }
  });

  res.json({ score: score, total: questions.length });
});

app.listen(5000, () => {
  console.log("Server running on port 5000");
});
