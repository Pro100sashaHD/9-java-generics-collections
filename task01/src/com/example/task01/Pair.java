package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<A, B> {
    private final A Value1;
    private final B Value2;
    private Pair(A value1, B value2)
    {
        this.Value1 = value1;
        this.Value2 = value2;
    }
    public A getFirst() {
        return Value1;
    }
    public B getSecond() {
        return Value2;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || o.getClass() != getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(Value1, pair.Value1) && Objects.equals(Value2, pair.Value2);
    }
    public int hashCode() {
        return Objects.hash(Value1, Value2);
    }
    public static <A, B> Pair<A, B> of(A value1, B value2) {
        return new Pair<>(value1, value2);
    }
    public void ifPresent(BiConsumer<? super A, ? super B> consumer) {
        if (Value1 != null && Value2 != null) {
            consumer.accept(Value1, Value2);
        }
    }
}
