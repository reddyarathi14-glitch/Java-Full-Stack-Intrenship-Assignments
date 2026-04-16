function showMessage(callback) {
    setTimeout(() => {
        console.log("Message after 3 seconds ⏳");
        callback();
    }, 3000);
}

function done() {
    console.log("Process completed ✅");
}

showMessage(done);
