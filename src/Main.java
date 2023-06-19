// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

class DListUser {
    private Node head;
    private Node tail;

    private class Node {
        private int value;
        private Node previous;
        private Node next;
    }


    public void PushFront(int value) {
        Node newNode = new Node();
        newNode.value = value;

        if (head != null) {
            newNode.next = head;
            head.previous = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
    }

    public int PopFront() {
        if (head != null) {
            int ret = head.value;
            if (head.next == null) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.previous = null;
            }
            return ret;
        }
        return -1;
    }

    public void ShowFront() {
        Node node = head;
        while (node != null) {
            System.out.printf("%d ", node.value);
            node = node.next;
        }
        System.out.println();
    }

    public void ShowRear() {
        Node node = tail;
        while (node != null) {
            System.out.printf("%d ", node.value);
            node = node.previous;
        }
        System.out.println();
    }

    public boolean ContainsFront(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean ContainsRear(int value) {
        Node node = tail;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.previous;
        }
        return false;
    }

    public void PushBack(int value) {
        Node newNode = new Node();
        newNode.value = value;

        if (tail != null) {
            newNode.previous = tail;
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
    }

    public int PopBack() {
        if (tail != null) {
            int ret = tail.value;
            if (tail.previous == null) {
                head = null;
                tail = null;
            } else {
                tail = tail.previous;
                tail.next = null;
            }
            return ret;
        }
        return -1;
    }

    public void Sort(){
        boolean needSort = true;
        do{
            needSort = false;
            Node node = head;
            while(node != null && node.next != null){
                if(node.value > node.next.value){
                    Node before = node.previous;
                    Node current = node;
                    Node next = node.next;
                    Node after = next.next;

                    current.next = after;
                    current.previous = next;
                    next.next = current;
                    next.previous = before;

                    if(before != null)
                        before.next = next;
                    else
                        head = next;

                    if(after != null)
                        after.previous = current;
                    else
                        tail = current;

                    needSort = true;
                }
                node = node.next;
            }

        }while(needSort);
    }

    public DListUser RetRevertList() {
        DListUser retList = new DListUser();
        Node node = head;
        while (node != null) {
            retList.PushFront(node.value);
            node = node.next;
        }
        return retList;
    }

    public void RevertList() {
        Node node = head.next;
        while (node.next != null) {
            Node temp = node.next;
            node.next = node.previous;
            node.previous = temp;
            node = temp;
        }
        tail.next = tail.previous;
        head.previous = head.next;
        tail.previous = null;
        head.next = null;

        Node temp = head;
        head = tail;
        tail = temp;
    }
}

public class Main {
    private static DListUser lst = new DListUser();

    public static void main(String[] args) {

        for (int i = 0; i <= 5; i++) {
            lst.PushFront(i);
        }
//        lst.ShowFront();
//        lst.Sort();
//        lst.ShowFront();
//        System.out.println(lst.PopFront());
//        lst.ShowFront();
//        lst.ShowRear();
//
//        System.out.println(lst.ContainsFront(2));
//        System.out.println(lst.ContainsRear(5));
//
        lst.PushBack(10);
        lst.PushBack(20);
//        lst.ShowFront();
//        System.out.println(lst.PopBack());
//        System.out.println();

        System.out.println("Исходный список");
        lst.ShowFront();
        lst.RevertList();
        System.out.println("Инверсия текущего списка");
        lst.ShowFront();

        System.out.println("Создание нового инверсного списка");
        DListUser revertList = lst.RetRevertList();
        revertList.ShowFront();

    //реверс списка push pop
        DListUser revertList2 = new DListUser();
        int res;
        while ((res = lst.PopBack()) >= 0) {
            revertList2.PushBack(res);
        }
        System.out.println("Реверс спискак через push pop");
        revertList2.ShowFront();
    }
}