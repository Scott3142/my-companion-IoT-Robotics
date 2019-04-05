import React from 'react'
import { Route, BrowserRouter, Switch } from 'react-router-dom'
import Dashboard from './Components/Dashboard'
import Twitter from './Components/Twitter'
import Settings from './Components/Settings'
import AccountForm from './Components/AccountForm';
import Home from './Components/Home';
import LatestTweet from './Components/LatestTweet';
import App from './App';

const express = window.require('express');
const bodyParser = require('body-parser');

const app = express()
app.use(bodyParser.json());
app.get('/', (req, res) => res.send('Hello World!'))
app.listen(5000, () => console.log(`Example app listening on port 5000!`))

class Routes extends React.Component {
    state = {
        tweets: []
    }

    saveTweets = tweets => {
        this.setState({tweets: tweets});
    }

    render(){
        return (
            <BrowserRouter>
                <Switch>
                    <Route exact path='/' render={(props) => (<App {...props} express={app} saveTweets={this.saveTweets} />)}/>
                    <Route exact path="/dashboard" render={(props) => (<Dashboard latestTweet={this.state.tweets[0]} /> )} />
                    <Route exact path='/twitter' render={(props) => (<Twitter {...props} tweets={this.state.tweets} />)} />
                    <Route exact path='/settings' component={ Settings } />
                    <Route exact path='/register' component={ AccountForm } />
                    <Route exact path='/twitter/latest' component={ LatestTweet } />
                </Switch>
            </BrowserRouter>
        )
    }
}

export default Routes;

// export default props => (
//     <BrowserRouter>
//         <Switch>
//             <Route exact path='/' render={(props) => (<App {...props} express={app} />)}/>
//             <Route exact path="/dashboard" component={ Dashboard } />
//             <Route exact path='/twitter' component={ Twitter } />
//             <Route exact path='/settings' component={ Settings } />
//             <Route exact path='/register' component={ AccountForm } />
//             <Route exact path='/twitter/latest' component={ LatestTweet } />
//         </Switch>
//     </BrowserRouter>
// )