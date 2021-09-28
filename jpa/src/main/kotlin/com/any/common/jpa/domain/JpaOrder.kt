package com.any.common.jpa.domain

import com.any.common.jpa.domain.enums.JpaOrderModel

/**
 * javadoc JpaOrder
 * <p>
 * <p>
 * @author zhang yebai
 * @date 2021/7/23 3:32 PM
 * @version 1.0.0
 **/
class JpaOrder(val field: String, val mode: JpaOrderModel = JpaOrderModel.ASC) {
    override fun toString(): String {
        return "JpaOrder(field='$field', mode=$mode)"
    }
}