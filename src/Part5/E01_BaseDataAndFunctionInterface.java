package Part5;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

/**
 * 今まではジェネリックスにより使用時に型を指定可能でしたが
 * 基本型を利用する際にラッパークラアスを使用するが、
 * 多くの場合はコード内では基本データ型を扱うことから、intなどを特定した関数型インタフェースが提供されている
 *
 * 引数や戻り値にint double long を使用する場合、対応するインタフェースを使用
 * 　　IntFunction<R>  R apply(int value)
 * 　　IntConsumer  void apply(int value)
 * 　　IntPredicate  boolean test(int value)
 * 　　IntSupplier  int getAsInt()
 * 　　IntUnaryOprator  int applyAsInt(int value)
 * 　　IntBinaryOpertor  R applyAsInt(int value1, int value2)
 * int double long 上記命名を変更して対応する
 * 
 * booleanを特定したインタフェースは下記のみ
 * 　　BooleanSupplier  boolean getAsBoolean()
 * 
 */
public class E01_BaseDataAndFunctionInterface {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
//		IntFunction<String[]> function1 = i -> new String[i];
		IntFunction<String[]> function1 = String[]::new ;
		String[] ans1= function1.apply(5);
		
//		IntFunction function2 = i -> Math.random()  * i;//function2.apply(10);  型の不一致: Object から Double には変換できません
//		IntFunction<Integer> function22 = i -> Math.random()  * i;//function22.apply(10);  型の不一致: Integer から Double には変換できません
		IntFunction<Double> function222 = i -> Math.random()  * i;
		IntFunction<Double> function2 = i -> Math.random() * i;
		Double ans222 = function222.apply(10);
//		Double ans22 = function22.apply(10);
		Double ans2 = function2.apply(10);
		
//		IntSupplier funcIntSupp3 = ()  -> "JAVA".length();
		IntSupplier funIntSupplier3 = "JAVA"::length;
		int ans3 = funIntSupplier3.getAsInt();
		
		
		/*
		 * int double long固有の関数型インタフェース
		 * int型の例：
		 * 		ToIntFunction<T> int applyAsInt(T value)
		 * 		ToIntBiFunction<T, U> int applyAsInt(T value,U value)
		 * 		IntToDoubleFunction double applyAsDouble(int value)
		 * 		IntToLongFunction long applyAsLong(int value)
		 * 		ObjIntConsumer<T> void accept(T t,int value)
		 */
//		ToIntFunction<String> toIntFunction1 = str -> Integer.parseInt(str);
		ToIntFunction<String> toIntFunction1 = Integer::parseInt;
		int ans4 = toIntFunction1.applyAsInt("10");
		Integer ans44 = toIntFunction1.applyAsInt("10");
//		Double ans44 = toIntFunction1.applyAsInt("10");//型の不一致: int から Double には変換できません
//		Long ans44 = toIntFunction1.applyAsInt("10");//型の不一致: int から Long には変換できません
		
//		IntToDoubleFunction intToDoubleFunction1 = i -> i* Math.PI;
		IntToDoubleFunction intToDoubleFunction1 = (int i) -> {return i * Math.PI;};
//		IntToDoubleFunction intToDoubleFunction11 = (Integer i) -> {return i* Math.PI;};//NG  Lambda expression's parameter i is expected to be of type int
		//ラムダ式のメソッドの戻り値ではBoxing　UnBoxing自動変換が行われ、引数に関しては行われない
		double ans5 = intToDoubleFunction1.applyAsDouble(10);
		Double ans55 = intToDoubleFunction1.applyAsDouble(10);
		
		
	}

}























