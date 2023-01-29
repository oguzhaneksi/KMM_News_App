package com.newsapp.multiplatform.util

import android.os.Build
import android.os.LocaleList
import java.util.Locale

actual val deviceLanguage: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    LocaleList.getDefault().get(0).language
} else {
    Locale.getDefault().language
}