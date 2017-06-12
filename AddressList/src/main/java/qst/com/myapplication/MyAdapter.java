package qst.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2017/4/25.
 */

public class MyAdapter extends BaseAdapter{
    DBOpenHelper myHelper;
    SQLiteDatabase db;

    public static final String DB_NAME="datebase.db";
    public static final String TABLE_NAME="personInfo";
    public static final String ID="ID";
    public static final String NAME="Name";
    public static final String PHONE_NUMBER="Phone_number";
    public static final String ADDRESS="Address";
    public static final String EMAIL="Email";

    private LayoutInflater inflater;
    private List<PeopleInfo> list;
    private Context context;
    public MyAdapter(Context context,List<PeopleInfo> list){
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.list=list;

        myHelper=new DBOpenHelper(context,DB_NAME,null,1);
        db=myHelper.getWritableDatabase();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_list,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_phone= (TextView) convertView.findViewById(R.id.tv_phone);
            viewHolder.btn_del= (Button) convertView.findViewById(R.id.btn_del);
            viewHolder.btn_edit= (Button) convertView.findViewById(R.id.btn_edit);

            convertView.setTag(viewHolder);

        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_phone.setText(list.get(position).getPhone_number());

        final int id=list.get(position).getID();
        viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent((MainActivity)context,NewPersonActivity.class);
                intent.putExtra("id",id);
                ((MainActivity)context).startActivity(intent);

            }
        });
        viewHolder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(TABLE_NAME,"ID="+id,null);
                Intent intent=new Intent((MainActivity)context,MainActivity.class);
                ((MainActivity)context).startActivity(intent);
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        TextView tv_phone;
        TextView tv_name;
        Button btn_del;
        Button btn_edit;

    }

}
