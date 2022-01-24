package com.vinade.mytinder.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vinade.mytinder.model.Result
import com.vinade.mytinder.view.StartFragment

interface Navigator {
    fun btnLike()
    fun btnDisLike()
    fun chooseGender(gender:String)
}
fun Fragment.navigator(): Navigator{
    return  requireActivity() as Navigator
}
