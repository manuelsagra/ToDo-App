package com.manuelsagra.todo.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class Task(
    val id: Long,
    val parentId: Long,
    val content: String,
    val createdAt: Date,
    val isDone: Boolean,
    val priority: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()!!,
        Date(parcel.readLong()),
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(parentId)
        parcel.writeString(content)
        parcel.writeLong(createdAt.time)
        parcel.writeByte(if (isDone) 1 else 0)
        parcel.writeInt(priority)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return com.manuelsagra.todo.data.model.Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }

}