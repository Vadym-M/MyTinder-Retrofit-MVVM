package com.vinade.mytinder.utils

import android.app.Activity
import com.vinade.mytinder.model.Result
import com.vinade.mytinder.view.PersonFragment

interface FragmentCommunicator {
    fun passResult(result: Result)
}
fun Activity.startFragmentNavigator(): FragmentCommunicator{
    return  PersonFragment() as FragmentCommunicator
}