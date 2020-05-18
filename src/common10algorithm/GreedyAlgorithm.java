package common10algorithm;/*
    @author shl
    @create 2020-04-30-22:20
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {

    public static void main(String[] args) {

        //创建电台，放到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入broadcast
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津" );

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map中
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        //allAreas表示存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //selects，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义临时集合，在遍历过程中，存放遍历过程中电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义maxKey
        //保存在一次遍历过程中，能够覆盖的最多未覆盖地区对应的电台的key
        //如果maxkey不为空，则会加入selects
        String maxKey = null;
        while (allAreas.size() != 0) {

            //每进行一个while需要置空maxKey，为下一次循环做准备
            maxKey = null;

            //因为allAreas是在不停减少的，递减

            //遍历broadcasts，取出对应的key
            for (String key :
               broadcasts.keySet()  ) {

                //每进行一次for循环，就把tempSet进行清除
                tempSet.clear();

                //说明当前key能够覆盖的所有地区
                HashSet<String> areas = broadcasts.get(key);
                //将现在的地区全部加到临时的集合中去
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集，交集重新赋给tempSet
                tempSet.retainAll(allAreas);

                //如果当前这个集合包含未覆盖地区的数量比maxKey指向的集合未覆盖的地区还多
                //maxKey就需要重置

                //tempSet.size() > broadcasts.get(maxKey).size() 贪心算法特性：每次选择最优的
                if (tempSet.size() > 0 &&
                        ((maxKey == null) || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                //说明有选中的
                //将maxKey加入selects集合中
                selects.add(maxKey);
                //将maxKey指向的电台覆盖的地区，从allAreas中取出
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果：" + selects);
    }

}
