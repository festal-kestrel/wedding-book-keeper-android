package com.example.wedding_book_keeper.data.remote.response

enum class Role(private val roleName: String) {
    MANAGER("MANAGER"),
    PARTNER("PARTNER"),
    GUEST("GUEST");

    override fun toString(): String {
        return roleName
    }
}
