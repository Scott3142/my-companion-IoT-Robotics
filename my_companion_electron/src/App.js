import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
const ROSLIB = require('roslib');

const ros = new ROSLIB.Ros({
      url: 'ws://localhost:9090'
});

const topic = new ROSLIB.Topic({
      ros : ros,
      name : 'example_topic',
      messageType: 'std_msgs/String'
});

ros.on('connection', () => {
      console.log('Connected to websocket server.');

      const message = new ROSLIB.Message({
          data: "Hello world"
      });

      topic.publish(message);
});

class App extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    const message = new ROSLIB.Message({
      data: event.target.value
    });
    topic.publish(message);
  }

  render() {
    return (
      <div className="App">
        
      </div>
    );
  }
}

export default App;
