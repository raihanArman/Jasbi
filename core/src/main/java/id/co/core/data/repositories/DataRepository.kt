package id.co.core.data.repositories

import id.co.core.data.model.Menu
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.data.response.ResponseState
import id.co.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val remote: RemoteDataSource
): Repository{
    override fun getProduct(): Flow<ResponseState<List<Menu>>> {
        return remote.getProduct()
    }

}