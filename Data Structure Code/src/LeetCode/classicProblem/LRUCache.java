package LeetCode.classicProblem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:liuzidi
 * @Description:
 */
public class LRUCache {

    class Cache{
        class Node{
            Node next;
            Node pre;
            int key;
            int value;
            Node(){}
            Node(int key, int value){
                this.key = key;
                this.value = value;
            }
        }
        public int size;
        public int capacity;
        public Map<Integer, Cache.Node> map;
        Node head;
        Node tail;
        public Cache(int capacity){
            this.capacity = capacity;
            map = new HashMap<>();
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
            this.size = 0;
        }

        public void node2head(Node node){
            Node firstNode = head.next;
            head.next = node;
            node.pre = head;
            firstNode.pre = node;
            node.next = firstNode;
        }

        public void deleteNode(Node node){
            Node curNxt = node.next;
            Node curPre = node.pre;
            curPre.next = curNxt;
            curNxt.pre = curPre;
        }

        public void put(int key, int value){
            Node node = new Node(key, value);
            if(map.containsKey(key)){
                deleteNode(map.get(key));
                map.remove(key);
            }else{
                if(size < capacity){
                    size++;
                }else{
                    Node lastNode = tail.pre;
                    deleteNode(lastNode);
                    map.remove(lastNode.key);
                }
            }
            node2head(node);
            map.put(key,node);
        }

        public int get(int key){
            if(map.containsKey(key)){
                int value = map.get(key).value;
                Node node = new Node(key, value);
                deleteNode(map.get(key));
                node2head(node);
                map.put(key, node);
                return value;
            }else return -1;
        }

    }

    public Cache cache;
    public LRUCache(int capacity) {
        cache = new Cache(capacity);
    }

    public int get(int key) {
        return cache.get(key);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

}

class tetetest{

    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);
        c.put(1,0);
        c.put(2,2);
        System.out.println(c.get(1));
        c.put(3,3);
        System.out.println(c.get(2));
    }

}
