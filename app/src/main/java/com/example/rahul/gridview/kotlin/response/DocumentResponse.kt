package com.example.rahul.gridview.kotlin.response

import com.example.rahul.gridview.core.APIResponse

data class DocumentResponse(
        val Data: List<DocEntity>,

        ):APIResponse()