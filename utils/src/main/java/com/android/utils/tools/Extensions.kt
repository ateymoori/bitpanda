package com.android.utils.tools

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.round


fun String.log(tag: String? = null) :String{
    if (tag != null)
        Log.d(tag, this)
    else
        Log.d("debug_", this)
    return this
}

infix fun Double.round(decimals: Int): Double {
    var multiplier = 1.00
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}


fun String.toast(ctx: Context?): String {
    Toast.makeText(ctx, this, Toast.LENGTH_LONG).show()
    return this
}

fun <T : androidx.recyclerview.widget.RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}


fun Long.length() = when (this) {
    0.toLong() -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

inline fun <T: Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}
