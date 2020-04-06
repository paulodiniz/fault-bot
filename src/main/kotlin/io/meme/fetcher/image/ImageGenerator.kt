package io.meme.fetcher.image

import com.cloudinary.Cloudinary
import com.cloudinary.Transformation
import com.cloudinary.transformation.TextLayer

class ImageGenerator(val teamMember: String, val text: String) {
    val cloudinary = Cloudinary()

    fun call(): String {
        val url: String = cloudinary
            .url()
            .cloudName("dyfa7lpcj")
            .transformation(
                Transformation<Transformation<out Transformation<*>>?>()
                    .overlay(TextLayer()
                        .fontFamily("Impact")
                        .text(text.toUpperCase())
                        .fontSize(90)
                    )
                    ?.gravity("south")
                    ?.color("white")
            )
            .imageTag("$teamMember.jpg")

        return url.split("\'")[1]
    }
}