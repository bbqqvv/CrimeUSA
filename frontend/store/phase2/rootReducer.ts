import { combineReducers } from '@reduxjs/toolkit';
import authSlice from './slices/authSlice';
import reportSlice from './slices/reportSlice';
import evidenceSlice from './slices/evidenceSlice';
import uiSlice from './slices/uiSlice';

export const rootReducer = combineReducers({
  auth: authSlice,
  reports: reportSlice,
  evidence: evidenceSlice,
  ui: uiSlice,
});