package int20h.troipsa.demoapp.data.local.entity

@androidx.room.Entity(tableName = "sample")
data class SampleEntity(
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    val id: Int,
    @androidx.room.ColumnInfo(name = "name")
    val name: String,
)
