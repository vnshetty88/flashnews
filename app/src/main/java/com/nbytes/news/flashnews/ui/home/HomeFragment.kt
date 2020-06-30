package com.nbytes.news.flashnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nbytes.news.flashnews.R
import com.nbytes.news.flashnews.data.db.response.NewsResponse
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    private var response: NewsResponse? = null
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel.news.observe(viewLifecycleOwner, Observer { t ->
            response = t

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    val bounceAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_bounce)
                    activity?.runOnUiThread {
                        textHome.startAnimation(bounceAnimation)
                        textHome.text =
                            response?.articles?.get(rand(response?.articles?.size!!))?.title
                    }

                }
            }, 0, 5000)

        })
    }

    fun rand(end: Int): Int {
        return Random(System.nanoTime()).nextInt(end )
    }
}

