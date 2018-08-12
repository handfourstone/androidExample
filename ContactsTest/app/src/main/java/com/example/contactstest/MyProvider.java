package com.example.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher  = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.contactstest.provider","table1", TABLE1_DIR);
        uriMatcher.addURI("com.example.contactstest.provider","table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.example.contactstest.provider","table2", TABLE2_DIR);
        uriMatcher.addURI("com.example.contactstest.provider","table2/#", TABLE2_ITEM);
    }


    // 根据传入的 uri 返回相应的 MIME 类型。
    // MIME 由三部分组成，参考 getType() 的实现。
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contactstest.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contactstest.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contactstest.provider.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contactstest.provider.table2";
            default:
                break;
        };
        return null;
    }

    // 更新内容提供器中已有的数据。使用 uri 参数来确定要更新哪一张表中的数据，新数据保存在 values 参数中，
    // selection 和 selectionArgs 参数用于约束查询哪些行，受影响的行数将作为返回值返回。
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    //向内容提供器中添加一条数据。使用 uri 参数来确定要添加到的表，待添加的数据保存在 values 中。添加完成之后返回一个表示这条新纪录的 uri。
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    //初始化内容提供其的时候调用，通常会在这里完成对数据库的创建和升级等操作，返回true表示内容提供器初始化成功。
    @Override
    public boolean onCreate() {
        return false;
    }

    // 从内容提供器中删除数据。使用 uri 参数来确定删除哪一张表中的数据。selection 和 selectionArgs 参数用于约束查询哪些行，被删除的行数将作为返回值返回。
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    //从内容提供器中查询数据。使用 uri 参数来确定要查询哪张表，projection 参数用于确定要查询哪些列。
    //selection 和 selectionArgs 参数用于约束查询哪些行，sortOrder 参数用于对结果进行拍寻，
    //查询的结果放在 cursor 对象中返回。
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                // 查询 table1 表中的所有数据
                break;
            case TABLE1_ITEM:
                // 查询 table1 表中的单条数据
                break;
            case TABLE2_DIR:
                // 查询 table2 表中的所有数据
                break;
            case TABLE2_ITEM:
                // 查询 table2 表中的单条数据
                break;
            default:
                break;
        }
        return null;
    }
}
