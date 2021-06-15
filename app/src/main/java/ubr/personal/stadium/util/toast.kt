package ubr.personal.stadium.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.showSnack(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_SHORT).show()
}