package css.hcarlson1.databasepractice;

/**
 * This is the object that we are creating. It has the data that will be saved and shown in the UI of the app
 *
 * Created by hcarlson1 on 3/31/2017.
 */

public class Comment {
    private long id;
    private String comment;

    /**
     * getId method will retrieve the id of the entry in the database
     *
     * @return a Long id
     */
    public long getId() {
        return id;
    }

    /**
     * setId method will assign the id to the database entry
     *
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * getComment method will retrieve the comment in a certain database entry
     *
     * @return a Long id
     */
    public String getComment() {
        return comment;
    }

    /**
     * setComment method will retrieve the comment of the entry in the database
     *
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    /**
     * toString method will retrieve the comment of the entry in the database and return it as a string
     *
     */
    @Override
    public String toString() {
        return comment;
    }
}
