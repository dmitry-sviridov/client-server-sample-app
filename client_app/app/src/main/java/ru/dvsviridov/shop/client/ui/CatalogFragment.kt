package ru.dvsviridov.shop.client.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.dvsviridov.shop.client.databinding.CatalogFragmentBinding
import ru.dvsviridov.shop.client.viewmodel.CatalogViewModel
import ru.dvsviridov.shop.client.model.dto.Item
import ru.dvsviridov.shop.client.ui.adapter.ItemAdapter
import ru.dvsviridov.shop.client.ui.adapter.RecyclerItemEventListener

@AndroidEntryPoint
class CatalogFragment : Fragment(), RecyclerItemEventListener {

    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var binding: CatalogFragmentBinding

    private val itemAdapter: ItemAdapter by lazy { ItemAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CatalogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.resourceState.observe(viewLifecycleOwner, { resourceState ->
            render(resourceState)
        })

        initRecycler()
        initRefreshLayout()
    }

    private fun initRefreshLayout() {
        binding.refresher.setOnRefreshListener {
            binding.refresher.isRefreshing = true
            viewModel.getItemsFromShop()
        }
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this.context)

        binding.recycler.apply {
            layoutManager = linearLayoutManager
            adapter = itemAdapter
        }
    }

    private fun render(state: ResourceState<List<Item>>) {
        when (state.status) {
            ResourceState.FetchingStatus.ERROR -> {
                binding.refresher.isRefreshing = false
                Log.d(TAG, "render: ERROR")
            }
            ResourceState.FetchingStatus.LOADING -> {
                binding.refresher.isRefreshing = true
                Log.d(TAG, "render: LOADING")
            }
            ResourceState.FetchingStatus.SUCCESS -> {
                binding.refresher.isRefreshing = false
                state.data?.let {
                    itemAdapter.submitList(it)
                    if (it.isEmpty()) {
                        //TODO: render empty state with placeholder
                        Log.d(TAG, "render: SUCCESS / EMPTY")
                    } else {
                        it.forEach {
                            Log.d(TAG, "render: $it")
                        }
                    }
                }
                Log.d(TAG, "render: SUCCESS")
            }
        }
    }

    override fun onPriceClick(item: Item) {
        Log.d(TAG, "onPriceClick: ${item.price}")
        Snackbar.make(binding.root, "Добавлено: ${item.title}", Snackbar.LENGTH_LONG).show()
    }

    companion object {
        val TAG = CatalogFragment::class.java.simpleName
    }

}