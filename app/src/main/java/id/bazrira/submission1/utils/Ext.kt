package id.bazrira.submission1.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun AppCompatImageView.load(url: String) {
  Glide.with(context).asBitmap()
    .load(url)
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .into(this)
}