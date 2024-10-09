package com.aichat.features.feature_chat.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.noxai.base.model.UserBaseModel
import org.noxai.base.model.chat_model.model_chat.AllMessagesChatModel
import com.aichat.features.feature_chat.adapter.chat.ChatCustomRecyclerAdapter
import com.aichat.features.feature_chat.adapter.chat.ChatRecyclerViewItem
import com.aichat.features.feature_chat.adapter.menuChat.MenuChatCustomRecyclerAdapter
import org.noxai.features.feature_chat.adapter.menuChat.MenuChatRecyclerViewItem
import com.aichat.features.feature_chat.domain.model.ModelStateOfScreen
import com.aichat.features.feature_chat.domain.repository.ChatUserRepositoryImpl
import javax.inject.Inject


open class ChatViewModel @Inject constructor(

): ViewModel() {

    private val mutableLiveData_message: MutableLiveData<MutableList<ChatRecyclerViewItem>> =
        MutableLiveData<MutableList<ChatRecyclerViewItem>>(mutableListOf())

    private val mutableLiveData_chats: MutableLiveData<MutableList<MenuChatRecyclerViewItem>> =
        MutableLiveData<MutableList<MenuChatRecyclerViewItem>>(
            mutableListOf(
                MenuChatRecyclerViewItem.NewChat(0),
            ))

    private val mutableLiveData_stateScreen : MutableLiveData<ModelStateOfScreen> =
        MutableLiveData(ModelStateOfScreen())


    @Inject
    lateinit var chatUserRepositoryImpl: ChatUserRepositoryImpl

    /**
     * LiveData для Fragment
    */
    val livedata_chat_message : LiveData<MutableList<ChatRecyclerViewItem>> = mutableLiveData_message
    val livedata_chats : LiveData<MutableList<MenuChatRecyclerViewItem>> = mutableLiveData_chats
    val liveData_stateScreen : LiveData<ModelStateOfScreen> = mutableLiveData_stateScreen



    fun send(
        textMessage: String,
        user: UserBaseModel,
        chatCustomRecyclerAdapter: ChatCustomRecyclerAdapter,
        menuChatCustomRecyclerAdapter: MenuChatCustomRecyclerAdapter,
        onChange: () -> Unit,
        renameChatName: () -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {


        launch(Dispatchers.Main) {
            onChange()
        }
        sendMessageUser(textMessage, chatCustomRecyclerAdapter)
        sendMessage(textMessage, user, chatCustomRecyclerAdapter, renameChatName)
        updateChats(user = user, menuChatCustomRecyclerAdapter = menuChatCustomRecyclerAdapter)


    }


    fun updateChats(
        user: UserBaseModel,
        menuChatCustomRecyclerAdapter: MenuChatCustomRecyclerAdapter,
    ) = viewModelScope.launch(Dispatchers.IO) {

        /**
         * Получаем весь список чатоов, и заполняем меню
         */

        val all_chat = chatUserRepositoryImpl.chatUser(user)

        mutableLiveData_chats.value?.clear()

        mutableLiveData_chats.value?.add(MenuChatRecyclerViewItem.NewChat(0))

        all_chat.chats?.forEach {
            mutableLiveData_chats.value?.add(MenuChatRecyclerViewItem.SpecificChat(it.chatId.toInt(), it.chatName))
        }

        launch(Dispatchers.Main) {
            menuChatCustomRecyclerAdapter.notifyItemChanged(mutableLiveData_chats.value?.size!! - 1)
        }
    }

    private suspend fun sendMessage(
        textMessage: String,
        user: UserBaseModel,
        chatCustomRecyclerAdapter: ChatCustomRecyclerAdapter,
        renameChatName: () -> Unit
    ) {

        /**
         * Если мы находимся в новом чате, то создаем новый чат
         */
        if (mutableLiveData_stateScreen.value?.IdChat == "-1") {

            val answerRequestDeferred = viewModelScope.async(Dispatchers.IO) {
                chatUserRepositoryImpl.chatQuestionUser(user = user, text = textMessage)
            }
            val answerRequest = answerRequestDeferred.await()

            mutableLiveData_stateScreen.value?.IdChat = answerRequest.chat_id.toString()
            mutableLiveData_stateScreen.value?.nameChat = answerRequest.chatName.toString()

            mutableLiveData_message.value?.add(
                ChatRecyclerViewItem.GPTMessage(
                    mutableLiveData_message.value!!.size,
                    answerRequest.answer?.text.toString()
                )
            )

            viewModelScope.launch(Dispatchers.Main) {
                renameChatName()
            }
        } else {
            val answerRequest =
                chatUserRepositoryImpl.chatQuestionUserCurrentChat(user,
                    mutableLiveData_stateScreen.value?.IdChat.toString(),textMessage)

            mutableLiveData_message.value?.add(
                ChatRecyclerViewItem.GPTMessage(
                    mutableLiveData_message.value!!.size,
                    answerRequest.answer?.text.toString()
                )
            )

            viewModelScope.launch(Dispatchers.Main) {
                renameChatName()
            }
        }


        viewModelScope.launch(Dispatchers.Main) {
            chatCustomRecyclerAdapter.notifyItemChanged(mutableLiveData_message.value?.size!! - 1)
        }
    }


    private fun sendMessageUser(
        textMessage: String,
        chatCustomRecyclerAdapter: ChatCustomRecyclerAdapter,
    ) = viewModelScope.launch(Dispatchers.IO) {
        mutableLiveData_message.value?.add(ChatRecyclerViewItem.UserMessage(mutableLiveData_message.value!!.size, textMessage))
        viewModelScope.launch(Dispatchers.Main) {
            chatCustomRecyclerAdapter.notifyItemChanged(mutableLiveData_message.value?.size!! - 1)
        }
    }


    /**
     * Используем для смены чатов
    */
    fun setMessages(
        modelOfMessages: AllMessagesChatModel,
        chatCustomRecyclerAdapter: ChatCustomRecyclerAdapter,
        renameChatName: () -> Unit
    ) = viewModelScope.launch(Dispatchers.Main) {

        Log.e("setMessages", modelOfMessages.toString())


        mutableLiveData_message.value?.clear()

        mutableLiveData_stateScreen.value?.IdChat = modelOfMessages.chatId.toString()
        mutableLiveData_stateScreen.value?.nameChat = modelOfMessages.chatName.toString()

        modelOfMessages.msgs?.forEach {
            if(it.bot == false) {
                mutableLiveData_message.value?.add(ChatRecyclerViewItem.UserMessage(0, it.text))
            } else {
                mutableLiveData_message.value?.add(ChatRecyclerViewItem.GPTMessage(0, it.text))
            }
        }

        renameChatName()

        chatCustomRecyclerAdapter.notifyDataSetChanged()

    }

}