package id.bazrira.submission1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.bazrira.submission1.databinding.ActivityDetailBinding
import id.bazrira.submission1.utils.Constants.MOVIE
import id.bazrira.submission1.utils.load
import id.bazrira.submission1.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

  private lateinit var binding: ActivityDetailBinding
  private lateinit var viewModel: DetailViewModel

  private lateinit var id: String
  private lateinit var type: String

  companion object {
    const val EXTRA_ITEM_ID = "ITEM_ID"
    const val EXTRA_ITEM_TYPE = "ITEM_TYPE"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProvider(this@DetailActivity).get(DetailViewModel::class.java)

    id = intent.getStringExtra(EXTRA_ITEM_ID) as String
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
    viewModel.getDetailData(id, type).let {
      binding.textViewTitle.text = it.title
      binding.textViewDescription.text = it.desc
      binding.imageViewBackDrop.load(it.backDrop)
      binding.imageViewPoster.load(it.poster)
    }
  }
}