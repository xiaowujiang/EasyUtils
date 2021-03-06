package com.github.ixiaow.fragment

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.ixiaow.data.R

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.findFragmentById(@IdRes frameId: Int): Fragment? {
    return supportFragmentManager.findFragmentById(frameId)
}

inline fun <reified T : Fragment> AppCompatActivity.replaceFragmentInActivity(
    @IdRes id: Int = R.id.container
) = findFragmentById(id) as? T?
    ?: T::class.java.newInstance().also {
        replaceFragmentInActivity(it, id)
    }


inline fun <reified T : Fragment> AppCompatActivity.replaceFragmentInActivity(
    @IdRes id: Int = R.id.container, block: () -> T
): T = findFragmentById(id) as? T?
    ?: block.invoke().also {
        replaceFragmentInActivity(it, id)
    }


/**
 * 切换到目标fragment
 *
 * @param fragment 目标fragment
 */
fun AppCompatActivity.switchFragment(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.switchFragment(fragment, frameId)
}

/**
 * 切换到目标fragment
 *
 * @param fragment 目标fragment
 */
fun androidx.fragment.app.FragmentManager.switchFragment(fragment: Fragment, @IdRes frameId: Int) {
    transact {
        //获取当前fragmentManager中的所有fragment
        val fragments: List<Fragment> = fragments
        for (f in fragments) { //遍历所有fragment设置隐藏
            hide(f)
        }
        //判断当前目标fragment是否存在，并且没有被加入
        if (!fragments.contains(fragment) && !fragment.isAdded) {
            add(frameId, fragment)
        } else {
            show(fragment)
        }
    }
}


/**
 * Runs a FragmentTransaction, then calls commit().
 */
inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}


