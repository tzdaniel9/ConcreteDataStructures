package com.company;

public class FenwickTree {

    private long[] tree;  //This array contains the Fenwick Tree ranges

    //create an empty Fenwick Tree
    public FenwickTree(int sz) {
        tree = new long[sz + 1];
    }

    //Make sure the 'values' array is one based meaning values[0] does not get used, O(n) construction
    public FenwickTree(long[] values) {
        if (values == null) throw new IllegalArgumentException("Values array can not be null");

        //Make a clone of the values array since we manipulate the array in place destroying all its original content
        this.tree = values.clone();

        for (int i = 1; i < tree.length; i++) {
            int j = i + lsb(i);
            if (j < tree.length) tree[j] += tree[i];
        }
    }
    //Returns the value of the least significant bit(LSB)
    //lsb(108) = lsb(b1101100)  =      0b100 = 4
    //lsb(104) = lsb(0b110100)  =     0b1000 = 8
    //lsb(96)  = lsb(0b1100000) =   0b100000 = 32
    //lsb(64)  = lsb(0b1000000) = 0b10000000 = 64
    private  int lsb(int i){
        return  i & -i;  //Isolates the lowest one bit value
        //An alternative method is to use the Java's built in method
        //return Integer.lowestOneBit(i);
    }

    private long prefixSum(int i) {
        long sum = 0L;
        while (i != 0) {
            sum += tree[i];
            i &= ~lsb(i);  //Equivalent, i -= lsb(i)
        }
        return sum;
    }

    //Returns the sum of the interval [i, j], one based
    public long sum(int i, int j) {
        if (j < i) throw new IllegalArgumentException("Make sure j >= i");
        return prefixSum(j) - prefixSum(i - 1);
    }

    //Add 'K' to index 'i', one based
    public void add(int i, long k) {
        while (i < tree.length) {
            tree[i] += k;
            i += lsb(i);
        }
    }

    //Set index i to be equal to k, one based
    public void set(int i, long k) {
        long value = sum(i, i);
        add(i, k - value);
    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(tree);
    }

}