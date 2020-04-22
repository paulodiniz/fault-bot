package io.meme.fetcher.image

import com.cloudinary.Cloudinary
import com.cloudinary.Transformation
import com.cloudinary.transformation.TextLayer

class ImageGenerator(val teamMember: String, val text: String) {
    private val cloudinary = Cloudinary()

    fun call(): String {
        val url: String = cloudinary
            .url()
            .cloudName("dyfa7lpcj")
            .transformation(memefyImage())
            .imageTag("$teamMember.jpg")
        return url.split("\'")[1]
    }

    private fun memefyImage(): Transformation<out Transformation<*>>? {
        println("SIZE OF THE TEXT")
        println(text.length)

        if(text.length <= 10) {
            return Transformation<Transformation<out Transformation<*>>?>()
                .overlay(
                    TextLayer()
                        .fontFamily("Impact")
                        .text(text.toUpperCase())
                        .fontSize(90)
                )
                ?.gravity("south")
                ?.color("white")
        }

        return Transformation<Transformation<out Transformation<*>>?>()
            .overlay(
                TextLayer()
                    .fontFamily("Impact")
                    .text(text.substring(0, 10).toUpperCase())
                    .fontSize(50)
            )
            ?.gravity("north")?.color("white")
            ?.chain()
            ?.overlay(
                TextLayer()
                    .fontFamily("Impact")
                    .text(text.substring(10).toUpperCase())
                    .fontSize(50)
            )
            ?.gravity("south")?.color("white")
    }
}