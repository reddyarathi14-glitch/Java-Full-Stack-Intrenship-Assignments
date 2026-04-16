const order = (callback) => {
    setTimeout(() => {
        console.log("order placed");
        callback();
    }, 5000);
};

const delivery = () => {
    console.log("Delivery Boy reached your location");
};

order(delivery);
