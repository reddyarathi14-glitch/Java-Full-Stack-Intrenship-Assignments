class Animal {
  eat() {
    console.log("Animal eats food");
  }
}

class Dog extends Animal {
  eat() {
    super.eat(); // call parent method
    console.log("Dog eats bones");
  }
}

const d = new Dog();
d.eat();
