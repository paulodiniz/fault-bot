package io.meme.fetcher.controller

import io.meme.fetcher.image.ImageGenerator
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class WebhooksController {

    @PostMapping(path = ["/meme"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun meme(@RequestParam params: Map<String, String>) {
        val teamMember: String? = params["text"]?.split(" ")?.get(0)
        val text: String = params["text"]?.split(" ")
            ?.toList()
            ?.drop(1)
            ?.reduce { acc, s -> "$acc $s" }
            ?:"WAT"
        val responseUrl: String? = params["response_url"]

        val imageUrl : String = ImageGenerator(teamMember!!, text = text).call()
        val slackMessage : JSONObject = slackMessageJSON(params["channel_id"], imageUrl)

        responseUrl.let { khttp.post(url = it!!, json = slackMessage) }
    }

    private fun slackMessageJSON(channelId: String?, imageUrl: String) : JSONObject {
        val block: JSONObject = JSONObject()
            .put("type", "image")
            .put("image_url", imageUrl)
            .put("alt_text", "funny gif")

        return JSONObject()
            .put("username", "meme bot")
            .put("channel", channelId)
            .put("response_type", "in_channel")
            .put("blocks", JSONArray(listOf(block)))
    }
}