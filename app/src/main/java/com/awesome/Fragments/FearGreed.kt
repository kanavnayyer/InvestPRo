package com.awesome.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.awesome.investpro.R
import com.bumptech.glide.Glide


class FearGreed : Fragment() {
    private lateinit var cryptoImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fear_greed, container, false)

        cryptoImageView = view.findViewById(R.id.cryptoImageView)

        // Load the image every time the fragment view is created
        loadImage("https://alternative.me/crypto/fear-and-greed-index.png")

        return view
    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.placeholder_image)
            .into(cryptoImageView)
    }
}