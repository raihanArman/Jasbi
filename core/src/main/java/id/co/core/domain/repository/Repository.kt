package id.co.core.domain.repository

import id.co.core.data.model.Menu
import id.co.core.data.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getProduct(): Flow<ResponseState<List<Menu>>>
}