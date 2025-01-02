package com.example.assignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val blogUrl = getString(R.string.blog_url)
        val newsUrl = getString(R.string.news_url)
        val volunteerUrl = getString(R.string.volunteer_url)
        val socialUrl = getString(R.string.social_url)

        view.findViewById<Button>(R.id.home_volunteer_button).setOnClickListener {
            redirect(volunteerUrl)
        }
        view.findViewById<Button>(R.id.home_blog_button).setOnClickListener {
            redirect(blogUrl)
        }
        view.findViewById<MaterialCardView>(R.id.home_news_button).setOnClickListener {
            redirect(newsUrl)
        }
        view.findViewById<MaterialCardView>(R.id.home_social_button).setOnClickListener {
            redirect(socialUrl)
        }
    }

    private fun redirect(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        startActivity(intent)
    }
}