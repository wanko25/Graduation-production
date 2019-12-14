package GUI;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

class home extends JFrame{//JFrameを継承したhome(サブクラス)を定義
	public static void main(String[] args) {
		 home frame = new home("タイトル");//オブジェクト作成
		 frame.setVisible(true);//作ったオブジェクトを表示(ふつうは非表示になってる)
	}	 
	
	home(String title){	//コンストラクタ	 
		setTitle(title); //タイトルを設定
		setSize(1200, 800);//幅と高さ
		 setLocationRelativeTo(null);//windowsの真ん中に表示
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×ボタンでアプリを閉じる
		 //setContentPane(new JLabel(new ImageIcon("./images/back.jpg")));//フレームの背景画像
		 Image background = Toolkit.getDefaultToolkit().getImage("./images/back.jpg").getScaledInstance(1200,800,0);//わからん
		 
		 setLayout(new FlowLayout());
		 
		/* TestPanel p1 = new TestPanel();
		 Graphics g = p1.image.getGraphics();
		 g.drawImage(background,1200,800,this);
		 add(p1);
		 p1.repaint();いくと*/
		 

		 JPanel p2 = new JPanel();//パネルのオブジェクトの作成
		 p2.setPreferredSize(new Dimension(300,800));//パネルのサイズ設定
		 p2.setBackground(Color.BLUE);
		 p2.setOpaque(false);//falseにすると透明になる
//		 p.setLayout(new BorderLayout());
		 p2.setLayout(null);//レイアウトマネージャを使用しない

		 //Container contentPane = getContentPane();//ボタンやラベルを追加するオブジェクトをContainerクラスから取得

		 add(p2);
		 
		 JButton btn1 = new JButton("スタート");
		 JButton btn2 = new JButton("ルール");
		 //btn1.setPreferredSize(new Dimension(200,100));
		 //btn2.setPreferredSize(new Dimension(200,100));
		 btn1.setFont(new Font("MSゴシック", Font.PLAIN, 30));
		 btn2.setFont(new Font("MSゴシック", Font.PLAIN, 30));
		 btn1.setForeground(Color.WHITE);
		 btn2.setForeground(Color.WHITE);
		 //btn2.setBackground(Color.WHITE);
		 btn1.setBounds(40,300,200,100);//ｘ、ｙ、幅、高さ
		 btn2.setBounds(40,400,200,100);
		 btn1.setContentAreaFilled(false);//ボタンのボーダーと文字以外を透明にする
		 btn1.setBorderPainted(false);//ボタンのボーダーを透明にする
		 btn2.setContentAreaFilled(false);//ボタンのボーダーと文字以外を透明にする
		 btn2.setBorderPainted(false);//ボタンのボーダーを透明にする
		 JLabel label = new JLabel("Poker");
		 label.setFont(new Font("Century", Font.ITALIC, 100));
		 //label.setPreferredSize(new Dimension(200,100));//ラベルの大きさ
		 label.setBounds(10,150,400,100);
		 p2.add(label);
		 p2.add(btn1);
		 p2.add(btn2);
		 btn1.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
			    	new game("Poker");//gameのフレームを作る
			    }
		 });
		 btn2.addActionListener(new ActionListener(){//ボタンが押されたら
			    public void actionPerformed(ActionEvent e) {
			    	new Message("ルール説明");//Messageのフレームを作る
			        //JOptionPane.showMessageDialog(null , "ルールを説明します。");                                               
			    }
		 });


	}
	

}

