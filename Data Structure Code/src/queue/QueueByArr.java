package queue;

/**
 * @author:liuzidi
 * @Description:队列 实现功能：队列
 * 先进先出，可以通过数组和链表实现，这里用数组和链表分别实现队列
 * <p>
 * 数组实现队列思路：
 * 1.队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图, 其中 MaxSize
 * 是该队列的最大容量。
 * 2.因为队列的输出、输入是分别从前后端来处理，因此需要两个变量 front 及 rear 分别记录队列前后
 * 端的下标，front会随着数据输出而改变，而rear则是随着数据输入而改变
 * <p>
 * 实现功能：
 * 1.入列
 * 2.出列
 * 3.在没有队列满的前提下,可以进行添加成员
 */
/**
 * 几种情况：
 * frontIndex == rearIndex时：说明队列此时无任何成员，数组为空
 * rearIndex == MaxSize-1时：说明队列已经排满，无法再添加新成员
 * frontIndex最小为0，最大为MaxSize
 * 该队列显著的缺点：数组仅能使用一次，一旦出列，先前的空间就无法使用，后续采用循环队列解决该问题
 */
public class QueueByArr {
    private int MaxSize = 5;//队列的最大容量默认为5
    private final int[] queueArr;//队列所在的数组
    private int frontIndex = 0;//当前队列的头尾索引下标,初始状态队列头尾的下标均为0
    private int rearIndex = 0;

    public QueueByArr(int MaxSize) {//可以通过带参构造器对队列的最大容量进行设置
        this.MaxSize = MaxSize;
        this.queueArr = new int[MaxSize];
    }

    public QueueByArr() {
        this.queueArr = new int[MaxSize];
    }

    public void addMember(int member) {
        if (rearIndex <= MaxSize - 1) {
            queueArr[rearIndex] = member;
            rearIndex++;
        } else {
            throw new RuntimeException("已经满员，无法入列！");
        }
    }

    public void outQueue() {
        if (frontIndex == rearIndex) {
            System.out.println("队列为空,无法出列!");
        } else {
            frontIndex++;
        }
    }

    public void printQueue() {//打印队列所有成员
        if (frontIndex == rearIndex) {
            System.out.println("队列为空!");
        } else {
            System.out.println("当前队列成员为：");
            for (int i = frontIndex; i < rearIndex; i++) {
                System.out.println("queueArr[" + i + "]=" + queueArr[i]);
            }
        }
    }

    public int getFrontIndex() {
        return frontIndex;
    }

    public int getRearIndex() {
        return rearIndex;
    }
}
