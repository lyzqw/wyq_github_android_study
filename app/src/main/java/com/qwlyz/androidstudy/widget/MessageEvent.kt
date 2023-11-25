package com.qwlyz.androidstudy.widget

import com.yuwq.libs_common.BaseStatEvent

class MessageEvent : BaseStatEvent() {

    companion object{

        fun report(){
            MessageEvent()
                .apply { roomIdParam to "123" }
                .send()
        }
    }

    val roomIdParam = Param("room_Id")




}