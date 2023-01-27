package com.goodmit.movieserfer.presentation.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.goodmit.movieserfer.databinding.FragmentPopularBinding
import com.goodmit.movieserfer.presentation.MoviesAdapter
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {

    private val mDisposable = CompositeDisposable()

    private var _binding: FragmentPopularBinding? = null
    private val _popularVm by viewModel<PopularViewModel>()
    private lateinit var _moviesAdapter: MoviesAdapter

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)

        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _moviesAdapter = MoviesAdapter()

        binding.rvPopularMovies.layoutManager = GridLayoutManager(root.context, 2)
        binding.rvPopularMovies.adapter = _moviesAdapter

        mDisposable.add(_popularVm.getPopularMovies().subscribe{
            _moviesAdapter.submitData(lifecycle, it)
        })

//        val textView: TextView = binding.text
//        _popularVm.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root
    }

    override fun onDestroyView() {
        mDisposable.dispose()

        super.onDestroyView()
        _binding = null
    }
}