package kopyae.lovememory.com.database;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by Ko Pyae on 2/5/2018.
 */

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getSimpleName();

    public static void populateAsync(@NonNull final AppDatabase db){
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }
    public static void populateSynce(@NonNull final AppDatabase db){
        populateWithTestData(db);
    }

    private static User addUser(final AppDatabase db, User user){
        db.userDao().insertAll(user);
        return user;
    }

    private static void populateWithTestData(AppDatabase db){
        User user = new User();
        user.setFirstName("Ko Ko");
        user.setLastName("Pyae Sone");
        user.setAge(23);
        addUser(db,user);


    }

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{
        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db){
            mDb = db;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            populateWithTestData(mDb);
            return null;
        }
    }
}
