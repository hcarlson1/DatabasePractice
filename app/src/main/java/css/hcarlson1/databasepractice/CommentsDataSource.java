package css.hcarlson1.databasepractice;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class houses all of the CRUD methods. It is the DOA and maintains the database connection.
 *
 * Created by hcarlson1 on 3/31/2017.
 */

public class CommentsDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    /**
     * CommentsDataSource is the constructor for this class
     * @param Context context which allows access to application-specific resources and classes
     */
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    /**
     * open() will set the database vraible to a writeable version of the database
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * close() will close the database
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * createComment is the Create in the CRUD. Adds a new comment entry to the database
     * @param String comment to be added to the database
     * @return Comment added to the database
     */
    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        //insert(String table, String nullColumnHack, ContentValues values)
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        //INSERT INTO table_comments (null) VALUES (null);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    /**
     * deleteComment is the Delete in the CRUD.This will deleted a specified comment.
     * @param Comment comment is the comment that will be removed.
     */
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /**
     * getAllComments is the Read in the CRUD. This method will return all of the comments entered in the database.
     * @return List<> comments a list array of the comments
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    /**
     * cursorToComment is the method used to move through the database and to return a cursor of information
     * @param Cursor cursor
     * @return Comment object
     */
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}
