package com.aichat.features.feature_chat.ui

import android.content.Context
import android.text.Editable
import android.view.ContextMenu
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aichat.features.R
import com.aichat.features.databinding.FragmentChatBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.noxai.base.base.BaseFragment
import org.noxai.base.common.ToastMessage
import org.noxai.data.rerepository.repository.UserRepositoryImpl
import org.noxai.features.DaggerAppComponent
import com.aichat.features.feature_chat.adapter.chat.ChatCustomRecyclerAdapter
import com.aichat.features.feature_chat.adapter.chat.ChatRecyclerViewItem
import com.aichat.features.feature_chat.adapter.menuChat.MenuChatCustomRecyclerAdapter
import org.noxai.features.feature_chat.adapter.menuChat.MenuChatRecyclerViewItem
import com.aichat.features.feature_chat.domain.repository.ChatUserRepositoryImpl
import com.aichat.features.feature_chat.vm.ChatViewModel
import javax.inject.Inject




class ChatFragment : BaseFragment<FragmentChatBinding>(
    R.layout.fragment_chat,
    FragmentChatBinding::inflate
) {

    @Inject
    lateinit var viewModel: ChatViewModel

    @Inject
    lateinit var userRepository: UserRepositoryImpl

    @Inject
    lateinit var chatUserRepositoryImpl: ChatUserRepositoryImpl


    private val chatCustomRecyclerAdapter: ChatCustomRecyclerAdapter by lazy {
        ChatCustomRecyclerAdapter(
            _list = fillListMessage(),
            viewModel = viewModel,
            chatUserRepositoryImpl = chatUserRepositoryImpl,
            user = userRepository.getUser()!!,
            context = requireContext()
        )
    }

    private val menuChatCustomRecyclerAdapter: MenuChatCustomRecyclerAdapter by lazy {
        MenuChatCustomRecyclerAdapter(
            _list = fillListChats(),
            viewModel = viewModel,
            chatCustomRecyclerAdapter = chatCustomRecyclerAdapter,
            chatUserRepositoryImpl = chatUserRepositoryImpl,
            user = userRepository.getUser()!!,
            drawer = binding.drawer,
            renameChatName = ::goneText
        )
    }


    override fun onAttach(context: Context) {
        DaggerAppComponent.factory().factory(requireContext()).inject(this)
        super.onAttach(context)
    }

    override fun setupUi() = with(binding) {


        viewModel.updateChats(userRepository.getUser()!!, menuChatCustomRecyclerAdapter)


        registerForContextMenu(binding.chatsRecyclerView)

        /**
         * Инициализируем RecyclerView чата и меню
         **/
        initRecyclerView()
        initRecyclerViewMenu()

        /**
         * Инициализируем название чата
         **/


        /**
         * Добавляем работу с ViewModel(Observe и т.д.)
         **/
        // TODO()

        /**
         * Добавляем работу с UI элементами
         **/
        sendButton.setOnClickListener {

            val editTextString: String = binding.edittext.text.toString()


            if (editTextString.isNotEmpty()) {

                edittext.text = Editable.Factory.getInstance().newEditable("")

                lifecycleScope.launch(IO) {

                    viewModel.send(
                        editTextString,
                        userRepository.getUser()!!,
                        chatCustomRecyclerAdapter,
                        menuChatCustomRecyclerAdapter,
                        ::goneText,
                        ::setChatName
                    )


                }

                messageChatRecyclerView.scrollToPosition(viewModel.livedata_chat_message.value?.size!! + 1)

            }

            ToastMessage.makeToastLong(
                requireContext(),
                viewModel.liveData_stateScreen.value?.IdChat.toString()
            )

        }

        menuID.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        profileIDLayout.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_profileScreenFragment)
        }

        settingsIDLayout.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_settingsScreenFragment)
        }


        /**
         * Обрабатываем нажатие на кнопку назад
         **/
        addCallBackPressed()


    }

    private fun fillListMessage(): List<ChatRecyclerViewItem> =
        viewModel.livedata_chat_message.value!!

    private fun fillListChats(): List<MenuChatRecyclerViewItem> =
        viewModel.livedata_chats.value!!

    private fun goneText() {
        if (viewModel.livedata_chat_message.value?.size!! > 0) {
            binding.askAnyoneID.visibility = View.GONE
        }
    }

    private fun setChatName() {
        binding.chatName.text =
            viewModel.liveData_stateScreen.value?.nameChat.toString()
    }

    private fun initRecyclerView() {
        val recyclerView: RecyclerView = binding.messageChatRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
        )
        recyclerView.adapter = chatCustomRecyclerAdapter
    }

    private fun initRecyclerViewMenu() {
        val recyclerView = binding.chatsRecyclerView


        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
        )

        recyclerView.adapter = menuChatCustomRecyclerAdapter

    }


    private fun addCallBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = activity?.menuInflater
        inflater?.inflate(R.menu.context_menu_chat, menu)
    }

}

