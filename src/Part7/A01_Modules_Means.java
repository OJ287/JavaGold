package Part7;
/**
 * モジュール・システムとは
 * SE9より、モジュール・システムが導入されています。
 * プログラムの最小単位はクラスであり、そのクラスをグループ化する仕組みとしてパッケージが使用されます。
 * モジュールはパッケージの上位に位置づけられ、そのパッケージをさらにグループ化します。
 * 
 * モジュール・システムを導入することで、どのようなメリットがあるのでしょうか。
 * 従来、複数のライブラリを使用する場合は、CLASSPATHにライブラリのJARファイルを指定していました。
 * その際、以下のような問題が発生することがあります。
 * ●CLASSPATH 上に同名のクラスがあった場合、CLASSPATHは先頭から評価するため、想定外のクラスがロードされてしまう
 * ● CLASSPATH 上から不要と思われるJAR を削除したいが、どのクラスが使用しているか判断できない
 * ● ライブラリ内でのみ呼び出したいpublic クラスが、外部からでも呼び出しができてしまう
 * 
 * モジュール・システムはこれらの問題を解決します。
 * 複数のパッケージを1つのモジュールとしてまとめることで、そのモジュールの公開範囲や、
 * モジュール間の依存関係を設定することができます。
 * モジュール・システムの導入による効果をまとめると、以下のとおりです。
 * 
 * 概要　　　　　　　　　説明
 * 信頼性の高い構成　　　モジュールかにより、モジュール間の依存性を明示的に宣言するメカニズムが提供されるため、コンパイル時と実行時の両方で依存性を認識できる
 * 強力なカプセル化　　　モジュール内のパッケージは、そのモジュールで明示的にエクスポートされた場合のみ、他のモジュールからアクセスが可能。
 * 　　　　　　　　　　　パッケージのエクスポート元モジュールの機能が必要であることを明示的に宣言しない限り、エクスポートされたパッケージを
 * 　　　　　　　　　　　他のモジュールから使用することができない。これにより、攻撃者がアクセスできるクラスが少なくなるため、プラットフォームのセキュリティが向上する　
 * スケーラブルな　　　　SE 9以降、Java SE が提供する基本的なAPIもモジュールに分割されており、特定のアプリケーションや対象デバイスに必要なモジュールのみで
 * Javaブラット　　　　構成された Java 実行環境（JRE）を作ることが可能。
 * フォーム　　　　　　　たとえば、デバイスがGUIをサポートしていない場合、GUIモジュールを含まないJRE を作成することもできるため、
 * 　　　　　　　　　　　JRE のサイズを大幅に削減できる
 * ブラットフォーム　　　モジュール・システム未使用の環境下では、プラットフォームに同梱されているクラスのうち、
 * の整合性向上　　　　　その上で稼働するアプリケーションからは利用されたくないものでも、利用できてしまうという問題があったが、
 * 　　　　　　　　　　　モジュール・システムにより、そういった内部 API は完全にカプセル化され、アプリケーションには非公開にすることができる
 * パフォーマンスの　　　JVMでは、様々な最適化手法を使ってアプリケーションのパフォーマンスを改善しているが、そういった最適化手法は、
 * 向上　　　　　　　　　必要なクラスが特定のモジュール内にしかないことでさらにパフォーマンスが向上する
 * 
 * 
 */

/**
 * 1
 * |__modules
 * |__out
 * |__src
 *      |__client
 *            |__app
 *                |__main.java
 *            |__module-info.java
 *      |__foo
 *            |__xlib
 *                |__XTest.java
 *            |__ylib
 *                |__XTest.java
 *            |__module-info.java
 *      |__bar
 *            |__xlib
 *                |__XTest.java
 *            |__ylib
 *                |__XTest.java
 *            |__module-info.java
 * clientのmodule-info.java
 * 　requires foo;
 * fooのmodule-info.java
 * 　exports xlib;
 * 　requires java.base;
 * barのmodule-info.java
 * 　exports ylib;
 */

/**
 * モジュールの宣言：
 * 
 * module{
 * 	requires java.base;どのモジュールを依存するかを指定。これは全てのモジュールに暗黙的に含まれるため、省略可能
 * 	exports ;どのパッケージを公開するかを指定
 * 	provides ;
 * 	with ;
 * 	uses ;
 * 	opens ;
 * }
 *
 */

/**
 * モジュールコンパイル：
 * 　--module-path(または-p):：アプリケーション、モジュールを検索する位置を指定
 *   --module(または-m)：モジュール名とエントリ・ポイントとなるクラス（mainクラス）を指定
 *   
 *   foo:
 *   javac -d out¥foo src¥foo¥xlib¥Xtest.java
 *   javac -d out¥foo src¥foo¥xlib¥Ttest.java
 *   javac -d out¥foo src¥foo¥module-info.java
 *  　　　 -dオペションでクラスファイルの生成場所を指定。out¥foo
 *   　　　コンパイル対象を指定。src¥foo¥xlib¥Xtest.java
 *   client:
 *   javac -d out¥client --module-path out¥foo src¥client¥mpdule-info.java src¥client¥app¥Main.java
 *  　　　 -dオペションでクラスファイルの生成場所を指定。out¥foo
 *   　　　--module-pathオペションでモジュールを検索する位置を指定。out¥foo
 *   　　　コンパイル対象を指定。src¥client¥mpdule-info.java　と　src¥client¥app¥Main.java
 *   
 */
/**
 * モジュールの実行：クラスファイルで実行
 *	clientモジュールに格納されたMainクラスを実行
 *  java --module-path out¥foo;out¥client --module client¥app.Main
 *  java --module-path out¥foo;out¥client --module app.Main モジュール名の指定なく、エラーになる
 *  java -p out¥foo;out¥client -m client¥app.Main -p -mでもOK
 *  　　--module-pathオペションでモジュールが格納された場所を指定。out¥foo;out¥client
 *  　　--moduleオペションでモジュール名とエントリポイントとなるクラスの完全修職名を指定。client¥app.Main
 */
/**
 * モジュールの実行：JARファイルで実行
 * 各モジュールをJAR化し実行する
 *　jar -cvf modules¥foo.jar -C out¥foo .
 *　jar -cvf modules¥client.jar -C out¥client .
 *　上記のコマンドで各モジュールのJARファイルを作成
 *　
 *　java --module-path moubles --mouble client¥app.Main
 *  --module-pathオペションでJARファイルが保存された場所を指定。moubles
 *  --moduleオペションでモジュール名とエントリポイントとなるクラスを指定。client¥app.Main
 *
 */
/**
 * 間接エクスポート
 * requiresにtransitiveを付与することで、間接エクスポートすることが可能
 * transitive未使用：
 * clientのmodule-info.java
 * 　requires foo;
 * fooのmodule-info.java
 * 　exports xlib;
 * 　requires bar;
 * barのmodule-info.java
 * 　exports ylib;
 * 
 * 
 *   javac -d out¥bar src¥bar¥mpdule-info.java src¥bar¥ylib¥YTest.java
 *   javac -d out¥foo --module-path out¥bar src¥foo¥mpdule-info.java src¥foo¥xlib¥XTest.java
 *   javac -d out¥client --module-path out¥foo src¥client¥mpdule-info.java src¥client¥app¥Main.java
 *   java --module-path out¥bar;out¥foo;out¥client --module client¥app.Main
 *  
 *  clientのmodule-info.javaにrequires bar;を記載していないので、clientからbarへのアクセスができない
 *  もちろん、clientのMainクラスにJAVAソースのimport ylib.YTest;を指定して、以下のコマンドもエラーになる
 *   javac -d out¥client --module-path out¥foo;out¥bar src¥client¥mpdule-info.java src¥client¥app¥Main.java
 * 　 　--module-pathオペションにout¥barを追加してもエラーになる
 * 　　パッケージylibはモジュールbarで宣言されていますが、モジュールclientに読み込めない
 * でも、
 * fooのmodule-info.javaファイルを以下のように修正したら、間接エクスポートできる
 * fooのmodule-info.java
 * 　exports xlib;
 * 　requires transitive bar;
 * これにより、fooをrequiresしたモジュール（この場合はclient）は、barモジュールもrequiresしたことになる
 *   javac -d out¥foo --module-path out¥bar src¥foo¥mpdule-info.java
 *   javac -d out¥client --module-path out¥foo;out¥bar src¥client¥mpdule-info.java src¥client¥app¥Main.java
 *   
 * 
 * 
 * 
 */

public class A01_Modules_Means {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
