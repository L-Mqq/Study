package org.mycode.LinkList;

public class LinkListProblems {
    class LinkNode{
        int data;
        LinkNode next;
        LinkNode(int data){
            this.data = data;
        }
    }
    //建立一个链表
    public LinkNode buildList(int[] arr){
        if (arr == null ||arr.length == 0){
            return null;
        }

        LinkNode head = new LinkNode(arr[0]);
        LinkNode p = head;

        for (int i = 1; i < arr.length;i++){
            p.next = new LinkNode(arr[i]);
            p = p.next;
        }

        return head;
    }

    //打印链表
    public void printList(LinkNode head){
        for (LinkNode p = head;p!=null;p=p.next){
            System.out.print(p.data + "->");
        }
    }

    //反转链表
    public LinkNode reverseList(LinkNode head){
        if (head == null){
            return null;
        }

        //没有头结点 head直接指向第一个元素
        LinkNode pre = null;
        LinkNode p = head;

        while (p!=null){
            //要把p后面那个节点先保存起来！！
         LinkNode temp = p.next;
         p.next = pre;
         pre = p;
         p = temp;
        }
        return pre;
    }

    //找到中间节点
    //1.快慢指针  2.统计链表节点个数 再用数学方法找出中间那个结点。
    public LinkNode middleNode(LinkNode head){
        if (head== null || head.next == null){
            return null;
        }

        LinkNode slow = head;
        LinkNode fast = head;

        //如果 fast != null && fast.next != null 返回的是123中的2  123456中的4
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public LinkNode middleNodeMath(LinkNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        //求出长度
        int length = 0;
        LinkNode p = head;
        while (p != null) {
            length++;
            p = p.next;
        }

        //找出中间结点
        LinkNode middle = head;
        for (int i = 1; i <= (length - 1) / 2; i++) {
            middle = middle.next;
        }

        return middle;
    }


    //合并链表
    //List1: 1 -> 3 -> 5
    //List2: 2 -> 4
    //合并后:dummyNode -> 1 -> 2 -> 3 -> 4 -> 5
    public LinkNode mergeTwoLists(LinkNode head1,LinkNode head2){
        LinkNode dummyNode = new LinkNode(0);
        LinkNode cur = dummyNode;
        LinkNode cur1 = head1;
        LinkNode cur2 = head2;

        while (cur1 != null && cur2 != null){
            if (cur1.data <= cur2.data){
                cur.next = cur1;
                cur1 = cur1.next;
            }else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }

        if (cur1 != null){
             cur.next = cur1;
        }
        if (cur2 != null){
            cur.next = cur2;
        }

        return dummyNode.next;

    }

    //判断链表有没有环 -- 快慢指针  如果有环的话 最终两个指针会相遇
    public boolean hasCycle(LinkNode head){
        if (head == null){
            return false;
        }

        LinkNode slow = head;
        LinkNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }

    //找出环的入口
    public LinkNode CycleEntrance(LinkNode head){
        if (head == null){
            return null;
        }

        LinkNode slow = head;
        LinkNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                LinkNode cur = head;
                while (cur != slow){
                    cur = cur.next;
                    slow = slow.next;
                }
            }
        }
        return slow;

    }

    //环的长度
    public int cycleLength(LinkNode head){
        if (head == null){
            return 0;
        }

        LinkNode slow = head;
        LinkNode fast = head;

        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                LinkNode cur = head;
                while (cur != slow){
                    cur = cur.next;
                    slow= slow.next;
                }
                if (cur == slow){
                    int length = 1;
                    slow = slow.next;
                    while (slow != cur){
                        length++;
                        slow = slow.next;
                    }
                    return length;
                }

            }
        }

        return 0;
    }

    public static void main(String[] args) {
        LinkListProblems linkListProblems = new LinkListProblems();
        LinkNode head = linkListProblems.buildList(new int[]{1, 2, 3, 4, 5, 6,7});
//        linkListProblems.printList(head);

//        反转链表
//        System.out.println("");
//        LinkNode pre = linkListProblems.reverseList(head);
//        linkListProblems.printList(pre);

//        找出中间节点快慢指针
//        LinkNode middleNode = linkListProblems.middleNode(head);
//        System.out.println(middleNode.data);
//        linkListProblems.printList(middleNode);   打印后半段

//        找出中间节点数学方法
//        System.out.println("");
//        LinkNode middleNode = linkListProblems.middleNodeMath(head);
//        linkListProblems.printList(middleNode);

//        连接两个升序列表
        LinkNode head1 =  linkListProblems.buildList(new int[]{1, 2, 4, 6});
        LinkNode head2 = linkListProblems.buildList(new int[]{0,3, 5});
        LinkNode mergeNode = linkListProblems.mergeTwoLists(head1,head2);
        linkListProblems.printList(mergeNode);




    }


}
