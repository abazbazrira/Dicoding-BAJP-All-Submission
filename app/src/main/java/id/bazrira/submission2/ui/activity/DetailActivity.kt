package id.bazrira.submission2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.bazrira.submission2.databinding.ActivityDetailBinding
import id.bazrira.submission2.utils.Constants.API_IMAGE_ENDPOINT
import id.bazrira.submission2.utils.Constants.ENDPOINT_POSTER_SIZE_W185
import id.bazrira.submission2.utils.Constants.MOVIE
import id.bazrira.submission2.utils.load
import id.bazrira.submission2.viewmodel.DetailViewModel
import id.bazrira.submission2.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

  private lateinit var binding: ActivityDetailBinding
  private lateinit var viewModel: DetailViewModel

  private var id: Int = 0
  private lateinit var type: String

  companion object {
    const val EXTRA_ITEM_ID = "ITEM_ID"
    const val EXTRA_ITEM_TYPE = "ITEM_TYPE"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val factory = ViewModelFactory.getInstance()
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
    viewModel.getDetailData(id, type).observe(this, {
      binding.textViewTitle.text = it.title
      binding.textViewDescription.text = it.desc
      binding.imageViewBackDrop.load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + it.backDrop)
      binding.imageViewPoster.load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + it.poster)
    })
  }
}