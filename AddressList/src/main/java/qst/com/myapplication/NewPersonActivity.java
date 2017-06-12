package qst.com.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/4/25.
 */
public class NewPersonActivity extends Activity{
    DBOpenHelper myHelper;
    SQLiteDatabase db;
    EditText et_name,et_phone,et_address,et_email;
    ListView lv;
    Button btn_ok,btn_back;

    int flag=0;//标识新增0或修改1
    int id=0;//标识修改ID

    public static final String DB_NAME="datebase.db";
    public static final String TABLE_NAME="personInfo";
    public static final String ID="ID";
    public static final String NAME="Name";
    public static final String PHONE_NUMBER="Phone_number";
    public static final String ADDRESS="Address";
    public static final String EMAIL="Email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_layout);

        myHelper=new DBOpenHelper(this,DB_NAME,null,1);
        db=myHelper.getWritableDatabase();

        et_name= (EditText) findViewById(R.id.et_name);
        et_phone= (EditText) findViewById(R.id.et_phone);
        et_address= (EditText) findViewById(R.id.et_address);
        et_email= (EditText) findViewById(R.id.et_email);

        btn_ok= (Button) findViewById(R.id.btn_ok);
        btn_back= (Button) findViewById(R.id.btn_back);

        if(getIntent().getIntExtra("id",0)>0){
            id=getIntent().getIntExtra("id",0);
            setPersonInfo(id);
            flag=1;
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues value=new ContentValues();
                value.put(NAME,et_name.getText().toString());
                value.put(PHONE_NUMBER,et_phone.getText().toString());
                value.put(ADDRESS,et_address.getText().toString());
                value.put(EMAIL,et_email.getText().toString());

                long result;
                if(flag==0)
                    result=db.insert(TABLE_NAME,null,value);
                else
                    result=db.update(TABLE_NAME,value,"ID="+id,null);

                db.close();
                if(result>0){
                    Toast.makeText(NewPersonActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(NewPersonActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void setPersonInfo(int id) {
        Cursor c=db.query(TABLE_NAME,new String[]{},"ID="+id,null,null,null,null);
        if(c.moveToFirst()){
            String name=c.getString(c.getColumnIndex(NAME));
            String phone_number=c.getString(c.getColumnIndex(PHONE_NUMBER));
            String address=c.getString(c.getColumnIndex(ADDRESS));
            String email=c.getString(c.getColumnIndex(EMAIL));

            PeopleInfo d=new PeopleInfo(name,phone_number,address,email);

            d.setID(c.getInt(c.getColumnIndex("ID")));

            et_name.setText(d.getName());
            et_phone.setText(d.getPhone_number());
            et_address.setText(d.getAddress());
            et_email.setText(d.getEmail());

        }
        c.close();
    }


}
