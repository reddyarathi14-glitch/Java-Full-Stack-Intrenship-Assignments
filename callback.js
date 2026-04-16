function greetUser(name, callback) {
    callback(name);
}

function sayHello(name) {
    console.log("Hello " + name + " 👋");
}

greetUser("Arathi", sayHello);
