package com.crypto.resources

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

interface ResProvider {
    fun getStringRes(@StringRes resId: Int): String

    fun getStringRes(@StringRes resId: Int, vararg args: Any): String

    @ColorInt
    fun getColor(@ColorRes resId: Int): Int
}

class ResourcesProviderImpl(
    private val context: Context
) : ResProvider {

    private val resources = context.resources

    override fun getStringRes(@StringRes resId: Int): String =
        resources.getString(resId)

    override fun getStringRes(resId: Int, vararg args: Any): String {
        return resources.getString(resId, *args)
    }

    override fun getColor(resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

}