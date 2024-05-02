/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author roman
 */
public class Rnd extends Random {

    private static final Rnd INSTANCE = new Rnd(getSeed());

    private final Lock l = new ReentrantLock();
    private long u;
    private long v = 4101842887655102017L;
    private long w = 1;

    public Rnd() {
        this(System.nanoTime());
    }

    public Rnd(long seed) {
        l.lock();

        try {
            u = seed ^ v;
            nextLong();
            v = u;
            nextLong();
            w = v;
            nextLong();
        } finally {
            l.unlock();
        }
    }

    @Override
    public final long nextLong() {
        l.lock();
        try {
            u = u * 2862933555777941757L + 7046029254386353087L;
            v ^= v >>> 17;
            v ^= v << 31;
            v ^= v >>> 8;
            w = 4294957665L * (w & 0xffffffff) + (w >>> 32);
            long x = u ^ (u << 21);
            x ^= x >>> 35;
            x ^= x << 4;
            long ret = (x + v) ^ w;
            return ret;
        } finally {
            l.unlock();
        }
    }

    @Override
    protected int next(int bits) {
        return (int) (nextLong() >>> (64 - bits));
    }

    public static Rnd instance() {
        return INSTANCE;
    }

    public static int getInt(int bound) {
        if (bound == 0) {
            return 0;
        } else if (bound < 0) {
            return -INSTANCE.nextInt(-bound);
        } else {
            return INSTANCE.nextInt(bound);
        }
    }
    
    public static int getInt(int min, int max) {
        return min + getInt(max - min);
    }


    public static double getDouble(double bound) {
        if (bound == 0) {
            return 0;
        } else if (bound < 0) {
            return (-INSTANCE.nextDouble() * -bound);
        } else {
            return INSTANCE.nextDouble() * bound;
        }
    }

    public static double getDouble() {
        return INSTANCE.nextDouble();
    }

    public static double getGaussian(double bound) {
        return Math.abs(INSTANCE.nextGaussian() * bound / 4);
    }

    public static double getDouble(double min, double max) {
        return min + getDouble(max - min);
    }

    private static long getSeed() {
        SecureRandom sr = new SecureRandom();
        byte[] sbuf = sr.generateSeed(8);
        ByteBuffer bb = ByteBuffer.wrap(sbuf);
        return bb.getLong();
    }
}
