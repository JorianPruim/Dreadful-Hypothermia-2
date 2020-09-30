package util;

import java.util.Arrays;

public class RandomList<E>{
    int[] weights;
    E[] elements;

    private RandomList(E[] in, int[] weights){
        this.weights = weights;
        this.elements = in;
    }

    public static <E> RandomList<E> of(E[] in){
        int[] w = new int[in.length];
        Arrays.fill(w,1);
        return new RandomList<>(in,w);
    }
    public static <E> RandomList<E> of(E[] in, int[] weights){
        return new RandomList<>(in,weights);
    }

    public E get() throws NotImplementedException {
        throw new NotImplementedException();
    }


}