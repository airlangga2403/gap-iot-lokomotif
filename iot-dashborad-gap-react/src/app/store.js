
import { configureStore } from '@reduxjs/toolkit';
import apiReducer from '../app/apiSlice'

const store = configureStore({
    reducer: {
        users: apiReducer,
    },
});

export default store;