package dev.soul.moviedbkmp.shared


//actual class ImageLoader {
//    private val imageLoader = ImageLoader.Builder(context)
//        .availableMemoryPercentage(0.25)
//        .crossfade(true)
//        .build()
//
//    actual fun loadImage(url: String, onLoaded: (Any) -> Unit) {
//        val request = ImageRequest.Builder(context)
//            .data(url)
//            .target { drawable ->
//                onLoaded(drawable) // Handle the image loaded successfully
//            }
//            .build()
//        imageLoader.enqueue(request)
//    }
//}