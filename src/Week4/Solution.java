package Week4;
import java.util.ArrayList;

public class Solution {
    static void printLinkedList(SinglyLinkedListNode head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    public static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(data);
        head.next = llist;
        return head;
    }

    public static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
        if (head != null) {
            SinglyLinkedListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new SinglyLinkedListNode(data);
        } else {
            head = new SinglyLinkedListNode(data);
        }
        return head;
    }

    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
        // Write your code here
        SinglyLinkedListNode pos = llist;
        for (int i = 1; i < position; i++) {
            pos = pos.next;
        }
        SinglyLinkedListNode next = pos.next;
        pos.next = new SinglyLinkedListNode(data);
        pos.next.next = next;
        return llist;
    }

    public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode llist, int position) {
        // Write your code here
        if (position > 0) {
            SinglyLinkedListNode pos = llist;
            for (int i = 1; i < position; i++) {
                pos = pos.next;
            }
            pos.next = pos.next.next;
        } else {
            llist = llist.next;
        }
        return llist;
    }

    public static void reversePrint(SinglyLinkedListNode llist) {
        // Write your code here
        SinglyLinkedListNode pos = llist;
        ArrayList<Integer> data = new ArrayList<Integer>();
        while (pos != null) {
            data.add(pos.data);
            pos = pos.next;
        }
        for (int i = data.size() - 1; i >= 0; i--) {
            System.out.println(data.get(i));
        }
    }

    public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
        // Write your code here
        SinglyLinkedListNode pos = llist;
        SinglyLinkedListNode head = new SinglyLinkedListNode(pos.data);
        pos = pos.next;
        while (pos != null) {
            SinglyLinkedListNode next = head;
            head = new SinglyLinkedListNode(pos.data);
            head.next = next;
            pos = pos.next;
        }
        return head;
    }

    public static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode pos1 = head1;
        SinglyLinkedListNode pos2 = head2;
        while (pos1 != null && pos2 != null) {
            if (pos1.data != pos2.data) {
                return false;
            }
            pos1 = pos1.next;
            pos2 = pos2.next;
        }
        if (pos1 != null || pos2 != null) {
            return false;
        }
        return true;
    }

    public static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedList head = new SinglyLinkedList();
        SinglyLinkedListNode tail1 = head1;
        SinglyLinkedListNode tail2 = head2;
        while (tail1 != null && tail2 != null) {
            if (tail1.data < tail2.data) {
                head.insertNode(tail1.data);
                tail1 = tail1.next;
            } else {
                head.insertNode(tail2.data);
                tail2 = tail2.next;
            }
           
        }
        while (tail1 != null) {
                head.insertNode(tail1.data);
                tail1 = tail1.next;
        }
        while (tail2 != null) {
            head.insertNode(tail2.data);
            tail2 = tail2.next;
        }
        return head.head;
    }

    public static int getNode(SinglyLinkedListNode llist, int positionFromTail) {
        // Write your code here
        SinglyLinkedListNode tail = llist;
        int count = 0;
        while (tail.next != null) {
            tail = tail.next;
            count++;
        }
        tail = llist;
        while (count > positionFromTail) {
            tail = tail.next;
            count--;
        }
        return tail.data;
    }
    
    public static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode llist) {
        // Write your code here
        SinglyLinkedListNode list = llist;
        while (list.next != null) {
            if (list.next.data == list.data) {
                SinglyLinkedListNode nextNode = list.next.next;
                list.next = nextNode;
            } else {
                list = list.next;
            }
        }
        return llist;
    }

    public static boolean hasCycle(SinglyLinkedListNode head) {
        boolean result = false;
        ArrayList<SinglyLinkedListNode> list = new ArrayList<SinglyLinkedListNode>();
        SinglyLinkedListNode check = head;
        while(!result && check != null) {
            result = list.contains(check);
            list.add(check);
            check = check.next;
        }
        return result;
    }

    public static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode tail1 = head1;
        SinglyLinkedListNode tail2 = head2;
        ArrayList<SinglyLinkedListNode> list1 = new ArrayList<SinglyLinkedListNode>();
        ArrayList<SinglyLinkedListNode> list2 = new ArrayList<SinglyLinkedListNode>();
        list1.add(tail1);
        list2.add(tail2);
        while (tail1.next != null) {
            list1.add(tail1.next);
            tail1 = tail1.next;
        }
        while (tail2.next != null) {
            list2.add(tail2.next);
            tail2 = tail2.next;
        }
        int lastNode1 = list1.size() - 1;
        int lastNode2 = list2.size() - 1;
        SinglyLinkedListNode similar = new SinglyLinkedListNode(0);
        while (lastNode1 >= 0 && lastNode2 >= 0) {
            if (list1.get(lastNode1) == list2.get(lastNode2)) {
                similar = list1.get(lastNode1);
                lastNode1 --;
                lastNode2 --;
            } else {
                break;
            }
        }
        return similar.data;
    }

    public static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode llist, int data) {
        // Write your code here
        if (data < llist.data) {
            DoublyLinkedListNode nextNode = llist;
            llist = new DoublyLinkedListNode(data);
            llist.next = nextNode;
            nextNode.prev = llist;
        } else {
            DoublyLinkedListNode pos = llist;
            while (pos.next != null && pos.next.data < data) {
                pos = pos.next;
            }
            DoublyLinkedListNode nextNode = pos.next;
            pos.next = new DoublyLinkedListNode(data);
            pos.next.prev = pos;
            pos.next.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = pos;
            }
        }
        return llist; 
    }

    public static DoublyLinkedListNode reverse(DoublyLinkedListNode llist) {
        // Write your code here
            DoublyLinkedListNode tail = llist;
            while (tail.next != null) {
                tail = tail.next;
            }
            DoublyLinkedListNode reverse = new DoublyLinkedListNode(tail.data);
            DoublyLinkedListNode reverseHead = reverse;
            tail = tail.prev;
            while (tail != null) {
                reverse.next = new DoublyLinkedListNode(tail.data);
                reverse.next.prev = reverse;
                reverse = reverse.next;
                tail = tail.prev;
            }
            return reverseHead;
        }
}
