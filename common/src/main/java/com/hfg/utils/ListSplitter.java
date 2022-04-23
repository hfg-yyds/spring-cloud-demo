package com.hfg.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/22 23:41
 * @Description:切割容器的子集
 */
public class ListSplitter<T> {
    private List<T> list;
    private int chunksize; //把list按这个大小进行分割
    private int index;    //当前索引

    /**
     * 构建切割容器
     * @param list list集合
     * @param chunksize 每一块的大小
     */
    public ListSplitter(List<T> list, int chunksize){
        this.list = list;
        this.chunksize = chunksize;
        this.index = 0;
    }

    /**
     * 判断list是否还有元素
     * @return
     */
    public boolean hasNext() {
        return index < list.size();
    }

    /**
     *
     * @return 返回分割之后的list的子集合
     */
    public List<T> next(){
        int endIndex= Math.min(index + chunksize, list.size());
        List<T> ret= list.subList(index, endIndex);
        index = endIndex;
        return ret;
    }

    public int getIndex(){
        return index;
    }

    public int getSize(){
        return list.size();
    }
}
class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 53; i++) {
            list.add("顺序"+i);
        }
        ListSplitter<String> splitter = new ListSplitter<String>(list,5);
        while (splitter.hasNext()) {
            List<String> next = splitter.next();
            System.out.println(next.toString());
        }
    }
}
