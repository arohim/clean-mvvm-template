package com.cleanmvvm.presentation.githubrepository

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cleanmvvm.R

class GithubRepositoryFragment : Fragment() {

    companion object {
        fun newInstance() = GithubRepositoryFragment()
    }

    private lateinit var viewModel: GithubRepositoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_github_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GithubRepositoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
