package qst.com.sms_sendmessageservice;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_send)
    Button btnSend;

    private SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        smsManager=SmsManager.getDefault();

    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,new Intent(),0);
        smsManager.sendTextMessage(etTel.getText().toString(),null,etContent.getText().toString(),pi,null);

    }
}
