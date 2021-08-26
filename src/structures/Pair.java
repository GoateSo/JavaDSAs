package structures;

public record Pair<A,B>(A first, B second) {
    public static <A,B> Pair<A,B> of (A x, B y){
        return new Pair<>(x,y);
    }
}
