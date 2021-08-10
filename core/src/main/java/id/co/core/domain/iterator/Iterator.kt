package id.co.core.domain.iterator

import id.co.core.data.model.Menu
import id.co.core.data.response.ResponseState
import id.co.core.domain.repository.Repository
import id.co.core.domain.usecase.Usecase
import kotlinx.coroutines.flow.Flow

class Iterator(
    private val repository: Repository
): Usecase {
    override fun getProduct(): Flow<ResponseState<List<Menu>>> {
        return repository.getProduct()
    }
}