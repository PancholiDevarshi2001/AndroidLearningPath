package com.base.hilt.ui.login

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.base.hilt.CreateAccountMutation
import com.base.hilt.LoginMutation
import com.base.hilt.VerifyOtpMutation
import com.base.hilt.base.BaseRepository
import com.base.hilt.type.LoginInput
import com.base.hilt.type.OtpInput
import com.base.hilt.type.SignUpInput
import javax.inject.Inject

class LoginRepository @Inject constructor (var apolloClient: ApolloClient) :BaseRepository() {
    suspend fun callLoginApi(input: LoginInput): ApolloResponse<LoginMutation.Data> {
        return apolloClient.mutation(LoginMutation(input)).execute()
    }
    suspend fun onCreateAccountApi(input: SignUpInput): ApolloResponse<CreateAccountMutation.Data> {
        return apolloClient.mutation(CreateAccountMutation(input)).execute()
    }
    suspend fun verifyOtp(input: OtpInput): ApolloResponse<VerifyOtpMutation.Data> {
        return apolloClient.mutation(VerifyOtpMutation(input)).execute()
    }
}