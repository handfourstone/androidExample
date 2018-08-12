package example.com.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("MyBook");
                book.setAuthor("TuoLei");
                book.setPages(200);
                book.setPrice(16.96);
                book.save();
            }
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setAuthor("TuoLei");
                book.setToDefault("pages");
                book.updateAll("author = ? and pages > ?", "Liang", "100");
            }
        });

        Button deleteData = findViewById(R.id.delete_date);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class, "pages = ?", "0");
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG = "findAll";
               // List<Book> books = LitePal.findAll(Book.class);
                List<Book> books = LitePal.select("name", "author")
                                           .where("pages > ?", "100")
                                           .order("pages")
                                           .limit(10)
                                           .offset(1)
                                           .find(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "name:" + book.getName());
                    Log.d(TAG, "author:" + book.getAuthor());
                }
            }
        });
    }
}
