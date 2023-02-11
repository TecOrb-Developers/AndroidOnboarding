package com.vkpal.onboardlibrary

import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import androidx.annotation.DrawableRes
import androidx.annotation.ColorRes

class OnboardingCard {
    var title: String? = null
    var description: String? = null
    var imageResource: Drawable? = null

    @StringRes
    var titleResourceId = 0

    @StringRes
    var descriptionResourceId = 0

    @DrawableRes
    var imageResourceId = 0

    @ColorRes
    var titleColor = 0

    @ColorRes
    var descriptionColor = 0

    @ColorRes
    var backgroundColor = 0
    var titleTextSize = 0f
    var descriptionTextSize = 0f
    var iconWidth = 0
    var iconHeight = 0
    var marginTop = 0
    var marginLeft = 0
    var marginRight = 0
    var marginBottom = 0
    var isSkipDisplay = true
        private set

    constructor(title: String?, description: String?) {
        this.title = title
        this.description = description
    }

    constructor(title: Int, description: Int) {
        titleResourceId = title
        descriptionResourceId = description
    }

    constructor(title: String?, description: String?, imageResourceId: Int) {
        this.title = title
        this.description = description
        this.imageResourceId = imageResourceId
    }

    constructor(title: String?, description: String?, imageResource: Drawable?) {
        this.title = title
        this.description = description
        this.imageResource = imageResource
    }

    constructor(title: Int, description: Int, imageResourceId: Int) {
        titleResourceId = title
        descriptionResourceId = description
        this.imageResourceId = imageResourceId
    }

    constructor(title: Int, description: Int, imageResource: Drawable?) {
        titleResourceId = title
        descriptionResourceId = description
        this.imageResource = imageResource
    }

    fun setIconLayoutParams(iconWidth: Int, iconHeight: Int, marginTop: Int, marginLeft: Int, marginRight: Int, marginBottom: Int) {
        this.iconWidth = iconWidth
        this.iconHeight = iconHeight
        this.marginLeft = marginLeft
        this.marginRight = marginRight
        this.marginTop = marginTop
        this.marginBottom = marginBottom
    }

    /**
     * SetDisplaySkip
     * @param isDisplay if true the floating button is display
     */
    fun setDisplaySkip(isDisplay: Boolean) {
        isSkipDisplay = isDisplay
    }
}