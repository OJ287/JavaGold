package Part1;

import java.util.ArrayList;

public class Test1 {
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			String[] test1 = new String[3];
			System.out.println(test1.length);
			test1[0]="te";
			test1[1]="st";
			System.out.println(test1.length);
			System.out.println(test1[0]);
			
	
			//ERROR: 后面指定具体的数据的时候，就不可指定配列的长度
	//		String[] test2 = new String[2] {"te","st"};
	//		String[] test2[] = new String [2];
			
	//		String test3[] = {"h","e","l"};
	//		int[] test4[] = {1,2,3,4,5};
	//		System.out.println(test3.length + test4.length);
			
			
			//多元
			/*
			int[][] array = new int[3][4];
			System.out.println(array.length);
			System.out.println(array[0].length);
			System.out.println(array[1].length);
			System.out.println(array[2].length);
			*/
	//		
	//		int[][] array2 = new int[3][];
	//		array2[0] = new int[1];
	//		array2[1] = new int[2];
	//		array2[2] = new int[3];
	//		array2[0][0] = 1;
	//		array2[1][0] = 2;
	//		array2[1][1] = 3;
	//		array2[2][0] = 4;
	//		array2[2][1] = 5;
	//		array2[2][2] = 6;
	//		//java.lang.ArrayIndexOutOfBoundsException: 3  编译error
	//		array2[2][3] = 6;
	//		for (int[] is : array2) {
	//			for (int is2 : is) {
	//				System.out.println(is2);
	//			}
	//		}
			
			//ArrayList  不可指定基本型  byte short int long float double char boolean
	//		ArrayList<> test3 = new ArrayList<>();
			ArrayList<Integer> test4 = new ArrayList<>(1);
			System.out.println(test4.size());
			test4.add(1);
			System.out.println(test4.size());
			test4.add(2);
			System.out.println(test4.size());
			test4.add(2,2);
			System.out.println(test4.size());
			//要按照顺序添加，初期指定的size是大于5也不行
	//		test4.add(4,2);///Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 4, Size: 3
	//		System.out.println(test4.size());
			
		   //必须添加指定的型，但是null可以添加进任意
			test4.add(null);
			System.out.println(test4.size());
			
			//不指定泛型的话，会有警告但是可以正常编译和实行。且不指定泛型、且不经过强转的话就编译出错
	//		test4.add("");
	//		System.out.println(test4.size());
	
	//		test4.add("");//コンパイルエラー
	//		System.out.println(test4.size());
		
			//问题2-7
			ArrayList<Character> list1 = new ArrayList<>(1);//两侧都使用泛型的话，至少要在前面指定泛型
			ArrayList list2 = new ArrayList<Character>(1);//可以只有一侧使用泛型
			ArrayList<Character> list3 = new ArrayList();//可以只有一侧使用泛型
			ArrayList list5 = new ArrayList<>(1);//可以只有一侧使用泛型
	//		ArrayList<Object> list4 = new ArrayList<Character>();//前后必须一样
		}
}

