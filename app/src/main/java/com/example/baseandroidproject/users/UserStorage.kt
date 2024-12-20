package com.example.baseandroidproject.users

object UserStorage {
    private val listUsers = mutableListOf(User(
        id = 1,
        firstName = "გრიშა",
        lastName = "ონიანი",
        birthday = "1724647601641",
        address = "სტალინის სახლმუზეუმი",
        email = "grisha@mail.ru"
    ),
        User(
            id = 2,
            firstName = "Jemal",
            lastName = "Kakauridze",
            birthday = "1714647601641",
            address = "თბილისი, ლილოს მიტოვებული ქარხანა",
            email = "jemal@gmail.com"
        ),
        User(
            id = 2,
            firstName = "Omger",
            lastName = "Kakauridze",
            birthday = "1724647701641",
            address = "თბილისი, ასათიანი 18",
            email = "omger@gmail.com"
        ),
        User(
            id =32,
            firstName = "ბორის",
            lastName = "გარუჩავა",
            birthday = "1714947701641",
            address = "თბილისი, იაშვილი 14",
            email = ""
        ),
        User(
            id =34,
            firstName = "აბთო",
            lastName = "სიხარულიძე",
            birthday = "1711947701641",
            address = "ფოთი",
            email = "tebzi@gmail.com",
        )
    )

    fun addUser(user: User): Boolean {
        val alreadyExistingUser =
            listUsers.find { it.id == user.id || it.email.uppercase() == user.email.uppercase() }
        if (alreadyExistingUser == null)
        {
            user.id++
            listUsers.add(user)
            return true
        }
        return false
    }

    fun searchUser(search: String): List<User> {
        val lowerSearch = search.lowercase()
        return listUsers.filter { user ->
            user.firstName.lowercase().contains(lowerSearch)
                    || user.lastName.lowercase().contains(lowerSearch)
                    || user.email.lowercase().contains(lowerSearch)
                    || user.birthday.lowercase().contains(lowerSearch)
                    || user.address.lowercase().contains(lowerSearch)
        }
    }
}