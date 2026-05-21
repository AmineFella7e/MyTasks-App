package com.example.mytasks;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {

    private TextView textWelcome;
    private TextView textStats;
    private String username = "Invité";
    private int totalTasks = 0;
    private int completedTasks = 0;

    public InfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textWelcome = view.findViewById(R.id.textFragmentWelcome);
        textStats = view.findViewById(R.id.textFragmentStats);
        Button btnShowInfo = view.findViewById(R.id.btnFragmentInfo);

        btnShowInfo.setOnClickListener(clickedView -> {
            Log.d("InfoFragment", "Bouton info clique par : " + username);
            new AlertDialog.Builder(requireContext())
                    .setTitle(R.string.fragment_dialog_title)
                    .setMessage(getString(R.string.fragment_dialog_message, username, totalTasks, completedTasks))
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        });

        render();
    }

    public void updateContent(String username, int totalTasks, int completedTasks) {
        this.username = username == null || username.trim().isEmpty() ? "Invité" : username;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        render();
    }

    private void render() {
        if (textWelcome == null || textStats == null) {
            return;
        }
        textWelcome.setText(getString(R.string.fragment_welcome, username));
        textStats.setText(getString(R.string.fragment_stats, totalTasks, completedTasks));
    }
}
