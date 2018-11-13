package com.gpc.perfectarchitecture.model.db.helper;

import android.database.sqlite.SQLiteDatabase;
import com.gpc.perfectarchitecture.utils.LogUtil;

import java.io.File;

/*
 * @NAME: SQLHelper
 * @Package: com.gpc.perfectarchitecture.model.db.helper
 * @PoemAuthor : pcg
 * @Create at : 2018/11/13 下午2:15
 * @Description:
 */
public class SQLHelper {

    public static SQLiteDatabase openDatabase(String dbFilePath) {
        LogUtil.e("dbFilePath = " + dbFilePath);
        File dbFile = new File(dbFilePath);
        if (dbFile.exists()) {
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
            LogUtil.e("database = " + database);
            return database;
        } else {
            return null;
        }
    }

}
