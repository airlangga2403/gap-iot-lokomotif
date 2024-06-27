import React from 'react';
import { Grid, Paper, Typography } from '@mui/material';

const Navbar = () => {
    return (
        <Paper elevation={3} sx={{ marginBottom: 3, borderRadius: 3,bgcolor:'#2c3e50' }}>
            <Grid container justifyContent='center' spacing={4} p={2}>
                <Grid item>
                    <Typography variant='h4' fontFamily='Roboto' sx={{ color: '#ecf0f1' }}>Dashboard</Typography>
                </Grid>
                <Grid item>
                    <Typography variant='h4' fontFamily='Roboto' sx={{ color: '#ecf0f1' }}>Locomotif</Typography>
                </Grid>
            </Grid>
        </Paper>
    );
};

export default Navbar;
