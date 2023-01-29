package com.newsapp.multiplatform.util

import platform.Foundation.NSLocaleLanguageCode

actual val deviceLanguage: String = NSLocaleLanguageCode.toString()