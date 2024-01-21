package Part5;

import java.util.function.Function;
import java.util.function.LongPredicate;

/**
 * 今までは、特定の関数型インタフェースを1つずつ使用していましたが、各インタフェースで提供されているメソッドを使用して、
 * 関数型インタフェースを合成することができます。
 * 合成を提供する主なメソッドと、そのメソッドを提供するインタフェースは、表5-5のとおりです。
 * 
 * 合成を提供する主なメソッド：
 * andThen(T after) T:Function,BiFunction,Consumer,IntConsumer,LongConsumer,DoubleConsumer,IntUnaryOperator,LongUnaryOperator,DoubleUnaryOperator
 * 		自身を実行した後、afterを実行した結果を返す
 * compose(T before) T:Function,IntUnaryOperator,LongUnaryOperator,DoubleUnaryOperator
 * 		beforeを実行した後、自身を実行し結果を返す
 * and(T other) T:Predicate,BiPredicate,IntPredicate,LongPredicate,DoublePredicate
 * 		自身及びotherの条件を満たした時のみ、trueを返す
 * or(T other) T:Predicate,BiPredicate,IntPredicate,LongPredicate,DoublePredicate
 * 		自身及びotherのどちらかが条件を満たした時のみ、trueを返す
 * negate()　T:Predicate,BiPredicate,IntPredicate,LongPredicate,DoublePredicate
 * 		自身の条件結果を反転した結果を返す
 * 
 */


public class F01_FunctionInterfaceMake {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Function<String, Character> function1 = str -> str.charAt(0);
		Function<Character, Boolean> function2 = Character::isUpperCase;
		// andThenのりよう（自身.andThen（after））
		Function<String, Boolean> function3 = function1.andThen(function2);
		System.out.println(function3.apply("Java"));
		
		//composeの利用（自身.compose(after)）
		Function<String, Boolean> function4 = function2.compose(function1);
//		Function<String, Boolean> function44 = function1.compose(function2);//型 Function<String,Character> のメソッド compose(Function<? super V,? extends String>) は引数 (Function<Character,Boolean>) に適用できません
		System.out.println(function4.apply("Java"));
		
		
		
		//or and  negate
		LongPredicate longPredicate1 = l -> (l % 3) == 0;
		LongPredicate longPredicate2 = l -> (l % 5) == 0;
		LongPredicate longPredicate3 = longPredicate1.or(longPredicate2);
		System.out.println(longPredicate3.test(3));

		LongPredicate longPredicate4 = longPredicate1.and(longPredicate2);
		System.out.println(longPredicate4.test(15));

		LongPredicate longPredicate5 = longPredicate4.negate();
		System.out.println(longPredicate5.test(15));
	}

}













