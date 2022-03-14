package common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class PeekIterator<T> implements Iterator<T> {

    private Iterator<T> it;
    private LinkedList<T> queueCache = new LinkedList<>();
    private final static int CACHE_SIZE = 10;
    private int backPointer = 0;
    private T _endToken = null;

    public PeekIterator(Stream<T> stream) {
        it = stream.iterator();
    }

    /**
     *
     * @param stream
     * @param endToken add an endToken to the end of the stream. e.g. stream is "12345" and endToken is '-',
     *                 the iterator will use the endToken as the finish of this iterator.
     */
    public PeekIterator(Stream<T> stream, T endToken) {
        it = stream.iterator();
        _endToken = endToken;

    }

    public T peek() throws Exception {
        if (backPointer > 0) {
            return queueCache.get(queueCache.size() - backPointer);
        }
        // 在流的结尾加1个_endToken
        if (!it.hasNext()) {
            return _endToken;
        }
        T next = this.next();
        this.putBack();
        return next;
    }

    public void putBack() throws Exception {
        if (backPointer == queueCache.size()) {
            throw new Exception("No more element to put back or the operation exceed cache.");
        }
        backPointer++;
    }

    @Override
    public boolean hasNext() {
        if (backPointer > 0) {
            return true;
        }
        //如果有_endToken, 则以_endToken为主, 没有设置_endToken则以it.hasNext()为主
        return _endToken != null || it.hasNext();
    }

    @Override
    public T next() {
        T next = null;
        if (backPointer != 0) {
            next = queueCache.get(queueCache.size() - backPointer);
            backPointer--;
        } else {
            // 在流的结尾加1个_endToken
            if (!it.hasNext()) {
                T temp = _endToken;
                _endToken = null;
                return temp;
            }
            next = it.next();

            if (queueCache.size() >= CACHE_SIZE) {
                queueCache.poll();
            }
            queueCache.add(next);
        }

        return next;
    }
}
