import React from 'react'
import { Route, BrowserRouter, Switch } from 'react-router-dom'
import Dashboard from './Components/Dashboard'
import Twitter from './Components/Twitter'
import Settings from './Components/Settings'
import AccountForm from './Components/AccountForm';
import Home from './Components/Home';
import LatestTweet from './Components/LatestTweet';

export default props => (
    <BrowserRouter>
        <Switch>
            <Route exact path='/' component={ Home } />
            <Route exact path="/dashboard" component={ Dashboard } />
            <Route exact path='/twitter' component={ Twitter } />
            <Route exact path='/settings' component={ Settings } />
            <Route exact path='/register' component={ AccountForm } />
            <Route exact path='/twitter/latest' component={ LatestTweet } />
        </Switch>
    </BrowserRouter>
)