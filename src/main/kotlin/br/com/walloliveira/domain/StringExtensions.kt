package br.com.walloliveira.domain

import java.util.*

fun String.base64DecodeToByteArray(): ByteArray = Base64.getDecoder().decode(this)
