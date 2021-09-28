package com.any.common.core.id

import com.any.common.core.net.IpUtil
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

@Suppress(names = ["unused"])
class SfWorker @JvmOverloads constructor(dcId: Long, mId: Long = IpUtil.ip2longReverse(IpUtil.localIp())) {

    /**
     * 起始的时间戳 2020-05-03 00:05:03
     */
    private val timestamp: Long = 1588435503000L


    /**
     * 数据中心
     */
    private var dataCenterId: Long = dcId

    /**
     * 机器标识
     */
    private var machineId: Long = mId

    /**
     * 序列号
     */
    private var sequence = 0L

    /**
     * 上一次时间戳
     */
    private var lastStamp = -1L

    /**
     * 不要使用公平锁
     */
    private val lock: Lock = ReentrantLock()

    fun nextId(): Long {
        lock.lock()
        return try {
            id()
        } finally {
            lock.unlock()
        }
    }

    @Synchronized
    fun nextIdSync(): Long {
        return this.id()
    }

    private fun id(): Long {
        var stamp = System.currentTimeMillis()
        if (stamp < lastStamp) {
            throw RuntimeException("Clock moved backwards.  Refusing to generate id")
        }
        if (stamp == lastStamp) {
            sequence = sequence + 1 and MAX_SEQUENCE
            if (sequence == 0L) {
                stamp = nextStamp()
            }
        } else {
            sequence = 0L
        }
        lastStamp = stamp
        return (stamp - timestamp) shl TIME_STAMP_LEFT or (dataCenterId shl DATA_CENTER_LEFT) or (machineId shl MACHINE_LEFT) or sequence
    }

    private fun nextStamp(): Long {
        var stamp = System.currentTimeMillis()
        while (stamp <= lastStamp) {
            stamp = System.currentTimeMillis()
        }
        return stamp
    }

    companion object {
        /**
         * 每一部分占用的位数
         * SEQUENCE_BITS 序列号占用的位数
         * MACHINE_BITS 机器标识占用的位数
         * DATA_CENTER_BITS 数据中心占用的位数
         */
        private const val SEQUENCE_BITS: Int = 12
        private const val MACHINE_BITS: Int = 5
        private const val DATA_CENTER_BITS: Int = 5

        /**
         * 每一部分的最大值
         * MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BITS);
         * MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BITS);
         * MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BITS);
         */
        private const val MAX_SEQUENCE = -1L shl SEQUENCE_BITS

        /**
         * 每一部分向左的位移
         */
        private const val MACHINE_LEFT = SEQUENCE_BITS
        private const val DATA_CENTER_LEFT = SEQUENCE_BITS + MACHINE_BITS
        private const val TIME_STAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BITS
    }
}