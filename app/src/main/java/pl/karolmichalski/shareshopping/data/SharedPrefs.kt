package pl.karolmichalski.shareshopping.data

interface SharedPrefs {
	fun saveUid(uid: String)
	fun getUid(): String
}