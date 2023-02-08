package com.badlogic.sokolovtask_lab11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mDialButton = (Button) findViewById(R.id.call_button);
        final EditText mPhoneNoEt = (EditText) findViewById(R.id.edit_text_number);
        final EditText smsEdit = (EditText) findViewById(R.id.sms_send_edit);

        mDialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = mPhoneNoEt.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    // String dial = "tel:" + phoneNo;
                    // (new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo)));

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNo, null));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Введите номер телефона", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onSms(View view) {
        EditText edit_Number = (EditText) findViewById(R.id.edit_text_number);
        String phoneNo = edit_Number.getText().toString();
        EditText sms_edit = (EditText) findViewById(R.id.sms_send_edit);
        String toSms = "smsto:" + edit_Number.getText().toString();
        String messageText = sms_edit.getText().toString();
        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));

        sms.putExtra("sms_body", messageText);
        startActivity(sms);
        SmsManager.getDefault().sendTextMessage(phoneNo, null, messageText.toString(), null, null);
    }
}