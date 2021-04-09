package queue;

/**
 * @author:liuzidi
 * @Description: 循环队列：数组循环使用
 *
 * 我们将这个数组留一个空位，让rearIndex索引指向的这个位置一直空出来，方便循环队列的运行
 * 1.判断队列是否为空：头索引frontIndex是否 == rearIndex
 * 2.判断队列是否为满：尾指针rearIndex下一个是头索引frontIndex时为满：
 * 即(rearIndex + 1) % (MaxSize+1) == front满
 * 3.队列中数组最大容纳的有效元素个数为MaxSize,数组实际长度设置为MaxSize+1
 *
 */
public class LoopQueueByArr {
    private int MaxSize = 5;//队列的最大容纳元素数量默认为5
    private final int[] queueArr;//队列所在的数组
    private int frontIndex = 0;//指向队列中第一个元素的位置
    private int rearIndex = 0;//指向队列中最后一个元素后一个的位置


    public LoopQueueByArr(int MaxSize) {//可以通过带参构造器对队列的最大容量进行设置
        this.MaxSize = MaxSize;
        this.queueArr = new int[MaxSize + 1];//由于预留一位，因此创建多给数组分配一位
    }

    public LoopQueueByArr() {//空参构造器设置数组长度，默认长度为5
        this.queueArr = new int[MaxSize + 1];
    }

    public int getFrontIndex() {
        return frontIndex;
    }

    public int getRearIndex() {
        return rearIndex;
    }

    public boolean isEmpty() {//判断队列是否为空:头索引frontIndex == rearIndex
        return rearIndex == frontIndex;
    }

    public boolean isFull() {//判断队列是否已满:尾指针rearIndex下一个是头索引frontIndex时为满
        return (rearIndex + 1) % (MaxSize + 1) == frontIndex;

    }

    public void addMember(int member) {//增加成员member
        if (isFull()) {
            throw new RuntimeException("队列已满，无法再添加");
        } else {
            queueArr[rearIndex] = member;
            rearIndex = (rearIndex + 1) % (MaxSize + 1);//尾值针向后移动一位
        }
    }

    public void outQueue() {//出队列
        if (isEmpty()) {
            throw new RuntimeException("队列已空，无法出队列");
        } else {
            frontIndex = (frontIndex + 1) % (MaxSize + 1);//头值针向后移动一位
        }
    }

    public void printQueue() {//打印队列所有成员
        if (isEmpty()) {
            System.out.println("队列为空!");
        } else {
            System.out.println("\n" + "当前队列为：");
            for (int i = frontIndex; i != rearIndex; i = (i + 1) % (MaxSize + 1)) {
                System.out.println("queueArr[" + i + "] = " + queueArr[i]);//打印从头指针到尾指针的所有元素
            }
        }
    }

}
