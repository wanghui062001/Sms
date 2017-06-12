package qst.com.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/4/14.
 */

public class Main extends Activity {
    TextView tv1, tv2;
    Button btn1;
    EditText et1, et2;
    CheckBox cb_remember;

    private final static String SP_INFOS = "login";
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 1, 1, "设置");
        menu.add(1, 2, 1, "退出");

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                Toast.makeText(Main.this, "您点击的设置", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(Main.this, "您点击的退出", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder adb = new AlertDialog.Builder(Main.this);
                adb.setTitle("提示");
                adb.setMessage("您确定要退出吗？");
                adb.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                adb.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.create().show();

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    /*长按某一按钮或什么的时候弹出*/
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("您想要做什么？");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        menu.add(0, 1, 0, "复制");
        menu.add(0, 2, menu.NONE, "粘贴");
        menu.add(0, 3, menu.NONE, "剪切");
        menu.add(1, 4, menu.NONE, "退出");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(Main.this, "您点了复制", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(Main.this, "您点了粘贴", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(Main.this, "您点了剪切", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                AlertDialog.Builder adb = new AlertDialog.Builder(Main.this);
                adb.setTitle("提示");
                adb.setMessage("您确定要退出吗？");
                adb.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                adb.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.create().show();

                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayou);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        cb_remember = (CheckBox) findViewById(R.id.checkBox);
        btn1 = (Button) findViewById(R.id.btn1);

        this.registerForContextMenu(btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater LayoutInflater = getLayoutInflater();
                View view = LayoutInflater.inflate(R.layout.item, null);
                TextView tv3 = (TextView) view.findViewById(R.id.tv2);
                TextView tv4 = (TextView) view.findViewById(R.id.tv4);

                String name = et1.getText().toString();
                String password = et2.getText().toString();
                tv3.setText(name);
                tv4.setText(password);

               /*创建提示框，将姓名和密码添加到提示框中*/
                AlertDialog.Builder adb = new AlertDialog.Builder(Main.this);
                adb.setTitle("提示");
                adb.setView(view);
                adb.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Main.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Main.this,MainActivity.class);
                        startActivity(intent);

                    }
                });
                adb.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                adb.create();
                adb.show();
            }
        });


    }

    public void rememberMe(String username, String password) {
        SharedPreferences sp = getSharedPreferences(SP_INFOS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.commit();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (cb_remember.isChecked()) {
            this.rememberMe(et1.getText().toString(), et2.getText().toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkIfRemember();
    }

    private void checkIfRemember() {
        SharedPreferences sp=getSharedPreferences(SP_INFOS,MODE_PRIVATE);
        String username=sp.getString(USERNAME,null);
        String password=sp.getString(PASSWORD,null);

        if(username!=null&&password!=null){
            et1.setText(username);
            et2.setText(password);
            cb_remember.setChecked(true);
        }
    }
}
