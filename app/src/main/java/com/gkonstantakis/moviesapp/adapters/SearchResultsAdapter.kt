package com.gkonstantakis.moviesapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gkonstantakis.moviesapp.R
import com.gkonstantakis.moviesapp.activities.MainActivity
import com.gkonstantakis.moviesapp.databinding.SearchResultItemBinding
import com.gkonstantakis.moviesapp.mappers.UiSearchResultMapper
import com.gkonstantakis.moviesapp.models.UiSearchResult
import com.gkonstantakis.moviesapp.movies.utils.Constant
import com.gkonstantakis.moviesapp.viewmodels.SearchViewModel

class SearchResultsAdapter(
    var searchResults: MutableList<UiSearchResult>,
    val uiSearchResultMapper: UiSearchResultMapper,
    val viewModel: SearchViewModel,
    private val activity: MainActivity
) :
    RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder>() {

    inner class SearchResultViewHolder(var searchResultItemBinding: SearchResultItemBinding) :
        RecyclerView.ViewHolder(searchResultItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            SearchResultItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val binding = holder.searchResultItemBinding
        holder.setIsRecyclable(false);
        holder.itemView.apply {
            val searchResult = searchResults[holder.adapterPosition]

            val movieArea = binding.movieArea
            val movieTitle = binding.movieTitle
            val movieRatings = binding.movieRatings
            val movieImage = binding.movieImage

            movieTitle.text = searchResult.title
            movieRatings.text = searchResult.voteAverage!!.toString()

            val searchResultPath: String
            if (searchResult.posterPath != null) {
                searchResultPath = Constant.IMAGE_URL + searchResult.posterPath

                Glide.with(context).load(searchResultPath)
                    .into(movieImage);
            }

            movieArea.setOnClickListener {
                navigateToMovieDetailsScreen(searchResult, searchResult.isMovie!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    fun deleteSearchResults() {
        searchResults.clear()
        this.notifyDataSetChanged();
    }

    fun updateSearchResults(newProductItems: List<UiSearchResult>) {
        searchResults.addAll(newProductItems)
        this.notifyDataSetChanged();
    }

    fun navigateToMovieDetailsScreen(searchResult: UiSearchResult, isMovie: Boolean) {
        val bundle = Bundle()
        bundle.putInt("ID", searchResult.id!!)
        bundle.putBoolean("IS_MOVIE", isMovie)
        Navigation.findNavController(activity, R.id.nav_host_fragment)
            .navigate(R.id.action_searchFragment_to_detailsFragment, bundle)
    }
}