class BankAccount {
  #balance;
  #pin;
  transactions = [];

  constructor(type, pin) {
    this.type = type;
    this.#balance = 0;
    this.#pin = pin;
  }

  deposit(amount) {
    this.#balance += amount;
    this.transactions.push(`Deposited: ${amount}`);
  }

  withdraw(amount, pin) {
    if (pin !== this.#pin) {
      console.log("Invalid PIN");
      return;
    }

    if (amount > this.#balance) {
      console.log("Insufficient balance");
      return;
    }

    this.#balance -= amount;
    this.transactions.push(`Withdrawn: ${amount}`);
  }

  showBalance(pin) {
    if (pin === this.#pin) {
      console.log("Balance:", this.#balance);
    } else {
      console.log("Wrong PIN");
    }
  }

  calculateInterest() {
    if (this.type === "Savings") {
      return this.#balance * 0.05;
    }
    return 0;
  }
}

const acc = new BankAccount("Savings", 1234);
acc.deposit(1000);
acc.withdraw(200, 1234);
acc.showBalance(1234);
console.log("Interest:", acc.calculateInterest());
console.log(acc.transactions);
