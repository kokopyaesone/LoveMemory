package kopyae.lovememory.com.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Ko Pyae on 2/5/2018.
 */
@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCEE;

    public abstract UserDao userDao();

    public static AppDatabase getAppDatabase(Context context){
        if (INSTANCEE == null){
            INSTANCEE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,"user_database")
                            .allowMainThreadQueries()
                    .build();
        }
        return INSTANCEE;
    }
    public static void destoryInstance(){
        INSTANCEE = null;
    }
}
