package algorithmcamp.weekone;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 问题描述
 * 你有一个盒子，你可以往里面放数，也可以从里面取出数。
 * <p>
 * 初始时，盒子是空的，你会依次做 Q 个操作，操作分为两类：
 * <p>
 * 插入操作：询问盒子中是否存在数 x，如果不存在则把数 x 丢到盒子里。
 * 删除操作：询问盒子中是否存在数 x，如果存在则取出 x。
 * 对于每个操作，你需要输出是否成功插入或删除。
 * <p>
 * 操作次数范围： 5X10^5 数据范围： 10^18 10^5占总数据的60%
 * <p>
 * @author: zww
 * @date: 2019/10/23
 * @version: V1.0
 */
public class HashTable {
    private static final int MOD = 1000003;
    private List<Long>[] table = new ArrayList[MOD];

    public int Hash(long num) {
        return (int) (num % MOD);
    }

    public boolean insert(long num) {
        int hash = Hash(num);
        List<Long> list = table[hash];
        if (list == null || list.isEmpty()) {
            list = new ArrayList<>();
            list.add(num);
            table[hash]=list;
            return true;
        } else {
            for (long number : list) {
                if (number == num) {
                    return false;
                }
            }
            list.add(num);
            table[hash]=list;
            return true;
        }
    }

    public boolean delete(long num) {
        int hash = Hash(num);
        List<Long> list = table[hash];
        int index = -1;
        if (list == null || list.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == num) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                return false;
            }
            list.remove(index);
            return true;
        }
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        System.out.println(hashTable.insert(0));
        System.out.println(hashTable.insert(MOD));
        System.out.println(hashTable.delete(0));
        System.out.println(hashTable.delete(MOD));
    }

}
