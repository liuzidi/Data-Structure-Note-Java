package queue;

/**
 * @author:liuzidi
 * @Description:
 */
public class LoopQueueByArrTest {
    public static void main(String[] args) {
        LoopQueueByArr q1 = new LoopQueueByArr();
        q1.addMember(13);
        q1.printQueue();

        q1.addMember(56);
        q1.printQueue();

        q1.addMember(26);
        q1.printQueue();

        q1.addMember(18);
        q1.printQueue();

        q1.addMember(34);
        q1.printQueue();

        q1.outQueue();
        q1.printQueue();

        q1.outQueue();
        q1.printQueue();

        q1.outQueue();
        q1.printQueue();

        q1.addMember(56);
        q1.printQueue();

        q1.addMember(26);
        q1.printQueue();

        q1.addMember(8632);
        q1.printQueue();

        q1.outQueue();
        q1.printQueue();

        q1.addMember(35);
        q1.printQueue();
    }
}

