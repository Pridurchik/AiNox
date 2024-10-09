package org.noxai.data.rerepository.retrofit

import org.noxai.base.model.chat_model.model_chat.ModelChat
import org.noxai.base.model.ModelRegist
import org.noxai.base.model.chat_model.model_chat.AllMessagesChatModel
import org.noxai.base.model.chat_model.model_chat_question.ModelChatQuestion
import org.noxai.base.model.chat_model.model_current_chat_question.CurrentChatQuestionModel
import org.noxai.data.rerepository.model.AllMessagesOfChatBody
import org.noxai.data.rerepository.model.CurrentChatQuestionBody
import org.noxai.data.rerepository.model.NewChatQuestionBody
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServieces {

    @POST("api_log.php")
    suspend fun authRequest(@Body body: org.noxai.data.rerepository.model.Body) : ModelChat

    @POST("api_reg.php")
    suspend fun registrationRequest(@Body body: org.noxai.data.rerepository.model.Body) : ModelRegist

    @POST("api_chat.php")
    suspend fun allChatRequest(@Body body: org.noxai.data.rerepository.model.Body) : ModelChat

    @POST("api_chat.php")
    suspend fun allMessagesCurrentChat(@Body body: AllMessagesOfChatBody) : AllMessagesChatModel

    @POST("api_chat.php")
    suspend fun newChatQuestionRequest(@Body body: NewChatQuestionBody) : ModelChatQuestion

    @POST("api_chat.php")
    suspend fun currentChatQuestionRequest(@Body body: CurrentChatQuestionBody) : CurrentChatQuestionModel

}