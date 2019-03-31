import React from 'react'
import { Route, BrowserRouter, Switch } from 'react-router-dom'
import Dashboard from './Components/Dashboard'
import Twitter from './Components/Twitter'
import Settings from './Components/Settings'
import AccountForm from './Components/AccountForm';

export default props => (
    <BrowserRouter>
        <Switch>
            <Route exact path='/' component={ Dashboard } />
            <Route exact path='/twitter' component={ Twitter } />
            <Route exact path='/settings' component={ Settings } />
            <Route exact path='/register' component={ AccountForm } />
        </Switch>
    </BrowserRouter>
)