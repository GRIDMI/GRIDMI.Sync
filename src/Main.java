import java.util.ArrayList;

public class Main {

    public enum SimpleType {
        USER,
        TRIP
    }

    public static final Locker l = new Locker();

    public static void main(String[] args) throws Throwable {

        String result = l.lock(new ArrayList<Locker.Item>() {{
            add(new Locker.Item(SimpleType.TRIP, 1));
            add(new Locker.Item(SimpleType.USER, 2));
        }}, () -> {

            // When someone make request USER = 1 and TRIP = 2,
            // and at that time the procedure for USER = 1 and TRIP = 2
            // has not been completed, then the thread will be blocked
            // until the procedure is completed.

            // Moreover, if someone makes a request for USER = 1 and TRIP = 3,
            // then the stream will also be blocked, since USER = 1 and TRIP = 2
            // may not be completed yet.

            // If you pass items with a type and ID that are not in the queue,
            // then they will be executed without waiting or blocking.

            return "Result";

        });

        System.out.println(result);

    }

}
