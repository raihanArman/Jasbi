package id.co.core.data.repositories.remote

import id.co.core.data.model.Menu
import id.co.core.data.network.ApiService
import id.co.core.data.response.ResponseState
import id.co.core.util.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (
    private val apiService: ApiService
//    private val userDataStore: UserDataStore
) {
    
    fun getProduct(): Flow<ResponseState<List<Menu>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.getProduct()
                val status = response.status
                if(status == ResponseStatus.SUCCESS){
                    val data = response.data
                    if(!data.isNullOrEmpty()){
                        emit(ResponseState.Success(data))
                    }else{
                        emit(ResponseState.Empty)
                    }
                }else{
                    emit(ResponseState.Error("Ada kesalahan"))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
    
}