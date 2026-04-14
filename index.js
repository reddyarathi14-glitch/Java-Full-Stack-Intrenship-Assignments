class Person {
  constructor(name) {
    this.name = name;
  }
}

class Student extends Person {
  constructor(name, marks) {
    super(name); // call parent constructor
    this.marks = marks;
  }

  showDetails() {
    console.log(`Name: ${this.name}, Marks: ${this.marks}`);
  }
}

const s1 = new Student("John", 90);
s1.showDetails();


