package id.co.core.data.repositories.remote

import android.util.Log
import id.co.core.data.model.Menu
import id.co.core.data.model.User
import id.co.core.data.network.ApiService
import id.co.core.data.response.ResponseState
import id.co.core.util.ResponseStatus
import id.co.datastore.UserDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (
    private val apiService: ApiService,
    private val userDataStore: UserDataStore
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

    fun registerUser(
        name: String,
        email: String,
        phoneNumber: String,
        address: String,
        password: String
    ): Flow<ResponseState<String>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.registerUser(name, email, phoneNumber, address, password)
                if(response.status == 201){

                    emit(ResponseState.Success("Sukses"))

                }else{
                    Log.d("Mantap", "registerUser error : value ${response.msg}")
                    emit(ResponseState.Error(response.msg))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun loginUser(email: String, password: String): Flow<ResponseState<User>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.loginUser(email, password)
                if(response.status == ResponseStatus.SUCCESS){
                    val data = response.user
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                    userDataStore.storeTokenUser(response.token)
                    Log.d("Mantap", "loginUser sukses : value ${response.msg}")
                }else{
                    Log.d("Mantap", "loginUser error : value ${response.msg}")
                    emit(ResponseState.Error(response.msg))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}