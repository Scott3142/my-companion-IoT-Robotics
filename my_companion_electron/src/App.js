import React, { Component } from 'react';
import AudioAnalyser from './AudioAnalyser';
import ImageView from './Components/ImageView';
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
    this.state = {
      audio: null
    };
    this.toggleMicrophone = this.toggleMicrophone.bind(this);

  }
  
  async getMicrophone() {
    const audio = await navigator.mediaDevices.getUserMedia({
      audio: true,
      video: false
    });
    this.setState({ audio });
  }

  componentDidMount() {
    this.getMicrophone();
  }
  
  stopMicrophone() {
    this.state.audio.getTracks().forEach(track => track.stop());
    this.setState({ audio: null });
  }
  
  toggleMicrophone() {
    if (this.state.audio) {
      this.stopMicrophone();
    } else {
      this.getMicrophone();
    }
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
        <div className="controls">
          {<button onClick={this.toggleMicrophone}>
            {this.state.audio ? 'Stop Microphone' : 'Enable Microphone Input'}
          </button> }
        </div>
        {this.state.audio ? <AudioAnalyser audio={this.state.audio} /> : ''}
      </div>
    );
  }
}

export default App;
