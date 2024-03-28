package com.base.hilt.ui.login

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.base.hilt.LoginMutation
import com.base.hilt.UserDataQuery
import com.base.hilt.base.BaseRepository
import com.base.hilt.type.LoginInput
import javax.inject.Inject

class LoginRepository @Inject constructor (var apolloClient: ApolloClient) :BaseRepository() {

    suspend fun callLoginApi(input: LoginInput): ApolloResponse<LoginMutation.Data> {
        val res = apolloClient.mutation(LoginMutation(input)).execute()
        return res
    }

}