package int20h.troipsa.demoapp.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import int20h.troipsa.demoapp.data.prefs.base.BaseDataStoreManager
import int20h.troipsa.demoapp.data.prefs.base.value

class DemoPrefManager constructor(
    context: Context
) : BaseDataStoreManager(context) {

    override val fileName = PREFS_FILENAME

    val demoPref = dataStore.value(DEMO_PREF_NAME, false)

    companion object {
        const val PREFS_FILENAME = "pseudo_prefs"

        private val DEMO_PREF_NAME = booleanPreferencesKey("demo_pref_name")
    }

}