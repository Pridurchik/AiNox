package org.noxai.features

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.noxai.features.feature_auth.ui.AuthFragment
import com.aichat.features.feature_bottomsheet.GoogleBottomSheet
import com.aichat.features.feature_bottomsheet.YandexBottomSheet
import com.aichat.features.feature_chat.ui.ChatFragment
import com.aichat.features.feature_bottomsheet.PayBottomSheet
import com.aichat.features.feature_bottomsheet.PaymentBottomSheet
import com.aichat.features.feature_regist.ui.RegistFragment
import org.noxai.features.feature_transaction.ui.ChoosingEntryFragment
import org.noxai.features.feature_transaction.ui.MainFragment

@Component
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun factory(@BindsInstance context: Context) : AppComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(choosingEntryFragment: ChoosingEntryFragment)
    fun inject(authFragment: AuthFragment)
    fun inject(registFragment: RegistFragment)
    fun inject(chatFragment: ChatFragment)
    fun inject(payBottomSheet: PayBottomSheet)
    fun inject(paymentBottomSheet: PaymentBottomSheet)
    fun inject(googleBottomSheet: GoogleBottomSheet)
    fun inject(yandexBottomSheet: YandexBottomSheet)

}