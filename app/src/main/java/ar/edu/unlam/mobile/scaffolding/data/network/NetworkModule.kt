package ar.edu.unlam.mobile.scaffolding.data.network

import android.content.Context
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepositoryImpl
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
}
