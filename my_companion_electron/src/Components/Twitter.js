import React,  { Component } from 'react';
import withStyles from '@material-ui/core/styles/withStyles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import ToolbarMenu from './ToolbarMenu';
import TwitterGrid from './TwitterGrid';

const styles = theme => ({
    root: {
        display: "flex",
        backgroundColor: theme.palette.grey['100'],
    },
    grid: {
        width: 1200,
        margin: `0 ${theme.spacing.unit * 2}px`,
        [theme.breakpoints.down('sm')]: {
            width: 'calc(100% - 20px)'
        }
    },
    loadingState: {
        opacity: 0.05
    },
    paper: {
        padding: theme.spacing.unit * 3,
        textAlign: 'left',
        color: theme.palette.text.secondary
    },
    rangeLabel: {
        display: 'flex',
        justifyContent: 'space-between',
        paddingTop: theme.spacing.unit * 2
    },
    topBar: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center'
    },
    blockCenter: {
        padding: theme.spacing.unit * 2,
        textAlign: 'center'
    },
    block: {
        padding: theme.spacing.unit * 2,
    },
    inlining: {
        display: 'inline-block',
        marginRight: 10
    },
    buttonBar: {
        display: 'flex'
    },
    noBorder: {
        borderBottomStyle: 'hidden'
    },
    mainBadge: {
        textAlign: 'center',
        marginTop: theme.spacing.unit * 4,
        marginBottom: theme.spacing.unit * 4
    },
    appBarSpacer: theme.mixins.toolbar,
    content: {
        flexGrow: 1,
        padding: theme.spacing.unit * 3,
        height: '100vh',
        overflow: 'auto',
    },
    chartContainer: {
        marginLeft: -22,
    },
    tableContainer: {
        height: 320,
    },
    h5: {
        marginBottom: theme.spacing.unit * 2,
    },
});

class Twitter extends Component {
    constructor(props){
        super(props);
        this.state = {
            tweets: [
                {
                    at: "cusoftacademy",
                    tweets: [
                        {
                            content: "#3Dprinter is whirring away at @cardiffuni #OpenDay in #NSA. #students #liveprojects posters are on display & #PeppertheRobot is giving demos. Here until 4pm! Come & ask us about our #industryintegrated #innovativedegree that's creating #workreadygraduates! #SoftwareEngineering",
                            time: "8:02 am - 27 Mar 2019",
                            displayName: "CU Software Academy",
                            permalink: "/CUSoftAcademy/status/1110920152831459330",
                            video: ""
                        }
                    ]
                }
            ]
        };
    }
    render() {
        const {classes} = this.props;
        return (
            <div className={classes.root}>
                <CssBaseline/>
                <ToolbarMenu/>
                <main className={classes.content}>
                    <div className={classes.appBarSpacer} />
                    <TwitterGrid tweets={this.props.tweets} />
                </main>
            </div>
        )
    }
}

export default withStyles(styles)(Twitter);