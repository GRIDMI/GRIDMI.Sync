import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Locker {

    private final List<Holder> holders = Collections.synchronizedList(new ArrayList<>());

    /**
     * Blocks the event. Atomic execution.
     * */
    public <T> T lock(List<Item> items, Returner<T> executor) throws Throwable {
        final Holder holder = newSynchronize(items);
        synchronized (holder.sync) {
            final T r = executor.onReturn();
            synchronized (Locker.this) {
                items.forEach(holder._items::remove);
                if (holder._items.size() <= 0) {
                    this.holders.remove(holder);
                }
            }
            return r;
        }
    }

    private synchronized Holder newSynchronize(List<Item> items) {

        Holder h = null;

        for (Holder _h : this.holders) {
            if (_h.hasEqualLock(items)) {
                (h = _h)._items.addAll(items);
            }
        }

        if (h == null) this.holders.add(h = new Holder() {{
            this._items.addAll(items);
        }});

        return h;

    }

    private static class Holder {

        final HashSet<Item> _items = new HashSet<>();

        private static final AtomicInteger ids = new AtomicInteger();
        private static final String uniquePrefix = UUID.randomUUID().toString();

        final String sync = String.format("locker_%s_%d", uniquePrefix, ids.getAndIncrement()).intern();

        private boolean hasEqualLock(List<Item> items) {
            for (Item one : this._items) {
                for (Item two : items) {
                    if (one.isEqual(two)) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    public static class Item {

        public final Enum<?> type;
        public final int id;

        public Item(Enum<?> type, Integer id) {
            this.type = type;
            this.id = id;
        }

        public boolean isEqual(Item item) {
            return type == item.type && id == item.id;
        }

    }

    public interface Returner<T> {
        T onReturn() throws Throwable;
    }

}
