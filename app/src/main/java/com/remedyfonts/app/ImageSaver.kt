package com.remedyfonts.app

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

object ImageSaver {

    fun saveTextAsImage(context: Context, text: String, styleName: String) {
        val width = 1080
        val height = 1080
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Gradient background
        val paint = Paint()
        paint.shader = android.graphics.LinearGradient(
            0f, 0f, width.toFloat(), height.toFloat(),
            Color.parseColor("#FF007F"), Color.parseColor("#9D00FF"),
            android.graphics.Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

        // Text
        val textPaint = Paint().apply {
            color = Color.WHITE
            textSize = 80f
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
            isFakeBoldText = true
        }
        canvas.drawText(text, width / 2f, height / 2f, textPaint)

        // Style name watermark
        val watermarkPaint = Paint().apply {
            color = Color.parseColor("#AAFFFFFF")
            textSize = 30f
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Remedy Fonts - $styleName", width / 2f, height - 100f, watermarkPaint)

        // Save
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, "Remedy_${System.currentTimeMillis()}.png")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                    put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/RemedyFonts")
                }
                val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                uri?.let {
                    context.contentResolver.openOutputStream(it)?.use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    }
                }
            } else {
                val dir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "RemedyFonts")
                if (!dir.exists()) dir.mkdirs()
                val file = File(dir, "Remedy_${System.currentTimeMillis()}.png")
                FileOutputStream(file).use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                }
            }
            Toast.makeText(context, "Saved to Pictures/RemedyFonts", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
        }
    }
}