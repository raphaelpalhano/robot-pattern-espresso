package com.example.importanttodos.common.factory

interface UserInfo {
    val email: String
    val password: String
}

interface UserRegister {
    val name: String
    val email: String
    val password: String
}

class UserFactory {
    companion object {
        fun getLoginUser(conditional: String): UserInfo {
            val user: UserInfo = when(conditional) {
                "valid" -> object: UserInfo {
                    override val email = "rafa1@gmail.com"
                    override val password = "coca123"
                }
                else -> throw IllegalArgumentException("conditional invalid!")

            }
            return user
        }

        fun getRegisterUser(conditional: String): UserRegister {
            val user = when(conditional) {
                "valid" -> object: UserRegister {
                    override val name = "Rafa"
                    override val email = "rafa1@gmail.com"
                    override val password = "coca123"
                }
                else -> throw IllegalArgumentException("conditional invalid!")

            }
            return user
        }
    }
}