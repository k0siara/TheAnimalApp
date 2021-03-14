package com.patrykkosieradzki.theanimalapp.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

interface CipherProvider {
    val encryptCipher: Cipher
    fun decryptCipher(iv: ByteArray): Cipher
}

class AesCipherProvider : CipherProvider {

    override val encryptCipher: Cipher
        get() = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, getOrCreateKey())
        }

    override fun decryptCipher(iv: ByteArray): Cipher =
        Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getOrCreateKey(), IvParameterSpec(iv))
        }

    private fun getOrCreateKey(): SecretKey =
        (keyStore.getEntry(KEY_NAME, null) as? KeyStore.SecretKeyEntry)?.secretKey
            ?: generateKey()

    private fun generateKey(): SecretKey =
        KeyGenerator.getInstance(ALGORITHM, KEY_STORE_NAME)
            .apply { init(keyGenParams) }
            .generateKey()

    private val keyGenParams =
        KeyGenParameterSpec.Builder(
            KEY_NAME,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).apply {
            setBlockModes(BLOCK_MODE)
            setEncryptionPaddings(PADDING)
            setUserAuthenticationRequired(false)
            setRandomizedEncryptionRequired(true)
        }.build()

    companion object {
        const val KEY_NAME = "Key Name"
        const val KEY_STORE_NAME = "Key Store Name"
        private const val ANDROID_KEY_STORE_TYPE = "AndroidKeyStore"
        private const val SIMPLE_DATA_KEY_NAME = "SimpleDataKey"
        const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
        val keyStore: KeyStore = KeyStore.getInstance(ANDROID_KEY_STORE_TYPE)
            .apply { load(null) }
    }
}