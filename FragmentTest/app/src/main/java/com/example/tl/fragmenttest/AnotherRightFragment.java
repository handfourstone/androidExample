package com.example.tl.fragmenttest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AnotherRightFragment extends Fragment {
    private Button rightButton;
    private EditText rightText;
    private MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.another_right_fragment, container, false);
        rightButton = view.findViewById(R.id.right_button);
        rightText = view.findViewById(R.id.right_text);
        mainActivity = (MainActivity)getActivity();

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = rightText.getText().toString();
                if (!"".equals(content)) {
                    mainActivity.fillLeftText(content);
                }
            }
        });

        return view;
    }
}
