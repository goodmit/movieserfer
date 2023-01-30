package com.goodmit.movieserfer.presentation.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.goodmit.movieserfer.common.RxBus
import com.goodmit.movieserfer.databinding.FragmentTopBinding
import com.goodmit.movieserfer.presentation.MoviesAdapter
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopFragment : Fragment() {

    private val _rxBus : RxBus by inject()
    private val _mDisposable = CompositeDisposable()

    private var _binding: FragmentTopBinding? = null
    private val _topVm by viewModel<TopViewModel>()
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

        _binding = FragmentTopBinding.inflate(inflater, container, false)

        _binding = FragmentTopBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _moviesAdapter = MoviesAdapter(_rxBus)

        binding.rvTopMovies.layoutManager = GridLayoutManager(root.context, 2)
        binding.rvTopMovies.adapter = _moviesAdapter

        _mDisposable.add(_topVm.getTopMovies().subscribe{
            _moviesAdapter.submitData(lifecycle, it)
        })

        return root
    }

    override fun onDestroyView() {
        _mDisposable.dispose()

        super.onDestroyView()
        _binding = null
    }
}