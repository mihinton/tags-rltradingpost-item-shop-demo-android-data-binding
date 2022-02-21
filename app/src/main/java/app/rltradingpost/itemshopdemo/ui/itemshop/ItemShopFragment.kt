package app.rltradingpost.itemshopdemo.ui.itemshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.rltradingpost.itemshopdemo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemShopFragment : Fragment() {

    private val viewModel by viewModels<ItemShopViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getThings()
    }
}