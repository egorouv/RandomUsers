package com.example.randomusers

import android.os.Parcel
import android.os.Parcelable

data class User(
    val image: String,
    val name: String,
    val address: String,
    val phone: String,
    val gender: String,
    val email: String,
    val username: String,
    val age: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(gender)
        parcel.writeString(email)
        parcel.writeString(username)
        parcel.writeString(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}