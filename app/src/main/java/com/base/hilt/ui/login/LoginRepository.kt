package com.base.hilt.ui.login

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.base.hilt.LoginMutation
import com.base.hilt.SignupMutation
import com.base.hilt.VerifyOtpMutation
import com.base.hilt.base.BaseRepository
import com.base.hilt.network.ResponseHandler
import com.base.hilt.type.LoginInput
import com.base.hilt.type.OtpInput
import com.base.hilt.type.SignUpInput
import javax.inject.Inject

class LoginRepository @Inject constructor (var apolloClient: ApolloClient) :BaseRepository() {
    suspend fun callLoginApi(input: LoginInput): ResponseHandler<ApolloResponse<LoginMutation.Data>> {
        return graphQlApiCall {
            apolloClient.mutation(LoginMutation(input)).execute()
        }
    }
    suspend fun onCreateAccountApi(input: SignUpInput): ResponseHandler<ApolloResponse<SignupMutation.Data>> {
        return graphQlApiCall {
            apolloClient.mutation(SignupMutation(input)).execute()
        }
    }
    suspend fun verifyOtp(input: OtpInput): ResponseHandler<ApolloResponse<VerifyOtpMutation.Data>> {
        return graphQlApiCall {
            apolloClient.mutation(VerifyOtpMutation(input)).execute()
        }
    }
}