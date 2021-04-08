package queue;

/**
 * @author:liuzidi
 * @Description:
 */
public class QueueByArrTest {
    public static void main(String[] args) {
        QueueByArr q1 =new QueueByArr();
        q1.addMember(13);
        q1.printQueue();

        q1.addMember(56);
        q1.printQueue();

        q1.addMember(26);
        q1.printQueue();

        q1.outQueue();
        q1.printQueue();

        q1.outQueue();
        q1.printQueue();

        q1.outQueue();
        q1.printQueue();

        q1.outQueue();
        q1.printQueue();

        q1.addMember(18);
        q1.printQueue();

        q1.addMember(34);
        q1.printQueue();

        q1.addMember(54);
        q1.printQueue();

    }
}
