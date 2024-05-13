package com.awesome.investpro
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.awesome.Fragments.CryptoPRice
import com.awesome.Fragments.FearGreed

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager)
        var tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)


        setupViewPager(tab_viewpager)


        tab_tablayout.setupWithViewPager(tab_viewpager)








    }

    // This function is used to add items in arraylist and assign
    // the adapter to view pager
    private fun setupViewPager(viewpager: ViewPager) {
        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        // LoginFragment is the name of Fragment and the Login
        // is a title of tab
        adapter.addFragment(CryptoPRice(), "Screen")
adapter.addFragment(FearGreed(),"FearGreed")

        // setting adapter to view pager.
        viewpager.setAdapter(adapter)
    }

    // This "ViewPagerAdapter" class overrides functions which are
    // necessary to get information about which item is selected
    // by user, what is title for selected item and so on.*/
    class ViewPagerAdapter : FragmentPagerAdapter {

        // objects of arraylist. One is of Fragment type and
        // another one is of String type.*/
        private final var fragmentList1: ArrayList<Fragment> = ArrayList()
        private final var fragmentTitleList1: ArrayList<String> = ArrayList()

        // this is a secondary constructor of ViewPagerAdapter class.
        public constructor(supportFragmentManager: FragmentManager)
                : super(supportFragmentManager)

        // returns which item is selected from arraylist of fragments.
        override fun getItem(position: Int): Fragment {
            return fragmentList1.get(position)
        }

        // returns which item is selected from arraylist of titles.
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1.get(position)
        }

        // returns the number of items present in arraylist.
        override fun getCount(): Int {
            return fragmentList1.size
        }

        // this function adds the fragment and title in 2 separate arraylist.
        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
    }
}
