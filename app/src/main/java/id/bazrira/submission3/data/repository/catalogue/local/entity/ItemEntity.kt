package id.bazrira.submission3.data.repository.catalogue.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "items")
@Parcelize
data class ItemEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "item_title")
    var title: String = "",

    @ColumnInfo(name = "item_desc")
    var desc: String = "",

    @ColumnInfo(name = "item_poster")
    var poster: String = "",

    @ColumnInfo(name = "item_backDrop")
    var backDrop: String = "",

    @ColumnInfo(name = "item_type")
    var type: String = "",

    @NonNull
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) : Parcelable