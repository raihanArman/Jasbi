package id.co.core.domain.usecase

import id.co.core.data.model.Menu
import id.co.core.data.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface Usecase {

    fun getProduct(): Flow<ResponseState<List<Menu>>>

}