package com.example.baseandroidproject.data.workmanager

import android.content.Context
import android.content.pm.ServiceInfo
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.baseandroidproject.R
import com.google.firebase.storage.FirebaseStorage
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.tasks.await
import java.io.File

@HiltWorker
class UploadImageWorkManager @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters
) : CoroutineWorker(context, params) {
    private val storage = FirebaseStorage.getInstance()

    override suspend fun doWork(): Result {
        setForeground(notificationService())
        return try {
            val filePath = inputData.getString(TEMP_FILE_PATH)
                ?: return Result.failure(
                    workDataOf(ERROR_KEY to "No file path provided")
                )

            val file = File(filePath)
            val storageRef = FirebaseStorage.getInstance().reference
                .child("images/${System.currentTimeMillis()}.jpg")
            storageRef.putFile(Uri.fromFile(file)).await()

            Result.success()
        } catch (e: Exception) {
            Result.failure(
                workDataOf(ERROR_KEY to (e.message ?: "Upload failed"))
            )
        }
    }

    private fun notificationService(): ForegroundInfo {
        val notification = NotificationCompat.Builder(applicationContext, "UPLOAD")
            .setContentTitle("Upload")
            .setTicker("Upload")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setAutoCancel(false)
            .build()

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(1, notification)
        }
    }

    companion object {
        const val TEMP_FILE_PATH = "temp_file_path"
        const val ERROR_KEY = "error_key"
    }

}




