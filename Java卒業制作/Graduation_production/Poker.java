package Graduation_production;
import java.util.*;
public class Poker
{
	String[] card = {"●1","●2","●3","●4","●5","●6","●7","●8","●9","●10","●11","●12","●13",
			 "◆1","◆2","◆3","◆4","◆5","◆6","◆7","◆8","◆9","◆10","◆11","◆12","◆13",
			 "■1","■2","■3","■4","■5","■6","■7","■8","■9","■10","■11","■12","■13",
			 "▲1","▲2","▲3","▲4","▲5","▲6","▲7","▲8","▲9","▲10","▲11","▲12","▲13"};
			 /*●はハート、◆はダイヤ、■はクローバー、▲はスペード*/
	Scanner sc;
	String select;//スタートかルールの選択
	Random random;//ランダムにカード番号取得
	boolean flag;//カードが重複しているか
	int[] rv;//配るカードの添字が入る配列
	String[] tcard;//配られたカードが入っている配列
	int[] suuji;//カードの数字部分
	String[] mark;//カードのマーク部分
	int count;//同じ数字の枚数
	boolean Jflag;//カードが順番に並んでいるか
	boolean Mflag;//マークが同じかどうか
	boolean Sflag;//マークがすべてスペードか
	int[] rs = {1,10,11,12,13};//suujiの中身が10.11.12.13.1になっているか
	int[] change;//カードを替えるときの変数
	String henko;//カードを変えるかどうか

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
				System.out.println("ストレート(15Point)：５枚の数字が連続して順番に揃う（「1.2.3.4.5」「11.12.13.1.2」など）");
				System.out.println("フラッシュ(20Point)：５枚のマークが全部同じ");
				System.out.println("フルハウス(25Point)：スリーカードとワンペアが揃う");
				System.out.println("フォーカード(30Point)：同じ数字が４枚揃う");
				System.out.println("ストレートフラッシュ(35Point)：数字が連続して順番に揃い、マークも同じ");
				System.out.println("ロイヤルストレートフレッシュ(40Point)：スペードの「10.11.12.13.1」が揃う。\n\n");
				
			}
			else{
				System.out.println("\nスタートかルールのどちらかを入力してください。\n");
			}

		}
	}
	
	public void distribute()//カードを配る
	{
		random = new Random();
		rv = new int[5];

		//flag = false;
		do{
			flag = false;

			for(int i=0; i<5; i++){
				rv[i] = random.nextInt(51);//配るカードの添字を配列に入れる
			}

			for(int i=0; i<5; i++){
				for(int a=i+1; a<5; a++){
					if(rv[i] == rv[a]){//同じカードがあるか
						flag = true;
					}
				}
			}

		}while(flag);//true(同じカードがあるとき)にループする

		tcard = new String[5];
		for(int i=0; i<5; i++){
			tcard[i] = card[rv[i]];
			System.out.print(i+1 + "." + tcard[i] + "     ");//カードを配る
		}

		System.out.println("\n※●はハート、◆はダイヤ、■はクローバー、▲はスペード\n");

	}

	public void divide()//手札のカードをマークと数字に分けて昇順に並べる
	{
		suuji = new int[5];
		mark = new String[5];

		for(int i=0; i<5; i++){
			//suuji[i] = tcard[i].substring(1);
			suuji[i] = Integer.parseInt(tcard[i].substring(1));//手札のカードを数字に分ける
			mark[i] = tcard[i].substring(0,1);//手札のカードをマークに分ける
		}
		
		Arrays.sort(suuji);//昇順に並べ替え
	}
	
	public void same_number()//同じ数字の枚数と役
	{
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
			break;

			case 2://ツーペア
				System.out.println("ツーペアです。");
			break;

			case 3://スリーカード
				System.out.println("スリーカードです。");
			break;

			case 4://フルハウス
				System.out.println("フルハウスです。");
			break;

			case 6://フォーカード
				System.out.println("フォーカードです。");
			break;
		}

	}
	
	public void straight()//役がストレートか（数字が並んでるか）
	{
		Jflag = true;//trueの時はカードが並んでいる
		for(int i=0; i<4; i++){//ストレート
			if(suuji[i] != suuji[i+1]-1){//次のカードは順番通りか
				if(suuji[0] == 1  &&  suuji[4] == 13){
					if(suuji[i+1] != 13-(3-i)){
						Jflag = false;
						break;
					}
					continue;//ifの外に出る
				}
				Jflag = false;
				break;//forの外に出る
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
		if(Jflag && Mflag){
			System.out.println("ストレートフラッシュです。");
		}
		else if(Jflag){//trueの時
			System.out.println("ストレートです。");
		}
		else if(Mflag){
			System.out.println("フラッシュです。");
		}
	}
	
	public void royal_straight_flush()//ロイヤルストレートフラッシュか
	{
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
		}
	}
	
	
	
	public static void main(String[] args)
	{


		
		
		
		System.out.println("\n\nカードを替えますか？");
		System.out.println("※替えたい場合はY、替えない場合はNを入力してください。");

		henko = sc.nextLine();
		
		while(true){
			if(henko.equals("Y") || henko.equals("y")){
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

		System.out.println("\n替えたいカードの番号を選んでください。");


		String kae = sc.nextLine();//替えたいカードの番号を取得
		kae = kae.replaceAll("　", "");//全角スペースを空文字に置換
		kae = kae.replaceAll(" ", ""); //文字の間の半角スペースを空文字に変換
		int len = kae.length();//入力した番号の数
		change = new int[len];//入力した番号を格納する配列作成

		for (int i=0; i<len; i++) {
			change[i] = Character.getNumericValue(kae.charAt(i));//kaeをchar型で取り出しint型に変換
			//change[i] = Integer.parseInt(kae.substring(i, i + 1));//kaeの数を配列から1つずつ取り出して格納←これでもok
			if(change[i] < 1  ||  change[i] > 5){//数字が1~5の間か
				System.out.println("1～5の数字を入力してください。");
			}
		}
		Arrays.sort(change);//入力された数字を昇順に並べ替え
		for(int i=0; i<len; i++){
			for(int a=0; a<5; a++){
				if(change[i] == a+1){
					rv[a] = random.nextInt(51);//番号で指定されたカードだけランダムで配列に格納
				}
			}
		}





		for(int i=0; i<5; i++){
			tcard[i] = card[rv[i]];
			System.out.print(i+1 + "." + tcard[i] + "     ");//カードを配る
		}
	}
}
