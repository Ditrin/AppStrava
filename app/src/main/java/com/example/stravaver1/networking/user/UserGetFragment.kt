package com.example.stravaver1.networking.user

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.stravaver1.R
import com.example.stravaver1.databinding.FragmentListBinding
import com.example.stravaver1.networking.models.LoadingItem
import com.example.stravaver1.utils.PaginationScroll
import com.example.stravaver1.utils.autoCleared

class UserGetFragment : Fragment(R.layout.fragment_list) {
    private var button_hearts: ImageButton? = null
    private val viewModel : UserViewModel by viewModels()

    private val binding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    private var simpleAdapter: UserAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()

    }

    private fun initList() {
        simpleAdapter = UserAdapter()

        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = simpleAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            addOnScrollListener(
                PaginationScroll(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 0

                )
            )
            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
        viewModel.getId()
        viewModel.userList.observe(viewLifecycleOwner, Observer {
             simpleAdapter.items = it })

    }

    private fun loadMoreItems() {
        val newItems = simpleAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        }
        simpleAdapter.items = newItems
    }

    override fun onDestroyView() {
        super.onDestroyView()
        button_hearts = null
    }
}