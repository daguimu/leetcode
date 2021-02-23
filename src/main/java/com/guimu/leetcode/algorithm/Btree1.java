package com.guimu.leetcode.algorithm;


import java.util.Stack;

public class Btree1 {

    public static void main(String[] args) {

    }

    //中序遍历
    public void inOrderWithoutRecursion(BTNode root) {
        //树非空
        BTNode p = root;
        Stack<BTNode> s = new Stack<>();
        while (!s.empty() || p != null) {
            //一直遍历到左子树最下边，边遍历边保存根节点到栈中
            while (p != null) {
                s.push(p);
                p = p.lchild;
            }
            //当p为空时，说明已经到达左子树最下边，这时需要出栈了
            if (!s.empty()) {
                p = s.pop();
                view(p);
                //进入右子树，开始新的一轮左子树遍历(这是递归的自我实现)
                p = p.rchild;
            }
        }
    }

    public static void view(BTNode node) {
        System.out.println(node.val);
    }
}

class BTNode {

    int val;
    BTNode lchild;
    BTNode rchild;
}
