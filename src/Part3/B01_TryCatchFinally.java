package Part3;

/***
 *try-catch-finally
 *例外処理を行う方法には二つある
 *①：try-catch-finally
 *②：throws
 *
 */


public class B01_TryCatchFinally {
	/*
	 * **********例外が発生しても、後続の処理も続き*************
	 *	try：例外が発生しそうな箇所を囲む
	 *	catch：例外発生した時の処理をcatchの中に定義
	 *	finally：例外が発生してもしなくても必ず実行
	 *	
	 *	try-catch-finally
	 * 	try-finally
	 * 	try-catch
	 * 	例外処理ではtryのみの使用はコンパイルエラーになるが、
	 * 　後述のtry-with-resourcesの時のtryのみの使用は可能
	 * 
	 *   try-catch-finally内でtry-catch-finally定義してもOK
	 */
	
	public static void main(String[] args) {
		
//		int num = 3;//This is finally    //”try-catch-finallyブロック外のコード”
		int num = 4;//例外が発生した   //This is finally    //”try-catch-finallyブロック外のコード”
		int[] ary = {1,2,3};
		try {
			for (int i = 0; i < num ; i++) {
				System.out.println(ary[i]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("例外が発生した");
		}finally {
			System.out.println("This is finally");
		}
		System.out.println("”try-catch-finallyブロック外のコード”");
				

		/*
		 * catchのException指定は複数可能
		 * catch(Exception1){
		 * }
		 * catch(Exception2){
		 * }
		 * catch(Exception3){
		 * }
		 * ただし、catchで指定した例外クラス間に継承関係がある場合は、
		 * サブクラスタ側から記述する、スーパークラスから記述式するとコンパイルエラーになる
		 * サブクラスのcatchを先に記述します。スーパークラスのcatchを先に記述してしまうと、サブクラスのcatch節に処理が行かなくなってしまいます。
		 * https://relearn-java.com/exception/#:~:text=%E3%81%8C%E3%81%A7%E3%81%8D%E3%81%BE%E3%81%99%E3%80%82-,%E7%B6%99%E6%89%BF%E9%96%A2%E4%BF%82%E3%81%AE%E3%81%82%E3%82%8B%E8%A4%87%E6%95%B0%E3%81%AE%E4%BE%8B%E5%A4%96%E3%81%8C%E6%8A%95%E3%81%92%E3%82%89%E3%82%8C%E3%82%8B,%E8%A1%8C%E3%81%8B%E3%81%AA%E3%81%8F%E3%81%AA%E3%81%A3%E3%81%A6%E3%81%97%E3%81%BE%E3%81%84%E3%81%BE%E3%81%99%E3%80%82
		 */
		/*
		 * SE7以降、複数のcatch例外定義は　マルチキャッチを使用し、各例外を術棒|で区切り
		 * } catch (NumberFormatException | ArithmeticException e) {
		 * 注意点：
		 * 	・継承関係ある例外クラスは列記できない
		 * 　・キャッチした参照変数は暗黙的にfinalになる
		 * 			eに値の再代入することができない
		 */
		String string  = "A";
		int[] ary2 = {10,0};
		try {
			System.out.println(Integer.parseInt(string));//例外：For input string: "A"
			System.out.println(ary2[0] / ary2[1]);//例外：/ by zero
		} catch (NumberFormatException | ArithmeticException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
//		try {
//			FileReader fileReader = new FileReader("a.txt");
//			fileReader.read();
//		} catch (FileNotFoundException  e) {//FileNotFoundException  IOException別々でcatchOK
//			// TODO: handle exception
//		} catch (FileNotFoundException | IOException e) {//The exception FileNotFoundException is already caught by the alternative IOException
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//		try {
//			int a = 10/0;
//			FileReader fileReader = new FileReader("a.txt");
//			fileReader.read();
//		} catch (ArithmeticException | FileNotFoundException e) {
//			// TODO 自動生成された catch ブロック
//			e=null;//NG   The parameter e of a multi-catch block cannot be assigned
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e=null;//OK
//		}
		
		System.out.println("End;;;;;;;");
	}

}













