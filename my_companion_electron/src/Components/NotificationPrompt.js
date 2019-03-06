import React, { Component } from 'react';
import '../App.css';

class NotificationPrompt extends Component {
    constructor(props) {
        super(props)
    }

    render() {
        return(
            <section className="bgDarken">
                <section className="promptContainer">
                    <h3>Would you like to see new pictures uploaded by (name here) </h3>
                    <img src={require("../Assets/img/gr.jpg")} alt="Profile Picture"></img>
                </section>
            </section>
        );
    }
}

export default NotificationPrompt;