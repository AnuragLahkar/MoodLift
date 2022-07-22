package com.AnuragKonark.MoodLift.tracker.navigation;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.AnuragKonark.MoodLift.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
