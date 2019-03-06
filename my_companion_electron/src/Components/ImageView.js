import React, { Component } from 'react';
import '../App.css';

class ImageView extends Component {
    constructor(props) {
        super(props)
    }

    render() {
        return(
            <section className="imageViewContainer">
                <img src={require("../Assets/img/bg.png")} alt="Placeholder"></img>
                <p>Picture information will be here...</p>
            </section>
        );
    }
}

export default ImageView;