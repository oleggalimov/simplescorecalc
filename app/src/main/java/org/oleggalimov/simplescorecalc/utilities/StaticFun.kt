package org.oleggalimov.simplescorecalc.utilities

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun toastWithVibration (context:Context, message:String, vibration:Boolean) {

    if (!message.isBlank()) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        if (vibration) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val vibrator= context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                (context.getSystemService(AppCompatActivity.VIBRATOR_SERVICE) as Vibrator).vibrate(150)
            }
        }


    }

}