package com.example.baseandroidproject.data.imagecompressor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.net.toUri
import com.example.baseandroidproject.domain.repository.CompressImageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class Compressor @Inject constructor(
    @ApplicationContext private val context: Context
) : CompressImageRepository {
    override suspend fun compressImage(uri: String): ByteArray? {
        return withContext(Dispatchers.IO) {
            val bytes = context.contentResolver.openInputStream(uri.toUri())?.use { stream ->
                stream.readBytes()
            } ?: return@withContext null

            withContext(Dispatchers.Default) {
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                var outputBytes: ByteArray
                ByteArrayOutputStream().use { stream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
                    outputBytes = stream.toByteArray()
                }
                outputBytes
            }
        }
    }
}