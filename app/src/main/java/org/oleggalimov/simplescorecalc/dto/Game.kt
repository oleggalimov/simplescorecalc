package org.oleggalimov.simplescorecalc.dto

import org.json.JSONObject

class Game : Comparable<Game> {
    var title: String = ""
    var score: Int = 0
    var bestPlayerName: String = ""
    var timeStamp: Long = 0L

    constructor(title: String, score: Int, bestPlayerName: String) {
        this.title = title
        this.score = score
        this.bestPlayerName = bestPlayerName
        this.timeStamp = System.currentTimeMillis()
    }

    constructor(jsonString: String) {
        val json = JSONObject(jsonString)
        this.title = json.getString("title")
        this.score = json.getInt("score")
        this.bestPlayerName = json.getString("bestPlayerName")
        this.timeStamp = json.getLong("timeStamp")
    }

    override fun compareTo(other: Game): Int {
        return if (this.timeStamp == other.timeStamp) {
            if (this.title.compareTo(other.title) == 0) {
                if (this.score == other.score) {
                    this.bestPlayerName.compareTo(other.bestPlayerName)
                } else {
                    this.score.compareTo(other.score)
                }
            } else {
                this.title.compareTo(other.title)
            }
        } else {
            return timeStamp.compareTo(other.timeStamp)
        }

    }

    override fun toString(): String {
        val json = toJson()
        return json.toString()
    }

    private fun toJson(): JSONObject {
        val result = JSONObject()
        result.put("title", this.title)
        result.put("score", this.score)
        result.put("bestPlayerName", this.bestPlayerName)
        result.put("timeStamp", this.timeStamp)
        return result
    }
}