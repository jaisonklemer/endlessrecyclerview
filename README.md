## Endless RecyclerView
A simple lib to create an infinite list in a RecyclerView.

When you reach the end of the list, a callback is triggered, where you can make a call to fetch more data from the api, for example.

## Usage
You need to inform your your recyclerView and register your callback. Pass the recyclerView as a parameter in the constructor.

```kotlin
//Create a callback of EndlessRecyclerView
val infiniteScrolling = EndlessRecyclerView(binding.myRecyclerView) {
//You made it to the end of the list, fetch more data
    loadMoreItems()
}

//Bind EndlessRecyclerView to recyclerView scrolling
binding.myRecyclerView.addOnScrollListener(infiniteScrolling.create())
```
By default the lib does not show a loading view. You can call the ``` showLoading(true)``` method to show.

You can also create your own load view, just call the ```setCustomLoadingView(R.layout.my_custom_loading)``` method.

| Method | Explanation |
|--|--|
| ```showLoading(boolean)``` | Show loading view |
| ```stopLoading()``` | Stop current loading animation |
| ```setCustomLoadingView(@LayoutRes int)``` | Set a custom loading view to show |
| ```create()``` | Create a instance of EndlessRecyclerView |





Adding to your project
------------------------

- Add the following configuration in your build.gradle file.

```gradle
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
	implementation 'com.github.jaisonklemer:endlessrecyclerview:{latest-version}'
}
```

## License

<a href="LICENSE.md">MIT</a>