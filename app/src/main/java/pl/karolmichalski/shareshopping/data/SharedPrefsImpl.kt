package pl.karolmichalski.shareshopping.data

import android.content.Context
import pl.karolmichalski.shareshopping.R

class SharedPrefsImpl(context: Context) : SharedPrefs {

	private val sharedPreferences = context.getSharedPreferences(context.getString(R.string.sharedpreferences_file_key), Context.MODE_PRIVATE)

	override fun saveUid(uid: String) {
		sharedPreferences.edit().apply {
			putString("uid", uid)
			apply()
		}
	}

	override fun getUid(): String {
		return sharedPreferences.getString("uid", "") ?: ""
	}
}