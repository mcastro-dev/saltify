package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model

data class Album(
    val id: String,
    val title: String,
    val images: List<Image>,
    val artist: Artist
) {
    // Since none of the images have crazy sizes, it's ok for now
    val largestImage: Image
        get() {
            var largest: Image = images.first()
            for (i in 1 until images.size) {
                val current = images[i]
                if (current.height > largest.height) {
                    largest = current
                }
            }
            return largest
        }
}