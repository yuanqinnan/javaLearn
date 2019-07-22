package com.yuanqinnan.api.gather;

import java.util.*;
import java.util.function.Predicate;

public class Gather {

    public static void main(String[] str) {

        //collection();
        //set();
        //list();
        //linkedList();
        //queue();
        //hashMap();
        //collections();
        //collections2();
        collections3();
    }

    private static void collections3() {
        Collection<Object> objects = Collections.synchronizedCollection(new ArrayList<>());
        Collections.emptyList();
        Collections.singletonList("原来是这样");
        ArrayList<Integer> list = new ArrayList<>();
        Collections.unmodifiableCollection(list);
    }

    private static void collections2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(8);
        list.add(5);
        list.add(10);
        list.add(7);
        list.add(7);
        //自然排序
        Collections.sort(list);
        //二分法查找list，带入的参数为value，返回的为索引值（必须是排序之后）
        System.out.println(Collections.binarySearch(list, 10));
        //最大值
        System.out.println(Collections.max(list));
        //最小值
        System.out.println(Collections.min(list));
        //出现的次数
        System.out.println(Collections.frequency(list, 8));
        //新值替换所有的旧值
        Collections.replaceAll(list, 8, 6);
        list.forEach(p -> System.out.println(p));
        //全部替换
        Collections.fill(list, 8);
    }

    private static void collections() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(8);
        list.add(5);
        list.add(10);
        list.add(7);
        System.out.println(list.get(2) + 3);
        System.out.println("----自然排序----");
        //自然排序
        Collections.sort(list);
        list.forEach(p -> System.out.println(p));
        System.out.println("----反转----");
        //反转
        Collections.reverse(list);
        list.forEach(p -> System.out.println(p));
        System.out.println("----随机排序----");
        //随机排序，相当于洗牌
        Collections.shuffle(list);
        list.forEach(p -> System.out.println(p));
        System.out.println("----定制排序规则----");
        //定制排序规则
        Collections.sort(list, (o1, o2) -> (o1 - o2));
        list.forEach(p -> System.out.println(p));
        System.out.println("----定制排序规则----");
        //调换list中指定位置的顺序
        Collections.swap(list, 2, 4);
        list.forEach(p -> System.out.println(p));
        System.out.println("----将list最后的两个元素移到前面----");
        //将list最后的两个元素移到前面
        Collections.rotate(list, 2);
        list.forEach(p -> System.out.println(p));
        System.out.println("----将list最后的两个元素移到前面----");
        //将list中前面的两个元素移到后面
        Collections.rotate(list, -2);
        list.forEach(p -> System.out.println(p));
    }

    private static void hashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        //放入数据
        map.put(1, "宋江");
        map.put(2, "卢俊义");
        map.put(3, "吴用");
        //如果原先位置存在数据时会返回原先的数据
        System.out.println(map.put(3, "武松"));
        //是否存在某key
        System.out.println(map.containsKey(2));
        //是否存在某value
        System.out.println(map.containsValue("武松"));
        //是否为空
        System.out.println(map.isEmpty());
        //获取长度
        System.out.println(map.size());
        //循环key值
        for (Object key : map.keySet()) {
            //通过key值直接获取value
            System.out.println(map.get(key));
        }
        //根据key移除元素
        System.out.println(map.remove(3));
        //新的循环方式
        map.forEach((key, value) -> System.out.println(key + ":" + value));
        //获取value，不存在则返回默认值
        map.getOrDefault(8, "查无此人");
        //只是替换，不会新增
        map.replace(2, "林冲");
        //清空数据
        map.clear();
    }

    private static void queue() {
        ArrayDeque<String> stack = new ArrayDeque();
        stack.push("晚安");
        stack.push("愿路途遥远");
        stack.push("都有人陪在身边");
        System.out.println(stack);
        //访问第一个元素，但不弹出
        System.out.println(stack.peek());
        //访问第一个元素，并且弹出
        System.out.println(stack.pop());
        System.out.println(stack);

        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.offer("晚安");
        queue.offer("愿长夜无梦");
        queue.offer("在每个夜晚安眠");
        System.out.println(queue);
        //访问队列头部元素，但不删除
        System.out.println(queue.peek());
        //访问队列头部元素，并且删除
        System.out.println(queue.poll());
        System.out.println(queue);
    }

    private static void linkedList() {
        LinkedList<String> linkedList = new LinkedList();
        //将字符串放入队列尾部
        linkedList.offer("队列尾部字符串");
        //将字符放入栈顶部
        linkedList.push("栈顶部字符串");
        //将字符串放入到队列的头部
        linkedList.offerFirst("队列头部字符串");
        linkedList.forEach(p -> System.out.println(p));
        //访问不删除栈顶元素
        System.out.println(linkedList.peekFirst());
        //访问不删除队列的最后一个元素
        System.out.println(linkedList.peekLast());
        //弹出栈顶元素
        System.out.println(linkedList.pop());
        //访问并删除队列的最后一个元素
        System.out.println(linkedList.pollLast());
    }

    private static void list() {
        List list = new ArrayList();
        list.add("晚安");
        list.add("愿路途遥远");
        list.add("都有人陪在身边");
        list.forEach(p -> System.out.println(p));
        list.remove(1);
        //在索引处添加数据
        list.add(1, "愿路途遥远");
        //获取指定索引位置元素
        System.out.println(list.get(2));
        System.out.println(list.size());
        //设置索引位置的数据,index必须在现有的长度之内
        list.set(2, "想要说的话还没说完");
        //返回fromIndex(包含)，到toIndex（不包含）集合至新集合
        List list1 = list.subList(0, 2);
        //排序，比较函数
        list.sort((o1, o2) -> ((String) o1).length() - ((String) o2).length());
        //将字符串长度作为新的集合元素替换原来的集合
        list.replaceAll(p -> ((String) p).length());
        list.forEach(p -> System.out.println(p));
    }

    private static void set() {
        HashSet hashSet = new HashSet();
        TreeSet treeSet = new TreeSet((o1, o2) ->
        {
            String m1 = (String) o1;
            String m2 = (String) o2;
            return m1.length() > m2.length() ? -1 : 0;
        });

        TreeSet<Integer> treeSet1 = new TreeSet<>();
        treeSet1.add(1);
        treeSet1.add(2);
        treeSet1.add(3);
        //之前的一个元素
        System.out.println(treeSet1.lower(2));
        //后一个元素
        System.out.println(treeSet1.higher(2));
        //第一个元素
        System.out.println(treeSet1.first());
        //最后一个元素
        System.out.println(treeSet1.last());
    }

    private static void collection() {
        Collection collection = new ArrayList();
        //添加
        collection.add("晚安");
        collection.add(9);
        //返回长度
        System.out.println(collection.size());
        //移除
        collection.remove(9);
        //是否包含
        System.out.println(collection.contains("晚安"));
        //是否为空
        System.out.println(collection.isEmpty());
        Collection books = new HashSet();
        books.add("晚安");
        books.add("愿长夜无梦");
        books.add("在所有夜晚安眠");
        //去掉collection 包含的元素
        books.removeAll(collection);
        System.out.println(books);
        books.add("晚安");
        //保留两者都有的数据
        books.retainAll(collection);
        System.out.println(books);
        books.add("愿长夜无梦");
        books.add("在所有夜晚安眠");
        books.forEach(p -> System.out.println(p));
        //获取迭代器
        Iterator iterator = books.iterator();
        //判断是否遍历完成
        while (iterator.hasNext()) {
            //获取集合中的下一个元素，返回的Object对象，需要强制转换
            String text = (String) iterator.next();
            System.out.println(text);
            //这里的删除对象是迭代器中删除，删除的是上一个next返回的方法，而且不会真正删除books中的内容
            iterator.remove();
            //会报错
            books.remove(text);
        }
        books.forEach(p -> System.out.println(p));
        iterator.forEachRemaining(p -> System.out.println(p));
        for (Object s : books) {
            System.out.println(s);
        }
        books.removeIf(p -> ((String) p).length() > 5);
        System.out.println(count(p -> ((String) p).length() > 5, books));
        System.out.println(books.stream().filter(p -> ((String) p).contains("夜")).count());
    }

    public static int count(Predicate predicate, Collection collection) {
        int total = 0;
        for (Object object : collection) {
            //判断是否满足条件
            if (predicate.test(object)) {
                total++;
            }
        }
        return total;
    }
}