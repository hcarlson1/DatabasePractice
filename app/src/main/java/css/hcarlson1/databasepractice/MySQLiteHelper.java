package css.hcarlson1.databasepractice;

/**
 * This SQlite Helper class creates the database. The onUpgrade() method will
 * delete all of the data and recreate the table. A couple of constants for
 * the table name and table columns are defined.
 *
 * Created by hcarlson1 on 3/31/2017.
 */
//package de.vogella.android.sqlite.first;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{

        public static final String TABLE_COMMENTS = "comments"; //This constant will represent table name
        public static final String COLUMN_ID = "_id"; //This constant will represent data put in for the id. this is automatically generated with the SQLite import
        public static final String COLUMN_COMMENT = "comment"; //This constant will represent data put in for table comments
        public static final String COLUMN_RATINGS = "rating";

        private static final String DATABASE_NAME = "commments.db"; //This constant will represent the database name
        private static final int DATABASE_VERSION = 2; //This constant will represent the database version


        //Database creation sql statement
         private static final String DATABASE_CREATE = "create table "
                + TABLE_COMMENTS + "( "
                + COLUMN_ID
                + " integer primary key autoincrement, "
                + COLUMN_COMMENT
                + " text not null, "
                + COLUMN_RATINGS
                + " text);";

    /**
     *MySQLiteHelper method is the constructor for this class
     *
     * @param Context context which allows access to application-specific resources and classes
     */

        public MySQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
    /**
     *onCreate method will execute the DATABASE_CREATE constant. This will result in the creation of the database.
     *
     * @param SQLiteDatabase database which allows access to SQLite database commands
     */
        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }
    /**
     *onUpgrade method will first log what is what is happening and update the database
     * version. then it ill drop all of the tables and recreate them
     *
     * @param SQLiteDatabase db which allows access to SQLite database commands
     * @param int oldVersion is the database version before this method completes
     * @param int newVersion is the database version after this method completes
     */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(MySQLiteHelper.class.getName(),
                    "Upgrading database from version "
                            + oldVersion
                            + " to "
                            + newVersion
                            + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
            onCreate(db);
        }

    }
/**
 * CREATE TABLE Persons (
 * PersonID int,
 * LastName varchar(255),
 * FirstName varchar(255),
 * Address varchar(255),
 * City varchar(255));
 *
 */
