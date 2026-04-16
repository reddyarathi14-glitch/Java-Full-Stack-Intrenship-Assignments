class Product {
  constructor(name, price) {
    this.name = name;
    this.price = price;
  }
}

class Cart {
  constructor() {
    this.items = [];
  }

  addItem(product, quantity) {
    this.items.push({ product, quantity });
  }

  removeItem(name) {
    this.items = this.items.filter(item => item.product.name !== name);
  }

  getTotal() {
    let total = 0;
    this.items.forEach(item => {
      total += item.product.price * item.quantity;
    });
    return total;
  }

  applyCoupon(discount) {
    let total = this.getTotal();
    return total - (total * discount / 100);
  }
}

const p1 = new Product("Phone", 20000);
const p2 = new Product("Shoes", 3000);

const cart = new Cart();
cart.addItem(p1, 1);
cart.addItem(p2, 2);

console.log("Total:", cart.getTotal());
console.log("After Discount:", cart.applyCoupon(10));
