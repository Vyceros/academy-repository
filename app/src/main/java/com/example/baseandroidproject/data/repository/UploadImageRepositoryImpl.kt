package com.example.baseandroidproject.data.repository

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.baseandroidproject.data.util.SafeCall
import com.example.baseandroidproject.data.workmanager.UploadImageWorkManager
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.repository.UploadImageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class UploadImageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val workManager: WorkManager,
    private val apiCall: SafeCall
) : UploadImageRepository {
    override suspend fun uploadImage(byteArray: ByteArray): Flow<Resource<Unit>> = apiCall.safeCall {
        val file = File(context.cacheDir, "image_${System.currentTimeMillis()}.jpg")
        file.writeBytes(byteArray)

        val uploadRequest = OneTimeWorkRequest.Builder(UploadImageWorkManager::class.java)
            .setInputData(workDataOf(UploadImageWorkManager.TEMP_FILE_PATH to file.absolutePath))
            .build()

        val workInfo = workManager.beginUniqueWork(
            "UPLOAD_WORKER",
            ExistingWorkPolicy.KEEP,
            uploadRequest
        ).enqueue()

        val result = workManager.getWorkInfoById(uploadRequest.id).get()

        when (result?.state) {
            WorkInfo.State.SUCCEEDED -> Unit
            WorkInfo.State.FAILED -> {
                val errorData = result.outputData.getString(UploadImageWorkManager.ERROR_KEY)
                throw Exception(errorData ?: "Upload failed")
            }

            else -> throw Exception("Upload did not complete")
        }
    }
}