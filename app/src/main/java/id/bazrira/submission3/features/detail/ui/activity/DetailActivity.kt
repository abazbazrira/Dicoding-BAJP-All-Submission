package id.bazrira.submission3.features.detail.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import id.bazrira.submission3.R
import id.bazrira.submission3.abstraction.utils.Constants.ADDED_TO_FAVORITE
import id.bazrira.submission3.abstraction.utils.Constants.API_IMAGE_ENDPOINT
import id.bazrira.submission3.abstraction.utils.Constants.ENDPOINT_POSTER_SIZE_W185
import id.bazrira.submission3.abstraction.utils.Constants.MOVIE
import id.bazrira.submission3.abstraction.utils.Constants.REMOVED_FROM_FAVORITE
import id.bazrira.submission3.abstraction.utils.ViewModelFactory
import id.bazrira.submission3.abstraction.utils.load
import id.bazrira.submission3.data.repository.catalogue.local.entity.ItemEntity
import id.bazrira.submission3.databinding.ActivityDetailBinding
import id.bazrira.submission3.features.detail.viewmodel.DetailViewModel
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

  private lateinit var binding: ActivityDetailBinding
  private lateinit var viewModel: DetailViewModel

  @Inject
  lateinit var factory: ViewModelFactory

  private var id: Int = 0
  private lateinit var type: String

  private var menuItem: Menu? = null
  private var isFavorite: Boolean = false

  private var dataFavorite: ItemEntity = ItemEntity()

  companion object {
    const val EXTRA_ITEM_ID = "ITEM_ID"
    const val EXTRA_ITEM_TYPE = "ITEM_TYPE"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProvider(this@DetailActivity, factory).get(DetailViewModel::class.java)

    id = intent.getIntExtra(EXTRA_ITEM_ID, 0)
    type = intent.getStringExtra(EXTRA_ITEM_TYPE) as String

    supportActionBar?.title = if (type == MOVIE) {
      "Detail Movie"
    } else {
      "Detail Tv Show"
    }

    setData()

    binding.swlayout.setOnRefreshListener {
      setData()
      binding.swlayout.isRefreshing = false
    }
  }

  private fun setData() {
    viewModel.getDetailData(id).observe(this@DetailActivity, {
      binding.textViewTitle.text = it.title
      binding.textViewDescription.text = it.desc
      binding.imageViewBackDrop.load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + it.backDrop)
      binding.imageViewPoster.load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + it.poster)

      isFavorite = it.isFavorite
      setFavoriteState(isFavorite)

      dataFavorite.id = it.id
      dataFavorite.title = it.title
      dataFavorite.desc = it.desc
      dataFavorite.poster = it.poster
      dataFavorite.backDrop = it.backDrop
      dataFavorite.type = type
      dataFavorite.isFavorite = it.isFavorite
    })
  }

  private fun updateStatusFavorite(status: Boolean) {
    dataFavorite.isFavorite = status
    isFavorite = status
    viewModel.setFavoriteItem(dataFavorite)

    setFavoriteState(status)

    if (status) {
      showSnackBar(ADDED_TO_FAVORITE)
    } else {
      showSnackBar(REMOVED_FROM_FAVORITE)
    }
  }

  private fun setFavoriteState(status: Boolean) {
    if (status) {
      menuItem?.getItem(0)?.icon =
        ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
    } else
      menuItem?.getItem(0)?.icon =
        ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
  }

  private fun showSnackBar(msg: String) {
    Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.detail_menu, menu)
    menuItem = menu

    setFavoriteState(isFavorite)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.add_to_favorite -> {
        updateStatusFavorite(!isFavorite)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}