package com.any.common.core.id

import java.util.concurrent.ThreadLocalRandom

object Ids {

    private val SNOW_FLAKE_ID_WORKER_0: SfWorker = SfWorker(0L, 0L)

    private val SNOW_FLAKE_ID_WORKER_1: SfWorker = SfWorker(0L, 1L)

    private val SNOW_FLAKE_ID_WORKER_2: SfWorker = SfWorker(0L, 2L)

    private val SNOW_FLAKE_ID_WORKER_3: SfWorker = SfWorker(0L, 3L)

    private val SNOW_FLAKE_ID_WORKER_4: SfWorker = SfWorker(0L, 4L)

    @JvmStatic
    fun lid(): Long {
        return SNOW_FLAKE_ID_WORKER_0.nextId()
    }

    @JvmStatic
    fun id(): String {
        return lid().toString()
    }

    @JvmStatic
    fun balancedLid(): Long {
        return when (ThreadLocalRandom.current().nextInt(100) % 5) {
            0 -> SNOW_FLAKE_ID_WORKER_0.nextId()
            1 -> SNOW_FLAKE_ID_WORKER_1.nextId()
            2 -> SNOW_FLAKE_ID_WORKER_2.nextId()
            3 -> SNOW_FLAKE_ID_WORKER_3.nextId()
            4 -> SNOW_FLAKE_ID_WORKER_4.nextId()
            else -> throw RuntimeException("unknown id instance")
        }
    }

    @JvmStatic
    fun balancedId(): String {
        return balancedLid().toString()
    }
}