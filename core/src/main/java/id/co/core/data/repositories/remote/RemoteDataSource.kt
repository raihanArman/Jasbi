package id.co.core.data.repositories.remote

import android.util.Log
import id.co.core.data.model.Category
import id.co.core.data.model.CategoryMenu
import id.co.core.data.model.Menu
import id.co.core.data.model.User
import id.co.core.data.model.cost.ResultCost
import id.co.core.data.model.history.History
import id.co.core.data.model.province.Result
import id.co.core.data.model.shipment.Midtrans
import id.co.core.data.model.shipment.Transaction
import id.co.core.data.network.ApiService
import id.co.core.data.response.ResponseState
import id.co.core.data.response.ResponseTransaction
import id.co.core.util.ResponseStatus
import id.co.datastore.UserDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (
    private val apiService: ApiService,
    private val userDataStore: UserDataStore
) {

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

    fun getProduct(categoryId: String): Flow<ResponseState<List<CategoryMenu>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                val response = apiService.getProduct(categoryId)
                if(response.status == ResponseStatus.SUCCESS){
                    val data = response.data
                    if(!data.isNullOrEmpty())
                        emit(ResponseState.Success(data))
                    else
                        emit(ResponseState.Empty)
                }else{
                    emit(ResponseState.Error("Ada yang error"))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllProduct(): Flow<ResponseState<List<CategoryMenu>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                val response = apiService.getAllProduct()
                if(response.status == ResponseStatus.SUCCESS){
                    val data = response.data
                    if(!data.isNullOrEmpty())
                        emit(ResponseState.Success(data))
                    else
                        emit(ResponseState.Empty)
                }else{
                    emit(ResponseState.Error("Ada yang error"))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCategory(): Flow<ResponseState<List<Category>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                val response = apiService.getCategory()
                if(response.status == ResponseStatus.SUCCESS){
                    val data = response.data
                    if(!data.isNullOrEmpty())
                        emit(ResponseState.Success(data))
                    else
                        emit(ResponseState.Empty)
                }else{
                    emit(ResponseState.Error("Ada yang error"))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getProvince(): Flow<ResponseState<List<Result>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                val response = apiService.getProvince()
                if(response.rajaongkir.status.code == ResponseStatus.SUCCESS){
                    val data = response.rajaongkir.results
                    if(!data.isNullOrEmpty())
                        emit(ResponseState.Success(data))
                    else
                        emit(ResponseState.Empty)
                }else{
                    emit(ResponseState.Error(response.rajaongkir.status.description))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCity(provinceId: String): Flow<ResponseState<List<Result>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                val response = apiService.getCity(provinceId)
                if(response.rajaongkir.status.code == ResponseStatus.SUCCESS){
                    val data = response.rajaongkir.results
                    if(!data.isNullOrEmpty())
                        emit(ResponseState.Success(data))
                    else
                        emit(ResponseState.Empty)
                }else{
                    emit(ResponseState.Error(response.rajaongkir.status.description))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCost(destination: String, weight: String): Flow<ResponseState<List<ResultCost>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                val response = apiService.getCost(destination, weight)
                if(response.rajaongkir.status.code == ResponseStatus.SUCCESS){
                    val data = response.rajaongkir.results
                    if(!data.isNullOrEmpty())
                        emit(ResponseState.Success(data))
                    else
                        emit(ResponseState.Empty)
                }else{
                    emit(ResponseState.Error(response.rajaongkir.status.description))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun addTransaction(transaction: Transaction): Flow<ResponseState<Midtrans>>{
        return flow {
            emit(ResponseState.Loading())
            try {
                userDataStore.getTokenUserFlow.collect { token->
                    val response = apiService.addTransaction(
                        "api-pkm $token",
                        transaction
                    )
                    if(response.status == 201){
                        val data = response.midtransToken
                        if(data != null){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error("ada yang error"))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTransaction(): Flow<ResponseState<List<History>>>{
        return flow {
            emit(ResponseState.Loading())
            try {
                userDataStore.getTokenUserFlow.collect { token->
                    val response = apiService.getTransaction("api-pkm $token")
                    if(response.status == ResponseStatus.SUCCESS){
                        val data = response.data
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error("ada yang error"))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun editUser(user: User): Flow<ResponseState<String>>{
        return flow {
            emit(ResponseState.Loading())
            try {
                userDataStore.getTokenUserFlow.collect { token->
                    val response = apiService.editUser(
                        "api-pkm $token",
                        user
                    )
                    if(response.status == ResponseStatus.SUCCESS){
                        val data = response.data
                        if(data != ""){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error("ada yang error"))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getUser(): Flow<ResponseState<List<User>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect { token->
                    val response = apiService.getUser("api-pkm $token")
                    if(response.status == ResponseStatus.SUCCESS){
                        val data = response.data
                        if(!data.isNullOrEmpty())
                            emit(ResponseState.Success(data))
                        else
                            emit(ResponseState.Empty)
                    }else{
                        emit(ResponseState.Error("Ada yang error"))
                    }
                }

            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}