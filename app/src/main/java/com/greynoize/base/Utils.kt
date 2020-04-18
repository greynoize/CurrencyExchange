package com.greynoize.base

import android.app.Application
import android.content.Context
import java.io.IOException
import java.io.InputStream

class Utils {
    companion object {
        fun getJsonStringFromFile(path: String, context: Context): String? {
            return try {
                val inputStream = context.assets.open(path) // Or you can store json in String
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer)
            } catch (e: IOException) {
                throw IOException("Error when reading JSON with path: $path")
            }
        }
    }
}