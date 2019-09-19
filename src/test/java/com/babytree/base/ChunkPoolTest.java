package com.babytree.base;

import com.babytree.babyeye.base.util.ChunkPool;
import org.junit.Assert;
import org.junit.Test;


public class ChunkPoolTest {

    @Test
    public void test() {
        ChunkPool pool = ChunkPool.getInstance();
        test(pool, 16);
        test(pool, 1024);
        test(pool, 10 * 1024);
        test(pool, 100 * 1024);
        test(pool, 1024 * 1024);
        pool.returnChunk(new int[]{1});
    }

    private void test(ChunkPool pool, int chunkLength) {
        int[] chunk = null;
        try {
            chunk = pool.getChunk(chunkLength);
            Assert.assertNotNull(chunk);

            for (int i = 0; i < chunkLength; ++i) {
                chunk[i] = i;
            }
        } finally {
            if (chunk != null) {
                pool.returnChunk(chunk);
            }
        }
    }
}
