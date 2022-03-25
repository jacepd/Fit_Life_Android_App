package com.example.Fit_Life;


        import androidx.lifecycle.LiveData;
        import androidx.room.*;

        import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserTable weatherTable);

    @Query("DELETE FROM weather_table")
    void deleteAll();

    @Query("SELECT * from weather_table ORDER BY weatherdata ASC")
    LiveData<List<UserTable>> getAll();
}
