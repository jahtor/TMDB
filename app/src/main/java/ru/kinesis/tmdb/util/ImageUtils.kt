package ru.kinesis.tmdb.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ru.kinesis.tmdb.R

//const val DEFAULT_MOVIE_IMAGE = R.drawable.ic_no_movie_48

//асинхронная загрузка картинок
@Composable
fun LoadImage(
    url: String,
    @DrawableRes defaultImage: Int
): MutableState<Bitmap?>{
    val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null)}
    val context = LocalContext.current

    //показывает заглушку пока картинка загружается
    Glide.with(context)
        .asBitmap()
        .load(defaultImage)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                bitmapState.value = null
            }
        })

    //показывает картинку из сети
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                bitmapState.value = null
            }
        })
    return bitmapState
}
