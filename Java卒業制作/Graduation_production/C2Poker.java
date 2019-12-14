package Graduation_production;
import java.util.*;
public class C2Poker
{
	String[] card = {"●1","●2","●3","●4","●5","●6","●7","●8","●9","●10","●11","●12","●13",
			 "◆1","◆2","◆3","◆4","◆5","◆6","◆7","◆8","◆9","◆10","◆11","◆12","◆13",
			 "■1","■2","■3","■4","■5","■6","■7","■8","■9","■10","■11","■12","■13",
			 "▲1","▲2","▲3","▲4","▲5","▲6","▲7","▲8","▲9","▲10","▲11","▲12","▲13"};
			 /*●はハート、◆はダイヤ、■はクローバー、▲はスペード*/
	String[] Pcard = {"●A","●2","●3","●4","●5","●6","●7","●8","●9","●10","●J","●Q","●K",
			 "◆A","◆2","◆3","◆4","◆5","◆6","◆7","◆8","◆9","◆10","◆J","◆Q","◆K",
			 "■A","■2","■3","■4","■5","■6","■7","■8","■9","■10","■J","■Q","■K",
			 "▲A","▲2","▲3","▲4","▲5","▲6","▲7","▲8","▲9","▲10","▲J","▲Q","▲K"};
	Scanner sc;
	String select;//スタートかルールの選択
	Random random;//ランダムにカード番号取得
	int[] rv;//配るカードの添字が入る配列
	String[] tcard;//配られたカードが入っている配列
	int[] suuji;//カードの数字部分
	String[] mark;//カードのマーク部分
	int count;//同じ数字の枚数
	boolean flag;//一回目のカードを配るときに処理を飛ばすときに使うフラグ
	boolean Jflag;//カードが順番に並んでいるか
	boolean Mflag;//マークが同じかどうか
	boolean Sflag;//マークがすべてスペードか
	int[] rs = {1,10,11,12,13};//suujiの中身が10.11.12.13.1になっているか
	int[] change;//カードを替えるときの変数
	String henko;//カードを変えるかどうか
	int len;//changeの配列の長さ
	int point;//ポイントのカウント
	int[] before;//changeでかえた部分の以前のカードの番号
	int beforenum;//beforeの配列に格納するときの添字に使う
	boolean changeCardFlag;	//重複を確認したらtrue

	C2Poker(){//コンストラクタ
		changeCardFlag = false;
		random = new Random();
		flag = true;
		rv = new int[5];
		for(int i=0;i<5;i++) {
			rv[i] = random.nextInt(51);//一番最初に配る予定のカードを配列に入れる
		}
		len = 5;
		change = new int[len];		//lenを初期化しましょう
		before = new int[len];
		beforenum = 0;
		point = 0;
	}
	
	
	private void start() {
		show();
		check();
		judgment();
		change();
		judgment();
		point();
	}
	
	public void judgment() {//役を判定する	
		divide();
		straight();
		same_mark();
		royal_straight_flush();
		role();
		same_number();
	}
		
		
	public void show()//説明か開始か
	{
		System.out.println("Poker");
		System.out.println("スタート");
		System.out.println("ルール\n");

		while(true){
			System.out.println("スタート(s)かルール(r)を選択してください。");

			sc = new Scanner(System.in);
			select = sc.nextLine();

			if(select.equals("s") || select.equals("S")){
				System.out.println("\nゲームを開始します。\n");
				break;
			}
			else if(select.equals("r") || select.equals("R")){
				System.out.println("\nルールの説明を開始します。\n");
				System.out.println("1.ゲーム開始時の手持ちのポイントは０からです。");
				System.out.println("2.カードは1人５枚配ります。");
				System.out.println("3.手札のカードから好きな枚数を捨て、その枚数分のカードを自分の手札に加えます。");
				System.out.println("4.役に応じてポイントが加算され、そのポイントを増やしていくゲームです。");
				System.out.println("※手札のカードを捨て、新しいカードを加えることができるのは1回です。\n");
				System.out.println("役について※弱い順");
				System.out.println("ワンペア(2Point)：同じ数字が２枚揃う");
				System.out.println("ツーペア(5Point)：ワンペアが２組揃う");
				System.out.println("スリーカード(10Point)：同じ数字が３枚揃う");
				System.out.println("ストレート(20Point)：５枚の数字が連続して順番に揃う（「1.2.3.4.5」「11.12.13.1.2」など）");
				System.out.println("フラッシュ(25Point)：５枚のマークが全部同じ");
				System.out.println("フルハウス(30Point)：スリーカードとワンペアが揃う");
				System.out.println("フォーカード(35Point)：同じ数字が４枚揃う");
				System.out.println("ストレートフラッシュ(60Point)：数字が連続して順番に揃い、マークも同じ");
				System.out.println("ロイヤルストレートフレッシュ(100Point)：スペードの「10.11.12.13.1」が揃う。\n\n");
				
			}
			else{
				System.out.println("\nスタートかルールのどちらかを入力してください。\n");
			}

		}
	}
	
	public void check() {//カードがかぶっているか比べてカードを配る
		do {
			changeCardFlag = false;
			for(int i=0; i<5; i++){
				for(int a=i+1; a<5; a++){
					if(rv[i] == rv[a]){//同じカードがあるか
						changeCardFlag = true;
						duplication(i,a);//duplicationメソッドを呼び出す
					}
				}	
			}
		}while(changeCardFlag);
		
		flag = false;
		tcard = new String[5];
		for(int c=0; c<5; c++){
			tcard[c] = card[rv[c]];//ｔcardにカードを入れる
			before[c] = rv[c];//変えるカードの以前のカードを入れておく
			System.out.print(c+1 + "." + Pcard[rv[c]] + "     ");//カードを配る
		}
		
		
		
		
		
		/*tcard[0] = card[39];
		System.out.print(1 + "." + Pcard[39] + "     ");//カードを配る
		
		for(int i=1;i<5;i++) {
			tcard[i] = card[47+i];
			System.out.print(i+1 + "." + Pcard[47+i] + "     ");//カードを配る
		}*/

		
		
		
		
		
		System.out.println("\n※●はハート、◆はダイヤ、■はクローバー、▲はスペード\n");
	}
	
	public void duplication(int i,int a) {//changeの配列に入っているカードと交換したカードがかぶっているか
		
		for(int b=0; b<5; b++) {
			if(flag) {
				rv[a] = random.nextInt(51);//一回目にカードを配ったときにかぶっていたら後ろのカードを変える
			}
			else if(i == change[b]-1) {//変えるカードの番号がかぶっているか
				if(change[b] == 0) {//変えるカードの番号が0(初期値)だった場合は処理を飛ばす
					continue;
				}
				rv[i] = random.nextInt(51);//かぶってるのと初期値が0じゃない場合、かぶっている番号のカードを変える
			}
			else {
				rv[a] = random.nextInt(51);//かぶってなかったら後ろのカードを変える
			}
		}
	}

	public void divide()//手札のカードをマークと数字に分けて昇順に並べる
	{
		suuji = new int[5];
		mark = new String[5];

		for(int i=0; i<5; i++){                                                                           
			suuji[i] = Integer.parseInt(tcard[i].substring(1));//手札のカードを数字に分ける
			mark[i] = tcard[i].substring(0,1);//手札のカードをマークに分ける
		}
		
		Arrays.sort(suuji);//昇順に並べ替え
	}
	
	public void same_number()//同じ数字の枚数と役
	{
		if(point != 0 ) return;  
		count = 0;
		for(int i=0; i<5; i++){
			for(int a=i+1; a<5; a++){
				if(suuji[i] == suuji[a]){
					count = count + 1;
				}
			}
		}
		switch(count){
			case 0:
				System.out.println("役なしです。");
			break;

			case 1://ワンペア
				System.out.println("ワンペアです。");
				point = 2;
			break;

			case 2://ツーペア
				System.out.println("ツーペアです。");
				point = 5;
			break;

			case 3://スリーカード
				System.out.println("スリーカードです。");
				point = 10;
			break;

			case 4://フルハウス
				System.out.println("フルハウスです。");
				point = 30;
			break;

			case 6://フォーカード
				System.out.println("フォーカードです。");
				point = 35;
			break;
		}
	}
	
	public void straight()//役がストレートか（数字が並んでるか）
	{		
		Jflag = true;//trueの時はカードが並んでいる
		
		for(int i=0; i<4; i++){//ストレート
			if(suuji[i] != suuji[i+1]-1){//次のカードは順番通りか
				if(suuji[0] == 1  &&  suuji[4] == 13){//1から始まり13で終わるかどうか
					if(suuji[i+1] != 13-(3-i)){//1,10,11,12,13かどうか
						Jflag = false;//並んでいない
						break;//forの外に出る(straightの処理を終わる)
					}
					continue;//ifの外に出る
				}
				Jflag = false;//並んでいない
				break;//forの外に出る(straightの処理を終わる)
			}
		}
	}
	
	public void same_mark()//マークが同じか
	{
		Mflag = true;//trueの時はマークが同じ
		loop: for(int i=0; i<5; i++){//マークが同じか
			for(int a=i+1; a<5; a++){
				if(!(mark[i].equals(mark[a]))){
					Mflag = false;
					break loop;//ラベルのloopの外側に出る
				}
			}
		}
	}
	
	public void role()//マークと数字が並んでいるカードの役
	{
		if(point != 0 ) return;  
		if(Jflag && Mflag){
			System.out.println("ストレートフラッシュです。");
			point = 60;
		}
		else if(Jflag){//trueの時
			System.out.println("ストレートです。");
			point = 20;
		}
		else if(Mflag){
			System.out.println("フラッシュです。");
			point = 25;
		}
	}
	
	public void royal_straight_flush()//ロイヤルストレートフラッシュか
	{
		if(point != 0 ) return;  
		Sflag = true;//マークがすべてスペードか
		for(int i=0; i<5; i++){
			if(!(mark[i].equals("▲"))){
				Sflag = false;
				break;
			}
		}
		for(int i=0; i<5; i++){//数字が10.11.12.13.1になっているか
			if(suuji[i] != rs[i]){
				Sflag = false;
				break;
			}
		}
		if(Sflag){
			System.out.println("ロイヤルストレートフラッシュです。");
			point = point + 100;
		}
	}
	
	public void change() {//変えたいカードを交換する
		System.out.println("\n\nカードを替えますか？");
		System.out.println("※替えたい場合はY、替えない場合はNを入力してください。");
	
		while(true){
			henko = sc.nextLine();//変更の入力
			
			if(henko.equals("Y") || henko.equals("y")){
				point = 0;
				store();//storeメソッドを呼び出す
				check();//checkメソッドを呼び出す
				break;
			}
			else if(henko.equals("N") || henko.equals("n")){
				System.out.println("");
				break;
			}
			else{
				System.out.println("YかNのどちらかを入力してください。");
			}
		}

	}
	
	public void store() {//lenやchangeの値を並べて格納する
		System.out.println("\n替えたいカードの番号を選んでください。");
		String kae = sc.nextLine();//替えたいカードの番号を取得
		kae = kae.replaceAll("　", "");//全角スペースを空文字に置換
		kae = kae.replaceAll(" ", ""); //文字の間の半角スペースを空文字に変換
		len = kae.length();//入力した番号の数


		for (int i=0; i<len; i++) {
			change[i] = Character.getNumericValue(kae.charAt(i));//kaeをchar型で取り出しint型に変換
			//change[i] = Integer.parseInt(kae.substring(i, i + 1));//kaeの数を配列から1つずつ取り出して格納←これでもok
			if(change[i] < 1  ||  change[i] > 5){//数字が1~5の間か
				System.out.println("1～5の数字を入力してください。");
			}
		}
		Arrays.sort(change);//入力された数字を昇順に並べ替え(5より少ないときは変える番号の前にchangeの初期値の０が入る)
		/*for(int i=0; i<5; i++){
			for(int a=0; a<5; a++){
				if(change[i] == 0) {//changeの配列の初期値(0)から変わっていない部分の処理を飛ばす
					continue;
				}
				else if(change[i] == a+1){
					rv[a] = random.nextInt(51);//番号で指定されたカードだけランダムで配列に格納
				}
			}
		}		
	
		
		for(int i=0;i<len;i++) {
			before[i] = rv[i];//交換する前のカードの番号を配列に入れる
		}
		before = new int[len];*/
		
		for(int i=0; i<5; i++){
			if(change[i] == 0) {//changeの配列の初期値(0)から変わっていない部分の処理を飛ばす
				continue;
			}
			else {//changeが0以外だったら
				//before[beforenum] = rv[change[i]-1];//交換する前のカードの番号を配列に入れる
				rv[change[i]-1] = random.nextInt(51);//番号で指定されたカードだけランダムで配列に格納
				/*System.out.println(before[beforenum] + " " + rv[change[i]-1]);
				beforenum = beforenum + 1;
				if(before[change[i]-1] == rv[change[i]-1]) {//配られたカードが交換した元のカードと同じじゃないか比べる
					rv[change[i]-1] = random.nextInt(51);//同じだったらもう一度ランダムで配列に格納
				}*/
			}
		}
		//comparison();
	}
	
	/*public void comparison() {//配られたカードが交換した元のカードと同じじゃないか比べる
		for(int i=0; i<5; i++){
			for(int a=0; a<5; a++){
				if(before[i] == rv[a]) {//配られたカードが前と同じか
					rv[a] = random.nextInt(51);//もう一度同じになったカードの番号をランダムに格納
					//changeCardFlag = true;
				}
			}
		}
	}*/
	
	
	
	
	
	
	
	
	public void point() {//ポイントをつける
		System.out.println("今回の獲得ポイントは" + point + "ポイントです。");
	}
	
	public static void main(String[] args)
	{


		C2Poker poker = new C2Poker();//オブジェクト作成
		poker.start();
		
	}
}
		



