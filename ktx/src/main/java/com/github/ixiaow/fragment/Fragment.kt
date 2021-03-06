package com.github.ixiaow.fragment

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

/**
 * 可以确保只有在Activity不为空的情况下执行相关代码
 */
inline fun Fragment.requireActivity(function: Activity.() -> Unit) = activity?.let(function)

/**
 * 可以确保只有在Activity不为空的情况下执行相关代码
 */
inline fun Fragment.requireContext(function: Context.() -> Unit) = context?.let(function)

/**
 * 当前页面是否可见
 */
val Fragment.visible: Boolean
    get() = !isHidden && userVisibleHint

