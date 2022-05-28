package sg.edu.np.mad.practical3cyrus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context c, String name,SQLiteDatabase.CursorFactory factory,int version ){
        super(c, "Userdata.db", factory,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String Create_User_Data = "CREATE TABLE userdata(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Description TEXT, Followed BOOL)";
        db.execSQL(Create_User_Data);
        for (int i = 1 ; i<21 ; i++) {
            int min = -999999999;
            int max = 999999999;
            int int_random_username = (int) Math.floor(Math.random() * (max - min + 1) + min);
            int int_random_description = (int) Math.floor(Math.random() * (max - min + 1) + min);
            String newUsername = "Name" + int_random_username;
            String newDescription = "Description " + int_random_description;
            ContentValues values = new ContentValues();
            values.put("Name", newUsername);
            values.put("Description", newDescription);
            values.put("Followed", false);
            db.insert("userdata", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS userdata");
        onCreate(db);
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> userData= new ArrayList<>();
        String query = "SELECT * FROM userdata";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            Integer Id = Integer.parseInt(cursor.getString(0));
            String Name = cursor.getString(1);
            String Description = cursor.getString(2);
            Boolean Followed = Boolean.parseBoolean(cursor.getString(3));
            userData.add(new User(Name, Description, Id, Followed));
        }
        return userData;
    }
    public void updateUser(User newUser){
        Integer newUserId = newUser.id;
        String query = "UPDATE userdata SET Name=\""+newUser.name+"\""+","+"Description=\""+newUser.description+"\""+","+"Followed=\"" +newUser.followed+"\""+" WHERE Name=\"" +newUser.name+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

}
