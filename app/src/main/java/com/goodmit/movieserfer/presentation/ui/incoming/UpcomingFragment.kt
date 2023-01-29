package com.goodmit.movieserfer.presentation.ui.incoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.goodmit.movieserfer.databinding.FragmentUpcomingBinding
import com.goodmit.movieserfer.presentation.MoviesAdapter
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingFragment : Fragment() {

    private val _mDisposable = CompositeDisposable()

    private var _binding: FragmentUpcomingBinding? = null
    private val _upcomingVm by viewModel<UpcomingViewModel>()
    private lateinit var _moviesAdapter: MoviesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _moviesAdapter = MoviesAdapter()

        binding.rvUpcomingMovies.layoutManager = GridLayoutManager(root.context, 2)
        binding.rvUpcomingMovies.adapter = _moviesAdapter

        _mDisposable.add(_upcomingVm.getUpcomingMovies().subscribe{
            _moviesAdapter.submitData(lifecycle, it)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}