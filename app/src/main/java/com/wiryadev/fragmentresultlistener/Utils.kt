package com.wiryadev.fragmentresultlistener

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun Fragment.initFragmentResultListener(
    requestKey: String,
    fragmentManager: FragmentManager = childFragmentManager,
    owner: LifecycleOwner = viewLifecycleOwner,
    crossinline onResult: (Bundle) -> Unit,
) = fragmentManager.setFragmentResultListener(requestKey, owner) { _, bundle -> onResult(bundle) }

inline fun FragmentActivity.initFragmentResultListener(
    requestKey: String,
    fragmentManager: FragmentManager = supportFragmentManager,
    owner: LifecycleOwner = this,
    crossinline onResult: (Bundle) -> Unit,
) = fragmentManager.setFragmentResultListener(requestKey, owner) { _, bundle -> onResult(bundle) }