package qst.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBOpenHelper myHelper;
    SQLiteDatabase db;
    MyAdapter adapter;
    ListView lv;
    Button btn_add,btn_clear;

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
        setContentView(R.layout.listmain);
        lv= (ListView) findViewById(R.id.list);
        btn_add= (Button) findViewById(R.id.btn_add);
        btn_clear= (Button) findViewById(R.id.btn_clear);




        myHelper=new DBOpenHelper(this,DB_NAME,null,1);
        db=myHelper.getWritableDatabase();

        List<PeopleInfo> list=this.getBasicInfo();
        adapter=new MyAdapter(this,list);
        lv.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewPersonActivity.class);
                startActivity(intent);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteAll()>0){
                    Toast.makeText(MainActivity.this,"清空成功",Toast.LENGTH_SHORT).show();

                    myHelper=new DBOpenHelper(v.getContext(),DB_NAME,null,1);
                    db=myHelper.getWritableDatabase();
                    List<PeopleInfo> list=getBasicInfo();
                    adapter=new MyAdapter(v.getContext(),list);
                    lv.setAdapter(adapter);
                }
            }

            public int deleteAll() {
                return db.delete(TABLE_NAME,null,null);
            }
        });

    }

    private List<PeopleInfo> getBasicInfo() {
        Cursor c=db.query(TABLE_NAME,new String[]{},null,null,null,null,ID);
        List<PeopleInfo> list=new ArrayList<>();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            String name=c.getString(c.getColumnIndex(NAME));
            String phone_number=c.getString(c.getColumnIndex(PHONE_NUMBER));
            String address=c.getString(c.getColumnIndex(ADDRESS));
            String email=c.getString(c.getColumnIndex(EMAIL));

            PeopleInfo d=new PeopleInfo(name,phone_number,address,email);
            d.setID(c.getInt(c.getColumnIndex(ID)));
            list.add(d);

        }
        c.close();
        return list;
    }

}
