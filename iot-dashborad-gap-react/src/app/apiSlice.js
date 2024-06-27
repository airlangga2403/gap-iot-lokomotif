import { createSlice } from '@reduxjs/toolkit';
import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const initialState = {
    loading: false,
    users: [],
    error: '',
}

export const fetchData = createAsyncThunk('users/fetchData', () => {
    return axios
        .get('http://localhost:8082/report')
        .then(response => {
            console.log('Response Data:', response.data); // Add console log here
            return response.data;
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            throw error; // Re-throw error to propagate it to the Redux store
        });
});

const apiSlice = createSlice({
    name: 'users',
    initialState,
    extraReducers: (builder) => {
        builder
            .addCase(fetchData.pending, state => {
                state.loading = true;
                state.error = '';
            })
            .addCase(fetchData.fulfilled, (state, action) => {
                state.loading = false;
                state.users = action.payload;
                state.error = '';
            })
            .addCase(fetchData.rejected, (state, action) => {
                state.loading = false;
                state.error = action.error.message;
            });
    }
});

export default apiSlice.reducer;