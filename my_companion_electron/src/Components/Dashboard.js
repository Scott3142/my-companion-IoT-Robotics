import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import NotificationsIcon from '@material-ui/icons/Notifications';
import Person from '@material-ui/icons/Person';
import { mainListItems, secondaryListItems } from './ListItems';
import AudioAnalyser from '../AudioAnalyser';
import ImageView from './ImageView';
import NotificationPrompt from './NotificationPrompt';
import DashboardGrid from './DashboardGrid';
import ToolbarMenu from './ToolbarMenu';

const drawerWidth = 240;

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

class Dashboard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {value: ''};
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            audio: null,
            IVStatus: false,
            NPStatus: false,
            AFStatus:true
        };
        this.toggleMicrophone = this.toggleMicrophone.bind(this);
        this.showImageView = this.showImageView.bind(this);
        this.showNotificationPrompt = this.showNotificationPrompt.bind(this);
        this.showAccountForm = this.showAccountForm.bind(this);
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

    showImageView() {
        if (this.state.IVStatus) {
            this.setState({IVStatus: false});
        } else {
            this.setState({IVStatus: true});
        }
    }

    showNotificationPrompt() {
        if (this.state.NPStatus) {
            this.setState({NPStatus: false});
        } else {
            this.setState({NPStatus: true});
        }
    }

    showAccountForm() {
        if (this.state.AFStatus) {
            this.setState({AFStatus: false});
        } else {
            this.setState({AFStatus: true});
        }
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleDrawerOpen = () => {
        this.setState({ open: true });
    };

    handleDrawerClose = () => {
        this.setState({ open: false });
    };

    render() {
        const { classes } = this.props;

        return (
            <div className={classes.root}>
                <CssBaseline />
                <ToolbarMenu/>
                <main className={classes.content}>
                    <div className={classes.appBarSpacer} />
                    <DashboardGrid/>
                </main>
            </div>
        );
    }
}

Dashboard.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Dashboard);