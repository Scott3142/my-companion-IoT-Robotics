import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Switch from '@material-ui/core/Switch';
import FormGroup from '@material-ui/core/FormGroup';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import ToolbarMenu from './ToolbarMenu';

const styles = theme => ({
    root: {
        display: 'flex',
        backgroundColor: theme.palette.grey['100'],
    },
    appBarSpacer: theme.mixins.toolbar,
    content: {
        flexGrow: 1,
        padding: theme.spacing.unit * 3,
        height: '100vh',
        overflow: 'auto',
        justifyContent:'center',
        alignItems:'center',
        display: "flex"
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
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    formControl: {
        margin: theme.spacing.unit * 3,
    },
    group: {
        margin: `${theme.spacing.unit}px 0`,
    },
    textField: {
        // marginLeft: theme.spacing.unit,
        // marginRight: theme.spacing.unit,
        width: 400
    },
    // dense: {
    //     marginTop: 16,
    // },
    // menu: {
    //     width: 400,
    // },
});

class Settings extends React.Component {

    render() {
        const { classes } = this.props;
        return (
            <div className={classes.root}>
                <CssBaseline />
                <ToolbarMenu/>
                <main className={classes.content}>
                    <div className={classes.appBarSpacer} />
                    <FormControl component="fieldset" className={classes.formControl}>
                        <TextField
                            id="outlined-name"
                            label="Greeting Phrase (How the system will greet you)"
                            className={classes.textField}
                            margin="normal"
                            variant="outlined"
                        />
                        <FormControlLabel
                            label="Enable Audio Visualiser"
                            control={
                                <Switch
                                value="checkedB"
                                color="primary"
                                />
                            }
                        />
                        <FormControlLabel
                            label="Persist Speech (Send voice data to server)"
                            control={
                                <Switch
                                    value="checkedB"
                                    color="primary"
                                />
                            }
                        />
                        <TextField
                            id="outlined-number"
                            label="Number"
                            type="number"
                            className={classes.textField}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            margin="normal"
                            variant="outlined"
                        />
                        <TextField
                            id="outlined-number"
                            label="Temperature Sensor Frequency (in minutes)"
                            type="number"
                            className={classes.textField}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            margin="normal"
                            variant="outlined"
                        />
                        <TextField
                            id="outlined-number"
                            label="Light Sensor Frequency (in minutes)"
                            type="number"
                            className={classes.textField}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            margin="normal"
                            variant="outlined"
                        />
                    </FormControl>
                </main>
            </div>
        );
    }
}

Settings.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Settings);