package com.example.baseandroidproject.sessions

import UserDetail
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<UserDetail>{
    override val defaultValue: UserDetail = UserDetail.getDefaultInstance()


    override suspend fun readFrom(input: InputStream): UserDetail {
        try {
            return UserDetail.parseFrom(input)
        }catch (ex : InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto", ex)
        }
    }

    override suspend fun writeTo(t: UserDetail, output: OutputStream) {
        t.writeTo(output)
    }

}