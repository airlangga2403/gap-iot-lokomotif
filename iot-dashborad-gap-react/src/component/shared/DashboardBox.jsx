import { Grid, Paper, Typography, Stack } from '@mui/material';
import StackedLineChartIcon from '@mui/icons-material/StackedLineChart';

const DashboardBox = ({ title, data }) => {
    return (
        <Paper elevation={3} sx={{ padding: 3, borderRadius: 3, textAlign: 'center', bgcolor: '#f5f5f5' }}>
            <Grid container justifyContent='center' alignItems='center' direction='column' spacing={2}>
                <Grid item>
                    <Typography variant='h6' color='#2c3e50'>{title}</Typography>
                </Grid>
                <Grid item>
                    <Stack direction="row" alignItems="center" spacing={1}>
                        <Typography variant='h4' color='#34495e'>{data}</Typography>
                        <StackedLineChartIcon sx={{ color: '#3498db' }} />
                    </Stack>
                </Grid>
            </Grid>
        </Paper>
    );
};

export default DashboardBox;
