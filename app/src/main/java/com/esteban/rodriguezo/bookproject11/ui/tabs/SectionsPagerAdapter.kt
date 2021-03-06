package com.esteban.rodriguezo.bookproject11.ui.tabs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.esteban.rodriguezo.bookproject11.R
import com.esteban.rodriguezo.bookproject11.ui.delete.DeleteFragment
import com.esteban.rodriguezo.bookproject11.ui.newbook.NewBookFragment
import com.esteban.rodriguezo.bookproject11.ui.update.UpdateFragment

private val TAB_TITLES = arrayOf(
    R.string.title_list,
    R.string.title_new,
    R.string.title_update,
    R.string.title_delete,

    )

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {

            0 -> return com.esteban.rodriguezo.bookproject11.ui.list.ListFragment()
            1 -> return NewBookFragment()
            2 -> return UpdateFragment()
            else -> return DeleteFragment()


        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 4
    }
}