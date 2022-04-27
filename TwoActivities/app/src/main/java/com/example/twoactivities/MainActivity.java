package com.example.twoactivities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mMessageEditText;
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE =
            "com.example.android.twoactivities.extra.MESSAGE";

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result != null && result.getResultCode() == RESULT_OK){
                        String reply = result.getData().getStringExtra(SecondActivity.EXTRA_REPLY);
                        mReplyHeadTextView.setVisibility(View.VISIBLE);
                        mReplyTextView.setText(reply);
                        mReplyTextView.setVisibility(View.VISIBLE);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startForResult.launch(intent);
    }


}