package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import java.sql.Time;
import java.time.*;

public class DataBase extends AppCompatActivity {
    SQLiteDatabase sqlDB;
    anDBHelper anDBHelper;

    public DataBase() {
        anDBHelper = new anDBHelper(this);
    }

    public void selectTimeSetting() {
        sqlDB = anDBHelper.getWritableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT TimeSetting FROM AppSettingTable;", null);
        System.out.println(cursor.getString(0));
        sqlDB.close();
    }

    public void updateTimeSetting(Time time) {
        sqlDB = anDBHelper.getWritableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT RecordAgreed FROM AppSettingTable;", null);
        String temp = cursor.getString(0);

        sqlDB.execSQL("CREATE TABlE AppSettingTable2 ( TimeSetting Time, RecordAgreed Boolean);");
        sqlDB.execSQL("INSERT into AppSettingTable2 (TimeSetting, RecordAgreed)" + time.toString() + ", SELECT RecordAgreed FROM AppSettingTable");
        sqlDB.execSQL("DROP TABLE AppSettingTable;");
        sqlDB.execSQL("ALTER TABLE AppSettingTable2 RENAME TO AppSettingTable;");
        sqlDB.close();
    }

    public void selectDiaryDB() {

    }

    public void insertDiaryDB() {

    }

    public void selectStroyDB() {

    }

    public boolean updateRecordAgreed() {

        return true;
    }
}

class anDBHelper extends SQLiteOpenHelper {
    public anDBHelper(DataBase context) {
        super(context, "MainDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DiaryTable ( Diary TEXT, FaceAnalysResult REAL, FacePhotoPath CHAR(30));");
        db.execSQL("CREATE TABLE StoryTable ( Story TEXT, StoryViewState INTEGER, RecommandMovie CHAR(20), RecommandMusic CHAR(20), RecommandBook CHAR(20));");
        db.execSQL("CREATE TABLE AppSettingTable ( TimeSetting Time, RecordAgreed Boolean);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MainDB");
        onCreate(db);

    }
}
