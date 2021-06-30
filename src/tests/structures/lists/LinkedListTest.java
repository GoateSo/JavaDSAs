package tests.structures.lists;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import structures.lists.LinkedList;

import java.util.function.* ;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
class LinkedListTest {
    static Logger log = LoggerFactory.getLogger(LinkedList.class);

    /*@BeforeEach
    void setUp() {
        log.info(() -> "running test...");
    }*/

    /*@AfterEach
    void tearDown() {
        log.info(() -> "test concluded...");
    }*/

    @Test
    void of() {
        log.info(() -> "basic tests on validity of constructor");
        //size constraint
        assertEquals(0, LinkedList.of().size());
        //reflexivity
        assertEquals(LinkedList.of(),LinkedList.of());
    }

    static Stream<Arguments> headData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), 1),
                Arguments.of(LinkedList.of(1,2), 1),
                Arguments.of(LinkedList.of(2,1,3), 2),
                Arguments.of(LinkedList.of(5,2,3,4,1,6,7,8,9), 5)
        );
    }
    @ParameterizedTest(name="# {index}- Test {0}.head(), expecting {1}")
    @MethodSource("headData")
    void head(LinkedList<Integer> a, int b){
        log.info(() -> "head of: "+a+" should be "+b);
        assertEquals(a.head(),b);
    }

    static Stream<Arguments> lastData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), 1),
                Arguments.of(LinkedList.of(1,2), 2),
                Arguments.of(LinkedList.of(1,2,3), 3 ),
                Arguments.of(LinkedList.of(1,2,3,4,5,6,7,8,9,6,7), 7)
        );
    }

    @ParameterizedTest(name="# {index}- Test {0}.last(), expecting {1}")
    @MethodSource("lastData")
    void last(LinkedList<Integer> a, int b) {
        log.info(() -> "last value of: "+a+" should be "+b);
        assertEquals(a.last(),b);
    }

    static Stream<Arguments> getData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), 0, 1),
                Arguments.of(LinkedList.of(5,2), 0, 5),
                Arguments.of(LinkedList.of(1,2,3), 2, 3),
                Arguments.of(LinkedList.of(1,2,3,4,7,8,9,6,7), 7, 6)
        );
    }
    @ParameterizedTest(name="# {index}- Test {0}.get({1}), expecting {2}")
    @MethodSource("getData")
    void get(LinkedList<Integer> as, int i, int v) {
        assertEquals(as.get(i),v);
    }

    static Stream<Arguments> popData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), LinkedList.of(),1),
                Arguments.of(LinkedList.of(5,2), LinkedList.of(2),5),
                Arguments.of(LinkedList.of(1,2,3),  LinkedList.of(2,3),1),
                Arguments.of(LinkedList.of(4,7,8,9,6,7), LinkedList.of(7,8,9,6,7),4)
        );
    }

    @ParameterizedTest(name="# {index}- Test {0}.pop(), expecting {2} with resulting list {1}")
    @MethodSource("popData")
    void pop(LinkedList<Integer> a, LinkedList<Integer> b, int c) {
        assertEquals(c,a.pop());
        assertEquals(b,a);
    }

    static Stream<Arguments> poplastData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), LinkedList.of(),1),
                Arguments.of(LinkedList.of(5,2), LinkedList.of(5),2),
                Arguments.of(LinkedList.of(1,2,3),  LinkedList.of(1,2),3),
                Arguments.of(LinkedList.of(4,7,8,9,6,7), LinkedList.of(4,7,8,9,6),7)
        );
    }
    @ParameterizedTest(name="# {index}- Test {0}.popLast(), expecting {2} with resulting list {1}")
    @MethodSource("poplastData")
    void popLast(LinkedList<Integer> a, LinkedList<Integer> b, int c) {
        assertEquals(c,a.popLast());
        assertEquals(b,a);
    }

    static Stream<Arguments> removeData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), 0, LinkedList.of(),1),
                Arguments.of(LinkedList.of(5,2), 1, LinkedList.of(5),2),
                Arguments.of(LinkedList.of(1,2,3), 1,  LinkedList.of(1,3),2),
                Arguments.of(LinkedList.of(4,7,8,9,6,7), 2, LinkedList.of(4,7,9,6,7),8)
        );
    }

    @ParameterizedTest(name="# {index}- Test {0}.remove({1}), expecting {3} with resulting list {2}")
    @MethodSource("removeData")
    void remove(LinkedList<Integer> a, int b, LinkedList<Integer>c, int d) {
        assertEquals(d,a.remove(b));
        assertEquals(c,a);
    }

    static Stream<Arguments> rallData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1,2,3,4,5,6,7,8,9), (Predicate<Integer>)(i->i%2==0), LinkedList.of(1,3,5,7,9),"f: x -> x % 2 = 0"),
                Arguments.of(LinkedList.of(1,2,3,4,5,6,7,8,9), (Predicate<Integer>)(i->i%2>0), LinkedList.of(2,4,6,8), "f: x -> x % 2 = 1"),
                Arguments.of(LinkedList.of(1,5,10,15,20,25,30), (Predicate<Integer>)(i->i > 15), LinkedList.of(1,5,10,15), "f: x -> x > 15"),
                Arguments.of(LinkedList.of(1,5,10), (Predicate<Integer>)(i->true), LinkedList.of(), "f: x -> true"),
                Arguments.of(LinkedList.of(1,5,10), (Predicate<Integer>)(i->false), LinkedList.of(1,5,10), "f: x -> false"),
                Arguments.of(LinkedList.of(), (Predicate<Integer>)(i->i > 15),LinkedList.of(),"f: anything")
        );
    }
    @ParameterizedTest(name="# {index}- Test {0}.removeAll({3}), expecting {2}")
    @MethodSource("rallData")
    void removeAll(LinkedList<Integer> xs, Predicate<Integer> p, LinkedList<Integer> res, String name) {
        xs.removeAll(p);
        assertEquals(res, xs);
    }

    static Stream<Arguments> sizeData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), 1),
                Arguments.of(LinkedList.of(1,2), 2),
                Arguments.of(LinkedList.of(1,2,3), 3 ),
                Arguments.of(LinkedList.of(1,2,3,4,5,6,7,8,9), 9)
        );
    }

    @ParameterizedTest(name="# {index}- Test {0}.size(), expecting {1}")
    @MethodSource("sizeData")
    void size(LinkedList<Integer> a, int b) {
        assertEquals(a.size(),b);
    }

    static Stream<Arguments> prepData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), 1, LinkedList.of(1,1)),
                Arguments.of(LinkedList.of(1,2), 2, LinkedList.of(2,1,2)),
                Arguments.of(LinkedList.of(1,2,3), 3,LinkedList.of(3,1,2,3)),
                Arguments.of(LinkedList.of(1,6,7,8,9), 9, LinkedList.of(9,1,6,7,8,9))
        );
    }
    @ParameterizedTest(name="# {index}- Test {0}.prep({1}), expecting {2}")
    @MethodSource("prepData")
    void prep(LinkedList<Integer> xs, Integer x, LinkedList<Integer> ys) {
        xs.prep(x);
        assertEquals(ys,xs);
    }

    static Stream<Arguments> addData(){
        return Stream.of(
                Arguments.of(LinkedList.of(1), 1, LinkedList.of(1,1)),
                Arguments.of(LinkedList.of(1,2), 2, LinkedList.of(1,2,2)),
                Arguments.of(LinkedList.of(1,2,3), 3,LinkedList.of(1,2,3,3)),
                Arguments.of(LinkedList.of(1,6,7,8,7), 9, LinkedList.of(1,6,7,8,7,9))
        );
    }

    @ParameterizedTest(name="# {index}- Test {0}.add({1}), expecting {2}")
    @MethodSource("addData")
    void add(LinkedList<Integer> xs, Integer x, LinkedList<Integer> ys) {
        xs.add(x);
        assertEquals(ys,xs);
    }

    static Stream<Arguments> mapData(){
        return Stream.of(
                Arguments.of(LinkedList.of(), LinkedList.of(),
                        (Function<Integer,Integer>)((i)->i * 2), "f: x -> x*2"),
                Arguments.of(LinkedList.of(1,2), LinkedList.of(2,3),
                        (Function<Integer,Integer>)((i)->i + 1), "f: x -> x+1"),
                Arguments.of(LinkedList.of(1,2,3), LinkedList.of(5,10,15),
                        (Function<Integer,Integer>)((i)->i * 5), "f: x -> x*5"),
                Arguments.of(LinkedList.of(1,6,7,8,7), LinkedList.of(11,16,17,18,17),
                        (Function<Integer,Integer>)((i)->i + 10), "f: x -> x+10")
        );
    }

    @ParameterizedTest(name="# {index}- Test {0}.map({3}), expecting {1}")
    @MethodSource("mapData")
    void map(LinkedList<Integer> xs, LinkedList<Integer> ys, Function<Integer,Integer> f, String name) {
        var list= xs.map(f);
        assertEquals(ys, list);
    }

    static Stream<Arguments> flatmapData(){
        return Stream.of(
                Arguments.of(LinkedList.of(), LinkedList.of(),
                        (Function<Integer,LinkedList<Integer>>)((i)->LinkedList.of(i * 2)), "f: x -> List(x*2)"),
                Arguments.of(LinkedList.of(1,2), LinkedList.of(1,2,2,3),
                        (Function<Integer,LinkedList<Integer>>)((i)->LinkedList.of(i,i + 1)), "f: x -> List(x, x+1)"),
                Arguments.of(LinkedList.of(1,2,3), LinkedList.of(1,1,5,1,2,10,1,3,15),
                        (Function<Integer,LinkedList<Integer>>)((i)->LinkedList.of(1,i,i*5)), "f: x -> List(1, x, x*5)"),
                Arguments.of(LinkedList.of(1,6,7,8,7), LinkedList.of(),
                        (Function<Integer,LinkedList<Integer>>)((i)->LinkedList.of()), "f: x -> List()")
        );
    }

    @ParameterizedTest(name="# {index}- Test {0}.flatMap({3}), expecting {1}")
    @MethodSource("flatmapData")
    void flatMap(LinkedList<Integer> xs, LinkedList<Integer> ys, Function<Integer, LinkedList<Integer>> f, String name) {
        var list= xs.flatMap(f);
        assertEquals(ys, list);
    }

    static Stream<Arguments> filterData(){
        return Stream.of(
                Arguments.of(LinkedList.of(), LinkedList.of(),
                        (Predicate<Integer>)((i)->i % 2 == 0), "f: x -> x % 2 = 0"),
                Arguments.of(LinkedList.of(1,2), LinkedList.of(2),
                        (Predicate<Integer>)((i)->i > 1), "f: x -> x > 1"),
                Arguments.of(LinkedList.of(1,2,3), LinkedList.of(1,2,3),
                        (Predicate<Integer>)((i)->true), "f: x -> true"),
                Arguments.of(LinkedList.of(1,6,7,8,7), LinkedList.of(),
                        (Predicate<Integer>)((i)->false), "f: x -> false")
        );
    }

    @ParameterizedTest(name="# {index}- Test {0}.filter({3}), expecting {1}")
    @MethodSource("filterData")
    void filter(LinkedList<Integer> xs, LinkedList<Integer> ys, Predicate<Integer> f, String name) {
        var list= xs.filter(f);
        assertEquals(ys,list);
    }

    static Stream<Arguments> foldlData(){
        return Stream.of(
                Arguments.of(LinkedList.of(), 5, (BiFunction<Integer,Integer,Integer>)(Integer::sum),
                        "f: a,b -> a+b", 5),
                Arguments.of(LinkedList.of(1,2,3,4,5,6), 0, (BiFunction<Integer,Integer,Integer>)(Integer::sum),
                        "f: a,b -> a+b", 21),
                Arguments.of(LinkedList.of(1,-1,2,-3,3,5,-4), 1, (BiFunction<Integer,Integer,Integer>)((a,b) -> a*b),
                        "f: a,b -> a*b", -360),
                Arguments.of(LinkedList.of(1,4,2,1,5,6,2,23,6,7),300, (BiFunction<Integer,Integer,Integer>)((a,b) -> a-b),
                        "f: a,b -> a-b",243)
        );
    }
    @ParameterizedTest(name="# {index}- Test {0}.foldl({1},{3}), expecting {4}")
    @MethodSource("foldlData")
    void foldl(LinkedList<Integer> xs, int init, BiFunction<Integer,Integer,Integer> f, String name, int result) {
        var res = xs.foldl(init, f);
        assertEquals(result,res);
    }

    static Stream<Arguments> existsdata(){
        return Stream.of(
                Arguments.of(LinkedList.of(), (Predicate<Integer>)(i -> true), false, "p: x -> anything"),
                Arguments.of(LinkedList.of(1,2,3), (Predicate<Integer>)(i -> i > 0), true, "p: x -> x > 0"),
                Arguments.of(LinkedList.of(1,2,3,-1), (Predicate<Integer>)(i -> i < 0), true, "p: x -> x < 0"),
                Arguments.of(LinkedList.of(1,2,3), (Predicate<Integer>)(i -> i < 0), false, "p: x -> x < 0")
        );
    }
    @ParameterizedTest(name="# {index}- Test {0}.exists({3}), expecting {2}")
    @MethodSource("existsdata")
    void exists(LinkedList<Integer> xs, Predicate<Integer> p, boolean res, String name){
        assertEquals(res,xs.exists(p));
    }

    static Stream<Arguments> foralldata(){
        return Stream.of(
                Arguments.of(LinkedList.of(), (Predicate<Integer>)(i -> true), true, "p: x -> anything"),
                Arguments.of(LinkedList.of(1,2,3), (Predicate<Integer>)(i -> i > 0), true, "p: x -> x > 0"),
                Arguments.of(LinkedList.of(1,2,3,-1), (Predicate<Integer>)(i -> i > 0), false, "p: x -> x > 0"),
                Arguments.of(LinkedList.of(1,2,3), (Predicate<Integer>)(i -> i > 0), false, "p: x -> x > 0"),
                Arguments.of(LinkedList.of(-1,1,2,3), (Predicate<Integer>)(i -> i > 0), false, "p: x -> x > 0"),
                Arguments.of(LinkedList.of(1,2,-1,3), (Predicate<Integer>)(i -> i > 0), false, "p: x -> x > 0")
        );
    }
    @ParameterizedTest(name="# {index}- Test {0}.forall({3}), expecting {2}")
    @MethodSource("foralldata")
    void forall(LinkedList<Integer> xs, Predicate<Integer> p, boolean res, String name){
        assertEquals(res,xs.forall(p));
    }
}