package com.lms.worldoflol.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.lms.worldoflol.R
import kotlinx.coroutines.ExperimentalCoroutinesApi



@ExperimentalCoroutinesApi
@Composable
fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = remember {
        mutableStateOf(null)
    }

    // show default image while image loads
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(R.drawable.ic_logout)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onLoadStarted(placeholder: Drawable?) {
                super.onLoadStarted(placeholder)
            }
        })

    // get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })


    return bitmapState
}