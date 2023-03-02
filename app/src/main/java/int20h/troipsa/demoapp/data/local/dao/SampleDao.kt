package int20h.troipsa.demoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import int20h.troipsa.demoapp.data.local.base.BaseDao
import int20h.troipsa.demoapp.data.local.entity.EventWithTypeProjection
import int20h.troipsa.demoapp.data.local.entity.SampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SampleDao : BaseDao<SampleEntity>() {

    @Query("SELECT * FROM sample WHERE id=:id")
    abstract fun getSample(id: Int) : Flow<SampleEntity>

    @Query("SELECT * FROM sample")
    abstract fun getAllSamples(): Flow<List<SampleEntity>>

}