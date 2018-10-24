package pl.karolmichalski.shareshopping.data.user

import com.androidhuman.rxfirebase2.auth.rxCreateUserWithEmailAndPassword
import com.androidhuman.rxfirebase2.auth.rxSignInWithEmailAndPassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single
import pl.karolmichalski.shareshopping.domain.user.UserRepository

class UserRepositoryImpl(
		private val firebaseAuth: FirebaseAuth) : UserRepository {

	override fun logIn(email: String?, password: String?): Single<FirebaseUser> {
		return when {
			email.isNullOrBlank() -> Single.fromCallable { throw Exception("Enter email!") }
			password.isNullOrEmpty() -> Single.fromCallable { throw Exception("Enter Password!") }
			else -> firebaseAuth.rxSignInWithEmailAndPassword(email!!, password!!)
		}
	}

	override fun register(email: String?, password: String?): Single<FirebaseUser> {
		return when {
			email.isNullOrBlank() -> Single.fromCallable { throw Exception("Enter email!") }
			password.isNullOrEmpty() -> Single.fromCallable { throw Exception("Enter Password!") }
			else -> firebaseAuth.rxCreateUserWithEmailAndPassword(email!!, password!!)
		}
	}

	override fun getCurrentUser(): FirebaseUser? {
		return firebaseAuth.currentUser
	}

	override fun logOut() {
		firebaseAuth.signOut()
	}
}