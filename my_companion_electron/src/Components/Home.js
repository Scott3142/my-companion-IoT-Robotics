import React,  { Component } from 'react';
import withStyles from '@material-ui/core/styles/withStyles';
import CssBaseline from '@material-ui/core/CssBaseline';
import PropTypes from 'prop-types';
import { ReactComponent as Logo } from '../Assets/img/robot.svg';
import Button from '@material-ui/core/Button';
import { Link } from 'react-router-dom'

const styles = theme => ({
    root: {
        display: 'flex',
        // backgroundColor: theme.palette.grey['100'],
        backgroundColor: "rgb(253, 203, 107)",
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing.unit * 3,
        height: '100vh',
        overflow: 'auto',
        justifyContent:'center',
        alignItems:'center',
        display: "flex"
    },
    logo: {
        width: 500,
        height: 500
    },
    button: {
        margin: theme.spacing.unit,
    },
    input: {
        display: 'none',
    },
});

/**
 *  Robot Icon made by http://www.freepik.com/ from www.flaticon.com
 */

class Home extends Component {
    render() {
        const { classes } = this.props;
        return (
            <div className={classes.root}>
                <CssBaseline />
                <main className={classes.content}>
                    <Button variant="contained" color="primary" className={classes.button}>SIGN OUT</Button>
                    <Logo className={classes.logo}/>
                    <Button
                        variant="contained"
                        color="primary"
                        className={classes.button}
                        component={Link}
                        to="/dashboard"
                    >
                        DASHBOARD
                    </Button>
                </main>
            </div>
        );
    }
}

Home.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Home);