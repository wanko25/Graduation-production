package GUI;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Message extends JFrame{	
	Message(String title){
		setTitle(title); //タイトルを設定
		setSize(900,690);//幅と高さ
		setLocationRelativeTo(null);//windowsの真ん中に表示
		setContentPane(new JLabel(new ImageIcon("./images/cardframe.jpg")));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×ボタンでアプリを閉じる←アプリまで閉じるとだめだから消す
		
		setLayout(new FlowLayout());
		
		JPanel p = new JPanel();//パネルのオブジェクトの作成
		p.setPreferredSize(new Dimension(600,550));//パネルのサイズ設定
		p.setOpaque(false);//パネルを透明に
		p.setLayout(new BorderLayout());
		add(p);
		JTextArea area = new JTextArea();
		area.setLineWrap(true);//テキストを折り返す
		area.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 17));
		area.setEditable(false);//falseでテキストエリアを編集できないようにする
		area.append("\r\n\r\n\r\n\r\n1.ゲーム開始時の手持ちのポイントは０からです。\r\n");
		area.append("2.カードは1人５枚配ります。\r\n");
		area.append("3.手札のカードから好きな枚数を捨て、その枚数分のカードを自分の手札に加えます。\r\n");
		area.append("4.役に応じてポイントが加算され、そのポイントを増やしていくゲームです。\r\n");
		area.append("※手札のカードを捨て、新しいカードを加えることができるのは1回です。\r\n\r\n");
		area.append("\r\n役について※弱い順\r\n");
		area.append("ワンペア(2Point)：同じ数字が２枚揃う\r\n");
		area.append("ツーペア(5Point)：ワンペアが２組揃う\r\n");
		area.append("スリーカード(10Point)：同じ数字が３枚揃う\r\n");
		area.append("ストレート(20Point)：５枚の数字が連続して順番に揃う（「1.2.3.4.5」「11.12.13.1.2」など）\r\n");
		area.append("フラッシュ(25Point)：５枚のマークが全部同じ\r\n");
		area.append("フルハウス(30Point)：スリーカードとワンペアが揃う\r\n");
		area.append("フォーカード(35Point)：同じ数字が４枚揃う\r\n");
		area.append("ストレートフラッシュ(60Point)：数字が連続して順番に揃い、マークも同じ\r\n");
		area.append("ロイヤルストレートフレッシュ(100Point)：スペードの「10.11.12.13.1」が揃う。\r\n\r\n");
		p.add(area,BorderLayout.SOUTH);
		
		setVisible(true);
	}
}
