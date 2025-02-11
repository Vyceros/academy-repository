    package com.example.baseandroidproject.data.local.storage.user_details

    import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

    @Dao
    interface TokenDao {

        @Query("SELECT * FROM user_tokens")
        suspend fun getUserToken() : UserToken?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertUserToken(userToken: UserToken)

        @Query("DELETE FROM user_tokens")
        suspend fun clearToken()
    }