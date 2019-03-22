import React, { Component } from 'react';
import '../App.css';

class AccountForm extends Component {
    constructor() {
        super()
    }
    render() {
        return(
            <form>
                <fieldset>
                    <legend>YOUR DETAILS</legend>
                    <input type="email" name="uEmail" placeholder="Please enter a valid email address"></input>
                    <input type="text" name="uForename" placeholder="Please enter your forename"></input>
                    <input type="text" name="uSurname" placeholder="Please enter your surname"></input>
                    <input type="text" name="username" placeholder="Please enter a username"></input>
                    <input type="password" name="password" placeholder="Please enter a password"></input>
                </fieldset>
                <fieldset>
                    <legend>EMERGENCY CONTACT DETAILS</legend>
                    <input type="text" name="eForename" placeholder="Please enter their forename"></input>
                    <input type="text" name="eSurname" placeholder="Please enter their surname"></input>
                    <input type="email" name="eEmail" placeholder="Please enter their email"></input>
                    <input type="tel" name="ePhone" placeholder="Please enter their telephone number"></input>
                </fieldset>
            </form>
        );
    }
}

export default AccountForm;