package com.lms.worldoflol.di

import android.app.Application
import androidx.room.Room
import com.lms.worldoflol.data.local.database.SummonerDatabase
import com.lms.worldoflol.data.local.database.ChampionsDatabase
import com.lms.worldoflol.data.remote.RuneterraApi
import com.lms.worldoflol.data.repository.ChampionDetailsRepositoryImpl
import com.lms.worldoflol.data.repository.ChampionsRepositoryImpl
import com.lms.worldoflol.data.repository.MatchesRepositoryImpl
import com.lms.worldoflol.data.repository.SummonerEntityRepositoryImpl
import com.lms.worldoflol.data.repository.SummonerRepositoryImpl
import com.lms.worldoflol.domain.repository.ChampionDetailsRepository
import com.lms.worldoflol.domain.repository.ChampionsRepository
import com.lms.worldoflol.domain.repository.MatchesRepository
import com.lms.worldoflol.domain.repository.SummonerEntityRepository
import com.lms.worldoflol.domain.repository.SummonerRepository
import com.lms.worldoflol.domain.use_case.summoner_entitiy.AddSummonerUseCase
import com.lms.worldoflol.domain.use_case.summoner_entitiy.DeleteSummonerUseCase
import com.lms.worldoflol.domain.use_case.summoner_entitiy.GetSummonersUseCase
import com.lms.worldoflol.domain.use_case.summoner_entitiy.SummonersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWorlOfLolApi(): RuneterraApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.riotgames.com/lol/")
            .build()
            .create(RuneterraApi::class.java)
    }

    // summoners

    @Provides
    @Singleton
    fun provideSummonerRepository(api: RuneterraApi): SummonerRepository {
        return SummonerRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSummonerDatabase(app: Application): SummonerDatabase {
        return Room.databaseBuilder(
            app,
            SummonerDatabase::class.java,
            SummonerDatabase.SUMMONER
        ).build()
    }

    @Provides
    @Singleton
    fun provideSummonerEntityRepository(db: SummonerDatabase): SummonerEntityRepository {
        return SummonerEntityRepositoryImpl(db.summonerDao)
    }

    @Provides
    @Singleton
    fun provideSummonersUseCases(repository: SummonerEntityRepository): SummonersUseCases {
        return SummonersUseCases(
            getSummoners = GetSummonersUseCase(repository),
            deleteSummoner = DeleteSummonerUseCase(repository),
            addSummoner = AddSummonerUseCase(repository),
        )
    }

    // champions

    @Provides
    @Singleton
    fun provideChampionsRepository(api: RuneterraApi, db: ChampionsDatabase): ChampionsRepository {
        return ChampionsRepositoryImpl(api, db.championsDao)
    }

    @Provides
    @Singleton
    fun provideChampionDetailsRepository(api: RuneterraApi, db: ChampionsDatabase): ChampionDetailsRepository{
        return ChampionDetailsRepositoryImpl(api, db.championDetailsDao)
    }

    @Provides
    @Singleton
    fun provideChampionsDataBase(app: Application): ChampionsDatabase {
        return Room.databaseBuilder(
            app,
            ChampionsDatabase::class.java,
            ChampionsDatabase.CHAMPIONS
        ).build()
    }

    // matches
    @Provides
    @Singleton
    fun provideMatchesRepository(api: RuneterraApi): MatchesRepository {
        return MatchesRepositoryImpl(api)
    }
}