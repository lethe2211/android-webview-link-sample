package com.example.webviewlinksample.ui

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.DataBindingUtil
import com.example.webviewlinksample.R
import com.example.webviewlinksample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.searchbar.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            /**
             * Callback of a submit event.
             */
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("Custom", "onQueryTextSubmit: ${query}")
                return false
            }

            /**
             * Callback of a text change event.
             */
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("Custom", "onQueryTextChange: ${newText}")
                binding.linkText.text = HtmlCompat.fromHtml("<a href=${newText.orEmpty()}>${newText.orEmpty()}</a>", FROM_HTML_MODE_COMPACT)
                return false
            }
        })
        binding.linkText.movementMethod = LinkMovementMethod.getInstance()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}