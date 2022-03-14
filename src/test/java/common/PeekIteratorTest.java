package common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeekIteratorTest {
    String source;
    PeekIterator<Character> characterPeekIterator, characterPeekIterator2;
    @BeforeEach
    void setUp() {
        source = "abcdef";
        characterPeekIterator = new PeekIterator<>(source.chars().mapToObj(c -> (char) c));
        characterPeekIterator2 = new PeekIterator<>(source.chars().mapToObj(c -> (char) c), (char)0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void peek() {
        try {
            assertEquals('a', characterPeekIterator.peek());
            assertEquals('a', characterPeekIterator.peek());
            characterPeekIterator.next();
            characterPeekIterator.next();
            characterPeekIterator.next();
            assertEquals('d', characterPeekIterator.peek());
            assertEquals('d', characterPeekIterator.peek());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void putBack1() {
        assertEquals('a', characterPeekIterator.next());
        try {
            characterPeekIterator.putBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals('a', characterPeekIterator.next());
    }

    @Test
    void putBack2() {
        assertEquals('a', characterPeekIterator.next());
        try {
            characterPeekIterator.putBack();
            characterPeekIterator.putBack();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals('a', characterPeekIterator.next());
    }

    @Test
    void putBack3() {
        assertEquals('a', characterPeekIterator.next());
        assertEquals('b', characterPeekIterator.next());
        assertEquals('c', characterPeekIterator.next());
        assertEquals('d', characterPeekIterator.next());
        assertEquals('e', characterPeekIterator.next());
        try {
            characterPeekIterator.putBack();
            characterPeekIterator.putBack();
            characterPeekIterator.putBack();
            characterPeekIterator.putBack();
            characterPeekIterator.putBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals('a', characterPeekIterator.next());
    }



    @Test
    void hasNext() {
        assertEquals(true, characterPeekIterator.hasNext());
        assertEquals(true, characterPeekIterator.hasNext());
        characterPeekIterator.next();
        characterPeekIterator.next();
        assertEquals(true, characterPeekIterator.hasNext());
        characterPeekIterator.next();
        characterPeekIterator.next();
        characterPeekIterator.next();
        assertEquals(true, characterPeekIterator.hasNext());
        characterPeekIterator.next();
        assertEquals(false, characterPeekIterator.hasNext());
    }

    @Test
    void next() {
        try {
            assertEquals('a', characterPeekIterator.next());
            assertEquals('b', characterPeekIterator.next());
            characterPeekIterator.next();
            characterPeekIterator.next();
            assertEquals('e', characterPeekIterator.next());
            assertEquals('f', characterPeekIterator.next());
            assertEquals(null, characterPeekIterator.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void testEndToken() {
        int i = 0;
        while (characterPeekIterator.hasNext()) {
            if (i == 6) {
                assertEquals((char)0, characterPeekIterator.next());
            } else {
                assertEquals(source.charAt(i), characterPeekIterator.next());
            }
        }
    }
}