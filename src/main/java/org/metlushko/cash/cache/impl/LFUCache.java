package org.metlushko.cash.cache.impl;

import org.metlushko.cash.cache.Cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache<T, K> implements Cache<T, K> {
    private Node head;
    private Node tail;
    private Map<K, Node> map;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    @Override
    public T get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        Node item = map.get(key);
        removeNode(item);
        item.frequency++;
        addNodeWithUpdatedFrequency(item);

        return (T) item.value;
    }

    @Override
    public void put(K key, T value) {
        if (map.containsKey(key)) {
            Node item = map.get(key);
            item.value = value;
            item.frequency++;
            removeNode(item);
            addNodeWithUpdatedFrequency(item);
        } else {
            if (map.size() >= capacity) {
                map.remove(head.key);
                removeNode(head);
            }

            Node node = new Node(key, value, 1);
            addNodeWithUpdatedFrequency(node);
            map.put(key, node);
        }
    }

    @Override
    public void remove(K key ) {
        Node nodeToRemove = findNodeByKey(key);
        if (nodeToRemove != null) {
            map.remove(nodeToRemove.key);
            removeNode(nodeToRemove);
        }
    }

    @Override
    public T update(K key, T value) {
        Node nodeToUpdate = findNodeByKey(key);
        if (nodeToUpdate != null) {
            nodeToUpdate.value = value;
            nodeToUpdate.frequency++;
            addNodeWithUpdatedFrequency(nodeToUpdate);
            return (T) nodeToUpdate.value;
        }
        return null;
    }

    private Node findNodeByKey(K key) {
        return map.get(key);
    }

    private Node findNodeByValue(T value) {
        for (Node node : map.values()) {
            if (node.value.equals(value)) {
                return node;
            }
        }
        return null;
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private void addNodeWithUpdatedFrequency(Node node) {
        if (tail != null && head != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.frequency > node.frequency) {
                    if (temp == head) {
                        node.next = temp;
                        temp.prev = node;
                        head = node;
                        break;
                    } else {
                        node.next = temp;
                        node.prev = temp.prev;
                        temp.prev.next = node;
                        temp.prev = node;
                        break;
                    }
                } else {
                    temp = temp.next;
                    if (temp == null) {
                        tail.next = node;
                        node.prev = tail;
                        node.next = null;
                        tail = node;
                        break;
                    }
                }
            }
        } else {
            tail = node;
            head = tail;
        }
    }

    private class Node {
        K key;
        T value;
        int frequency;
        Node prev;
        Node next;

        public Node(K key, T value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }
}
