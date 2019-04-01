import React, { Component } from 'react';
import AudioAnalyser from './AudioAnalyser';
import ImageView from './Components/ImageView';
import NotificationPrompt from './Components/NotificationPrompt';
import AccountForm from './Components/AccountForm';
import './App.css';
import Dashboard from './Components/Dashboard';
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';
import { red, lightBlue } from '@material-ui/core/colors'
import Routes from './Routes';
const ROSLIB = require('roslib');
const express = window.require('express');
const bodyParser = require('body-parser');

const app = express()
app.use(bodyParser.json());
app.get('/', (req, res) => res.send('Hello World!'))
app.listen(5000, () => console.log(`Example app listening on port 5000!`))
app.post('/tweets', (req, res) => {
  console.log(req.body);
  res.status(200).send('ok')
});

const ros = new ROSLIB.Ros({
      url: 'ws://localhost:9090'
});

const topic = new ROSLIB.Topic({
      ros : ros,
      name : '/speech/input',
      messageType: 'std_msgs/String'
});

const theme = createMuiTheme({
    palette: {
        secondary: {
            main: red[500]
        },
        primary: {
            main: lightBlue[400],
            contrastText: '#fff',
        }
    },
    typography: {
        // Use the system font instead of the default Roboto font.
        fontFamily: [
            '"Lato"',
            'sans-serif'
        ].join(',')
    }
  });
    
const temperature = new ROSLIB.Topic({
      ros : ros,
      name : '/temperature',
      messageType: 'my_companion/Temperature'
});

const twitter = new ROSLIB.Topic({
  ros: ros,
  name: '/twitter/tweet',
  messageType: 'my_companion/Twitter'
});

twitter.subscribe(function(message) {
  console.log(message.permalink)
})

temperature.subscribe(function(message) {
     console.log('Received message on ' + temperature.name + ': ' +  'Temperature: ' + message.temperature + ' Sensor Name: ' + message.sensorName);
     const url = 'http://localhost:8080/api/temperatures/';
     fetch(url, {
        method: 'POST',
        body: JSON.stringify(message),
        headers: {
           'Content-Type': 'application/json',
        }
     })
     .then(res => res.json())
     .then(response => console.log('Success:', JSON.stringify(response)))
});

ros.on('connection', () => {
      console.log('Connected to websocket server.');

      const message = new ROSLIB.Message({
          data: "Hello world"
      });

      topic.publish(message);
});

/**
 *  Material-UI has been used to create the application UI
 *  All Material-UI references can be found on their official website: https://material-ui.com/api/
 */

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {value: ''};
    this.handleChange = this.handleChange.bind(this);
    this.state = {
      audio: null,
      IVStatus: false,
      NPStatus: false,
      AFStatus:true,
      loggedIn: true,
    };
    this.toggleMicrophone = this.toggleMicrophone.bind(this);
    this.showImageView = this.showImageView.bind(this);
    this.showNotificationPrompt = this.showNotificationPrompt.bind(this);
    this.showAccountForm = this.showAccountForm.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async getMicrophone() {
    const audio = await navigator.mediaDevices.getUserMedia({
      audio: true,
      video: false
    });
    this.setState({ audio });
  }

  componentDidMount() {
    
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

  showImageView() {
    if (this.state.IVStatus) {
      this.setState({IVStatus: false});
    } else {
      this.setState({IVStatus: true});
    }
  }

  showNotificationPrompt() {
    if (this.state.NPStatus) {
      this.setState({NPStatus: false});
    } else {
      this.setState({NPStatus: true});
    }
  }

  showAccountForm() {
    if (this.state.AFStatus) {
      this.setState({AFStatus: false});
    } else {
      this.setState({AFStatus: true});
    }
  }

  handleSubmit(event) {
    const message = new ROSLIB.Message({
      data: this.state.value
    });
    topic.publish(message);
    event.preventDefault();
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  render() {
    return (
      <MuiThemeProvider theme={theme}>
          {this.state.loggedIn ? <Routes/> : <AccountForm/>}
      </MuiThemeProvider>
    );
  }
}

export default App;
