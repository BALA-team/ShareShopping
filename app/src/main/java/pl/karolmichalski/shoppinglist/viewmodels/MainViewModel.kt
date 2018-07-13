package pl.karolmichalski.shoppinglist.viewmodels

import android.app.Application
import android.arch.lifecycle.*
import pl.karolmichalski.shoppinglist.ProductRepository
import pl.karolmichalski.shoppinglist.models.Product

class MainViewModel(application: Application) : AndroidViewModel(application) {

	val newProductName = MutableLiveData<String>()

	private val repository = ProductRepository(application)

	fun getCreatedProduct(): Product {
		val product = Product(newProductName.value!!)
		repository.insert(product)
		return product
	}

	fun clearNewProductName() {
		newProductName.value = ""
	}

	fun getProducts(): LiveData<List<Product>> {
		return repository.getAll()
	}
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
	observeForever(object : Observer<T> {
		override fun onChanged(t: T?) {
			observer.onChanged(t)
			removeObserver(this)
		}
	})
}