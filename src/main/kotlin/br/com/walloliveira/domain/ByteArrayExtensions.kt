package br.com.walloliveira.domain

import java.util.*


fun ByteArray.base64EncodeToString(): String = Base64.getEncoder().encodeToString(this)
