import React, { Component } from 'react';
import '../App.css';

class AccountForm extends Component {
    constructor() {
        super()
        this.postUserDetails = this.postUserDetails.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    postUserDetails(e) {
        e.preventDefault();
        // let details = e.target.value;
        const formatted = {
            email: this.state.uEmail,
            firstName: this.state.uForename,
            lastName: this.state.uSurname,
            username: this.state.username,
            password: this.state.password,
            contacts: [ {
                firstName: this.state.eForename,
                lastName: this.state.eSurname,
                email: this.state.eEmail,
                phone: this.state.ePhone
            }
            ]
        }
        const url = 'http://10.72.97.47:8080/api/users';
          fetch(url, {
            method: 'POST',
            body: JSON.stringify(formatted),
            headers: {
              'Content-Type': 'application/json'
            }
          })
          .then(result => result.json())
          .then(resp => console.log('Success', JSON.stringify(resp)));
    }

    // Syntax needed to handle multiple inputs in forms with react retrieved from docs:
    // https://reactjs.org/docs/forms.html
    // Accessed: 20th March 2019
    handleChange(e) {
        let name = e.target.name;
        let value = e.target.value;
        this.setState({[name] : value});
    }

    render() {
        return(
            <form action='#' onSubmit={this.postUserDetails}>
                <fieldset>
                    <legend>YOUR DETAILS</legend>
                    <input onChange={this.handleChange} type="email" name="uEmail" placeholder="Please enter a valid email address"></input>
                    <input onChange={this.handleChange} type="text" name="uForename" placeholder="Please enter your forename"></input>
                    <input onChange={this.handleChange} type="text" name="uSurname" placeholder="Please enter your surname"></input>
                    <input onChange={this.handleChange} type="text" name="username" placeholder="Please enter a username"></input>
                    <input onChange={this.handleChange} type="password" name="password" placeholder="Please enter a password"></input>
                </fieldset>
                <fieldset>
                    <legend>EMERGENCY CONTACT DETAILS</legend>
                    <input onChange={this.handleChange} type="text" name="eForename" placeholder="Please enter their forename"></input>
                    <input onChange={this.handleChange} type="text" name="eSurname" placeholder="Please enter their surname"></input>
                    <input onChange={this.handleChange} type="email" name="eEmail" placeholder="Please enter their email"></input>
                    <input onChange={this.handleChange} type="tel" name="ePhone" placeholder="Please enter their telephone number"></input>
                </fieldset>
                <button type="submit" name="submit">&#x2713;</button>      
            </form>
        );
    }
}

export default AccountForm;