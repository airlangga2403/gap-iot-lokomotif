const express = require('express');
const kafka = require('kafka-node');
const mongoose = require('mongoose')

const app = express();

// Kafka configuration
const kafkaClientOptions = { kafkaHost: 'localhost:9092' };
const kafkaConsumerOptions = { groupId: 'iot-lokomotif', autoCommit: true };
const kafkaClient = new kafka.KafkaClient(kafkaClientOptions);
const kafkaConsumer = new kafka.Consumer(kafkaClient, [{ topic: 'iot' }], kafkaConsumerOptions);


// mongo configuration
// jika tidak bisa localhost gunakan 127.0.0.1
mongoose.connect('mongodb://127.0.0.1:27017/locomotif', {
    useNewUrlParser: true,
    useUnifiedTopology: true,
});

const db = mongoose.connection;

db.on("error", (error) => {
    console.error("MongoDB connection error:", error);
});

db.once("open", () => {
    console.log("connected to mongo db")
})

const Locomotif = new mongoose.model('data-iots', {
    kodeLoko: String,
    namaLoko: String,
    dimensiLoko: String,
    status: String,
    dateAndTime: Date
})

kafkaConsumer.on('message', async message => {
    try {
        // Parse Kafka message
        const data = JSON.parse(message.value);
        // save mongo here 
        const locomotif = await new Locomotif(data);
        await locomotif.save();
        console.log("success saved data to mongo db ");
    } catch (error) {
        console.error('Error Fetching kafka message:', error);
    }
});

const port = 3000;
app.listen(port, () => {
    console.log(`Server listening on port ${port}`);
});