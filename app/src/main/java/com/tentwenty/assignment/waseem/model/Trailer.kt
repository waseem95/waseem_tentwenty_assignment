package com.tentwenty.assignment.waseem.model

import com.google.gson.annotations.SerializedName

class Trailer(
    @field:SerializedName("key") var key: String, @field:SerializedName(
        "name"
    ) var name: String
)