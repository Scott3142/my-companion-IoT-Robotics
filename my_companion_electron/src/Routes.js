import React from 'react'
import { Route, BrowserRouter, Switch } from 'react-router-dom'
import Dashboard from './Components/Dashboard'
import Twitter from './Components/Twitter'

export default props => (
    <BrowserRouter>
        <Switch>
            <Route exact path='/' component={ Dashboard } />
            <Route exact path='/twitter' component={ Twitter } />
        </Switch>
    </BrowserRouter>
)