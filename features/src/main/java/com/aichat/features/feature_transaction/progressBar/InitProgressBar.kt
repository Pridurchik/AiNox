package org.noxai.features.feature_transaction.progressBar

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.aichat.features.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.noxai.base.repository.ChatUserRepository
import org.noxai.base.repository.UserRepository

class InitProgressBar {

    companion object {

//        suspend fun goneProgressBarAndNavigate(
//            userRepository : UserRepository,
//            progressBar : ProgressBar,
//            fragment: Fragment,
//            fragmentManager: FragmentManager,
//            chatUserRepository: ChatUserRepository? = null,
//            context: Context
//        ) {
//            try {
//
//                /**
//                 * Если у user есть подключение к интернету
//                 **/
//                if(CheckInternet.isConnect(context)) {
//
//                    /**
//                     * Если user вошел в систему и не вышел из нее
//                     **/
//                    if (userRepository.stateUser()) {
//
//                        findNavController(fragment).navigate(R.id.action_mainFragment_to_chatFragment)
//
//
//                        progressBar.visibility = View.GONE
//                        ToastMessage.makeToastShort(context, userRepository.getUser().toString())
//
//
//
//                    }
//
//                    /**
//                     *
//                     * Если user не вошел в систему
//                     *
//                     **/
//                    else {
//
//                        delay(1500)
//                        progressBar.visibility = View.GONE
//                        findNavController(fragment).navigate(R.id.action_mainFragment_to_pagerFragment)
//                    }
//
//                }
//                /**
//                 * Если отсутствует подключение к интернету
//                 **/
//                else {
//                    ToastMessage.makeToastShort(
//                        context = context,
//                        textMessage = "Отсутствует подключенгие к сети интернет"
//                    )
//
//                    delay(5000)
//
//                    goneProgressBarAndNavigate(
//                        userRepository, progressBar, fragment, fragmentManager, chatUserRepository, context
//                    )
//                }
//
//            } catch (e: Exception) {
//                goneProgressBarAndNavigate(
//                    userRepository,
//                    progressBar,
//                    fragment,
//                    fragmentManager,
//                    chatUserRepository,
//                    context
//                )
//            }
//        }

        suspend fun navigateMainFrag(
            userRepository: UserRepository,
            navController: NavController,
            progressBar: ProgressBar,

            ) = withContext(Dispatchers.Main) {


            /**
             *
             * Условие если user вошел в систему
             *
             */
            launch(Dispatchers.Main) {
                if (userRepository.stateUser()) {
                    delay(1000)
                    navController.navigate(R.id.action_mainFragment_to_chatFragment)
                    progressBar.visibility = View.GONE
                }

                /**
                 *
                 * Если user не вошел в систему
                 *
                 */
                else {
                    delay(1000)
                    navController.navigate(R.id.action_mainFragment_to_pagerFragment)
                    progressBar.visibility = View.GONE
                }
            }
        }


        private fun initBottomSheet(
            userRepository: UserRepository,
            chatUserRepository: ChatUserRepository,
            parentFragmentManager: FragmentManager,
            context: Context
        ) {
//
//            try {
//
//                val dialog = Dialog(context)
//                dialog.setContentView(R.layout.fragment_pay_bottom_sheet)
//
//                runBlocking {
//
//                    if (chatUserRepository.chatUser(
//                            userRepository.getUser()
//                        ).sub == "n"
//                    ) {
//
//                        /**
//                         * Условие если у юзера имеется подписка
//                         **/
//
//
//                        Log.e(
//                            "TAG_USER",
//                            chatUserRepository.chatUser(userRepository.getUser()
//                            ).toString()
//                        )
//
//
//                    } else {
//
//
//                        val payBottomSheet = PayBottomSheet(context)
//                        payBottomSheet.setCancelable(false)
//                        payBottomSheet.show(parentFragmentManager, "tag")
//                        payBottomSheet.binding.idBtnDismiss.setOnClickListener {
//
//                        }
//                    }
//                }
//
//            } catch (e: Exception) {
//
//            }
//        }
        }
    }
}