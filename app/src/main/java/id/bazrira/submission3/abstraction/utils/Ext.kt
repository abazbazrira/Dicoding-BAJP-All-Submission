package id.bazrira.submission3.abstraction.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun AppCompatImageView.load(url: String) {
  Glide.with(context).asBitmap()
    .load(url)
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .into(this)
}

fun Context.showToast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}