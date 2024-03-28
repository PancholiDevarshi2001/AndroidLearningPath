package com.base.hilt.domain.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.base.hilt.UserDataQuery
import com.base.hilt.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton


class UserRepository @Inject constructor (var apolloClient: ApolloClient) :BaseRepository() {

    suspend fun callApi(): ApolloResponse<UserDataQuery.Data> {
        val res = apolloClient.query(UserDataQuery()).execute()
        return res
    }

}