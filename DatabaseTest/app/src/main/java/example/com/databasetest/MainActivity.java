package example.com.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"BookStore.db", null, 2);
        Button createDatabase = (Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book",null,values);
                values.clear();

                values.put("name", "Tuo Lei");
                values.put("author", "Xiao Tuo Lei");
                values.put("pages", 500);
                values.put("price", 20);
                db.insert("Book",null,values);
            }
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book",values,"name = ?",new String[]{"The Da Vinci Code"});
            }
        });

        Button deleteDate = findViewById(R.id.delete_date);
        deleteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[]{"480"});
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG = "QUERY";
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        //遍历所有的 Cursor 对象，去除数据并打印
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name =  cursor.getString(cursor.getColumnIndex("name"));
                        String author =  cursor.getString(cursor.getColumnIndex("author"));
                        int pages =  cursor.getInt(cursor.getColumnIndex("pages"));
                        double prices =  cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "ID:"+id+"; name:" + name + "; author:" + author + "; pages:" + pages + "; prices:" + prices);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
