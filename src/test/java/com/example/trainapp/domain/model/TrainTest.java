package com.example.trainapp.domain.model;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TrainTest {

    @Test
    void shouldBeValidForValidTrain() {
        Train t = Train.fromCompactString("0A0B0");
        assertTrue(t.isValid());
    }

    @Test
    void shouldBeInvalidIfAbAdjacent() {
        Train t = Train.fromCompactString("AB");
        assertFalse(t.isValid());
    }

    @Test
    void shouldBeInvalidIfTooManyConsecutiveA() {
        Train t = Train.fromCompactString("AAA");
        assertFalse(t.isValid());
    }

    @Test
    void shouldSortInvalidToValid() {
        Train t = Train.fromCompactString("0AAABB0");
        assertFalse(t.isValid());
        assertTrue(t.trySortToValid().isPresent());
        assertTrue(t.trySortToValid().get().isValid());
    }

    @Test
    void shouldAddRemoveReplace() {
        Train t = Train.fromCompactString("");
        t.addLeft(WagonType.A);
        t.addRight(WagonType.B);
        assertEquals(List.of(WagonType.A,WagonType.B), t.wagons());

        t.replaceAt(1,WagonType.ZERO);
        assertEquals(List.of(WagonType.A,WagonType.ZERO), t.wagons());

        t.removeLeft();
        assertEquals(List.of(WagonType.ZERO), t.wagons());
    }

}