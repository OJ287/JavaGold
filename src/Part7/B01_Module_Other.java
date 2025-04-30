package Part7;
/**
 * 前述したmodule-info.javaでは、独自にモジュール名を付け、依存するモジュールや
 * 公開するモジュールを、モジュール・ディレクティブを使用して指定していました。
 * このようなモジュールは、名前付きモジュールと呼ばれます。
 * 名前付きモジュールは、モジュールパス（-module-path オプションで指定したパス）上に配置します。
 * 
 * 現在、モジュール化されたアプリケーションと、モジュール未対応のアプリケーションが混在しています。
 * そのため、モジュール未対応のアプリケーションの後方互換、経過措置のため、
 * モジュール宣言されていないクラスは自動モジュール、もしくは無名モジュールに属するようになっています。
 *
 */

/**
 * 無名モジュール Unnamed Module
 * CLASSPATH上に存在し、モジュール名（module-info.calssがない）を持たないモジュール
 * ・全てのパッケージをexportsする
 * ・モジュールパス上の全てのモジュールをrequiresする
 * ・名前付きモジュールから無名モジュールを参照することはできない（逆はOK）
 *
 */
/**
 * 自動モジュール Automatic Module
 * モジュールパス上に存在し、モジュール名（module-info.calssがない）を持たないモジュール
 * たとえば、自身が作成したアプリケーションはモジュール化しており、そのアプリケーションの中で
 * 使用している他社が作成したライブラリ （JARファイル）がモジュール化していない場合などがあります。
 * その際、そのライブラリは自動モジュールとして扱われます。
 * ●すべてのパッケージを exports する
 * ● モジュールパス上のすべてのモジュールを requires する
 * ● 名前付きモジュールから自動モジュールの参照が可能（逆でもOK）
 *
 * 自动的modules名字，先看modules¥META-INF下面的MANIFAST.MF定义的，Automatic-Module-Name:com.XXX
 * 没有定义的情况下，使用JAR名字（不要扩张子和版本名）
 * modules的依存性确认命令(java XXX):option
 * 1.--show-module-resolution
 * 2.--describe-module(-d)
 *
 * 命令：
 * jdeps
 * -summary
 * -jdkinternals
 * -dotouput
 */


public class B01_Module_Other {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
