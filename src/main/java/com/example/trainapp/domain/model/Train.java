package com.example.trainapp.domain.model;

import static com.example.trainapp.domain.model.WagonType.*;

import java.util.*;

/**
 * Aggregate representing a train as an ordered list of wagons.
 */
public record Train(List<WagonType> wagons) {
    public Train(List<WagonType> wagons) {
        this.wagons = new ArrayList<>(Objects.requireNonNull(wagons));
    }

    public void addLeft(WagonType type) {
        wagons.addFirst(type);
    }

    public void addRight(WagonType type) {
        wagons.add(type);
    }

    public Optional<WagonType> removeLeft() {
        if (wagons.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(wagons.removeFirst());
    }

    public Optional<WagonType> removeRight() {
        if (wagons.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(wagons.removeLast());
    }

    public void replaceAt(int index, WagonType type) {
        if (index < 0 || index >= wagons.size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        wagons.set(index, type);
    }

    /**
     * Validation: Wagon A cannot be adjacent to B, and cannot chain more than two times.
     */
    public boolean isValid() {
        for (int i = 0; i < wagons.size(); i++) {
            WagonType current = wagons.get(i);
            WagonType left = i > 0 ? wagons.get(i - 1) : null;
            WagonType right = i < wagons.size() - 1 ? wagons.get(i + 1) : null;
            // Rule 1: A not adjacent to B
            if (current == A && (left == B || right == B)) return false;
            if (current == B && (left == A || right == A)) return false;
            // Rule 2: Disallow streaks of 3 or more for A
            if (current == A && left == A && right == A) return false;
        }
        return true;
    }

    /**
     * Attempt to produce a valid permutation. Returns new Train or empty if impossible.
     */
    public Optional<Train> trySortToValid() {
        int countA = 0;
        int countB = 0;
        int count0 = 0;
        for (WagonType type : wagons) {
            if (type == A) {
                countA++;
            } else if (type == B) {
                countB++;
            } else if (type == ZERO) {
                count0++;
            }
        }
        List<WagonType> sortedTrainList = new ArrayList<>();
        // Has valid solution
        if (countB > 0 && count0 * 2 >= countA) {
            while (countA > 1) {
                sortedTrainList.addAll(List.of(A, A, ZERO));
                countA -= 2;
                count0--;
            }
            if (countA == 1) {
                sortedTrainList.addAll(List.of(A, ZERO));
                count0--;
            }
            for (int i = 0; i < count0; i++) {
                sortedTrainList.add(ZERO);
            }
            for (int i = 0; i < countB; i++) {
                sortedTrainList.add(B);
            }
        } else if (countB == 0 && (count0 + 1) * 2 >= countA) {
            if (countA > 1) {
                sortedTrainList.addAll(List.of(A, A));
                countA -= 2;
            }
            while (countA > 1) {
                sortedTrainList.addAll(List.of(ZERO, A, A));
                countA -= 2;
                count0--;
            }
            if (countA == 1) {
                sortedTrainList.addAll(List.of(ZERO, A));
                count0--;
            }
            for (int i = 0; i < count0; i++) {
                sortedTrainList.add(ZERO);
            }
        } else {
            // No valid solution
            return Optional.empty();
        }
        Train train = new Train(sortedTrainList);
        return Optional.of(train);
    }

    public String toCompactString() {
        StringBuilder sb = new StringBuilder();
        for (WagonType w : wagons) sb.append(w.code);
        return sb.toString();
    }

    public static Train fromCompactString(String s) {
        List<WagonType> list = new ArrayList<>();
        if (s != null) {
            for (char c : s.toCharArray()) list.add(fromChar(c));
        }
        return new Train(list);
    }

}