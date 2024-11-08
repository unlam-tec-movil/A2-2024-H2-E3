package ar.edu.unlam.mobile.scaffolding.data.di

import android.content.Context
import ar.edu.unlam.mobile.scaffolding.data.network.ApiService
import ar.edu.unlam.mobile.scaffolding.data.network.AuthInterceptor
import ar.edu.unlam.mobile.scaffolding.data.network.RetrofitService
import ar.edu.unlam.mobile.scaffolding.data.local.TokenManager
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.TuitRepositoryImpl
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideTokenManager(
        @ApplicationContext context: Context,
    ): TokenManager = TokenManager(context)

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor = AuthInterceptor(tokenManager)

    @Provides
    fun provideApiService(tokenManager: TokenManager): ApiService =
        RetrofitService.createApiService(
            tokenManager,
        )

    @Provides
    fun provideTuitRepository(apiService: ApiService): TuitRepository = TuitRepositoryImpl(apiService)

    @Provides
    fun provideUserRepository(apiService: ApiService, tokenManager: TokenManager): UserRepository = UserRepositoryImpl(apiService, tokenManager)
}
