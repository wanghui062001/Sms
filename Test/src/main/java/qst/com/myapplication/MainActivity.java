package qst.com.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.button_start)
    Button button;
    @BindView(R.id.button_stop)
    Button button2;
    @BindView(R.id.button_bind)
    Button button3;
    @BindView(R.id.button_unbind)
    Button button4;
    @BindView(R.id.activity_main)
    ConstraintLayout activityMain;

    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        //绑定成功之后的回掉方法
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService myservice=((MyService.MyBinder) service).getService();
            myservice.playMusic();

        }

        @Override

        //系统回收SERVICE
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.button_start, R.id.button_stop, R.id.button_bind, R.id.button_unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_start:
                Intent intent1=new Intent(MainActivity.this,MyService.class);
                intent1.putExtra("main","hello");
                startService(intent1);
                break;
            case R.id.button_stop:
                Intent intent2=new Intent(MainActivity.this,MyService.class);
                stopService(intent2);
                break;
            case R.id.button_bind:
                Intent intent3=new Intent(MainActivity.this,MyService.class);
                bindService(intent3,serviceConnection,BIND_AUTO_CREATE);

                break;
            case R.id.button_unbind:
                unbindService(serviceConnection);
                break;
        }
    }


}
