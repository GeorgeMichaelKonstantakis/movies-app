package com.gkonstantakis.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.gkonstantakis.moviesapp.MoviesApplication
import com.gkonstantakis.moviesapp.R
import com.gkonstantakis.moviesapp.databinding.FragmentDetailsBinding
import com.gkonstantakis.moviesapp.databinding.FragmentSearchBinding
import com.gkonstantakis.moviesapp.models.DetailsEventParams
import com.gkonstantakis.moviesapp.movies.models.Movie
import com.gkonstantakis.moviesapp.movies.models.Trailer
import com.gkonstantakis.moviesapp.movies.models.TvShow
import com.gkonstantakis.moviesapp.movies.state.DataState
import com.gkonstantakis.moviesapp.movies.utils.Constant
import com.gkonstantakis.moviesapp.viewmodels.DetailsStateEvent
import com.gkonstantakis.moviesapp.viewmodels.DetailsViewModel
import com.gkonstantakis.moviesapp.viewmodels.SearchViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var moviesApplication: MoviesApplication
    private var viewModel: DetailsViewModel? = null

    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding

    private var id: Int? = null
    private var isMovie = false

    private lateinit var movieImage: ImageView
    private lateinit var dismissMovieButton: Button
    private lateinit var movieTitle: TextView
    private lateinit var movieGenre: TextView
    private lateinit var movieOverview: TextView
    private lateinit var movieTrailer: WebView
    private lateinit var networkErrorTextView: TextView
    private lateinit var progressBar: ProgressBar

    private var movie: Movie? = null
    private var tvShow: TvShow? = null
    private var trailer: Trailer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            id = it.getInt("ID")
            isMovie = it.getBoolean("IS_MOVIE")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater)
        return fragmentDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesApplication = (this.requireActivity().application as MoviesApplication)

        viewModel = DetailsViewModel(
            moviesApplication.movieRepository
        )

        movieImage = fragmentDetailsBinding.movieImage
        dismissMovieButton = fragmentDetailsBinding.dismissMovie
        movieTitle = fragmentDetailsBinding.movieTitle
        movieGenre = fragmentDetailsBinding.movieGenre
        movieOverview = fragmentDetailsBinding.movieOverview
        movieTrailer = fragmentDetailsBinding.movieTrailer
        networkErrorTextView = fragmentDetailsBinding.networkErrorText
        progressBar = fragmentDetailsBinding.progressBar

        subscribeObservers()

        if(isMovie){
            viewModel!!.setStateEvent(
                DetailsStateEvent.NetworkGetMovie,
                DetailsEventParams(id)
            )
        } else{
            viewModel!!.setStateEvent(
                DetailsStateEvent.NetworkGetTvShow,
                DetailsEventParams(id)
            )
        }

        dismissMovieButton.setOnClickListener {
            navigateToSearchScreen()
        }
    }

    fun subscribeObservers() {
        viewModel?.movieDataState?.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is DataState.SuccessMovie -> {
                    movie = datastate.movie
                    displayMovieInfo()
                    viewModel!!.setStateEvent(
                        DetailsStateEvent.NetworkGetMovieTrailers,
                        DetailsEventParams(id)
                    )
                }
                is DataState.Error -> {

                }
                is DataState.Loading -> {
                    dataLoading(true)
                }
            }
        })

        viewModel?.tvShowDataState?.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is DataState.SuccessTvShow -> {
                    tvShow = datastate.tvShow
                    displayTvShowInfo()
                    viewModel!!.setStateEvent(
                        DetailsStateEvent.NetworkGetTvShowTrailers,
                        DetailsEventParams(id)
                    )
                }
                is DataState.Error -> {

                }
                is DataState.Loading -> {
                    dataLoading(true)
                }
            }
        })

        viewModel?.trailersDataState?.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is DataState.SuccessMovie -> {

                }
                is DataState.Error -> {

                }
                is DataState.Loading -> {
                    dataLoading(true)
                }
            }
        })
    }

    fun displayMovieInfo() {
        val moviePath: String
        if (movie?.posterPath != null) {
            moviePath = Constant.IMAGE_URL + movie?.posterPath

            Glide.with(this).load(moviePath)
                .into(fragmentDetailsBinding.movieImage);
        }

        movieTitle.text = movie?.title
        movieGenre.text = movie?.genre
        movieOverview.text = movie?.overview
    }

    fun displayTvShowInfo() {
        val tvShowPath: String
        if (tvShow?.posterPath != null) {
            tvShowPath = Constant.IMAGE_URL + tvShow?.posterPath

            Glide.with(this).load(tvShowPath)
                .into(fragmentDetailsBinding.movieImage);
        }

        movieTitle.text = tvShow?.title
        movieGenre.text = tvShow?.genre
        movieOverview.text = tvShow?.overview
    }

    fun displayVideo(key: String){
        val videoStr =
            "<html><body><iframe ${trailerDimensions()} src=\"https://www.youtube.com/embed/${key}\" frameborder=\"0\" allowfullscreen></iframe></body></html>"

        movieTrailer.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        })
        val ws: WebSettings = movieTrailer.settings
        ws.javaScriptEnabled = true
        movieTrailer.loadData(videoStr, "text/html", "utf-8")
    }

    fun getYoutubeTrailerKey(trailers: List<Trailer>?): String? {
        var trailerKey: String? = null
        try {
            if (!trailers.isNullOrEmpty()) {
                for (trailer in trailers) {
                    if (trailer.site == "YouTube") {
                        trailerKey = trailer.key
                        break
                    }
                }
            }
        } catch (e: Exception) {
        }
        return trailerKey
    }

    fun trailerDimensions(): String{
        val height = 315
        val width = 420

        val aspectRatio: Double = (width.toDouble()/ height.toDouble())

        val viewResources = this.resources
        val widthScreenPixels = (viewResources.displayMetrics.widthPixels)
        var heightScreenPixels = (viewResources.displayMetrics.heightPixels)
        val scale: Double = (viewResources!!.displayMetrics.density).toDouble()

        val viewMarginHorizontal: Double = (viewResources.getDimension(R.dimen._20sdp).toDouble())

        val finalWidth = widthScreenPixels - viewMarginHorizontal
        val finalHeight = (((1/aspectRatio).toDouble())*(finalWidth.toDouble())).toInt()

        return "width=\"${finalWidth/scale}\" height=\"${finalHeight/scale}\""
    }

    private fun dataLoading(isDataLoading: Boolean) {
        if (isDataLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    fun navigateToSearchScreen() {
        val bundle = Bundle()
        Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
            .navigate(R.id.action_searchFragment_to_detailsFragment, bundle)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}