package com.canter.fullscreendemo.data;

/**
 * Description : ItemData.java
 * Creation    : 2020-04-12
 * Author      : cangui.ccg
 */
public class ItemData {
    String itemName;
    int viewId;

    public String getItemName() {
        return itemName;
    }

    public ItemData setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public int getViewId() {
        return viewId;
    }

    public ItemData setViewId(int viewId) {
        this.viewId = viewId;
        return this;
    }
}
