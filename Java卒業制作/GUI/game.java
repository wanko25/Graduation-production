package GUI;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class game extends JFrame{
	game(String title){
		setTitle(title); //タイトルを設定
		setSize(1200, 800);//幅と高さ
		setLocationRelativeTo(null);//windowsの真ん中に表示
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×ボタンでアプリを閉じる
		setContentPane(new JLabel(new ImageIcon("./images/green.jpg")));//フレームの背景画像
		
		setLayout(new FlowLayout());
		
		setVisible(true);
	}
}
