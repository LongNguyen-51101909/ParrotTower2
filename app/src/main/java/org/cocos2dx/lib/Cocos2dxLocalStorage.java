package org.cocos2dx.lib;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Cocos2dxLocalStorage
{
  private static String DATABASE_NAME = "jsb.sqlite";
  private static final int DATABASE_VERSION = 1;
  private static String TABLE_NAME = "data";
  private static final String TAG = "Cocos2dxLocalStorage";
  private static SQLiteDatabase mDatabase = null;
  private static DBOpenHelper mDatabaseOpenHelper = null;

  public static void destory()
  {
    if (mDatabase != null) {
      mDatabase.close();
    }
  }

  public static String getItem(String paramString)
  {
    localCursor = null;
    Object localObject2 = null;
    localObject1 = localCursor;
    try
    {
      String str = "select value from " + TABLE_NAME + " where key=?";
      localObject1 = localCursor;
      localCursor = mDatabase.rawQuery(str, new String[] { paramString });
      paramString = (String)localObject2;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = (String)localObject1;
        continue;
        localObject1 = paramString;
        paramString = localCursor.getString(localCursor.getColumnIndex("value"));
      }
    }
    localObject1 = paramString;
    if (!localCursor.moveToNext()) {}
    for (;;)
    {
      localObject1 = paramString;
      localCursor.close();
      localObject1 = paramString;
      if (paramString == null) {
        localObject1 = "";
      }
      return (String)localObject1;
      if (paramString == null) {
        break;
      }
      localObject1 = paramString;
      Log.e("Cocos2dxLocalStorage", "The key contains more than one value.");
    }
  }

  public static boolean init(String paramString1, String paramString2)
  {
    if (Cocos2dxActivity.getContext() != null)
    {
      DATABASE_NAME = paramString1;
      TABLE_NAME = paramString2;
      mDatabaseOpenHelper = new DBOpenHelper(Cocos2dxActivity.getContext());
      mDatabase = mDatabaseOpenHelper.getWritableDatabase();
      return true;
    }
    return false;
  }

  public static void removeItem(String paramString)
  {
    try
    {
      String str = "delete from " + TABLE_NAME + " where key=?";
      mDatabase.execSQL(str, new Object[] { paramString });
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  public static void setItem(String paramString1, String paramString2)
  {
    try
    {
      String str = "replace into " + TABLE_NAME + "(key,value)values(?,?)";
      mDatabase.execSQL(str, new Object[] { paramString1, paramString2 });
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }

  private static class DBOpenHelper
    extends SQLiteOpenHelper
  {
    DBOpenHelper(Context paramContext)
    {
      super(Cocos2dxLocalStorage.DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Cocos2dxLocalStorage.TABLE_NAME + "(key TEXT PRIMARY KEY,value TEXT);");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Log.w("Cocos2dxLocalStorage", "Upgrading database from version " + paramInt1 + " to " + paramInt2 + ", which will destroy all old data");
    }
  }
}