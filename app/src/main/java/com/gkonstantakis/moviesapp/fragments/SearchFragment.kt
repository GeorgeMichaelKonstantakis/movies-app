package com.gkonstantakis.moviesapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gkonstantakis.moviesapp.MoviesApplication
import com.gkonstantakis.moviesapp.R
import com.gkonstantakis.moviesapp.activities.MainActivity
import com.gkonstantakis.moviesapp.adapters.SearchResultsAdapter
import com.gkonstantakis.moviesapp.databinding.FragmentSearchBinding
import com.gkonstantakis.moviesapp.mappers.UiSearchResultMapper
import com.gkonstantakis.moviesapp.models.SearchEventParams
import com.gkonstantakis.moviesapp.models.UiSearchResult
import com.gkonstantakis.moviesapp.movies.models.SearchResult
import com.gkonstantakis.moviesapp.movies.state.DataState
import com.gkonstantakis.moviesapp.viewmodels.SearchStateEvent
import com.gkonstantakis.moviesapp.viewmodels.SearchViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var moviesApplication: MoviesApplication
    private lateinit var viewModel: SearchViewModel

    private lateinit var fragmentSearchBinding: FragmentSearchBinding

    private lateinit var searchInputText: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var moviesListRecyclerView: RecyclerView
    private lateinit var networkErrorText: TextView

    private var searchResults: MutableList<UiSearchResult> = ArrayList<UiSearchResult>()
    private var searchResultsAdapter: SearchResultsAdapter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var countPaging = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater)
        return fragmentSearchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesApplication = (this.requireActivity().application as MoviesApplication)

        viewModel = SearchViewModel(
            moviesApplication.movieRepository
        )

        searchInputText = fragmentSearchBinding.searchInputText
        progressBar = fragmentSearchBinding.progressBar
        moviesListRecyclerView = fragmentSearchBinding.moviesList
        networkErrorText = fragmentSearchBinding.networkErrorText

        subscribeObservers()

        searchInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val inputText = p0.toString()
                if (inputText.isNotEmpty()) {
                    viewModel.setStateEvent(
                        SearchStateEvent.GetNetworkProducts,
                        SearchEventParams(inputText, countPaging)
                    )
                }
            }
        })
    }

    fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is DataState.Success<List<SearchResult>> -> {
                    dataLoading(false)
                    networkErrorText.visibility = View.GONE
                    if (datastate.data.isNullOrEmpty()) {
                        searchResults.clear()
                        //DisplayErrorMessage
                    } else {
                        if (searchResults.isNullOrEmpty()) {
                            for (data in datastate.data) {
                                searchResults.add(UiSearchResultMapper().mapToEntity(data))
                            }
                            displayProductsList()
                        } else {
                            searchResults.clear()
                            for (data in datastate.data) {
                                searchResults.add(UiSearchResultMapper().mapToEntity(data))
                            }
                            updateSearchResults(searchResults)
                        }
                    }
                }
                is DataState.Error -> {
                    dataLoading(false)
                    if (datastate.message == "SEARCH_RESULTS_ERROR") {
                        networkErrorText.visibility = View.VISIBLE
                        deleteSearchResults()
                        updateSearchResults(searchResults)
                    }
                }
                is DataState.Loading -> {
                    deleteSearchResults()
                    dataLoading(true)
                }
                else -> {
                    dataLoading(false)
                }
            }
        })
    }

    private fun displayProductsList() {
        if (!searchResults.isNullOrEmpty()) {
            moviesListRecyclerView.adapter = SearchResultsAdapter(
                searchResults,
                UiSearchResultMapper(),
                viewModel,
                this.requireActivity() as MainActivity
            )
            searchResultsAdapter = moviesListRecyclerView.adapter as SearchResultsAdapter
            linearLayoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            moviesListRecyclerView.layoutManager = linearLayoutManager
        }
    }

    private fun deleteSearchResults() {
        searchResultsAdapter?.deleteSearchResults()
    }

    fun updateSearchResults(newSearchResults: List<UiSearchResult>) {
        searchResultsAdapter?.updateSearchResults(newSearchResults)
    }

    private fun dataLoading(isDataLoading: Boolean) {
        if (isDataLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}