import React, { useEffect } from 'react';
import DashboardBox from '../component/shared/DashboardBox';
import { Box, Grid, Paper, Typography } from '@mui/material';
import { fetchData } from '../app/apiSlice';
import { useDispatch, useSelector } from 'react-redux';

const DashboardLayout = () => {
    const { loading, users, error } = useSelector((state) => state.users);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(fetchData());
    }, [dispatch]);

    if (loading) {
        return <Box display='flex' height='100vh' alignItems='center' justifyContent='center'>Loading...</Box>;
    }

    if (error) {
        return <Box display='flex' height='100vh' alignItems='center' justifyContent='center'>Error: {error}</Box>;
    }

    return (
        <Paper elevation={3} sx={{ margin: 5, borderRadius: 3, minHeight: '700px', bgcolor:'#2980b9'}}>
            <Typography variant='h4' p={5} align='center' sx={{ color: 'white' }}>Informasi Tentang Lokomotif</Typography>
            <Grid container spacing={2} padding={5} justifyContent='center'>
                <Grid item xs={12} sm={6} md={3}>
                    <DashboardBox title='Total Locomotif' data={users.totalLocomotif} />
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                    <DashboardBox title='Total Poor' data={users.totalLocomotifPoor} />
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                    <DashboardBox title='Total Good' data={users.totalLocomotifGood} />
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                    <DashboardBox title='Total Excellent' data={users.totalLocomotifExcellent} />
                </Grid>
            </Grid>
        </Paper>
    );
};

export default DashboardLayout;
