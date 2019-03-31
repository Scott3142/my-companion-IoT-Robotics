import React,  { Component } from 'react';
import withStyles from '@material-ui/core/styles/withStyles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import AudioAnalyser from '../AudioAnalyser';
import SimpleLineChart from './SimpleLineChart';

const styles = theme => ({
    root: {
        flexGrow: 1,
        backgroundColor: theme.palette.grey['100'],
        overflow: 'hidden',
        backgroundSize: 'cover',
        backgroundPosition: '0 400px',
        paddingBottom: 200
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
    }
});

class DashboardGrid extends Component {
    constructor(props) {
        super(props);
        this.state = {
            audio: null
        };
        this.toggleMicrophone = this.toggleMicrophone.bind(this);
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
    render() {
        const {classes} = this.props;
        return (
            <React.Fragment>
                <CssBaseline />
                <div className={classes.root}>
                    <Grid container justify="center">
                        <Grid spacing={16} alignItems="flex-start" justify="center" container className={classes.grid}>
                            <Grid item xs={12} md={4}>
                                <Paper className={classes.paper}>
                                    <div>
                                        {<button onClick={this.toggleMicrophone}>
                                            {this.state.audio ? 'Stop Microphone' : 'Enable Microphone Input'}
                                        </button> }
                                        {this.state.audio ? <AudioAnalyser audio={this.state.audio} /> : ''}
                                    </div>
                                </Paper>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <Paper className={classes.paper}>
                                    <div>
                                        <Typography variant="subtitle1" gutterBottom>
                                            Kitchen Temperature Overview
                                        </Typography>
                                        <div >
                                            <SimpleLineChart />
                                        </div>
                                    </div>
                                </Paper>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <Paper className={classes.paper}>
                                    <div>
                                        <Typography variant="subtitle1" gutterBottom>
                                            Latest Tweet from @CUSoftAcademy
                                        </Typography>
                                        <Typography variant="body1">
                                            A sample period really long stuff A sample period really long stuff
                                            A sample period really long stuff A sample period really long stuff
                                            A sample period really long stuff A sample period really long stuff
                                            A sample period really long stuff A sample period really long stuff
                                        </Typography>
                                        <div className={classes.rangeLabel}>
                                            <div>
                                                <Typography variant="subtitle2">
                                                    1 month
                                                </Typography>
                                            </div>
                                            <div>
                                                <Typography variant="subtitle2">
                                                    6 months
                                                </Typography>
                                            </div>
                                        </div>
                                    </div>
                                </Paper>
                            </Grid>
                            <Grid item xs={12} md={8} >
                                <Paper className={classes.paper} style={{position: 'relative'}}>
                                    <div>
                                        <Typography variant="subtitle1" gutterBottom>
                                            Light Sensor Overview
                                        </Typography>
                                        <div style={{marginTop: 14, marginBottom: 14}}>
                                            <SimpleLineChart />
                                        </div>
                                        {/*<div >*/}
                                        {/*<SimpleLineChart data={data} />*/}
                                        {/*</div>*/}
                                    </div>
                                </Paper>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <Paper className={classes.paper} style={{position: 'relative'}}>
                                    <div style={{marginTop: 14, marginBottom: 14}}>
                                    </div>
                                </Paper>
                            </Grid>
                        </Grid>
                    </Grid>
                    {/*<Grid container spacing={24} justify="left">*/}
                        {/*<Grid item xs={12} md={8} >*/}
                            {/*<Paper className={classes.paper} style={{position: 'relative'}}>*/}
                                {/*<div>*/}
                                    {/*<Typography variant="subtitle1" gutterBottom>*/}
                                        {/*Some details*/}
                                    {/*</Typography>*/}
                                    {/*<Typography variant="body1">*/}
                                        {/*Details about the graph*/}
                                    {/*</Typography>*/}
                                    {/*<div style={{marginTop: 14, marginBottom: 14}}>*/}
                                    {/*</div>*/}
                                    {/*/!*<div >*!/*/}
                                        {/*/!*<SimpleLineChart data={data} />*!/*/}
                                    {/*/!*</div>*!/*/}
                                {/*</div>*/}
                            {/*</Paper>*/}
                        {/*</Grid>*/}

                    {/*</Grid>*/}
                </div>
            </React.Fragment>
        )
    }
}

export default withStyles(styles)(DashboardGrid);